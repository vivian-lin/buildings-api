package com.msr.data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msr.exception.ResourceNotFoundException;
import com.msr.model.Site;
import com.msr.model.SiteWrapper;
import com.msr.model.UseTypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SiteDao {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private UseTypesRepository useTypesRepository;

    public Site getSiteWithSupplementalData(int id) {
        Optional<Site> find = siteRepository.findById(id);

        if (!find.isPresent()) {
            throw new ResourceNotFoundException("No site found for id: " + id);
        }

        // Tried to avoid another DB call using @Formula annotation on Site.java, but alas, here we are.
        UseTypes primaryType = useTypesRepository.getUseTypeOfMaxSqftSiteUseForSiteId(id);

        Site site = find.get().toBuilder().primaryType(primaryType).build();
        log.debug("Found site with supplemental data {}", find);
        return site;
    }

    public SiteWrapper getAllSites(Optional<Integer> useTypeId) {
        Iterable<Site> sites;
        if (useTypeId.isPresent()) {
            sites = siteRepository.findAllByUseTypeId(useTypeId.get());
        } else {
            sites = siteRepository.findAll();
        }
        return SiteWrapper.builder().sites(sites).build();
    }

    public Site createSite(Site site) {
        Site create = siteRepository.save(site);
        log.debug("Created site {}", create);
        return create;
    }

}

// Copyright 2018 Measurabl, Inc. All rights reserved.
