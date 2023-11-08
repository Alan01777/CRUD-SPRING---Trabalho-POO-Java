package com.crud.userCrud.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.userCrud.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    // BookRepository é uma interface que estende JpaRepository, que é fornecida
    // pelo Spring Data JPA.
    // Essa interface fornece métodos de acesso aos dados do tipo Book no banco de
    // dados.

    // Book é o tipo de entidade com o qual essa interface está associada.

    // Long especifica o tipo de dados do ID da entidade, que neste caso é Book.
    // Ou seja, os IDs dos livros são do tipo Long.

    // Com essa interface, é possivel ter acesso a métodos como save, findById, findAll,
    // delete, entre outros,
    // para realizar operações de CRUD no banco de dados associadas à entidade Book.
    // O Spring Data JPA implementa esses métodos automaticamente com base nas
    // convenções de nomenclatura e mapeamento da entidade.

}
