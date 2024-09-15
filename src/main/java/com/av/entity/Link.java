package com.av.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Table(name = Link.TABLE_NAME, schema = "USS")
@Entity
@SequenceGenerator(name = "link_sequence_generator", sequenceName = "link_sequence_generator", allocationSize = 1)
public class Link implements Serializable {

    public static final String TABLE_NAME = "LINK";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "link_sequence_generator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @NotNull
    @Column(name = "short_code", nullable = false, unique = true)
    private String shortCode;

    @NotNull
    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "access_count")
    private long accessCount;

    @PrePersist
    public void prePersist() {
        this.createdDate = new Date();
        accessCount = 0;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = new Date();
        accessCount++;
    }

}
