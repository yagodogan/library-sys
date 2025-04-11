package com.yago.libsystem.business.rules;

import com.yago.libsystem.entity.Author;
import com.yago.libsystem.repository.IAuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorBusinessRules {

    private final IAuthorRepository authorRepository;

    public AuthorBusinessRules(IAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public boolean checkIfAuthorExist(String authorName) {
        return authorRepository.existsByName(authorName);
    }

}
