package com.crud.userCrud.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// A classe representa um autor de livros no sistema.
@Entity
@Table(name = "author")
public class Author {
    // ID único do autor.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do autor.
    private String name;

    // Relacionamento um-para-muitos com a entidade Book.
    @OneToMany(mappedBy = "author")
    // Anotação para gerenciar referências no JSON (evita referências
    // cíclicas/loops).
    @JsonManagedReference
    private List<Book> books;

    // Construtor vazio padrão.
    public Author() {
    }

    // Getters e Setters

    // Método getter para obter o ID do autor.
    public Long getId() {
        return id;
    }

    // Método setter para definir o ID do autor.
    public void setId(Long id) {
        this.id = id;
    }

    // Método getter para obter o nome do autor.
    public String getName() {
        return name;
    }

    // Método setter para definir o nome do autor.
    public void setName(String name) {
        this.name = name;
    }

    // Método getter para obter a lista de livros associados a este autor.
    public List<Book> getBooks() {
        return books;
    }

    // Método setter para definir a lista de livros associados a este autor.
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
