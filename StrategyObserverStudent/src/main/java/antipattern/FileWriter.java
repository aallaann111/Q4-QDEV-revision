package antipattern;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter {
    public static void clearFolder(File folder) {

        File[] files = folder.listFiles();

        for (File file : files) {

            file.delete();

        }

    }

    public static void writeToFile(String fileName, String content) {

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(fileName, true); // true pour écrire à la fin du fichier
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.close();
            System.out.println("Contenu écrit dans le fichier:" + fileName);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
}
