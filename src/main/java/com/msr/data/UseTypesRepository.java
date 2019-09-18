package com.msr.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.msr.model.UseTypes;

public interface UseTypesRepository extends JpaRepository<UseTypes, Integer> {

    @Query(value = "SELECT * FROM use_types u WHERE u.id = (SELECT su.use_type_id FROM site_uses su WHERE su.site_id = :id GROUP BY su.use_type_id ORDER BY su.size_sqft DESC LIMIT 1)",
            nativeQuery = true)
    UseTypes getUseTypeOfMaxSqftSiteUseForSiteId(@Param("id") int id);

}

// Copyright 2018 Measurabl, Inc. All rights reserved.
