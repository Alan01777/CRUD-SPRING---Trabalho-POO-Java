package com.crud.userCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.crud.userCrud.entities.Author;
import com.crud.userCrud.entities.Book;
import com.crud.userCrud.Repositories.AuthorRepository;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    // Injeção de dependência do AuthorRepository.
    @Autowired
    private AuthorRepository authorRepository;

    // Retorna todos os autores.
    @GetMapping
    public Iterable<Author> getAllAuthors() {
        // Obtém todos os autores do repositório.
        Iterable<Author> authors = authorRepository.findAll();
        // Para cada autor, carrega a lista de livros associados.
        for (Author author : authors) {
            author.setBooks(author.getBooks());
        }
        // Retorna a lista de autores.
        return authors;
    }

    // Retorna um autor por ID.
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        // Busca um autor pelo ID no repositório ou retorna nulo se não encontrado.
        return authorRepository.findById(id).orElse(null);
    }

    // Cria um novo autor.
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        // Salva o novo autor no repositório e o retorna.
        return authorRepository.save(author);
    }

    // Atualiza um autor por ID.
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        // Verifica se o autor existe no repositório.
        if (authorRepository.findById(id).orElse(null) == null) {
            return null;
        } else {
            // Define o ID do autor e salva as alterações no repositório.
            author.setId(id);
            return authorRepository.save(author);
        }
    }

    // Exclui um autor por ID e desassocia os livros do autor.
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        // Busca o autor no repositório.
        Author author = authorRepository.findById(id).orElse(null);
        // Se o autor existir, desassocia os livros e exclui o autor.
        if (author != null) {
            List<Book> books = author.getBooks();
            for (Book book : books) {
                // Desassocia o autor dos livros
                book.setAuthor(null);
            }
            // Exclui o autor do repositório.
            authorRepository.delete(author);
        }
    }
}
