package com.msr.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.msr.model.Site;

public interface SiteRepository extends PagingAndSortingRepository<Site, Integer> {

    @Query("SELECT s FROM Site s JOIN SiteUses su ON su.siteId = s.id JOIN UseTypes ut ON ut.id = su.useTypeId WHERE ut.id = :useTypeId")
    List<Site> findAllByUseTypeId(@Param("useTypeId") int useTypeId);

}

// Copyright 2018 Measurabl, Inc. All rights reserved.
