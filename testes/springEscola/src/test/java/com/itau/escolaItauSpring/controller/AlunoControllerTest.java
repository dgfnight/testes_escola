package com.itau.escolaItauSpring.controller;

import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.service.AlunoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

@WebMvcTest
public class AlunoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AlunoService alunoService;

    @Test
    void testeGetAlunoController() throws Exception{
        Mockito.when(alunoService.listar()).thenReturn(Collections.singletonList(new AlunoResponse()));
        var result = this.mockMvc.perform(MockMvcRequestBuilders.get("/aluno"))
                .andReturn();

        var expected = "[{\"id\":null,\"nome\":null,\"idade\":null,\"cpf\":null,\"curso\":null}]";
        Mockito.verify(alunoService, Mockito.times(1)).listar();
        Assertions.assertEquals(expected, result.getResponse().getContentAsString());
        Assertions.assertEquals(200, result.getResponse().getStatus());
    }
}
