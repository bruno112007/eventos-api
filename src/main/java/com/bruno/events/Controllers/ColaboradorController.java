package com.bruno.events.Controllers;

import com.bruno.events.dtos.ColaboradorDTO;
import com.bruno.events.entities.Colaborador;
import com.bruno.events.exceptions.ColaboradorNaoEncontrado;
import com.bruno.events.repositories.ColaboradorRepository;
import com.bruno.events.services.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {
    @Autowired
    private ColaboradorService servico;

    @PostMapping("/criar")
    public ResponseEntity<?> salvar(@RequestBody@Valid ColaboradorDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.save(dtoParaEntity(dto)));
    }

    @GetMapping("/")
    public ResponseEntity<?> todosColaboradores(){
        return ResponseEntity.status(HttpStatus.OK).body(servico.getColaboradores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> colaboradoresPorId(@PathVariable Integer id){
        Colaborador colaborador = servico
                .findById(id)
                .orElseThrow(() ->
                        new ColaboradorNaoEncontrado("Colaborador com o ID informado não encontrado."));
        return ResponseEntity.status(HttpStatus.OK).body(colaborador);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        Colaborador colaborador = servico
                .findById(id)
                .orElseThrow(() ->
                        new ColaboradorNaoEncontrado("Colaborador com o ID informado não encontrado."));
        servico.delete(colaborador);
        return ResponseEntity.status(HttpStatus.OK).body("Colaborador deletado com sucesso.");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody@Valid ColaboradorDTO dto, @PathVariable Integer id){
        Colaborador colaborador = servico
                .findById(id)
                .orElseThrow(() -> new ColaboradorNaoEncontrado("Colaborador com o ID informado não encontrado."));

        Colaborador colaboradorAtualizar = new Colaborador();
        BeanUtils.copyProperties(dto, colaboradorAtualizar);
        colaboradorAtualizar.setId(colaborador.getId());
        servico.save(colaboradorAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body("Colaborador atualizado com sucesso.");
    }

    @GetMapping("/filtro")
    public List<Colaborador> filtro (Colaborador colaborador){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example example = Example.of(colaborador, matcher);
        List<Colaborador> result = servico.findAll(example);
        return result;
    }

    private Colaborador dtoParaEntity(ColaboradorDTO dto){
        Colaborador colaborador = new Colaborador();
        BeanUtils.copyProperties(dto, colaborador);
        return colaborador;
    }
}
