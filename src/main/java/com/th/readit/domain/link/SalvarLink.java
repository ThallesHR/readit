package com.th.readit.domain.link;

import com.th.readit.domain.conteudo.Conteudo;
import com.th.readit.domain.conteudo.ConteudoRepository;
import io.swagger.v3.oas.models.links.Link;
import jakarta.validation.Valid;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SalvarLink {
    @Autowired
    ConteudoRepository repository;
    public Conteudo pegarLink(@Valid LinkRequest dadosLink) {
        if (repository.existsByUrl(dadosLink.url())) {
            return repository.findByUrl(dadosLink.url());
        }
        try {
            Document document = Jsoup.connect(dadosLink.url()).userAgent("Mozilla/5.0(Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                    .referrer("http://www.google.com")
                    .timeout(10000).get();
            String titulo = document.title();
            String html = document.body().html();
            String texto = document.select("p").text();
            if (texto.length()>200){
                texto = texto.substring(0,200) + "...";
            }
            String urlImagem = document.select("meta[property=og:image]").attr("content");
            if(urlImagem.isEmpty()){
                urlImagem = document.select("link[rel=apple-touch-icon]").attr("href");
            }
            return new Conteudo(dadosLink.url(), titulo,texto,urlImagem);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        
    }
}
