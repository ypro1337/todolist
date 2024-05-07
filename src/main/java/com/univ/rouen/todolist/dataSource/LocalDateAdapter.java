package com.univ.rouen.todolist.dataSource;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * XmlAdapter for converting LocalDate objects to and from String representations during XML marshalling and unmarshalling.
 */
@XmlJavaTypeAdapter(LocalDateAdapter.class)
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * Converts a String representation of a date to a LocalDate object.
     *
     * @param value The String representation of the date.
     * @return The LocalDate object.
     */
    @Override
    public LocalDate unmarshal(String value) {
        return value != null ? LocalDate.parse(value) : null;
    }

    /**
     * Converts a LocalDate object to its String representation.
     *
     * @param value The LocalDate object.
     * @return The String representation of the date.
     */
    @Override
    public String marshal(LocalDate value) {
        return value != null ? value.toString() : null;
    }
}
