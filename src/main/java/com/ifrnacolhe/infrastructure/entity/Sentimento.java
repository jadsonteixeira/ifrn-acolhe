package com.ifrnacolhe.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sentimento")
@Builder
public class Sentimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ativo")
    private Boolean ativo;

    public void ativar() {
        setAtivo(true);
    }
}
