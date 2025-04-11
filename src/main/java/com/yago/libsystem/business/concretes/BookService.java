package com.yago.libsystem.business.concretes;

import com.yago.libsystem.business.abstracts.IBookService;
import com.yago.libsystem.business.constants.Messages;
import com.yago.libsystem.business.rules.BookBusinessRules;
import com.yago.libsystem.core.exception.BusinessException;
import com.yago.libsystem.core.exception.constants.ExceptionMessages;
import com.yago.libsystem.core.response.GenericResponse;
import com.yago.libsystem.dto.CreateBookDto;
import com.yago.libsystem.dto.BookDto;
import com.yago.libsystem.dto.UpdateBookDto;
import com.yago.libsystem.dto.converter.BookDtoConverter;
import com.yago.libsystem.entity.Author;
import com.yago.libsystem.entity.Book;
import com.yago.libsystem.repository.IAuthorRepository;
import com.yago.libsystem.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    private final IBookRepository bookRepository;
    private final IAuthorRepository authorRepository;
    private final BookDtoConverter converter;
    private final BookBusinessRules rules;

    public BookService(IBookRepository repository, IAuthorRepository authorRepository, BookDtoConverter converter, BookBusinessRules rules) {
        this.bookRepository = repository;
        this.authorRepository = authorRepository;
        this.converter = converter;
        this.rules = rules;
    }

    @Override
    public GenericResponse<List<BookDto>> get() {
        List<Book> books = bookRepository.findAll();

        List<BookDto> response = books.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());

        return new GenericResponse<>(true, response);
    }

    @Override
    public GenericResponse<CreateBookDto> create(CreateBookDto dto) {
        Author author = authorRepository.findById(dto.author_id())
                .orElseThrow(() -> new BusinessException(ExceptionMessages.AUTHOR_NOT_FOUND));

        boolean isExists = rules.checkIfBookExist(
                dto.title(),
                dto.author_id()
        );

        if (!isExists) {
            Book book = converter.toEntity(dto, author);
            bookRepository.save(book);
            return new GenericResponse<>(true, Messages.BOOK_CREATED, dto);
        }
        return new GenericResponse<>(false, Messages.BOOK_EXIST);
    }

    @Override
    public GenericResponse<BookDto> delete(int id) {
        boolean isExists = bookRepository.existsById(id);
        if(isExists) {
            bookRepository.deleteById(id);
            return new GenericResponse<>(true, Messages.BOOK_DELETED);
        }
        return new GenericResponse<>(false, Messages.BOOK_NOT_EXIST);
    }

    @Override
    public GenericResponse<UpdateBookDto> update(UpdateBookDto dto, int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ExceptionMessages.BOOK_NOT_FOUND));

        Author author = authorRepository.findById(dto.author_id())
                .orElseThrow(()-> new BusinessException(ExceptionMessages.AUTHOR_NOT_FOUND));

        boolean isExist = rules.checkIfBookExist(
                dto.title(),
                dto.author_id()
        );

        if(!isExist) {
            Book updatedEntity = converter.updateEntity(book,dto,author);
            bookRepository.save(updatedEntity);
            return new GenericResponse<>(true,Messages.BOOK_UPDATED,dto);
        }
        return new GenericResponse<>(false, Messages.BOOK_EXIST);
    }

    @Override
    public GenericResponse<BookDto> getById(int id) {
        Book book = bookRepository.getBookById(id)
                .orElseThrow(()-> new BusinessException(ExceptionMessages.BOOK_NOT_FOUND));

        BookDto response = converter.toDto(book);
        return new GenericResponse<>(true, response);
    }
}
