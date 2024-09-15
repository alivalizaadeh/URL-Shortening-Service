package com.av.service;

import com.av.dto.LinkDto;
import com.av.dto.LinkExtraDto;

public interface LinkService {
    LinkDto save(LinkDto dto);

    LinkDto updateByShortCode(String shortCode, LinkDto dto);

    LinkDto getDtoByShortCode(String shortCode);

    boolean deleteByShortCode(String shortCode);

    LinkExtraDto getExtraDtoByShortCode(String shortCode);
}
