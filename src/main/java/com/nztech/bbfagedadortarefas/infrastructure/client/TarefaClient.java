package com.nztech.bbfagedadortarefas.infrastructure.client;


import com.nztech.bbfagedadortarefas.business.dto.in.TarefasDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.out.TarefasDTOResponse;
import com.nztech.bbfagedadortarefas.infrastructure.enums.StatusNotificaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefas", url = "${tarefas.url}")
public interface TarefaClient {

    @PostMapping
    TarefasDTOResponse criarTarefa(@RequestHeader("Authorization") String token,
                                   @RequestBody TarefasDTORequest tarefaDTO);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token
    );
    @GetMapping
    List<TarefasDTOResponse> buscarListaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    Void deletarTarefaPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alterarStatus(@RequestParam("status") StatusNotificaoEnum status,
                                     @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefa(@RequestParam("id") String id,
                                    @RequestBody TarefasDTORequest tarefaDTO,
                                    @RequestHeader("Authorization") String token);
}