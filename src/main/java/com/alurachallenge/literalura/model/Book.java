package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String lenguajes;
    private Integer numeroDescargas;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="author_id")
    private Author autor;

    public Book() {}

    public Book(DataBook dataBook, DataAuthors dataAuthor) {
        this.titulo = dataBook.titulo();
        this.lenguajes = dataBook.lenguajes();
        this.numeroDescargas = dataBook.numeroDescargas();
        this.autor = new Author(dataAuthor);
    }

    @Override
    public String toString() {
        var messg = """
                -------------- LIBRO -------------
                Título: %s
                Autor: %s
                Idioma: %s
                Numero de descargas: %d
                -----------------------------------
                """.formatted(titulo, autor.getNombre(),lenguajes, numeroDescargas);

        return messg;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
