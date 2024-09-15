package com.av.controller;

import com.av.dto.LinkDto;
import com.av.dto.LinkExtraDto;
import com.av.service.LinkService;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("/shorten")
@RestController
public class LinkController {

    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<LinkDto> createNewLink(@RequestBody LinkDto dto) {
        if (ObjectUtils.isEmpty(dto) ||
                ObjectUtils.isEmpty(dto.url()))
            return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(linkService.save(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<LinkDto> updateLink(@RequestBody LinkDto dto,
                                              @PathVariable("shortCode") @NotNull String shortCode) {
        if (Objects.isNull(dto) ||
                Objects.isNull(dto.url()))
            return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(linkService.updateByShortCode(shortCode, dto), HttpStatus.OK);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<LinkDto> getByShortCode(@PathVariable("shortCode") @NotNull String shortCode) {
        LinkDto result = linkService.getDtoByShortCode(shortCode);
        if (Objects.nonNull(result))
            return ResponseEntity.ok(result);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<ObjectUtils.Null> deleteByShortCode(@PathVariable("shortCode") @NotNull String shortCode) {
        boolean deleted = linkService.deleteByShortCode(shortCode);
        if (Boolean.TRUE.equals(deleted))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<LinkExtraDto> getLinkWithStats(@PathVariable("shortCode") @NotNull String shortCode) {
        LinkExtraDto result = linkService.getExtraDtoByShortCode(shortCode);
        if (Objects.nonNull(result))
            return ResponseEntity.ok(result);
        return ResponseEntity.notFound().build();
    }
}
