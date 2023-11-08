package com.crud.userCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud.userCrud.entities.Book;
import com.crud.userCrud.Repositories.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {
    // Injeção de dependência do BookRepository.
    @Autowired
    private BookRepository bookRepository;

    // Retorna todos os livros.
    @GetMapping
    public Iterable<Book> getAllBooks() {
        // Obtém todos os livros do repositório e os retorna.
        return bookRepository.findAll();
    }

    // Retorna um livro por ID.
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        // Busca um livro pelo ID no repositório ou retorna nulo se não encontrado.
        return bookRepository.findById(id).orElse(null);
    }

    // Cria um novo livro.
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        // Salva o novo livro no repositório e o retorna.
        return bookRepository.save(book);
    }

    // Atualiza um livro por ID.
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        // Verifica se o livro existe no repositório.
        if (bookRepository.findById(id).orElse(null) == null) {
            return null;
        } else {
            // Define o ID do livro e salva as alterações no repositório.
            book.setId(id);
            return bookRepository.save(book);
        }
    }

    // Exclui um livro por ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            // Passo 1: Tente excluir o livro do repositório com base no ID.
            bookRepository.deleteById(id);

            // Passo 2: Retorna uma resposta com código 204 (No Content) e corpo vazio para
            // indicar exclusão bem-sucedida.
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {

            // Passo 1b: Se o livro não for encontrado, retorne um código de status 404 (Not
            // Found).
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            
            // Passo 1c: Se ocorrer uma exceção inesperada, retorne um código de status 500
            // (Internal Server Error).
            return ResponseEntity.status(500).build();
        }
    }

}
