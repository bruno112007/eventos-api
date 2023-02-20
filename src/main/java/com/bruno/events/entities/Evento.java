package com.bruno.events.entities;

import com.bruno.events.enums.EventoStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "evento_tb")
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Evento {

    public Evento(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nomeEvento;
    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;
    @Column
    private LocalDate dataEvento;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private LocalTime horaInicio;
    @Column(nullable = false)
    private LocalTime horaTermino;

    @Column
    @Enumerated(EnumType.STRING)
    private EventoStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Evento evento = (Evento) o;
        return id != null && Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
