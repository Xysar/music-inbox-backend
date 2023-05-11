package com.example.music.inbox.backend.serializer;

import com.example.music.inbox.backend.entity.Album;
import com.example.music.inbox.backend.entity.Review;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ReviewSerializer extends StdSerializer<Review> {
    public ReviewSerializer() {
        this(null);
    }

    public ReviewSerializer(Class<Review> t) {
        super(t);
    }

    public void serialize(
            Review value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("content", value.getContent());
        jgen.writeNumberField("rating", value.getRating());
        jgen.writeFieldName("album");
            jgen.writeStartObject();
            jgen.writeNumberField("id", value.getAlbum().getId() );
        jgen.writeStringField("title",value.getAlbum().getTitle());
        jgen.writeStringField("artist",value.getAlbum().getArtist());
        jgen.writeStringField("content",value.getAlbum().getMbid());
        jgen.writeEndObject();

        jgen.writeFieldName("user");
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getUser().getId() );
        jgen.writeStringField("clerkId",value.getUser().getClerkId());
        jgen.writeStringField("username",value.getUser().getUsername());
            jgen.writeEndObject();

        jgen.writeEndObject();
    }
}
