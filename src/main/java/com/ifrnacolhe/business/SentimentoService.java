package com.ifrnacolhe.business;

import com.ifrnacolhe.business.dto.request.SentimentoRequestDTO;
import com.ifrnacolhe.business.dto.response.SentimentoResponseDTO;
import com.ifrnacolhe.business.mapper.SentimentoMapper;
import com.ifrnacolhe.infrastructure.entity.Sentimento;
import com.ifrnacolhe.infrastructure.exceptions.ConflictException;
import com.ifrnacolhe.infrastructure.exceptions.ResourceNotFoundException;
import com.ifrnacolhe.infrastructure.repository.SentimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentimentoService {

    private final SentimentoRepository sentimentoRepository;
    private final SentimentoMapper sentimentoMapper;

    public SentimentoResponseDTO salvar(SentimentoRequestDTO dto) {

        verificarSentimentoExistente(dto.getNome());

        Sentimento entity = sentimentoMapper.toEntity(dto);

        entity.ativar();

        return sentimentoMapper.toResponseDTO(sentimentoRepository.save(entity));
    }

    private void verificarSentimentoExistente(String nome) {
        if (sentimentoRepository.existsByNome(nome)) {
            throw new ConflictException("Já existe um sentimento cadastrado com esse nome! - Nome: " + nome);
        }
    }

    public SentimentoResponseDTO buscarPorNome(String nome) {

        return sentimentoMapper.toResponseDTO(
                sentimentoRepository.findByNomeIgnoreCase(nome)
                        .orElseThrow(() -> new ResourceNotFoundException("Sentimento " + nome + " não encontrado"))
        );
    }
}
