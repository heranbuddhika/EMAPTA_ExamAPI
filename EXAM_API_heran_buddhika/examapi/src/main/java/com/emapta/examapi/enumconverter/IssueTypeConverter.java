package com.emapta.examapi.enumconverter;

import com.emapta.examapi.enums.IssueType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class IssueTypeConverter implements AttributeConverter<IssueType, String> {

    @Override
    public String convertToDatabaseColumn(IssueType issueType) {
        if (issueType == null) {
            return null;
        }
        return issueType.getText();
    }

    @Override
    public IssueType convertToEntityAttribute(String text) {
        if (text == null) {
            return null;
        }

        return Stream.of(IssueType.values())
                .filter(c -> c.getText().equals(text))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}