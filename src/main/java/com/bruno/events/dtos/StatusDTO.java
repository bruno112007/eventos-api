package com.bruno.events.dtos;

import com.bruno.events.enums.EventoStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class StatusDTO {
    @NotBlank(message = "Você deve colocar um status para atualizar.")
    private String status;
}
