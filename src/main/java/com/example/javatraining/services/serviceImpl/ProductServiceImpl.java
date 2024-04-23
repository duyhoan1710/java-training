package com.example.javatraining.services.serviceImpl;

import com.example.javatraining.dtos.product.request.CreateProductDto;
import com.example.javatraining.entities.Inventory;
import com.example.javatraining.entities.Product;
import com.example.javatraining.exceptions.ErrorCode;
import com.example.javatraining.exceptions.ErrorException;
import com.example.javatraining.repositories.InventoryRepository;
import com.example.javatraining.repositories.ProductRepository;
import com.example.javatraining.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    InventoryRepository inventoryRepository;

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
}
