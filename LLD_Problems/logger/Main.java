import MyLogger.*;
import java.util.*;
import java.util.function.Predicate;

import MyLogger.Formatter;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        
        Appender console = new ConsoleAppender();
        Appender text_file = new FileAppender("log.txt");
        Appender json_file = new FileAppender("log.jsonl");
        Formatter text = new PlainTextFormatter();
        Formatter json = new JsonFormatter();

        Output output1 = new Output(console, text);
        Output output2 = new Output(text_file, text);
        Output output3 = new Output(json_file, json);

        Predicate<LogMessage> predicate1 = logmsg -> logmsg.level == LogLevel.DEBUGGING;
        Predicate<LogMessage> predicate2 = logmsg -> logmsg.level == LogLevel.INFO || logmsg.level == LogLevel.WARNING;
        Predicate<LogMessage> predicate3 = logmsg -> logmsg.level == LogLevel.ERROR || logmsg.level == LogLevel.FATAL;

        Handler handler1 = new Handler(new ArrayList<>(List.of(output1)), predicate1);
        Handler handler2 = new Handler(new ArrayList<>(List.of(output2,output1)), predicate2);
        Handler handler3 = new Handler(new ArrayList<>(List.of(output3,output1)), predicate3);

        logger.setHandler(handler1);
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        logger.log(LogLevel.DEBUGGING, "Debugging Log");
        logger.log(LogLevel.INFO, "Info Log");
        logger.log(LogLevel.WARNING, "Warning Log");
        logger.log(LogLevel.ERROR, "Error Log");
        logger.log(LogLevel.FATAL, "FATAL Log");
        logger.log(LogLevel.DEBUGGING, "Debugging Log 2");
    }
}
