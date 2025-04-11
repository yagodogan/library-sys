package com.yago.libsystem.business.abstracts;

import com.yago.libsystem.core.response.GenericResponse;
import com.yago.libsystem.dto.AuthorDto;
import com.yago.libsystem.dto.BookDto;
import com.yago.libsystem.dto.CreateAuthorDto;
import com.yago.libsystem.dto.UpdateAuthorDto;

import java.util.List;

public interface IAuthorService {
    GenericResponse<List<BookDto>> get(int id);
    GenericResponse<List<AuthorDto>> getAll();
    GenericResponse<CreateAuthorDto> create(CreateAuthorDto createAuthorDto);
    GenericResponse<UpdateAuthorDto> update(UpdateAuthorDto updateAuthorDto, int id);
    GenericResponse<AuthorDto> delete(int id);
}
