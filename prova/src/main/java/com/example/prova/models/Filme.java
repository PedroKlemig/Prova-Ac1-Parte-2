package com.example.prova.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Integer duracao;

    @ManyToOne
    @JoinColumn(name = "diretor_id")
    private Diretor diretor;
}
