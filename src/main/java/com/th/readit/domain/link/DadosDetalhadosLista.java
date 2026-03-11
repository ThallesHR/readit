package com.th.readit.domain.link;

import com.th.readit.domain.conteudo.Conteudo;

public record DadosDetalhadosLista (Long id, String url, String titulo, String textoLimpo,String urlImagem) {

    public DadosDetalhadosLista(Conteudo conteudo){
        this(conteudo.getId(), conteudo.getUrl(), conteudo.getTitulo(), conteudo.getTextoLimpo(),conteudo.getUrlImagem());
    }
}
