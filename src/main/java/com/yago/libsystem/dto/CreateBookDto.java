package com.yago.libsystem.dto;

public record CreateBookDto(
        String title,
        int pages,
        int author_id
) {
}
