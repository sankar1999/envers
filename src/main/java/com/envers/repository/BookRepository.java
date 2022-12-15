package com.envers.repository;

import com.envers.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface BookRepository extends RevisionRepository<Book, Integer, Integer>, JpaRepository<Book, Integer> {
}
