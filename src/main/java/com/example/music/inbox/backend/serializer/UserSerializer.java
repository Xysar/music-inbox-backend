package com.example.music.inbox.backend.serializer;

import com.example.music.inbox.backend.entity.Review;
import com.example.music.inbox.backend.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class UserSerializer extends StdSerializer<User> {


        public UserSerializer() {
            this(null);
        }

        public UserSerializer(Class<User> t) {
            super(t);
        }

        @Override
        public void serialize(
                User value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException, JsonProcessingException {

            jgen.writeStartObject();
            jgen.writeNumberField("id", value.getId());
            jgen.writeStringField("username", value.getUsername());
            jgen.writeStringField("clerkId", value.getClerkId());
            jgen.writeStringField("imageId", value.getImageId());
            jgen.writeArrayFieldStart("reviews");
            for (Review userReview : value.getReviews()) {
                jgen.writeStartObject();
                jgen.writeNumberField("id", userReview.getId());
                jgen.writeStringField("content",userReview.getContent());
                jgen.writeNumberField("rating",userReview.getRating());
                jgen.writeStringField("mbid", userReview.getAlbum().getMbid());
                jgen.writeStringField("imageId", value.getImageId());
                jgen.writeStringField("title", userReview.getAlbum().getTitle());
                jgen.writeStringField("artist", userReview.getAlbum().getArtist());
                jgen.writeEndObject();
            }
            jgen.writeEndArray();
            jgen.writeEndObject();
        }
    }

