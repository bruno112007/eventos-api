package com.bruno.events.services.Impl;

import com.bruno.events.entities.Colaborador;
import com.bruno.events.exceptions.ColaboradorJaEstaRegistrado;
import com.bruno.events.repositories.ColaboradorRepository;
import com.bruno.events.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;
    @Override
    public Colaborador save(Colaborador colaborador) {
        return repository.save(colaborador);
    }

    @Override
    public List<Colaborador> getColaboradores() {
        return repository.findAll();
    }

    @Override
    public Optional<Colaborador> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Colaborador colaborador) {
        repository.delete(colaborador);
    }

    @Override
    public List<Colaborador> findAll(Example example) {
        return repository.findAll(example);
    }
}
