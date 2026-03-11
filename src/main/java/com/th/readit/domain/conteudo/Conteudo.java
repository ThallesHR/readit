package com.th.readit.domain.conteudo;

import com.th.readit.domain.link.DadosAtualizacaoLink;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conteudos")
@NoArgsConstructor
public class Conteudo {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String url;
    @Getter
    private String titulo;
    @Getter
    @Column(columnDefinition = "TEXT")
    private String textoLimpo;
    @Getter
    private boolean ativo;
    @Getter
    private String urlImagem;




    public Conteudo(String url, String titulo,String textoLimpo, String urlImagem) {
        this.url = url;
        this.titulo = titulo;
        this.textoLimpo = textoLimpo;
        this.ativo = true;
        this.urlImagem = urlImagem;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoLink dados){
        if (dados.url() != null){
            this.url = dados.url();
        }
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.textoLimpo() != null) {
            this.textoLimpo = dados.textoLimpo();
        }
        if (dados.urlImagem() != null) {
            this.urlImagem = dados.urlImagem();
        }
    }

    public void excluir(){
        this.ativo = false;
    }

}
