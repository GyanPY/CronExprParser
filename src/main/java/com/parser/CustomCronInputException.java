package com.parser;

/**
 * CustomCronInputException - Throw Input validation exception
 * @Constrcutor - Supplied exception message
 * @ConstrcutorWithCause - Supplied message with actual cause
 */
public class CustomCronInputException extends RuntimeException{
    public CustomCronInputException(String message) {
        super(message);
    }

    public CustomCronInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
