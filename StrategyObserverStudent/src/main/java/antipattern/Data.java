package antipattern;

import java.io.File;

abstract class Data {
    private File file;

    public Data(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

}
