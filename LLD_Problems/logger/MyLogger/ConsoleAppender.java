package MyLogger;

public class ConsoleAppender implements Appender {
    @Override
    public void append(String logmsg){
        System.out.println(logmsg);
    }
}
