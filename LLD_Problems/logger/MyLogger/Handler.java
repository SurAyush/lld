package MyLogger;
import java.util.List;
import java.util.function.Predicate;


public class Handler {
    private Handler next;
    List<Output> outputs;
    Predicate<LogMessage> predicate;

    public Handler(List<Output> outputs,Predicate<LogMessage> predicate){
        this.outputs = outputs;
        this.predicate = predicate;
        this.next=null;
    }

    void handle(LogMessage logmsg){
        if(predicate.test(logmsg)){
            for (Output output: outputs){
                output.appender.append(output.formatter.format(logmsg));
            }
        }
        else if(next!=null){
            next.handle(logmsg);
        }
    }

    public void setNextHandler(Handler next){
        this.next = next;
    }
}
