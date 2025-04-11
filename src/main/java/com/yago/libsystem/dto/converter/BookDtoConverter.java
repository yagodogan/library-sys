package com.yago.libsystem.dto.converter;

import com.yago.libsystem.dto.CreateBookDto;
import com.yago.libsystem.dto.BookDto;
import com.yago.libsystem.dto.UpdateBookDto;
import com.yago.libsystem.entity.Author;
import com.yago.libsystem.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDtoConverter {

    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getPages(),
                book.getAuthor() != null ? book.getAuthor().getName() : null
        );
    }

    public Book toEntity(CreateBookDto dto, Author author) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setPages(dto.pages());
        book.setAuthor(author);
        return book;
    }

    public Book updateEntity(Book book, UpdateBookDto dto, Author author) {
        book.setTitle(dto.title());
        book.setPages(dto.pages());
        book.setAuthor(author);
        return book;
    }

}
