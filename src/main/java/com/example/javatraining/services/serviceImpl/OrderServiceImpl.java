package com.example.javatraining.services.serviceImpl;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.order.request.CreateOrderDto;
import com.example.javatraining.dtos.order.request.ListOrderQueryDto;
import com.example.javatraining.dtos.order.request.ProductDto;
import com.example.javatraining.dtos.order.response.OrderResponse;
import com.example.javatraining.entities.*;
import com.example.javatraining.exceptions.ErrorCode;
import com.example.javatraining.exceptions.ErrorException;
import com.example.javatraining.repositories.InventoryRepository;
import com.example.javatraining.repositories.LineOrderRepository;
import com.example.javatraining.repositories.OrderRepository;
import com.example.javatraining.services.CustomerService;
import com.example.javatraining.services.OrderService;
import com.example.javatraining.services.ProductService;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Value
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    LineOrderRepository lineOrderRepository;
    InventoryRepository inventoryRepository;

    ProductService productService;
    CustomerService customerService;

    private Set<ProductDto> getDuplicateProduct(List<ProductDto> products) {
        return products.stream()
                .filter(i -> Collections.frequency(products, i.getId()) > 1)
                .collect(Collectors.toSet());
    }

    public void createOrder(CreateOrderDto payload) {
        Customer customer = customerService.getCustomerById(payload.getCustomerId());

        List<ProductDto> productsInput = payload.getProducts();

        Set<ProductDto> duplicateProducts = getDuplicateProduct(productsInput);

        if (!duplicateProducts.isEmpty()) {
            throw new ErrorException(ErrorCode.DUPLICATE_PRODUCT_IN_ORDER);
        }

        List<Long> productIds = productsInput.stream().map(ProductDto::getId).toList();
        List<Product> products = productService.getProductsByIds(productIds);

        if (products.size() != productsInput.size()) {
            throw new ErrorException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        List<Inventory> updatedInventories = new ArrayList<>();
        List<LineOrder> newLineOrders = new ArrayList<>();

        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder = orderRepository.save(newOrder);

        for (ProductDto productInput : productsInput) {
            Product product = products.stream().filter(p -> p.getId() == productInput.getId()).findFirst().orElse(null);

            if (product.getInventory().getStockQuantity() < productInput.getQuantity()) {
                throw new ErrorException(ErrorCode.QUANTITY_NOT_ENOUGH);
            }

            Inventory inventory = product.getInventory();
            inventory.setStockQuantity(inventory.getStockQuantity() - productInput.getQuantity());

            updatedInventories.add(inventory);

            LineOrder lineOrder = new LineOrder();
            lineOrder.setOrderId(newOrder.getId());
            lineOrder.setCustomerId(customer.getId());
            lineOrder.setProductId(product.getId());

            newLineOrders.add(lineOrder);
        }

        inventoryRepository.saveAll(updatedInventories);
        lineOrderRepository.saveAll(newLineOrders);
    }

    public ResponsePagination<OrderResponse> getOrders(ListOrderQueryDto query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit());

        Page<Order> orders = orderRepository.findAll(pageable);

        return new ResponsePagination<>(
                query.getPage(),
                query.getLimit(),
                orders.getTotalElements(),
                orders.getContent().stream().map(OrderResponse::from).toList());
    }
}
