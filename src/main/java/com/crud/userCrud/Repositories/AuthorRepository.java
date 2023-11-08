package com.crud.userCrud.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.userCrud.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // AuthorRepository é uma interface que estende JpaRepository, que é fornecida
    // pelo Spring Data JPA.
    // Essa interface fornece métodos de acesso aos dados do tipo Author no banco de
    // dados.

    // Author é o tipo de entidade com o qual essa interface está aFssociada.

    // Long especifica o tipo de dados do ID da entidade, que neste caso é Author.
    // Isso indica que os IDs dos autores são do tipo Long.

    // Com essa interface, você terá acesso a métodos como save, findById, findAll,
    // delete, entre outros,
    // para realizar operações de CRUD no banco de dados associadas à entidade
    // AFuthor.
    // O Spring Data JPA implementa esses métodos automaticamente com base nas
    // convenções de
    // nomenclatura e mapeamento da entidade.
}
