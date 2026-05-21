package com.ifrnacolhe.infrastructure.repository;

import com.ifrnacolhe.infrastructure.entity.Sentimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SentimentoRepository extends JpaRepository<Sentimento, Long> {

    Optional<Sentimento> findByNomeIgnoreCase(String nome);
    boolean existsByNomeIgnoreCase(String nome);
}
