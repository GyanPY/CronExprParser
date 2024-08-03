# CronExprParser
The script was developed in java and maven was used for building and unit testing.

Building Project
You will need at least Apache Maven 3.x and JDK 1.8 to build and run the script.

To build the project, run the following command

```$ mvn clean install```


Running the script

``` java -jar deliveroo-cron-parser.jar "*/15 0 1,15 * 1-5 /usr/bin/find" ```

Result
```
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

