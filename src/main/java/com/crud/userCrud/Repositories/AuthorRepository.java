package com.crud.userCrud.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.userCrud.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    
}
