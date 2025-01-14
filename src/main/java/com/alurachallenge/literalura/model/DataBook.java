package com.alurachallenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DataAuthors> autores,
        @JsonAlias("languages") String lenguajes,
        @JsonAlias("download_count") Integer numeroDescargas
) {
}
