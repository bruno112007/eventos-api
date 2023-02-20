package com.bruno.events.services;

import com.bruno.events.entities.Evento;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;

public interface EventoService {
    Optional<Evento> findById(Integer id);

    Evento save(Evento evento);

    List<Evento> findAll();
    List<Evento> findAll(Example example);

    void delete(Evento evento);
}
