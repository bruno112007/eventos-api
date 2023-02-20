package com.bruno.events.Controllers;

import com.bruno.events.dtos.EventoDTO;
import com.bruno.events.dtos.StatusDTO;
import com.bruno.events.entities.Colaborador;
import com.bruno.events.entities.Evento;
import com.bruno.events.enums.EventoStatus;
import com.bruno.events.exceptions.ColaboradorNaoEncontrado;
import com.bruno.events.exceptions.EventoNaoEncontrado;
import com.bruno.events.services.ColaboradorService;
import com.bruno.events.services.EventoService;
import jakarta.validation.Valid;
import jdk.jfr.Event;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoServico;
    @Autowired
    private ColaboradorService colaboradorServico;

    @PostMapping("/create")
    public ResponseEntity<?> criar(@RequestBody@Valid EventoDTO dto){
        eventoServico.save(dtoToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body("Evento cadastrado com sucesso.");
    }

    @PutMapping("/update/status/{id}")
    public ResponseEntity<?> atualizarStatus(@RequestBody@Valid StatusDTO status, @PathVariable Integer id){
        Evento evento = eventoServico.findById(id).orElseThrow(() -> new EventoNaoEncontrado("O evento com ID informado não encontrado"));
        evento.setStatus(EventoStatus.valueOf(status.getStatus()));
        eventoServico.save(evento);
        return ResponseEntity.status(HttpStatus.OK).body("Status atualizado com sucesso.");
    }

    @GetMapping("/")
    public List<Evento> getAll(){
        return eventoServico.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPorId(@PathVariable Integer id){
        Evento evento = eventoServico.findById(id).orElseThrow(() -> new EventoNaoEncontrado("Evento com ID informado não encontrado."));
        return ResponseEntity.status(HttpStatus.OK).body(evento);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        Evento evento = eventoServico.findById(id).orElseThrow(() -> new EventoNaoEncontrado("Evento com ID informado não encontrado."));
        eventoServico.delete(evento);
        return ResponseEntity.status(HttpStatus.OK).body("Evento deletado com sucesso.");
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody@Valid EventoDTO dto, @PathVariable Integer id){
        Evento evento = eventoServico.findById(id).orElseThrow(() -> new EventoNaoEncontrado("Evento com ID informado não encontrado."));
        Evento eventoAtualizar = new Evento();
        BeanUtils.copyProperties(dto, eventoAtualizar);
        eventoAtualizar.setId(evento.getId());
        eventoServico.save(eventoAtualizar);
        return ResponseEntity.status(HttpStatus.OK).body("Evento atualizado com sucesso.");
    }

    @GetMapping("/filtro")
    public List<Evento> filtro(Evento evento){
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
        Example example = Example.of(evento, matcher);
        List<Evento> eventos = eventoServico.findAll(example);
        return eventos;
    }

    private Evento dtoToEntity(EventoDTO dto){
        Colaborador colaborador = colaboradorServico.findById(dto.getColaborador()).orElseThrow(() -> new ColaboradorNaoEncontrado("O colaborador com ID informado não encontrado."));
        //categoriaDTO = CaSaMeNtO
        //categoriaCapitalizada = Casamento
        String categoriaDTO = dto.getCategoria();
        String categoriaCapitalizada = WordUtils.capitalizeFully(categoriaDTO);
        return Evento.builder()
                .nomeEvento(dto.getNomeEvento())
                .colaborador(colaborador)
                .dataEvento(dto.getDataEvento())
                .categoria(categoriaCapitalizada)
                .horaInicio(dto.getHoraInicio())
                .horaTermino(dto.getHoraTermino())
                .status(EventoStatus.PENDENTE)
                .build();
    }
}
