package org.example.orderservice.service;

import org.example.orderservice.dto.InventoryResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryServiceClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public InventoryResponse[] getInventoryDetails(Long productId) {
        String url = "http://localhost:8080/inventory/" + productId;
        return restTemplate.getForObject(url, InventoryResponse[].class);
    }
}
