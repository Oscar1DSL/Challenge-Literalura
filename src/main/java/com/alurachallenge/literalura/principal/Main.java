package com.alurachallenge.literalura.principal;

import com.alurachallenge.literalura.model.Book;
import com.alurachallenge.literalura.model.DataAuthors;
import com.alurachallenge.literalura.model.DataBook;
import com.alurachallenge.literalura.repository.LiteraturaRepository;
import com.alurachallenge.literalura.service.ApiClient;
import com.alurachallenge.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner input = new Scanner(System.in);
    private ApiClient apiClient = new ApiClient();
    private String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LiteraturaRepository repositorio;
    private List<Book> books;

    public Main(LiteraturaRepository repository) {
        this.repositorio = repository;
    }

    public void fnMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    1 - Buscar Libro
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    fnSearchBook();
                    break;
                case 2:
                    fnSearchBooks();
                    break;
                case 3:
                    fnSearchAuthors();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación");
                    break;
                default:
                    System.out.println("Opcion inválida");
            }
        }
    }

    public DataBook getDataBook() {
        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var bookName = input.nextLine();
        var json = apiClient.obtenerDatos(URL_BASE + bookName.replace(" ", "%20"));
        DataBook data = conversor.obtenerDatos(json, DataBook.class);
        System.out.println(data);
        return data;
    }

    public void fnSearchBook() {
        DataBook data = getDataBook();
        DataAuthors dataAuthor = data.autores().get(0);

        Book book = new Book(data, dataAuthor);
        repositorio.save(book);
        String string = book.toString();
        System.out.println(string);
    }

    public void fnSearchBooks() {
        books = repositorio.findAll();
        books.stream()
                .sorted(Comparator.comparing(Book::getTitulo))
                .forEach(System.out::println);
    }

    public void fnSearchAuthors() {

    }
}
