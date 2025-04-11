package com.yago.libsystem.controller;

import com.yago.libsystem.business.abstracts.IAuthorService;
import com.yago.libsystem.controller.constants.RequestMappingField;
import com.yago.libsystem.core.response.GenericResponse;
import com.yago.libsystem.dto.AuthorDto;
import com.yago.libsystem.dto.BookDto;
import com.yago.libsystem.dto.CreateAuthorDto;
import com.yago.libsystem.dto.UpdateAuthorDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AuthorController {

    private final IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(RequestMappingField.AUTHOR_GET_BY_ID)
    public GenericResponse<List<BookDto>> getBooks(@PathVariable int id) {
        return authorService.get(id);
    }

    @GetMapping(RequestMappingField.AUTHOR_GET)
    public GenericResponse<List<AuthorDto>> getAll() {
        return authorService.getAll();
    }

    @PostMapping(RequestMappingField.AUTHOR_CREATE)
    public GenericResponse<CreateAuthorDto> create(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorService.create(createAuthorDto);
    }

    @PutMapping(RequestMappingField.AUTHOR_UPDATE)
    public GenericResponse<UpdateAuthorDto> update(@PathVariable int id, @RequestBody UpdateAuthorDto dto) {
        return authorService.update(dto, id);
    }

    @DeleteMapping(RequestMappingField.AUTHOR_DELETE_BY_ID)
    public GenericResponse<AuthorDto> delete(@PathVariable int id) {
        return authorService.delete(id);
    }
}
