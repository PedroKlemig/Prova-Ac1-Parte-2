package com.example.prova;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.prova.models.Diretor;
import com.example.prova.models.Filme;
import com.example.prova.repositories.DiretorRepository;
import com.example.prova.repositories.FilmeRepository;

@SpringBootApplication
public class ProvaApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired FilmeRepository filmeRepository,
            @Autowired DiretorRepository diretorRepository) {

        return args -> {

            Diretor d1 = Diretor.builder()
                    .nome("Christopher Nolan")
                    .build();

            Diretor d2 = Diretor.builder()
                    .nome("James Cameron")
                    .build();

            d1 = diretorRepository.save(d1);
            d2 = diretorRepository.save(d2);


            Filme f1 = Filme.builder()
                    .titulo("Carros 2")
                    .duracao(169)
                    .diretor(d1)
                    .build();

            Filme f2 = Filme.builder()
                    .titulo("Sonic")
                    .duracao(152)
                    .diretor(d1)
                    .build();

            Filme f3 = Filme.builder()
                    .titulo("Avatar")
                    .duracao(195)
                    .diretor(d2)
                    .build();

            f1 = filmeRepository.save(f1);
            f2 = filmeRepository.save(f2);
            f3 = filmeRepository.save(f3);

            
            System.out.println("FILMES COM DURACAO > 160");
            List<Filme> maior = filmeRepository.findByDuracaoGreaterThan(160);
            maior.stream().map(Filme::getTitulo).forEach(System.out::println);

            System.out.println("FILMES COM DURACAO <= 160");
            List<Filme> menor = filmeRepository.findByDuracaoLessThanEqual(160);
            menor.stream().map(Filme::getTitulo).forEach(System.out::println);

            System.out.println("FILMES QUE COMECAM COM A");
            List<Filme> comA = filmeRepository.findByTituloStartingWith("A");
            comA.stream().map(Filme::getTitulo).forEach(System.out::println);

            
            System.out.println("DIRETORES QUE COMECAM COM J");
            List<Diretor> diretores = diretorRepository.findByNomeStartingWith("J");
            diretores.stream().map(Diretor::getNome).forEach(System.out::println);

            System.out.println("BUSCAR DIRETOR COM FILMES");
            Diretor dir = diretorRepository.findByIdWithFilmes(d1.getId()).get();

            System.out.println("Diretor: " + dir.getNome());
            dir.getFilmes().stream()
                    .map(Filme::getTitulo)
                    .forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ProvaApplication.class, args);
    }
}