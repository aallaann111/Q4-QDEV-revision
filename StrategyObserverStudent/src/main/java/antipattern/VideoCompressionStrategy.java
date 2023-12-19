package antipattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VideoCompressionStrategy implements DataCompressionStrategy {
    @Override
    public String compress(Data data) {
        if (data instanceof VideoData) {
            String result = "./data/compressed/mp4/c-" + data.getFile().getName();
            try {
                FileInputStream inputStream = new FileInputStream(data.getFile().getAbsolutePath());

                FileOutputStream outputStream = new FileOutputStream(result);


                byte[] buffer = new byte[4096];
                int length;

                while (true) {

                    if (!((length = inputStream.read(buffer)) > 0)) break;
                    outputStream.write(buffer, 0, length);


                }


                inputStream.close();

                outputStream.close();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateVideo(result);
            return result;
        }
        return "";
    }

    public void updateVideo(String fileName) {
        // Code d'observation pour les videos
        if (fileName.contains("/mp4/")) {
            LocalTime now = LocalTime.now();


            // Formater l'heure dans le format souhaité (hh:mm:ss)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = now.format(formatter);
            String content = formattedTime;


            System.out.println("-----------------------------------------------------------------------");
            System.out.println("Observation des audios...");

            content += " ::Fichier compressé : " + fileName;
            FileWriter.writeToFile("./data/compressed/mp4/VideoCompressionObserver.txt", content);
            System.out.println("-----------------------------------------------------------------------");
        }
    }

}