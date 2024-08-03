package com.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CronInputFieldTest {


    @Test
    public void testFixedValues() {
        try {
            CronInputField field = new CronInputField("1,15", CronInputFieldType.MINUTE);
            assertEquals("1 15", field.toString());
        } catch (CustomCronInputException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testRangeValues() {
        try {
            CronInputField field = new CronInputField("1-5", CronInputFieldType.MINUTE);
            assertEquals("1 2 3 4 5", field.toString());
        } catch (CustomCronInputException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testIntervals() {
        try {
            CronInputField field = new CronInputField("*/15", CronInputFieldType.MINUTE);
            assertEquals("0 15 30 45", field.toString());
        } catch (CustomCronInputException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testSingleValue() {
        try {
            CronInputField field = new CronInputField("7", CronInputFieldType.MINUTE);
            assertEquals("7", field.toString());
        } catch (CustomCronInputException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testInvalidInterval() {
        assertThrows(CustomCronInputException.class, () -> {
            new CronInputField("*/15/20", CronInputFieldType.MINUTE);
        });
    }

    @Test
    public void testInvalidRange() {
        assertThrows(CustomCronInputException.class, () -> {
            new CronInputField("5-1", CronInputFieldType.MINUTE);
        });
    }

    @Test
    public void testInvalidNumber() {
        assertThrows(CustomCronInputException.class, () -> {
            new CronInputField("a", CronInputFieldType.MINUTE);
        });
    }

}