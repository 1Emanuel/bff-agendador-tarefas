package com.nztech.bbfagedadortarefas.business;

import com.nztech.bbfagedadortarefas.business.dto.out.TarefasDTOResponse;
import com.nztech.bbfagedadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse tarefaDTO) {
        emailClient.enviarEmail(tarefaDTO);
    }
}
