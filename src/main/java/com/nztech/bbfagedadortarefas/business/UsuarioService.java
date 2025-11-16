package com.nztech.bbfagedadortarefas.business;


import com.nztech.bbfagedadortarefas.business.dto.in.EnderecoDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.in.LoginRequestDTO;
import com.nztech.bbfagedadortarefas.business.dto.in.TelefoneDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.in.UsuarioDTORequest;
import com.nztech.bbfagedadortarefas.business.dto.out.EnderecoDTOResponse;
import com.nztech.bbfagedadortarefas.business.dto.out.TelefoneDTOResponse;
import com.nztech.bbfagedadortarefas.business.dto.out.UsuarioDTOResponse;
import com.nztech.bbfagedadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioClient usuarioClient;



    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTO) {
        return usuarioClient.salvarUsuario(usuarioDTO);

    }

    public String loginUsuario(LoginRequestDTO usuarioDTO) {
        return  usuarioClient.login(usuarioDTO);
    }


    public UsuarioDTOResponse buscarPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }


    public void deletarPorEmail(String email, String token) {
        usuarioClient.deletarPorEmail(email, token);
    }


    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest usuarioDTO) {
        return  usuarioClient.atualizaDadosUsuari(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizarEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return  usuarioClient.atualizarEnderceo(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizarTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.atualizarTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastroEndereco(String token, EnderecoDTORequest enderecoDTO) {
       return usuarioClient.cadastroEndereco(enderecoDTO, token);

    }

    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest telefoneDTO) {
        return usuarioClient.cadastroTelefone(telefoneDTO, token);
    }

}
