package com.sky.service.catalogue.repository;

import com.sky.service.catalogue.api.Catalogue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by lgemela on 28/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogueRepositoryTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void readLondonChannels() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("locationID", "LONDON");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Catalogue> cat = this.restTemplate.exchange(
                "/catalogue", HttpMethod.GET, entity, Catalogue.class);
        assertThat(cat.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(cat.getBody().news(), containsInAnyOrder("Sky News", "Sky Sport News"));
        assertThat(cat.getBody().sports(), containsInAnyOrder("Arsenal TV", "Chelsea TV"));
    }

    @Test
    public void readLiverpoolChannels() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("locationID", "LIVERPOOL");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Catalogue> cat = this.restTemplate.exchange(
                "/catalogue", HttpMethod.GET, entity, Catalogue.class);
        assertThat(cat.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(cat.getBody().news(), containsInAnyOrder("Sky News", "Sky Sport News"));
        assertThat(cat.getBody().sports(), containsInAnyOrder("Liverpool TV"));
    }

    @Test
    public void readChannelsWithUnknownLocation() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("locationID", "LEEDS");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Catalogue> cat = this.restTemplate.exchange(
                "/catalogue", HttpMethod.GET, entity, Catalogue.class);
        assertThat(cat.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(cat.getBody().news(), containsInAnyOrder("Sky News", "Sky Sport News"));
        assertThat(cat.getBody().sports(), hasSize(0));
    }

    @Test
    public void sadPathMissingLocationHeader() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> cat = this.restTemplate.exchange(
                "/catalogue", HttpMethod.GET, entity, String.class);
        assertThat(cat.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
    }
}


