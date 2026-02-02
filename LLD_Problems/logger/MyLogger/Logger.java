package MyLogger;


public class Logger {
    private static Logger instance = new Logger();   // eager
    private Handler basicHandler;

    private Logger(){
        basicHandler = null;
    }

    public static Logger getInstance(){
        return instance;
    }
    
    public void log(LogLevel level, String msg){
        LogMessage logmsg = new LogMessage(msg, level);
        if(basicHandler!=null){
            basicHandler.handle(logmsg);
        }
    }

    public void setHandler(Handler handler){
        this.basicHandler = handler;
    } 
}
