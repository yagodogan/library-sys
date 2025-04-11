package com.yago.libsystem.business.concretes;

import com.yago.libsystem.business.abstracts.IAuthorService;
import com.yago.libsystem.business.constants.Messages;
import com.yago.libsystem.business.rules.AuthorBusinessRules;
import com.yago.libsystem.core.exception.BusinessException;
import com.yago.libsystem.core.exception.constants.ExceptionMessages;
import com.yago.libsystem.core.response.GenericResponse;
import com.yago.libsystem.dto.AuthorDto;
import com.yago.libsystem.dto.BookDto;
import com.yago.libsystem.dto.CreateAuthorDto;
import com.yago.libsystem.dto.UpdateAuthorDto;
import com.yago.libsystem.dto.converter.AuthorDtoConverter;
import com.yago.libsystem.dto.converter.BookDtoConverter;
import com.yago.libsystem.entity.Author;
import com.yago.libsystem.entity.Book;
import com.yago.libsystem.repository.IAuthorRepository;
import com.yago.libsystem.repository.IBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService implements IAuthorService {

    private final IAuthorRepository authorRepository;
    private final AuthorDtoConverter authorConverter;
    private final IBookRepository bookRepository;
    private final BookDtoConverter bookConverter;
    private final AuthorBusinessRules rules;

    public AuthorService(IAuthorRepository authorRepository, AuthorDtoConverter authorConverter, IBookRepository bookRepository, BookDtoConverter bookConverter, AuthorBusinessRules rules) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
        this.bookRepository = bookRepository;
        this.bookConverter = bookConverter;
        this.rules = rules;
    }

    @Override
    public GenericResponse<List<BookDto>> get(int id) {
        boolean exists = authorRepository.existsById(id);

        if(exists) {
        List<Book> books = bookRepository.findByAuthorId(id);

        List<BookDto> response = books.stream()
                .map(bookConverter::toDto)
                .collect(Collectors.toList());

        return new GenericResponse<>(true, response);

        }
        return new GenericResponse<>(false,Messages.AUTHOR_NOT_FOUND);
    }

    @Override
    public GenericResponse<List<AuthorDto>> getAll() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDto> response = authors.stream()
                .map(authorConverter::toDto)
                .collect(Collectors.toList());

        return new GenericResponse<>(true, response);
    }

    @Override
    public GenericResponse<CreateAuthorDto> create(CreateAuthorDto dto) {

        boolean isExists = rules.checkIfAuthorExist(dto.name());

        if(!isExists) {
            Author author = authorConverter.toEntity(dto.name());
            authorRepository.save(author);
            return new GenericResponse<>(true, Messages.AUTHOR_CREATED,dto);
        }
        return new GenericResponse<>(false,Messages.AUTHOR_EXIST);
    }

    @Override
    public GenericResponse<UpdateAuthorDto> update(UpdateAuthorDto updateAuthorDto, int id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new BusinessException(ExceptionMessages.AUTHOR_NOT_FOUND));

        boolean isExist = rules.checkIfAuthorExist(updateAuthorDto.name());
        if(!isExist) {
            Author updatedAuthor = authorConverter.updateEntity(author, updateAuthorDto);
            authorRepository.save(updatedAuthor);
            return new GenericResponse<>(true, Messages.AUTHOR_UPDATED,updateAuthorDto);
        }
        return new GenericResponse<>(false,Messages.AUTHOR_EXIST);
    }

    @Override
    public GenericResponse<AuthorDto> delete(int id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionMessages.AUTHOR_NOT_FOUND));

        List<Book> books= bookRepository.findBooksByAuthorId(id)
                        .orElseThrow(()-> new BusinessException(ExceptionMessages.BOOK_NOT_FOUND));

        if(!books.isEmpty()){
            bookRepository.deleteAll(books);
        }

        authorRepository.delete(author);
        return new GenericResponse<>(true, Messages.AUTHOR_DELETED);
    }
}
