package com.ifrnacolhe.business.mapper;

import com.ifrnacolhe.business.dto.request.SentimentoRequestDTO;
import com.ifrnacolhe.business.dto.response.SentimentoResponseDTO;
import com.ifrnacolhe.infrastructure.entity.Sentimento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SentimentoMapper {

    Sentimento toEntity(SentimentoRequestDTO dto);

    SentimentoResponseDTO toResponseDTO(Sentimento entity);
}
