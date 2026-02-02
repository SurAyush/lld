package MyLogger;

public class PlainTextFormatter implements Formatter {
    @Override
    public String format(LogMessage logmsg){
        String out = "["+logmsg.timestamp+"] ["+logmsg.level+"] : " + logmsg.mesage;
        return out;
    }
}
