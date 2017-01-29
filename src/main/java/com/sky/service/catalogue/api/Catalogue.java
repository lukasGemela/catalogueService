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
@JsonDeserialize(builder = Catalogue.Builder.class)
public abstract class Catalogue {

    public static Catalogue.Builder builder() {
        return new AutoValue_Catalogue.Builder().news(emptyList()).sports(emptyList());
    }

    public abstract List<String> news();
    public abstract List<String> sports();
    public abstract Builder toBuilder();

    @AutoValue.Builder
    @JsonPOJOBuilder(withPrefix = "")
    public abstract static class Builder {

        @JsonCreator
        private static Catalogue.Builder create() {
            return Catalogue.builder();
        }

        public abstract Catalogue.Builder news(List<String> news);
        public abstract Catalogue.Builder sports(List<String> sports);

        public abstract Catalogue build();
    }
}


