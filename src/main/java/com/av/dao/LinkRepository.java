package com.av.dao;

import com.av.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {

    @Query(value = "select l from Link l where l.shortCode = :shortCode")
    Optional<Link> getLinkByShortCode(@Param("shortCode") String shortCode);

    @Query(value = "select l from Link l where l.url = :url")
    Optional<Link> getLinkByUrl(@Param("url") String url);

    @Transactional
    @Modifying
    @Query(value = "update uss.link set access_count = (access_count + 1) where id = :id",
            nativeQuery = true)
    void increaseAccessCount(@Param("id") Long id);
}
