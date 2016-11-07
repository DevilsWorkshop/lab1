package sandro.programInfo;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Eugenia Bronfman on 07.11.16.
 */
public class ProgramInfo {
    String version = "unknown";

    public void getVersion() throws IOException {
        Scanner scanner;
        Path path = Paths.get("version.txt");
        try {
            scanner =  new Scanner(path);
        } catch (IOException e) {
            throw new IOException(e);
        }
        if (scanner.hasNextLine()) {
            version = scanner.nextLine();
        }
    }
}
