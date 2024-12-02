package com.projeto_fuji.consultas.controller;

import com.projeto_fuji.consultas.exception.ReceitaMedicaNotFoundException;
import com.projeto_fuji.consultas.model.ReceitaMedica;
import com.projeto_fuji.consultas.service.ReceitaMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
public class ReceitaMedicaController {
    private final ReceitaMedicaService receitaMedicaService;

    @GetMapping
    public List<ReceitaMedica> retornarTodasReceitas() {return receitaMedicaService.retornarTodasReceitas();}

    @GetMapping("/buscar/{id}")
    public Optional<ReceitaMedica> buscarReceita(@PathVariable Long id){return receitaMedicaService.buscarReceita(id);}

    @PostMapping("/criar")
    public ResponseEntity<ReceitaMedica> criarReceita(@RequestBody ReceitaMedica receitaMedica){
        ReceitaMedica novaReceita = receitaMedicaService.criarReceita(receitaMedica);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReceita);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ReceitaMedica> atualizarReceita(@PathVariable Long id, @RequestBody ReceitaMedica receitaDetalhes){
        try{
            ReceitaMedica receitaAtualizada = receitaMedicaService.atualizarReceita(id, receitaDetalhes);
            return ResponseEntity.ok(receitaAtualizada);
        } catch (ReceitaMedicaNotFoundException e) {
            throw new ReceitaMedicaNotFoundException();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long id){
        boolean deletado = receitaMedicaService.deletarReceita(id);
        if (deletado){
            return ResponseEntity.ok().build();

        } else {
            throw new ReceitaMedicaNotFoundException();
        }
    }

}