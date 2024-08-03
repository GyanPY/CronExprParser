package com.parser;

/**
 *   CronExpressionParser represent a cron string with all separate type:
 *   [minute] [hour] [day of month] [day of week] [command]
 *   This class validate the supplied input and format the input
 *   as required to be output and return via toString method
 */
public class CronExpressionParser {
    private static final int NUMBER_OF_ARGUMENTS = 6;

    private final CronInputField minute;
    private final CronInputField hour;
    private final CronInputField dayOfMonth;
    private final CronInputField month;
    private final CronInputField dayOfWeek;
    private final String command;

    /**
     * Function does basic input check and convert it to
     * returned output format
     * @param input supplied argument
     */
    public CronExpressionParser(String input) {
        String[] cronParts = input.split("\\s+");
        if (cronParts.length != NUMBER_OF_ARGUMENTS) {
            throw new CustomCronInputException("Supplied arguments are in invalid format");
        }

        minute = new CronInputField(cronParts[0], CronInputFieldType.MINUTE);
        hour = new CronInputField(cronParts[1], CronInputFieldType.HOUR);
        dayOfMonth = new CronInputField(cronParts[2], CronInputFieldType.DAY_OF_MONTH);
        month = new CronInputField(cronParts[3], CronInputFieldType.MONTH);
        dayOfWeek = new CronInputField(cronParts[4], CronInputFieldType.DAY_OF_WEEK);
        command = cronParts[5];

    }

    /**
     * @return String format of several parts of
     * cron expression
     */
    @Override
    public String toString() {
        return String.format("%-14s %s%n%-14s %s%n%-14s %s%n%-14s %s%n%-14s %s%n%-14s %s",
                "minute", minute.toString(),
                "hour", hour.toString(),
                "day of month", dayOfMonth.toString(),
                "month", month.toString(),
                "day of week", dayOfWeek.toString(),
                "command", command);
    }
}
