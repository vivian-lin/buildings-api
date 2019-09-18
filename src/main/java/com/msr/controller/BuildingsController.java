package com.msr.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msr.data.SiteDao;
import com.msr.model.Site;
import com.msr.model.SiteWrapper;

@RestController
@RequestMapping("/buildings")
public class BuildingsController {

    @Autowired
    private SiteDao siteDao;

    @GetMapping(value = "/{id}")
    public Site get(@PathVariable int id) {
        return siteDao.getSiteWithSupplementalData(id);
    }

    @GetMapping
    public ResponseEntity<SiteWrapper> getAll(@RequestParam(value = "useTypeId") Optional<Integer> useTypeId) {
        SiteWrapper sites = siteDao.getAllSites(useTypeId);
        return ResponseEntity.ok(sites);
    }

}

// Copyright 2018 Measurabl, Inc. All rights reserved.
