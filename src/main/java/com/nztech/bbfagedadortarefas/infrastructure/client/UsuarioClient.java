package com.nztech.bbfagedadortarefas.infrastructure.client;


import com.nztech.bbfagedadortarefas.business.dto.in.EnderecoDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.in.LoginRequestDTO;
import com.nztech.bbfagedadortarefas.business.dto.in.TelefoneDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.in.UsuarioDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.out.EnderecoDTOResponse;
import com.nztech.bbfagedadortarefas.business.dto.out.TelefoneDTOResponse;
import com.nztech.bbfagedadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO    usuarioDTO);

    @GetMapping
    UsuarioDTOResponse buscarPorEmail(@RequestParam("email") String email,
                                      @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    Void deletarPorEmail(@PathVariable String email,
                         @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuari(@RequestBody UsuarioDTORequest usuarioDTO,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizarEnderceo(@RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizarTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastroEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastroTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                         @RequestHeader("Authorization") String token);
}