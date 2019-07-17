package com.msr.model;

import lombok.Data;

import javax.persistence.Id;

/**
 * Site uses POJO
 *
 * @author Measurabl
 * @since 2019-06-11
 */
@Data
public class SiteUses {
    @Id
    private int id;

    private int siteId;

    private String description;

    private long sizeSqft;

    private int useTypeId;

    private UseTypes useTypes;
}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    