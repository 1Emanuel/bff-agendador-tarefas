package com.nztech.bbfagedadortarefas.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nztech.bbfagedadortarefas.infrastructure.enums.StatusNotificaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTORequest {
    private String nome;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
}
