package com.example.enfermeria.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.enfermeria.dao.NurseRepository;
import com.example.enfermeria.entity.Nurse;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NurseController.class)
class NurseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NurseRepository nurseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Nurse nurse;

    @BeforeEach
    void setUp() {
        nurse = new Nurse();
        nurse.setIdNurse(1);
        nurse.setName("Maria Lopez");
        nurse.setUser("mlopez");
        nurse.setPassword("abc123");
        nurse.setEmail("maria@example.com");
    }

    // ---------- LOGIN ----------
    @Test
    void testLoginSuccess() throws Exception {
        when(nurseRepository.findByUser("mlopez")).thenReturn(Optional.of(nurse));

        mockMvc.perform(post("/nurse/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nurse)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testLoginUnauthorized() throws Exception {
        when(nurseRepository.findByUser("mlopez")).thenReturn(Optional.empty());

        mockMvc.perform(post("/nurse/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nurse)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("false"));
    }

    // ---------- GET ALL ----------
    @Test
    void testGetAll() throws Exception {
        when(nurseRepository.findAll()).thenReturn(List.of(nurse));

        mockMvc.perform(get("/nurse/index"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Maria Lopez"));
    }

    // ---------- FIND BY NAME ----------
    @Test
    void testFindByNameFound() throws Exception {
        when(nurseRepository.findByNameIgnoreCase("Maria Lopez")).thenReturn(Optional.of(nurse));

        mockMvc.perform(get("/nurse/name").param("name", "Maria Lopez"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").value("mlopez"));
    }

    @Test
    void testFindByNameNotFound() throws Exception {
        when(nurseRepository.findByNameIgnoreCase("Unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/nurse/name").param("name", "Unknown"))
                .andExpect(status().isNotFound());
    }

    // ---------- CREATE NURSE ----------
    @Test
    void testCreateNurseValid() throws Exception {
        when(nurseRepository.save(any(Nurse.class))).thenReturn(nurse);

        mockMvc.perform(post("/nurse/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nurse)))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateNurseInvalidEmail() throws Exception {
        nurse.setEmail("invalidEmail");

        mockMvc.perform(post("/nurse/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nurse)))
                .andExpect(status().isBadRequest());
    }

    // ---------- FIND BY ID ----------
    @Test
    void testFindByIdFound() throws Exception {
        when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));

        mockMvc.perform(get("/nurse/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("maria@example.com"));
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        when(nurseRepository.findById(2)).thenReturn(Optional.empty());

        mockMvc.perform(get("/nurse/2"))
                .andExpect(status().isNotFound());
    }

    // ---------- PUT NURSE ----------
    @Test
    void testPutNurseValidUpdate() throws Exception {
        when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));
        when(nurseRepository.save(any(Nurse.class))).thenReturn(nurse);

        Nurse update = new Nurse();
        update.setName("Maria Updated");
        update.setUser("mupdated");
        update.setPassword("newpass1");
        update.setEmail("updated@example.com");

        mockMvc.perform(put("/nurse/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk());
    }

    @Test
    void testPutNurseInvalidPassword() throws Exception {
        when(nurseRepository.findById(1)).thenReturn(Optional.of(nurse));

        Nurse update = new Nurse();
        update.setName("Maria Updated");
        update.setUser("mupdated");
        update.setPassword("123"); // too short
        update.setEmail("updated@example.com");

        mockMvc.perform(put("/nurse/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isBadRequest());
    }

    // ---------- DELETE ----------
    @Test
    void testDeleteExistingNurse() throws Exception {
        when(nurseRepository.existsById(1)).thenReturn(true);
        doNothing().when(nurseRepository).deleteById(1);

        mockMvc.perform(delete("/nurse/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteNonExistingNurse() throws Exception {
        when(nurseRepository.existsById(99)).thenReturn(false);

        mockMvc.perform(delete("/nurse/99"))
                .andExpect(status().isNotFound());
    }
}
