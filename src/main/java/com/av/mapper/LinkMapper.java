package com.av.mapper;

import com.av.dto.LinkDto;
import com.av.dto.LinkExtraDto;
import com.av.entity.Link;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LinkMapper {

    public LinkDto entityToDto(Link link) {
        if (Objects.nonNull(link))
            return LinkDto.builder()
                    .id(link.getId())
                    .shortCode(link.getShortCode())
                    .url(link.getUrl())
                    .createdDate(link.getCreatedDate())
                    .updatedDate(link.getUpdatedDate())
                    .build();
        return null;
    }

    public Link dtoToEntity(LinkDto dto) {
        Link link = new Link();
        if (Objects.nonNull(dto)) {
            if (ObjectUtils.isNotEmpty(dto.id())) {
                link.setId(dto.id());
            }
            if (ObjectUtils.isNotEmpty(dto.url())) {
                link.setUrl(dto.url());
            }
        }
        return link;
    }

    public LinkExtraDto entityToExtraDto(Link link) {
        if (Objects.nonNull(link))
            return LinkExtraDto.builder()
                    .id(link.getId())
                    .shortCode(link.getShortCode())
                    .url(link.getUrl())
                    .createdDate(link.getCreatedDate())
                    .updatedDate(link.getUpdatedDate())
                    .accessCount(link.getAccessCount())
                    .build();
        return null;
    }
}
