package com.ifrnacolhe.business;

import com.ifrnacolhe.business.dto.request.SentimentoRequestDTO;
import com.ifrnacolhe.business.dto.response.SentimentoResponseDTO;
import com.ifrnacolhe.business.mapper.SentimentoMapper;
import com.ifrnacolhe.infrastructure.entity.Sentimento;
import com.ifrnacolhe.infrastructure.repository.SentimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentimentoService {

    private final SentimentoRepository sentimentoRepository;
    private final SentimentoMapper sentimentoMapper;

    public SentimentoResponseDTO salvar(SentimentoRequestDTO dto) {

        Sentimento entity = sentimentoMapper.toEntity(dto);

        entity.ativar();

        return sentimentoMapper.toResponseDTO(sentimentoRepository.save(entity));
    }
}
