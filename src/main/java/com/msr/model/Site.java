package com.msr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = {"id"})
@ToString
@Entity
@Table(name = "site")
public class Site {

    @Id
    private int id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zipcode;

    @JsonProperty("total_size")
    @Formula("SELECT SUM(su.size_sqft) FROM site_uses su WHERE su.site_id = id")
    private long totalSize;

    @Transient
    @JsonProperty("primary_type")
    private UseTypes primaryType;

}

// Copyright 2018 Measurabl, Inc. All rights reserved.
