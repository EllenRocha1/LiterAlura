package com.daydreamer.literalura.service;

import com.daydreamer.literalura.model.Autor;
import com.daydreamer.literalura.repository.AutorRepository;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @OneToMany(mappedBy = "autor")
    private List<com.daydreamer.literalura.model.Livro> livros;


    @Transactional(readOnly = true)
    public List<String> listarAutores() {
        List<Autor> autores = autorRepository.findAll();

        return autores.stream().map(autor -> {
            String livros = autor.getLivros().stream()
                    .map(livro -> livro.getTitulo())
                    .collect(Collectors.joining(", "));

            return "Autor: " + autor.getNome() + "\n" +
                    "Ano de nascimento: " + autor.getAnoNascimento() + "\n" +
                    "Ano de falecimento: " + (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "N/A") + "\n" +
                    "Livros: [" + livros + "]\n";
        }).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public List<String> listarAutoresVivosNoAno(int ano) {
        List<Autor> autores = autorRepository.findAll();

        return autores.stream()
                .filter(autor -> autor.getAnoNascimento() <= ano &&
                        (autor.getAnoFalecimento() == null || autor.getAnoFalecimento() > ano))
                .map(autor -> {
                    String livros = autor.getLivros().stream()
                            .map(livro -> livro.getTitulo())
                            .collect(Collectors.joining(", "));

                    return "Autor: " + autor.getNome() + "\n" +
                            "Ano de nascimento: " + autor.getAnoNascimento() + "\n" +
                            "Ano de falecimento: " + (autor.getAnoFalecimento() != null ? autor.getAnoFalecimento() : "N/A") + "\n" +
                            "Livros: [" + livros + "]\n";
                })
                .collect(Collectors.toList());
    }
}