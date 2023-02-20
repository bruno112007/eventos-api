package com.bruno.events.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class ColaboradorDTO {
    @NotBlank(message = "O campo 'nome' é obrigatório.")
    private String nome;
}
