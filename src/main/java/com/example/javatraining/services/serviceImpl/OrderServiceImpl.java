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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private LineOrderRepository lineOrderRepository;
    private InventoryRepository inventoryRepository;

    private ProductService productService;
    private CustomerService customerService;

    private Set<ProductDto> getDuplicateProduct(List<ProductDto> products) {
        return products.stream()
                .filter(i -> Collections.frequency(products, i.getId()) > 1)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void createOrder(CreateOrderDto payload) {
        Customer customer = customerService.getCustomerById(payload.getCustomerId());

        if (customer == null) {
            throw new ErrorException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

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

        double totalPrice = 0;

        for (ProductDto productInput : productsInput) {
            Product product = products.stream().filter(p -> p.getId() == productInput.getId()).findFirst().orElse(null);

            if (product == null) {
                continue;
            }

            if (product.getInventory().getStockQuantity() < productInput.getQuantity()) {
                throw new ErrorException(ErrorCode.QUANTITY_NOT_ENOUGH);
            }

            totalPrice += product.getPrice() * productInput.getQuantity();

            Inventory inventory = product.getInventory();
            inventory.setStockQuantity(inventory.getStockQuantity() - productInput.getQuantity());
            updatedInventories.add(inventory);

            LineOrder lineOrder = new LineOrder();
            lineOrder.setCustomer(customer);
            lineOrder.setProduct(product);
            lineOrder.setOrder(newOrder);

            newLineOrders.add(lineOrder);
        }
        newOrder.setLineOrders(newLineOrders);
        newOrder.setTotalMoney(totalPrice);
        orderRepository.save(newOrder);
        inventoryRepository.saveAll(updatedInventories);
    }

    @Transactional
    public ResponsePagination<OrderResponse> getOrders(ListOrderQueryDto query) {
        Pageable pageable = PageRequest.of(query.getPage() - 1, query.getLimit());

        Page<Order> orders = orderRepository.findAll(pageable);

        return new ResponsePagination<>(
                query.getPage(),
                query.getLimit(),
                orders.getTotalElements(),
                orders.getContent().stream().map(OrderResponse::withCustomerAndLineOrderFrom).toList());
    }
}
