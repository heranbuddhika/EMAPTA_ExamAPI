package com.emapta.examapi.enumconverter;

import com.emapta.examapi.enums.State;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<State, String> {

    @Override
    public String convertToDatabaseColumn(State state) {
        if (state == null) {
            return null;
        }
        return state.getText();
    }

    @Override
    public State convertToEntityAttribute(String text) {
        if (text == null) {
            return null;
        }

        return Stream.of(State.values())
                .filter(c -> c.getText().equals(text))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
