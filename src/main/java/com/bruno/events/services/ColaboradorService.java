package com.bruno.events.services;

import com.bruno.events.entities.Colaborador;
import org.springframework.data.domain.Example;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public interface ColaboradorService {
    Colaborador save(Colaborador colaborador);

    List<Colaborador> getColaboradores();

    Optional<Colaborador> findById(Integer id);

    void delete(Colaborador colaborador);

    List<Colaborador> findAll(Example example);
}
