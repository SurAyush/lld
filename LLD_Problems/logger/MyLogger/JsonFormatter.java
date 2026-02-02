package MyLogger;

public class JsonFormatter implements Formatter {

    @Override
    public String format(LogMessage logmsg) {
        return "{"
                + "\"timestamp\":\"" + escape(String.valueOf(logmsg.timestamp)) + "\","
                + "\"level\":\"" + escape(String.valueOf(logmsg.level)) + "\","
                + "\"message\":\"" + escape(logmsg.mesage) + "\""
                + "}";
    }

    private String escape(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n");
    }
}
