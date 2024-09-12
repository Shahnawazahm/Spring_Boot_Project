package com.redbus.operator.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
    private static final String DATE_FORMAT="dd/mm/yyyy";

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JacksonException {
        String date=jsonParser.getText();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.parse(date);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
    }
}
