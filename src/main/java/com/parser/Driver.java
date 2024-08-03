package com.parser;

/**
 * Driver class to run the script via supplied arguments
 */
public class Driver {

    public static void main(String[] args) {
        validateArgs(args);
        try {
            CronExpressionParser inpurExpression = new CronExpressionParser(args[0]);
            System.out.println(inpurExpression);

        } catch (CustomCronInputException exception) {
            System.out.println(exception.getMessage());
        }

    }

    /**
     * Validating the arguments passed via command line
     * @param args command line arguments
     */
    private static void validateArgs(String[] args) {
        if (args.length != 1) {
            throw new CustomCronInputException("Invalid Input, Only One Argument is required" + args[0]);
        }
    }
}
