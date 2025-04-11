package com.yago.libsystem.repository;

import com.yago.libsystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthorRepository extends JpaRepository<Author, Integer> {
    boolean existsByName(String name);
    Optional<Author> findById(int id);
}
