package com.yago.libsystem.dto;

public record UpdateBookDto(
        String title,
        int pages,
        int author_id
) {
}
