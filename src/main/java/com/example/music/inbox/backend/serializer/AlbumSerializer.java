package com.example.music.inbox.backend.serializer;

import com.example.music.inbox.backend.entity.Album;
import com.example.music.inbox.backend.entity.Review;
import com.example.music.inbox.backend.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AlbumSerializer extends StdSerializer<Album> {

    public AlbumSerializer() {
        this(null);
    }

    public AlbumSerializer(Class<Album> t) {
        super(t);
    }

    public void serialize(
            Album value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("mbid", value.getMbid());
        jgen.writeStringField("title", value.getTitle());
        jgen.writeStringField("artist", value.getArtist());
        jgen.writeStringField("imageId", value.getImageId());
        jgen.writeArrayFieldStart("reviews");
        for (Review userReview : value.getReviews()) {
            jgen.writeStartObject();
            jgen.writeNumberField("id", userReview.getId() );
            jgen.writeStringField("content",userReview.getContent());
            jgen.writeNumberField("rating",userReview.getRating());
            jgen.writeStringField("userName",userReview.getUser().getUsername());
            jgen.writeStringField("userId",userReview.getUser().getClerkId());
            jgen.writeStringField("imageId",userReview.getUser().getImageId());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}
