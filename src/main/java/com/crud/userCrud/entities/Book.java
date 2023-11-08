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

    // Método getter para obter o ID do livro.
    public Long getId() {
        return id;
    }

    // Método setter para definir o ID do livro.
    public void setId(Long id) {
        this.id = id;
    }

    // Método getter para obter o título do livro.
    public String getTitle() {
        return title;
    }

    // Método setter para definir o título do livro.
    public void setTitle(String title) {
        this.title = title;
    }

    // Método getter para obter o autor do livro.
    public Author getAuthor() {
        return author;
    }

    // Método setter para definir o autor do livro.
    public void setAuthor(Author author) {
        this.author = author;
    }
}
