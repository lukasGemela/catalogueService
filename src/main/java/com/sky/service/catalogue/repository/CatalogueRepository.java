package com.sky.service.catalogue.repository;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.sky.service.catalogue.api.Catalogue;
import com.sky.service.catalogue.api.Channel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.sky.service.catalogue.api.ChannelCategory.NEWS;
import static com.sky.service.catalogue.api.ChannelCategory.SPORT;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * Created by lgemela on 28/01/2017.
 */
@RestController
public class CatalogueRepository {

    private Map<String,List<Channel>> locationDependent = ImmutableMap.<String,List<Channel>>builder()
            .put("LONDON", ImmutableList.of(
                    Channel.builder().channelCategory(SPORT).channelName("Arsenal TV").build(),
                    Channel.builder().channelCategory(SPORT).channelName("Chelsea TV").build()
                    )
            )
            .put("LIVERPOOL", ImmutableList.of(
                    Channel.builder().channelCategory(SPORT).channelName("Liverpool TV").build()
            ))
            .build();

    private List<Channel> locationIndependent = ImmutableList.of(
            Channel.builder().channelCategory(NEWS).channelName("Sky News").build(),
            Channel.builder().channelCategory(NEWS).channelName("Sky Sport News").build()
            );

    @CrossOrigin //allow cors - dont use in production!!!!
    @RequestMapping("/catalogue")
    public Catalogue catalogue(@RequestHeader("locationID") String locationId) {

        List<Channel> mergedList = Stream.of(locationDependent.getOrDefault(locationId, emptyList()), locationIndependent)
                .flatMap(Collection::stream)
                .collect(toList());


        return Catalogue.builder()
                .sports(mergedList.stream().filter((channel) -> SPORT.equals(channel.channelCategory())).map(Channel::channelName).collect(toList()))
                .news(mergedList.stream().filter((channel) -> NEWS.equals(channel.channelCategory())).map(Channel::channelName).collect(toList()))
                .build();
    }


}
