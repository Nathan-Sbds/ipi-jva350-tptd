package com.ipi.jva350;
import com.ipi.jva350.model.LinkedHashSetStringConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
class LinkedHashSetStringConverterTest {
    private final LinkedHashSetStringConverter converter = new LinkedHashSetStringConverter();

    @Test
    void testConvertToDatabaseColumn() {
        LinkedHashSet<LocalDate> dates = new LinkedHashSet<>();
        dates.add(LocalDate.of(2023, 1, 1));
        dates.add(LocalDate.of(2023, 1, 2));
        String expected = "2023-01-01;2023-01-02";
        String result = converter.convertToDatabaseColumn(dates);
        assertEquals(expected, result);
    }

    @Test
    void testConvertToDatabaseColumn_Null() {
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void testConvertToEntityAttribute() {
        String datesString = "2023-01-01;2023-01-02";
        LinkedHashSet<LocalDate> expected = new LinkedHashSet<>();
        expected.add(LocalDate.of(2023, 1, 1));
        expected.add(LocalDate.of(2023, 1, 2));
        LinkedHashSet<LocalDate> result = converter.convertToEntityAttribute(datesString);
        assertEquals(expected, result);
    }

    @Test
    void testConvertToEntityAttribute_Null() {
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    void testConvertToEntityAttribute_EmptyString() {
        LinkedHashSet<LocalDate> result = converter.convertToEntityAttribute("");
        assertEquals(new LinkedHashSet<>(), result);
    }
}