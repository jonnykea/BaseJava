package com.urise.webapp.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/*public class LocalDateTimeTypeAdapter extends TypeAdapter<LocalDateTime>  {

    @Override
    public void write(final JsonWriter jsonWriter, final LocalDateTime localDate) throws IOException {
        if (localDate == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(localDate.toString());
    }

    @Override
    public LocalDateTime read(final JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        return ZonedDateTime.parse(jsonReader.nextString()).toLocalDateTime();
    }
}*/

class LocalDateDeserializer implements JsonDeserializer< LocalDate >, JsonSerializer < LocalDate > {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("d-MMM-yyyy").withLocale(Locale.ENGLISH));
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}