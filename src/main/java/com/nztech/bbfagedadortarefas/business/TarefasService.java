package com.nztech.bbfagedadortarefas.business;


import com.nztech.bbfagedadortarefas.business.dto.in.TarefasDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.out.TarefasDTOResponse;
import com.nztech.bbfagedadortarefas.infrastructure.client.TarefaClient;
import com.nztech.bbfagedadortarefas.infrastructure.enums.StatusNotificaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefaClient tarefaClient;

    // criar metodo para criar terefa
    public TarefasDTOResponse criarTarefa(TarefasDTORequest dto, String token) {
        return tarefaClient.criarTarefa(token, dto);
    }

    // criar o metodo que busca lista de tarefas baseado no periodo
    public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
        return tarefaClient.buscarListaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    // metodo para buscar tarefas por email
    public List<TarefasDTOResponse> buscarTarefasPorEmail(String token) {
        return tarefaClient.buscarListaTarefasPorEmail(token);
    }

    // metodo de deletar tarefas por ID
    public void deleterPorId(String id, String token) {
        tarefaClient.deletarTarefaPorId(id, token);
    }

    public TarefasDTOResponse alterarStatus(StatusNotificaoEnum staus, String id, String token) {
        return tarefaClient.alterarStatus(staus, id, token);
    }


    public TarefasDTOResponse updateTarefa(TarefasDTORequest tarefasDTO, String id, String token) {
       return tarefaClient.updateTarefa(id, tarefasDTO, token);
    }




}
