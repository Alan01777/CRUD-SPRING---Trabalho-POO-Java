package com.crud.userCrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.crud.userCrud.entities.Author;
import com.crud.userCrud.entities.Book;
import com.crud.userCrud.Repositories.AuthorRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;

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

    // Retorna um autor específico por ID.
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
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        // Passo 1: Verifica se o autor existe.
        Author author = authorRepository.findById(id).orElse(null);

        if (author != null) {
            try {
                // Passo 2: Desassocia o autor dos livros.
                List<Book> books = author.getBooks();
                for (Book book : books) {
                    book.setAuthor(null);
                }

                // Passo 3: Exclui o autor do repositório.
                authorRepository.delete(author);

                // Passo 4: Retorna uma resposta com código 204 (No Content) e corpo vazio para
                // indicar exclusão bem-sucedida.
                return ResponseEntity.noContent().build();

            } catch (Exception e) {
                
                // Passo 2b: Se ocorrer uma exceção inesperada, retorne um código de status 500
                // (Internal Server Error).
                return ResponseEntity.status(500).build();
            }
        } else {
            // Passo 1b: Se o autor não for encontrado, retorne um código de status 404 (Not
            // Found).
            return ResponseEntity.notFound().build();
        }
    }
}
