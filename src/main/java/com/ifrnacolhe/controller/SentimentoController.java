package com.ifrnacolhe.controller;

import com.ifrnacolhe.business.SentimentoService;
import com.ifrnacolhe.business.dto.request.SentimentoRequestDTO;
import com.ifrnacolhe.business.dto.response.SentimentoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ifrnacolhe/sentimentos")
@RequiredArgsConstructor
@Tag(name = "Sentimento", description = "CRUD de Sentimento")
public class SentimentoController {

    private final SentimentoService sentimentoService;

    @PostMapping
    @Operation(summary = "Cadastrar Sentimento", description = "Cria um novo sentimento")
    @ApiResponse(responseCode = "201", description = "Sentimento cadastrado com sucesso")
    @ApiResponse(responseCode = "409", description = "Já existe um sentimento cadastrado com esse nome")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<SentimentoResponseDTO> salvar(@RequestBody SentimentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sentimentoService.salvar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar Sentimentos", description = "Listar todos os sentimentos")
    @ApiResponse(responseCode = "200", description = "Sentimentos listados com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<SentimentoResponseDTO>> listar() {
        return ResponseEntity.ok(sentimentoService.listar());
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar Sentimento pelo nome", description = "Busca os dados do sentimento pelo nome")
    @ApiResponse(responseCode = "200", description = "Sentimento encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Sentimento não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<SentimentoResponseDTO> buscarPorNome(@RequestParam("nome") String nome) {
        return ResponseEntity.ok(sentimentoService.buscarPorNome(nome));
    }
}
