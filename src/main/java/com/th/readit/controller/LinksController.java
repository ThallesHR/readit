package com.th.readit.controller;

import com.th.readit.domain.conteudo.Conteudo;
import com.th.readit.domain.conteudo.ConteudoRepository;
import com.th.readit.domain.link.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("conteudos")
public class LinksController {

    @Autowired
    private SalvarLink salvaLink;

    @Autowired
    private ConteudoRepository conteudoRepository;
    @PostMapping
    @Transactional
    public ResponseEntity receberLinks(@RequestBody @Valid LinkRequest dadosLink, UriComponentsBuilder uriB){

        Conteudo conteudo = salvaLink.pegarLink(dadosLink);
        conteudoRepository.save(conteudo);


        var uri = uriB.path("/conteudos/{id}").buildAndExpand(conteudo.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhadosLink(conteudo));


    }
    @GetMapping
    public ResponseEntity <Page<DadosDetalhadosLista>> listar(@PageableDefault(size = 10,sort = "id") Pageable paginacao){
        var page = conteudoRepository.findAllByAtivoTrue(paginacao).map(DadosDetalhadosLista::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoLink dados){
        var conteudo = conteudoRepository.getReferenceById(dados.id());
        conteudo.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhadosLink(conteudo));

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var conteudo = conteudoRepository.getReferenceById(id);
        conteudo.excluir();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id ){
        var conteudo = conteudoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhadosLink(conteudo));
    }
}
