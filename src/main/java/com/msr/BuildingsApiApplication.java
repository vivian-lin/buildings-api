package com.msr;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msr.data.SiteRepository;
import com.msr.data.SiteUseRepository;
import com.msr.data.UseTypesRepository;
import com.msr.model.Site;
import com.msr.model.SiteUses;
import com.msr.model.UseTypes;
import com.msr.util.ClasspathUtil;

@SpringBootApplication
public class BuildingsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuildingsApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedData(SiteRepository siteRepository, SiteUseRepository siteUseRepository,
            UseTypesRepository useTypesRepository) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();

            String siteString = ClasspathUtil.readFileToString("data/sites.json", BuildingsApiApplication.class);
            String siteUsesString =
                    ClasspathUtil.readFileToString("data/site_uses.json", BuildingsApiApplication.class);
            String useTypesString =
                    ClasspathUtil.readFileToString("data/use_types.json", BuildingsApiApplication.class);

            Site[] sites = objectMapper.readValue(siteString, Site[].class);
            SiteUses[] siteUses = objectMapper.readValue(siteUsesString, SiteUses[].class);
            UseTypes[] useTypes = objectMapper.readValue(useTypesString, UseTypes[].class);

            siteRepository.saveAll(Arrays.asList(sites));
            siteUseRepository.saveAll(Arrays.asList(siteUses));
            useTypesRepository.saveAll(Arrays.asList(useTypes));
        };
    }
}
