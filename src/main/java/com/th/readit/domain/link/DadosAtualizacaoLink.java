package com.th.readit.domain.link;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoLink(
        @NotNull
        Long id,
        String url,
        String titulo,
        String textoLimpo,
        String urlImagem

) {
}
