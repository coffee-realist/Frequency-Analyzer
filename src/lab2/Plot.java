package lab2;

import java.io.IOException;
import java.io.Writer;

abstract public class Plot extends Writer {
    abstract public void write(Result result) throws IOException;

    protected final Writer writer;

    public Plot(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void write(char[] c_buf, int off, int len) throws IOException {
        writer.write(c_buf, off, len);
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
