package com.av.dao;

import com.av.entity.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkRepository extends MongoRepository<Link, Long>, LinkCustomRepository {
}
