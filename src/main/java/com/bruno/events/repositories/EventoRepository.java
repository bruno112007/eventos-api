package com.bruno.events.repositories;

import com.bruno.events.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
