package com.parser;

/**
 * Enum class for provided input fields via arguments
 */
public enum CronInputFieldType {
    MINUTE(0, 59),
    HOUR(0, 23),
    DAY_OF_MONTH(1, 31),
    MONTH(1, 12),
    DAY_OF_WEEK(1, 7);


    private final int minimum;
    private final int maximum;

    CronInputFieldType(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return this.minimum;
    }

    public int getMaximum() {
        return this.maximum;
    }

}
