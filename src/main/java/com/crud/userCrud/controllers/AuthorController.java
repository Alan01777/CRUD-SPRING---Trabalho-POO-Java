package com.crud.userCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.crud.userCrud.entities.Author;
import com.crud.userCrud.entities.Book;
import com.crud.userCrud.Repositories.AuthorRepository;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public Iterable<Author> getAllAuthors() {
        Iterable<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            author.setBooks(author.getBooks());
        }
        return authors;
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        author.setId(id);
        return authorRepository.save(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            List<Book> books = author.getBooks();
            for (Book book : books) {
                book.setAuthor(null); // Desassocia o autor dos livros
            }
            authorRepository.delete(author);
        }
    }
}
