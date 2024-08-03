package com.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles parsing of cron input fields.
 * It processes fixed values, ranges, and intervals.
 */
public class CronInputField {
    private final String rawInput;
    private final CronInputFieldType fieldType;
    private final List<Integer> parsedValues = new ArrayList<>();

    public CronInputField(String rawInput, CronInputFieldType fieldType) throws CustomCronInputException {
        this.rawInput = rawInput;
        this.fieldType = fieldType;
        handleFixedValues();
        handleRange();
        handleIntervals();

        if (parsedValues.isEmpty()) {
            parsedValues.add(convertToInteger(rawInput));
        }
    }

    /**
     * Handles interval patterns such as /15.
     * @throws CustomCronInputException if the interval format is invalid
     */
    private void handleIntervals() throws CustomCronInputException {
        if (rawInput.startsWith("*")) {
            String[] parts = rawInput.split("/");
            if (parts.length > 2) {
                throw new CustomCronInputException("Invalid interval: " + Arrays.toString(parts));
            }

            int intervalStep = (parts.length == 2) ? convertToInteger(parts[1]) : 1;
            populateValues(fieldType.getMinimum(), fieldType.getMaximum(), intervalStep);
        }
    }

    /**
     * Handles range patterns such as 1-5.
     * @throws CustomCronInputException if the range format is invalid
     */
    private void handleRange() throws CustomCronInputException {
        String[] rangeParts = rawInput.split("-");
        if (rangeParts.length == 2) {
            int rangeStart = convertToInteger(rangeParts[0]);
            int rangeEnd = convertToInteger(rangeParts[1]);
            populateValues(rangeStart, rangeEnd, 1);
        }
    }

    /**
     * Handles fixed values such as 1,15.
     * @throws CustomCronInputException if the fixed values format is invalid
     */
    private void handleFixedValues() throws CustomCronInputException {
        String[] fixedValues = rawInput.split(",");
        if (fixedValues.length > 1) {
            for (String value : fixedValues) {
                int fixedValue = convertToInteger(value);
                populateValues(fixedValue, fixedValue, 1);
            }
        }
    }

    /**
     * Fills the parsedValues list based on the start, end, and step values.
     * @param start start value
     * @param end end value
     * @param step step value
     * @throws CustomCronInputException if validation fails
     */
    private void populateValues(int start, int end, int step) throws CustomCronInputException {
        validateValues(start, end, step);
        for (int i = start; i <= end; i += step) {
            parsedValues.add(i);
        }
    }

    /**
     * Validates the input values.
     * @param start start value
     * @param end end value
     * @param step step value
     * @throws CustomCronInputException if validation fails
     */
    private void validateValues(int start, int end, int step) throws CustomCronInputException {
        if (start < fieldType.getMinimum() || end > fieldType.getMaximum() || end < start || step <= 0) {
            throw new CustomCronInputException("Invalid input values");
        }
    }

    /**
     * Converts a string to an integer.
     * @param value string to convert
     * @return converted integer
     * @throws CustomCronInputException if conversion fails
     */
    private int convertToInteger(String value) throws CustomCronInputException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new CustomCronInputException("Invalid number format: " + value, e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int value : parsedValues) {
            sb.append(value).append(" ");
        }
        return sb.toString().trim();
    }
}