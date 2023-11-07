package lab2;

import java.io.IOException;
import java.io.Writer;

abstract public class Plot extends Writer {
    abstract public void write(Result result) throws IOException;
}
