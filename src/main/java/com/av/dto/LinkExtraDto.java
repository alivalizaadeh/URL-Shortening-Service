package com.av.dto;

import lombok.Builder;

import java.util.Date;

@Builder
public record LinkExtraDto(
        Long id,
        String url,
        String shortCode,
        Date createdDate,
        Date updatedDate,
        long accessCount
) {
}
