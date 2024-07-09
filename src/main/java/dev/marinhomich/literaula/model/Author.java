package dev.marinhomich.literaula.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
    public Author() {}

    public Author(AuthorData author) {
        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
    }

    public String getName() {
        return name;
    }

    public void setBooks(Book book) {
        this.books.add(book);
    }

    public void printAuthor() {
        System.out.println("**** Autor: " + name + " ****");
        if (birthYear != null) {
            System.out.println("--- Nascimento: " + birthYear);
        }
        if (deathYear != null) {
            System.out.println("--- Viveu até: " + deathYear);
        }
        System.out.println("--- Livros: ");
        books.forEach(book -> System.out.println("+ " + book.getTitle()));
        System.out.println();
    }
}