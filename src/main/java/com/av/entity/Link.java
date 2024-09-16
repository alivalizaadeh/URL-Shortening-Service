package com.av.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = Link.COLLECTION_NAME)
public class Link implements Serializable {

    public static final String COLLECTION_NAME = "LINK";

    @Id
    @NotNull
    @Field(name = "id", write = Field.Write.NON_NULL)
    private Long id;

    @NotNull
    @Field(name = "url", targetType = FieldType.STRING, write = Field.Write.NON_NULL)
    private String url;

    @NotNull
    @Field(name = "short_code", targetType = FieldType.STRING, write = Field.Write.NON_NULL)
    private String shortCode;

    @NotNull
    @CreatedDate
    @Field(name = "created_date", targetType = FieldType.TIMESTAMP, write = Field.Write.NON_NULL)
    private Date createdDate;

    @LastModifiedDate
    @Field(name = "updated_date", targetType = FieldType.TIMESTAMP)
    private Date updatedDate;

    @Field(name = "access_count", targetType = FieldType.INT64)
    private long accessCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link link)) return false;
        return getAccessCount() == link.getAccessCount() && Objects.equals(getId(), link.getId()) && Objects.equals(getUrl(), link.getUrl()) && Objects.equals(getShortCode(), link.getShortCode()) && Objects.equals(getCreatedDate(), link.getCreatedDate()) && Objects.equals(getUpdatedDate(), link.getUpdatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUrl(), getShortCode(), getCreatedDate(), getUpdatedDate(), getAccessCount());
    }
}
