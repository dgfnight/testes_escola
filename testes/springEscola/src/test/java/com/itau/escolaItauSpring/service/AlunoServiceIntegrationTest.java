package com.itau.escolaItauSpring.service;

import com.itau.escolaItauSpring.config.mapper.AlunoMapper;
import com.itau.escolaItauSpring.config.mapper.AlunoMapperImpl;
import com.itau.escolaItauSpring.dto.request.AlunoRequest;
import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;
import com.itau.escolaItauSpring.model.Aluno;
import com.itau.escolaItauSpring.repository.AlunoRepository;
import com.itau.escolaItauSpring.service.AlunoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;


//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {AlunoService.class, AlunoRepository.class, AlunoMapperImpl.class})
@SpringBootTest
public class AlunoServiceIntegrationTest {

    @Autowired
    AlunoService alunoService;

    @Autowired
    AlunoRepository alunoRepository;


    @Test
    public void testeAdicionarAlunoIntegration() {
        AlunoRequest aluno = new AlunoRequest();
        aluno.setNome("Anakin");
        aluno.setCpf(0440004401L);
        var alunoResponse = alunoService.adicionar(aluno);
        Assertions.assertNotNull(alunoResponse);
    }

    @Test
    public void testeDesativarAlunoIntegration() throws ItemNaoExistenteException {
        AlunoRequest alunoRequest = new AlunoRequest();
        alunoRequest.setNome("Organa");
        alunoRequest.setCpf(0440004400L);
        var alunoResponse = alunoService.adicionar(alunoRequest);
        Optional<Aluno> aluno = alunoRepository.findById(alunoResponse.getId());
        alunoService.desativar(aluno.get());
        aluno = alunoRepository.findById(aluno.get().getId());
        Assertions.assertFalse(aluno.get().getAtivado());
    }


}
