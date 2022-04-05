package dades;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetFileSize {

    public static void printFileSizeNIO(String fileName) {

        Path path = Paths.get(fileName);

        try {

            // size of a file (in bytes)
            long bytes = Files.size(path);
            System.out.printf(fileName + "%,d bytes%n", bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String compareFileSizeNIO(String fileName1,String fileName2) {

        Path path1 = Paths.get(fileName1);
        Path path2 = Paths.get(fileName2);
        String menor ="";
        try {
            long bytes1 = Files.size(path1);

            long bytes2 = Files.size(path2);

            if(bytes1<bytes2){
                menor = fileName1;
            }else menor = fileName2;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return menor;
    }

}