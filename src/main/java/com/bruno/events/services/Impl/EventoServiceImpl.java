package com.bruno.events.services.Impl;

import com.bruno.events.entities.Evento;
import com.bruno.events.repositories.EventoRepository;
import com.bruno.events.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {
    @Autowired
    private EventoRepository repository;
    @Override
    public Optional<Evento> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Evento save(Evento evento) {
        return repository.save(evento);
    }

    @Override
    public List<Evento> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Evento> findAll(Example example) {
        return repository.findAll(example);
    }

    @Override
    public void delete(Evento evento) {
        repository.delete(evento);
    }
}
