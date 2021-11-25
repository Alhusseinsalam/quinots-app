package dev.husein.quinots.adapter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class TagsConverter implements AttributeConverter<List<String>, String> {
    private static final String SEPARATOR_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return strings != null ? String.join(SEPARATOR_CHAR, strings) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return s != null ? Arrays.asList(s.split(SEPARATOR_CHAR)) : Collections.emptyList();
    }
}
