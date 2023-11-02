package com.crud.userCrud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// A classe representa um livro no sistema.

@Entity
@Table(name = "books")
public class Book {
    // ID único do livro.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Título do livro.
    private String title;

    // Relacionamento muitos-para-um com a entidade Author.
    @ManyToOne
    @JoinColumn(name = "author_id")
    // Anotação para evitar referências cíclicas ao serializar para JSON.
    @JsonBackReference
    private Author author;

    // Construtor vazio padrão.
    public Book() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }

    
    public Author getAuthor() {
        return author;
    }

    
    public void setAuthor(Author author) {
        this.author = author;
    }
}
