package lab2;

import java.io.*;

public class MultiReader extends Reader {
    private final Reader[] readers;
    private int reader_index = 0;

    public MultiReader(Reader[] readers) {
        this.readers = readers;
    }

    public MultiReader(File[] files) throws FileNotFoundException {
        FileReader[] readers = new FileReader[files.length];
        for (int i = 0; i < files.length; i++)
            readers[i] = new FileReader(files[i]);
        this.readers = readers;
    }

    public int read() throws IOException {
        if (reader_index >= readers.length) return -1;
        int next = readers[reader_index].read();
        if (next != -1) {
            return next;
        } else if (++reader_index < readers.length) {
            readers[reader_index - 1].close();
            return readers[reader_index].read();
        } else {
            readers[reader_index - 1].close();
            return -1;
        }
    }

    @Override
    public int read(char[] c_buf, int off, int len) throws IOException {
        return this.read();
    }

    @Override
    public void close() throws IOException {
        for (Reader reader : readers) reader.close();
    }
}
