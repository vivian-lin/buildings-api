package com.msr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "use_types")
public class UseTypes {

    @Id
    private int id;

    private String name;

}

// Copyright 2018 Measurabl, Inc. All rights reserved.
