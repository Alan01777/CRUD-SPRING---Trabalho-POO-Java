package com.crud.userCrud.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.userCrud.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}
