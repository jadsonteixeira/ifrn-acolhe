package com.ifrnacolhe.controller;

import com.ifrnacolhe.business.SentimentoService;
import com.ifrnacolhe.business.dto.request.SentimentoRequestDTO;
import com.ifrnacolhe.business.dto.response.SentimentoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ifrnacolhe/sentimentos")
@RequiredArgsConstructor
public class SentimentoController {

    private final SentimentoService sentimentoService;

    @PostMapping
    public ResponseEntity<SentimentoResponseDTO> salvar(@RequestBody SentimentoRequestDTO dto) {
        return ResponseEntity.ok(sentimentoService.salvar(dto));
    }
}
