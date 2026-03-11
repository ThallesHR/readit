package com.th.readit.domain.link;

import com.th.readit.domain.conteudo.Conteudo;

public record DadosDetalhadosLink(Long id, String url, String titulo, String textoLimpo,String urlImagem) {

    public DadosDetalhadosLink(Conteudo conteudo){
        this(conteudo.getId(), conteudo.getUrl(), conteudo.getTitulo(), conteudo.getTextoLimpo(),conteudo.getUrlImagem());
    }
}
