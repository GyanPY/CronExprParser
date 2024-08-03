package com.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CronExpressionParserTest {

    @Test
    public void testValidCronExpression() {
        try {
            String input = "*/15 0 1,15 * 1-5 /usr/bin/find";
            CronExpressionParser parser = new CronExpressionParser(input);
            String expectedOutput = String.format(
                    "%-14s %s%n%-14s %s%n%-14s %s%n%-14s %s%n%-14s %s%n%-14s %s",
                    "minute", "0 15 30 45",
                    "hour", "0",
                    "day of month", "1 15",
                    "month", "1 2 3 4 5 6 7 8 9 10 11 12",
                    "day of week", "1 2 3 4 5",
                    "command", "/usr/bin/find"
            );
            assertEquals(expectedOutput, parser.toString());
        } catch (CustomCronInputException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testInvalidCronExpressionLength() {
        String input = "*/15 0 1,15 * 1-5";
        assertThrows(CustomCronInputException.class, () -> {
            new CronExpressionParser(input);
        });
    }

    @Test
    public void testInvalidCronExpressionField() {
        String input = "*/15 0 1,15 * 1-5a /usr/bin/find";
        assertThrows(CustomCronInputException.class, () -> {
            new CronExpressionParser(input);
        });
    }

}