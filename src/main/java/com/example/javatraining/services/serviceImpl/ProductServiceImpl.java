package com.example.javatraining.services.serviceImpl;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.product.request.CreateProductDto;
import com.example.javatraining.dtos.product.request.ListProductQueryDto;
import com.example.javatraining.dtos.product.request.UpdateProductDto;
import com.example.javatraining.dtos.product.response.ProductResponse;
import com.example.javatraining.entities.Inventory;
import com.example.javatraining.entities.Product;
import com.example.javatraining.exceptions.ErrorCode;
import com.example.javatraining.exceptions.ErrorException;
import com.example.javatraining.repositories.InventoryRepository;
import com.example.javatraining.repositories.ProductRepository;
import com.example.javatraining.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    InventoryRepository inventoryRepository;

    @Transactional
    public void createProduct(CreateProductDto payload) {
        Product product = productRepository.findByName(payload.getName()).orElse(null);

        if (product != null) {
            throw new ErrorException(ErrorCode.DUPLICATE_PRODUCT);
        }

        Product newProduct = new Product();
        newProduct.setName(payload.getName());
        newProduct.setPrice(payload.getPrice());

        Inventory newInventory = new Inventory();
        newInventory.setStockQuantity(payload.getQuantity());
        newInventory.setProduct(newProduct);

        productRepository.save(newProduct);
        inventoryRepository.save(newInventory);

    }

    @Transactional
    public void updateProduct(long productId, UpdateProductDto payload) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ErrorException(ErrorCode.PRODUCT_NOT_FOUND));

        product.setName(payload.getName());
        product.setPrice(payload.getPrice());

        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(() -> new ErrorException(ErrorCode.PRODUCT_NOT_FOUND));

        inventory.setStockQuantity(payload.getQuantity());

        productRepository.save(product);
        inventoryRepository.save(inventory);
    }

    public ResponsePagination<ProductResponse> getProducts(ListProductQueryDto query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit(), Sort.by(query.getSortType(), query.getSortBy().toString()));

        Page<Product> products = productRepository.findByNameAndPriceGreaterThanAndPriceLessThan(query.getName(), query.getPriceFrom(), query.getPriceTo(), pageable);

        return new ResponsePagination<>(
                query.getPage(),
                query.getLimit(),
                products.getTotalElements(),
                products.getContent().stream().map(ProductResponse::from).toList()
        );
    }

    public List<Product> getProductsByIds(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

}
