package com.yago.libsystem.controller;

import com.yago.libsystem.business.abstracts.IBookService;
import com.yago.libsystem.controller.constants.RequestMappingField;
import com.yago.libsystem.core.response.GenericResponse;
import com.yago.libsystem.dto.CreateBookDto;
import com.yago.libsystem.dto.BookDto;
import com.yago.libsystem.dto.UpdateBookDto;
import com.yago.libsystem.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BookController {

    private final IBookService service;

    public BookController(IBookService service) {
        this.service = service;
    }

    @GetMapping(RequestMappingField.BOOK_GET)
    public GenericResponse<List<BookDto>> get(){
        return service.get();
    }

    @PostMapping(RequestMappingField.BOOK_CREATE)
    public GenericResponse<CreateBookDto> create(@RequestBody CreateBookDto dto){
        return service.create(dto);
    }

    @PutMapping(RequestMappingField.BOOK_UPDATE)
    public GenericResponse<UpdateBookDto> update(@RequestBody UpdateBookDto dto,@PathVariable int id){
        return service.update(dto, id);}

    @GetMapping(RequestMappingField.BOOK_GET_BY_ID)
    public GenericResponse<BookDto> getById(@PathVariable int id){
        return service.getById(id);
    }

    @DeleteMapping(RequestMappingField.BOOK_DELETE)
    public GenericResponse<BookDto> delete(@PathVariable int id){
        return service.delete(id);
    }
}
