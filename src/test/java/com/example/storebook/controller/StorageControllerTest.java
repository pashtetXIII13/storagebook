package com.example.storebook.controller;

import com.example.storebook.dto.StorageDto;
import com.example.storebook.services.StorageService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author pashtet
 */
@WebMvcTest(StorageController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class StorageControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    StorageService service;
    @Test
    void getStorage() throws Exception {
        when(service.getStorage(1L)).thenReturn(StorageDto
                .builder()
                .id(1L)
                .name("Библиотека №1")
                .build());
        this.mockMvc.perform(get("/api/storages/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Библиотека №1\"}"));
    }

    @Test
    void getStorages() throws Exception {
        List<StorageDto> storagesDto = new ArrayList<>();
        storagesDto.add(StorageDto
                .builder()
                .id(1L)
                .name("Библиотека №1")
                .build());
        when(service.getStorages()).thenReturn(storagesDto);
        this.mockMvc.perform(get("/api/storages")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Библиотека №1\"}]"));
    }

    @Test
    void addStorage() throws Exception {
        when(service.addStorage(StorageDto
                .builder()
                .id(1L)
                .name("Библиотека №1")
                .build())).thenReturn(StorageDto
                .builder()
                .id(1L)
                .name("Библиотека №1")
                .build());
        this.mockMvc.perform(post("/api/storages").content("{\"id\":1,\"name\":\"Библиотека №1\"}")
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"name\":\"Библиотека №1\"}"));
    }

    @Test
    void deleteStorage() throws Exception {
        this.mockMvc.perform(delete("/api/storages/{id}", 1) )
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteStorages() throws Exception {
        this.mockMvc.perform(delete("/api/storages") )
                .andExpect(status().isNoContent());
    }
}