package antipattern;


import javax.sound.sampled.*;
import java.io.IOException;
import java.io.OutputStream;

// Classe personnalisée AudioOutputStream pour créer un flux de sortie audio
class AudioOutputStream extends OutputStream {
    private final OutputStream outputStream;
    private final AudioFormat audioFormat;
    private final byte[] buffer;

    private SourceDataLine line;

    public AudioOutputStream(OutputStream outputStream, AudioFormat audioFormat) throws LineUnavailableException {
        this.outputStream = outputStream;
        this.audioFormat = audioFormat;

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(audioFormat);
        line.start();

        int bufferSize = (int) audioFormat.getSampleRate() * audioFormat.getFrameSize();
        buffer = new byte[bufferSize];
    }

    @Override
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        line.write(b, off, len);
        outputStream.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        line.stop();
        line.close();
        outputStream.close();
    }
}
