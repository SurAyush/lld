package MyLogger;
import java.time.LocalDateTime;

public class LogMessage {
    public String mesage;
    public LocalDateTime timestamp;
    public LogLevel level;

    LogMessage(String message,LogLevel level){
        this.level = level;
        this.mesage = message;
        this.timestamp = LocalDateTime.now();
    }
}
