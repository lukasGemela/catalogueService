package com.sky.service.catalogue.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.auto.value.AutoValue;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * Created by lgemela on 28/01/2017.
 */
@AutoValue
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonDeserialize(builder = Channel.Builder.class)
public abstract class Channel {

    public static Channel.Builder builder() {
        return new AutoValue_Channel.Builder();
    }

    public abstract ChannelCategory channelCategory();
    public abstract String channelName();
    public abstract Builder toBuilder();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {

        @JsonCreator
        private static Channel.Builder create() {
            return Channel.builder();
        }

        public abstract Channel.Builder channelCategory(ChannelCategory channelCategory);
        public abstract Channel.Builder channelName(String channelName);
        public abstract Channel build();
    }
}
