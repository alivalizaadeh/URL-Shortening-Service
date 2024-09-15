package com.av.service;

import com.av.dao.LinkRepository;
import com.av.dto.LinkDto;
import com.av.dto.LinkExtraDto;
import com.av.entity.Link;
import com.av.mapper.LinkMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class LinkServiceImpl implements LinkService {

    private LinkRepository linkRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final LinkMapper linkMapper;

    @Autowired
    public LinkServiceImpl(LinkRepository linkRepository,
                           SequenceGeneratorService sequenceGeneratorService,
                           LinkMapper linkMapper) {
        this.linkRepository = linkRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.linkMapper = linkMapper;
    }

    private String generateKey() {
        int length = 5;
        boolean useLetters = true;
        boolean useNumbers = true;
        String key;

        Optional<Link> optional;
        do {
            key = RandomStringUtils.random(length, useLetters, useNumbers);
            optional = this.linkRepository.getLinkByShortCode(key);
        } while (optional.isPresent());
        return key;
    }

    private Link increaseAccessCount(Link link) {
        link.setAccessCount(link.getAccessCount() + 1);
        return link;
    }

    private Link save(Link entity) {
        Objects.requireNonNull(entity, "Entity is null!");
        boolean generateLink = false;
        if (Objects.isNull(entity.getId())) {
            Long nextId = sequenceGeneratorService.generateSequence(Link.COLLECTION_NAME);
            entity.setId(nextId);
            generateLink = true;
        } else {
            Optional<Link> optional = linkRepository.findById(entity.getId());
            if (optional.isEmpty()) {
                Long nextId = sequenceGeneratorService.generateSequence(Link.COLLECTION_NAME);
                entity.setId(nextId);
                generateLink = true;
            }
        }
        if (generateLink) {
            String key = generateKey();
            entity.setShortCode(key);
            entity.setCreatedDate(new Date());
        }
        entity.setUpdatedDate(new Date());
        return linkRepository.save(entity);
    }

    @Override
    public LinkDto save(LinkDto dto) {
        Link link = linkMapper.dtoToEntity(dto);
        link = save(link);
        return linkMapper.entityToDto(link);
    }

    @Override
    public LinkDto updateByShortCode(String shortCode, LinkDto dto) {
        Link link = getByShortCode(shortCode);
        if (Objects.nonNull(link)) {
            link.setUrl(dto.url());
            link = save(link);
            return linkMapper.entityToDto(link);
        }
        return null;
    }

    @Override
    public boolean deleteByShortCode(String shortCode) {
        Link link = getByShortCode(shortCode);
        if (Objects.nonNull(link)) {
            linkRepository.deleteById(link.getId());
            return true;
        }
        return false;
    }

    private Link getByShortCode(String shortCode) {
        return linkRepository.getLinkByShortCode(shortCode).orElse(null);
    }

    private Link getByUrl(String url) {
        return linkRepository.getLinkByUrl(url).orElse(null);
    }

    @Override
    public LinkDto getDtoByShortCode(String shortCode) {
        return linkMapper.entityToDto(getByShortCode(shortCode));
    }

    @Override
    public LinkDto getDtoByUrl(String url) {
        return linkMapper.entityToDto(getByUrl(url));
    }

    @Override
    public LinkExtraDto getExtraDtoByShortCode(String shortCode) {
        return linkMapper.entityToExtraDto(getByShortCode(shortCode));
    }
}
