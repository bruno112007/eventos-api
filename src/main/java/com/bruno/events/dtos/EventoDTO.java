package com.bruno.events.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class EventoDTO {
    @NotBlank(message = "O evento deve ter nomes que o represente.")
    private String nomeEvento;
    @NotBlank(message = "O evento deve ter uma categoria informada.")
    private String categoria;
    @NotNull(message = "O colaborador deve ser informado para a criação do evento.")
    private Integer colaborador;
    @NotNull(message = "A data do evento deve ser informada antes de postar.")
    @DateTimeFormat(pattern = "yyyy-MM/dd")
    private LocalDate dataEvento;
    @NotNull(message = "A hora de início deve ser informada antes de postar.")
    private LocalTime horaInicio;
    @NotNull(message = "A hora de término deve ser informada antes de postar.")
    private LocalTime horaTermino;
}
