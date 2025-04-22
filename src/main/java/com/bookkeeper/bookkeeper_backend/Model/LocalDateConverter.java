package com.bookkeeper.bookkeeper_backend.Model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null ? null : localDate.format(formatter);
    }

    @Override
    public LocalDate convertToEntityAttribute(String dateString) {
        return dateString == null ? null : LocalDate.parse(dateString, formatter);
    }
} 