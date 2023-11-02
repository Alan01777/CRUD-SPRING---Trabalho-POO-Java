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
    // Anotação para gerenciar referências no JSON (evita referências cíclicas).
    @JsonManagedReference
    private List<Book> books;

    // Construtor vazio padrão.
    public Author() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
