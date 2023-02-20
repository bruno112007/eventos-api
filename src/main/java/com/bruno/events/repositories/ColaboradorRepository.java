package com.bruno.events.repositories;

import com.bruno.events.entities.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
}
