package com.msr;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msr.data.SiteRepository;
import com.msr.data.SiteUseRepository;
import com.msr.data.UseTypesRepository;
import com.msr.model.Site;
import com.msr.model.SiteUses;
import com.msr.model.SiteWrapper;
import com.msr.model.UseTypes;
import com.msr.util.ClasspathUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BuildingsControllerTest {

    private static final String SITES_PATH = "/buildings";
    private static final String SITES_BY_USE_TYPE_ID_PATH = SITES_PATH + "?useTypeId={useTypeId}";
    private static final String SINGLE_SITE_PATH = SITES_PATH + "/{id}";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private SiteUseRepository siteUseRepository;

    @Autowired
    private UseTypesRepository useTypesRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void before() throws IOException {
        clearDatabases();

        String siteString = ClasspathUtil.readFileToString("data/sites.json", BuildingsApiApplication.class);
        String siteUsesString = ClasspathUtil.readFileToString("data/site_uses.json", BuildingsApiApplication.class);
        String useTypesString = ClasspathUtil.readFileToString("data/use_types.json", BuildingsApiApplication.class);

        Site[] sites = objectMapper.readValue(siteString, Site[].class);
        SiteUses[] siteUses = objectMapper.readValue(siteUsesString, SiteUses[].class);
        UseTypes[] useTypes = objectMapper.readValue(useTypesString, UseTypes[].class);

        siteRepository.saveAll(Arrays.asList(sites));
        siteUseRepository.saveAll(Arrays.asList(siteUses));
        useTypesRepository.saveAll(Arrays.asList(useTypes));

        assertDataSeeded();
    }

    @Test
    public void shouldRespond200WhenSiteFoundById() {
        int measurablId = 1;
        ResponseEntity<Site> response = restTemplate.getForEntity(getUrl(SINGLE_SITE_PATH), Site.class, measurablId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Site result = response.getBody();
        assertThat(result.getId()).isEqualTo(measurablId);
        assertThat(result.getName()).isEqualTo("Measurabl HQ");
        assertThat(result.getAddress()).isEqualTo("707 Broadway Suite 1000");
        assertThat(result.getCity()).isEqualTo("San Diego");
        assertThat(result.getState()).isEqualTo("CA");
        assertThat(result.getZipcode()).isEqualTo("92101");
        assertThat(result.getPrimaryType().getId()).isEqualTo(4);
        assertThat(result.getPrimaryType().getName()).isEqualTo("Data Center");
        assertThat(result.getTotalSize()).isEqualTo(13000L);
    }

    @Test
    public void shouldRespond404WhenSiteNotFound() {
        int nonExistentSiteId = 1000;
        ResponseEntity<Site> response =
                restTemplate.getForEntity(getUrl(SINGLE_SITE_PATH), Site.class, nonExistentSiteId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void shouldRespond200WhenGetAllSitesReturned() {
        ResponseEntity<SiteWrapper> response = restTemplate.getForEntity(getUrl(SITES_PATH), SiteWrapper.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        SiteWrapper result = response.getBody();

        assertThat(result.getSites()).hasSize(6);
    }

    @Test
    public void shouldRespond200WhenGetAllSitesEmptyReturned() {
        clearDatabases();
        assertThat(siteRepository.findAll()).isEmpty();

        ResponseEntity<SiteWrapper> response = restTemplate.getForEntity(getUrl(SITES_PATH), SiteWrapper.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        SiteWrapper result = response.getBody();
        assertThat(result.getSites()).isEmpty();
    }

    @Test
    public void shouldRespond200WhenGetAllSitesByUseTypeIdReturned() {
        int useTypeId = 16;
        ResponseEntity<SiteWrapper> response =
                restTemplate.getForEntity(getUrl(SITES_BY_USE_TYPE_ID_PATH), SiteWrapper.class, useTypeId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        SiteWrapper result = response.getBody();

        assertThat(result.getSites()).hasSize(3);
    }

    @Test
    public void shouldRespond200WhenGetAllSitesByUseTypeIdEmptyReturned() {
        int nonExistentUseTypeId = 1000;
        ResponseEntity<SiteWrapper> response =
                restTemplate.getForEntity(getUrl(SITES_BY_USE_TYPE_ID_PATH), SiteWrapper.class, nonExistentUseTypeId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        SiteWrapper result = response.getBody();

        assertThat(result.getSites()).isEmpty();
    }

    private void assertDataSeeded() {
        assertThat(siteRepository.findAll()).hasSize(6);
        assertThat(siteUseRepository.findAll()).hasSize(12);
        assertThat(useTypesRepository.findAll()).hasSize(112);
    }

    private void clearDatabases() {
        siteRepository.deleteAll();
        siteUseRepository.deleteAll();
        useTypesRepository.deleteAll();
    }

    private String getUrl(String path) {
        return "http://localhost:" + port + path;
    }
}
