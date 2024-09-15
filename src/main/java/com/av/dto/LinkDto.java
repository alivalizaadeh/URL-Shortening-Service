package com.av.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record LinkDto(
        Long id,
        String url,
        String shortCode,
        Date createdDate,
        Date updatedDate
) {
}
