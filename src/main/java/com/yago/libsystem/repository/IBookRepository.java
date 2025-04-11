package com.yago.libsystem.repository;

import com.yago.libsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorId(int authorId);
    boolean existsByTitleAndAuthorId(String title, int authorId);
    Optional<Book> getBookById(int id);
    Optional<List<Book>> findBooksByAuthorId(int authorId);
}
