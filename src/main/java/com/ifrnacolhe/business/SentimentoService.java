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

import java.util.List;

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
        if (sentimentoRepository.existsByNomeIgnoreCase(nome)) {
            throw new ConflictException("Já existe um sentimento cadastrado com esse nome: " + nome);
        }
    }

    public List<SentimentoResponseDTO> listar() {
        return sentimentoRepository.findAll()
                .stream()
                .map(sentimentoMapper::toResponseDTO)
                .toList();
    }

    public SentimentoResponseDTO buscarPorNome(String nome) {

        return sentimentoMapper.toResponseDTO(
                sentimentoRepository.findByNomeIgnoreCase(nome)
                        .orElseThrow(() -> new ResourceNotFoundException("Sentimento " + nome + " não encontrado"))
        );
    }

    public void verificarSentimentoExistenteUpdate(String nome, Long id) {
        if (sentimentoRepository.existsByNomeIgnoreCaseAndIdNot(nome, id)) {
            throw new ConflictException("Já existe um sentimento cadastrado com esse nome: " + nome);
        }
    }

    public SentimentoResponseDTO atualizar(Long id, SentimentoRequestDTO dto) {

        Sentimento entity = sentimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sentimento não encontrado com id: " + id));

        verificarSentimentoExistenteUpdate(dto.getNome(), id);

        entity.setNome(dto.getNome());

        return sentimentoMapper.toResponseDTO(sentimentoRepository.save(entity));
    }

    public SentimentoResponseDTO desativar(Long id) {

        Sentimento entity = sentimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sentimento não encontrado com id: " + id));

        entity.desativar();

        return sentimentoMapper.toResponseDTO(sentimentoRepository.save(entity));
    }

    public SentimentoResponseDTO reativar(Long id) {

        Sentimento entity = sentimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sentimento não encontrado com id: " + id));

        entity.ativar();

        return sentimentoMapper.toResponseDTO(sentimentoRepository.save(entity));
    }
}
