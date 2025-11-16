package com.nztech.bbfagedadortarefas.controller;


import com.nztech.bbfagedadortarefas.business.TarefasService;
import com.nztech.bbfagedadortarefas.business.dto.in.TarefasDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.out.TarefasDTOResponse;
import com.nztech.bbfagedadortarefas.infrastructure.enums.StatusNotificaoEnum;
import com.nztech.bbfagedadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Agendar tarefas para usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Criar tarefa", description = "Criar tarefa para usuario")
    @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> criarTarefa(@RequestHeader(name ="Authorization", required = false) String token,
                                                          @RequestBody TarefasDTORequest tarefaDTO) {
        return ResponseEntity.ok(tarefasService.criarTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar tarefas por periodo", description = "Buscar tarefas cadastradas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name ="Authorization", required = false) String token
    ) {
        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar lista de tarefas por email do usuario", description = "Buscar tarefas cadastradas para cada email")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscarListaTarefasPorEmail(@RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deletar tarefas por ID", description = "Deletar tarfes cadastrads por Id")
    @ApiResponse(responseCode = "200", description = "tarefa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "tarefa nao encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id,
                                                   @RequestHeader(name ="Authorization", required = false) String token) {
        tarefasService.deleterPorId(id, token);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping
    @Operation(summary = "Atualizar status da tarefa", description = "Atualizar tarfes cadastrads por status")
    @ApiResponse(responseCode = "200", description = "status da tarefa atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "tarefa nao encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alterarStatus(@RequestParam("status") StatusNotificaoEnum status,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alterarStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualizar tarefas por ID", description = "Atualizar tafera cadstrada por ID")
    @ApiResponse(responseCode = "200", description = "tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "tarefa nao encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefa(@RequestParam("id") String id,
                                                           @RequestBody TarefasDTORequest tarefaDTO,
                                                           @RequestHeader(name ="Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefa(tarefaDTO, id, token));
    }
}
