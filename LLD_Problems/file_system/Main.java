import FileSystem.*;

public class Main {
    public static void main(String[] args) {
        FileSystemSession session = new FileSystemSession();
        FileSystemCommands fs = new FileSystemCommands(session);

        fs.pwd();
        fs.mkdir("New Folder");
        fs.touch("code.c");
        fs.cd("./New Folder");
        fs.touch("document.pdf");
        fs.mkdir("Hidden Gems");
        fs.cp("./document.pdf", "./Hidden Gems");
        fs.cd("/");
        fs.mv("./code.c","./New Folder/Hidden Gems");
        fs.cp("./New Folder/Hidden Gems","./");
        fs.ls();
    }    
}
