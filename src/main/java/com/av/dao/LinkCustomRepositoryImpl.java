package com.av.dao;

import com.av.entity.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class LinkCustomRepositoryImpl implements LinkCustomRepository {

    private MongoOperations mongoOperations;

    @Autowired
    public void setMongoOperations(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Optional<Link> getLinkByShortCode(String shortCode) {
        Link link = mongoOperations.findOne(
                query(
                        where("short_code").is(shortCode)
                ),
                Link.class,
                Link.COLLECTION_NAME
        );
        return Optional.ofNullable(link);
    }

    public Optional<Link> getLinkByUrl(String url) {
        Link link = mongoOperations.findOne(
                query(
                        where("url").is(url)
                ),
                Link.class,
                Link.COLLECTION_NAME
        );
        return Optional.ofNullable(link);
    }
}
