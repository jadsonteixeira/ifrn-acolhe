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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
