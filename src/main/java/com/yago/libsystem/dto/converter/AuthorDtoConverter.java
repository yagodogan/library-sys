package com.yago.libsystem.dto.converter;

import com.yago.libsystem.dto.AuthorDto;
import com.yago.libsystem.dto.UpdateAuthorDto;
import com.yago.libsystem.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDtoConverter {

    public AuthorDto toDto(Author author) {
        return new AuthorDto(
                author.getId(),
                author.getName()
        );
    }

    public Author toEntity(String authorName) {
        Author author = new Author();
        author.setName(authorName);
        return author;
    }

    public Author updateEntity(Author author, UpdateAuthorDto dto) {
        author.setName(dto.name());
        return author;
    }
}
