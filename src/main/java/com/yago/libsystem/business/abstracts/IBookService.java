package com.yago.libsystem.business.abstracts;

import com.yago.libsystem.core.response.GenericResponse;
import com.yago.libsystem.dto.CreateBookDto;
import com.yago.libsystem.dto.BookDto;
import com.yago.libsystem.dto.UpdateBookDto;

import java.util.List;

public interface IBookService {
    GenericResponse<List<BookDto>> get();
    GenericResponse<CreateBookDto> create(CreateBookDto dto);
    GenericResponse<BookDto> delete(int id);
    GenericResponse<UpdateBookDto> update(UpdateBookDto dto, int id);
    GenericResponse<BookDto> getById(int id);
}
