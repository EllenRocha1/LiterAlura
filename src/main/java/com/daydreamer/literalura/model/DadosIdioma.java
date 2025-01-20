package com.daydreamer.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosIdioma(@JsonAlias("languages") String idioma) {
}
