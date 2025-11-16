package com.nztech.bbfagedadortarefas.business;

import com.nztech.bbfagedadortarefas.business.dto.in.LoginRequestDTO;
import com.nztech.bbfagedadortarefas.business.dto.out.TarefasDTOResponse;
import com.nztech.bbfagedadortarefas.infrastructure.enums.StatusNotificaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    // contruir DTO de login com metodos estaticos pk p cron nao aceita parametros no metodo

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public  void BuscarTarefasProximaHora() {
        log.info("Iniciada a busca de tarefas");
        String token = login(coverterParaRequestDTO());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMais5 = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefasDTOResponse> listaDeTarefas =  tarefasService.buscarTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMais5, token);
        log.info("Tarefas encontradas" + listaDeTarefas);
        // para cada tarefa, enviar um email da tarefa, e altera o status de cada tarefa para notificado
        listaDeTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuario " + tarefa.getEmailUsuario());
            tarefasService.alterarStatus(StatusNotificaoEnum.NOTIFICADO,tarefa.getId(), token);});
        log.info("finalizada a busca e notificacao de tarefas");
    }

    // implemetar login para passar como credenciar para o CRON enviar email
    public String login(LoginRequestDTO dto) {
        return usuarioService.loginUsuario(dto);
    }

    // converter dados para o request
    public LoginRequestDTO coverterParaRequestDTO() {
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
