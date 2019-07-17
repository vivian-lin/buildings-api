package com.msr.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Lookup types
 *
 * @author Measurabl
 * @since 2019-06-11
 */
@Data
@Entity
public class UseTypes {

    @Id
    private int id;

    private String name;
}

////////////////////////////////////////////////////////////
// Copyright 2018  Measurabl, Inc. All rights reserved.
////////////////////////////////////////////////////////////
    