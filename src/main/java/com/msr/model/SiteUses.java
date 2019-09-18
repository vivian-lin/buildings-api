package com.msr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Builder
@EqualsAndHashCode(exclude = {"id"})
@ToString
@Entity
@Table(name = "site_uses")
public class SiteUses {

    @Id
    private int id;

    @JsonProperty("site_id")
    @Column(name = "site_id")
    private int siteId;

    private String description;

    @JsonProperty("size_sqft")
    private long sizeSqft;

    @JsonProperty("use_type_id")
    private int useTypeId;

}

// Copyright 2018 Measurabl, Inc. All rights reserved.
