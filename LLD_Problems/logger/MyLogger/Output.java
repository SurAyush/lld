package MyLogger;

public class Output {
    Appender appender;
    Formatter formatter;

    public Output(Appender appender, Formatter formatter){
        this.appender = appender;
        this.formatter = formatter;
    }
}
