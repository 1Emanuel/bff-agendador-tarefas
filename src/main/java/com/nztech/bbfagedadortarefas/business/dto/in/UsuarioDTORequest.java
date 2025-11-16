package com.nztech.bbfagedadortarefas.business.dto.in;

import com.nztech.bbfagedadortarefas.business.dto.out.EnderecoDTOResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTORequest> telefones;


}
