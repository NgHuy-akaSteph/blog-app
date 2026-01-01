package com.huyquang.blogcore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass // Indicates that this class is a base class for JPA entities
@EntityListeners(AuditingEntityListener.class) // Enables auditing features
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate // Automatically set the creation timestamp
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate // Automatically set the last modified timestamp
    @Column(nullable = false)
    private Instant updatedAt;

    @CreatedBy // Needs AuditorAware implementation to set the creator
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy // Needs AuditorAware implementation to set the last modifier
    @Column
    private String updatedBy;
}
