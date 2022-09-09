package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.config.mapper.AlunoMapper;
import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.dto.response.AlunoResponse;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.repository.AlunoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {
    @InjectMocks
    private AlunoService alunoService;

    @Mock
    private AlunoMapper mapper;

    @Mock
    private AlunoRepository alunoRepository;

    @Test
    void testeAdicionarAluno() {
        Aluno aluno = new Aluno();
        AlunoRequest alunoRequest = new AlunoRequest();
        Mockito.when(mapper.toModel(alunoRequest)).thenReturn(aluno);
        Mockito.when(mapper.toResponse(aluno)).thenReturn(new AlunoResponse());
        Mockito.when(alunoRepository.save(ArgumentMatchers.any(Aluno.class))).thenReturn(aluno);
        AlunoResponse alunoResponse = alunoService.adicionar(alunoRequest);
        Assertions.assertNotNull(alunoResponse);
    }

    @Test
    void testeLocalizarAluno() throws ItemNaoExistenteException {
        Aluno aluno = new Aluno();
        Mockito.when(alunoRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(aluno));
        Mockito.when(mapper.toResponse(aluno)).thenReturn(new AlunoResponse());
        AlunoResponse alunoResponse = alunoService.localizar(UUID.randomUUID());
        Assertions.assertNotNull(alunoResponse);
    }

}
