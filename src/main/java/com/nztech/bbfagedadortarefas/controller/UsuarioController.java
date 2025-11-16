package com.nztech.bbfagedadortarefas.controller;
import com.nztech.bbfagedadortarefas.business.UsuarioService;
import com.nztech.bbfagedadortarefas.business.dto.in.EnderecoDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.in.LoginRequestDTO;
import com.nztech.bbfagedadortarefas.business.dto.in.TelefoneDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.in.UsuarioDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.out.EnderecoDTOResponse;
import com.nztech.bbfagedadortarefas.business.dto.out.TelefoneDTOResponse;
import com.nztech.bbfagedadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadstro e login de usuarios") // indica o nome e descricao da nossa controle na documentcao
public class UsuarioController {
    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar usuarios", description = "Cria um novo usuario") // serve para descrever oque o metodo faz
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")// indica o status code de cada requisicao
    @ApiResponse(responseCode = "400", description = "Usuario ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {

        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de  usuarios", description = "Fazer login do usuario") // serve para descrever oque o metodo faz
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")// indica o status code de cada requisicao
    @ApiResponse(responseCode = "401", description = "Credencias invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO) {
        return (usuarioService.loginUsuario(usuarioDTO));
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuario por email", description = "Buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarPorEmail(@RequestParam("email") String email,
                                                             @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.buscarPorEmail(email,token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta dados de usuario por email", description = "Deletar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarPorEmail(@PathVariable String email,
                                                @RequestHeader("Authorization") String token) {
        usuarioService.deletarPorEmail(email, token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuario", description = "Atualizar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                   @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereco do usuario por id", description = "Atualizar endereco do usuario")
    @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizarEnderceo(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefone do usuario por id", description = "Atualizar telefone do usuario")
    @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizarTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, telefoneDTO, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "cadastrar endereco", description = "cadastrar endereco do usuario")
    @ApiResponse(responseCode = "200", description = "Endereco cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastroEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastroEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "cadastrar telefone", description = "cadastrar telefone do usuario")
    @ApiResponse(responseCode = "200", description = "Endereco cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastroTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastroTelefone(token, telefoneDTO));
    }
}
