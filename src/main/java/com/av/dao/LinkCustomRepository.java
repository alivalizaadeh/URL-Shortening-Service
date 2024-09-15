package com.av.dao;

import com.av.entity.Link;

import java.util.Optional;

public interface LinkCustomRepository {

    Optional<Link> getLinkByShortCode(String shortCode);

    Optional<Link> getLinkByUrl(String url);
}
