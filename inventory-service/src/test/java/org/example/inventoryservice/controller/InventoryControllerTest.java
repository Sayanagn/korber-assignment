package org.example.inventoryservice.controller;

import org.example.inventoryservice.dto.InventoryResponse;
import org.example.inventoryservice.dto.InventoryResponseData;
import org.example.inventoryservice.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness =  Strictness.LENIENT)
public class InventoryControllerTest {


    @Mock
    private MockMvc mockMvc;

    @Mock
    private InventoryService inventoryService;
    @InjectMocks
    private InventoryController inventoryController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registeredModules();
    }


    @Test
    void shouldReturnInventoryForProduct() throws Exception {

        InventoryResponseData data = new InventoryResponseData();
        data.setBatchId(1L);
        data.setProductName("Laptop");
        data.setQuantity(10);
        data.setExpiryDate(LocalDate.now().plusDays(30));

        InventoryResponse response = new InventoryResponse();
        response.setData(data);

        when(inventoryService.getInventoryByProductId(101L))
                .thenReturn(List.of(response));

        when(inventoryService.getInventoryByProductId(101L))
                .thenReturn(List.of(response));

        mockMvc.perform(get("/inventory/101"))
                .andExpect(status().isOk());
    }
}