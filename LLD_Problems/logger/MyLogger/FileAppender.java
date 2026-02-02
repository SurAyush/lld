package MyLogger;
import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender{
    String file_path;
    public FileAppender(String file_path){
        this.file_path  = file_path;
    }
    public void append(String s){
        try (FileWriter writer = new FileWriter(this.file_path, true)) { 
            // true = append mode
            writer.write(s);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
