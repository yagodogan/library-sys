package com.yago.libsystem.business.rules;

import com.yago.libsystem.repository.IBookRepository;
import org.springframework.stereotype.Component;

@Component
public class BookBusinessRules {

    private final IBookRepository bookRepository;

    public BookBusinessRules(IBookRepository bookRepository ){
        this.bookRepository = bookRepository;
    }

    public boolean checkIfBookExist(String title, Integer authorId) {
        return bookRepository.existsByTitleAndAuthorId(title, authorId);
    }
}
