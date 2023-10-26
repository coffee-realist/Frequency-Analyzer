package lab2;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class VerticalPlot extends Writer {
    private final int width, height;
    private final Result result;
    private final Writer writer;

    public VerticalPlot(Result result, Writer writer, int width, int height) {
        this.width = width;
        this.height = height;
        this.result = result;
        this.writer = writer;
    }

    public void write() throws IOException {
        writer.write(String.format("%" + width + "s\n", "-").replace(' ', '-'));
        int max = result.getMaxFrequency();
        int i = 0;
        for (Map.Entry<Character, Integer> current_letter : result.getList()) {
            if (i > height - 3) break;
            int num_of_blocks = (int) ((double) current_letter.getValue() / max * (width - 12));
            num_of_blocks = (num_of_blocks == 0) ? 1 : num_of_blocks;
            writer.write(String.format("%c:  %-8d|", current_letter.getKey(), current_letter.getValue()));
            writer.write(String.format("%" + num_of_blocks + "s\n", " ").replace(' ', '█'));
            i++;
        }
        writer.write(String.format("%" + width + "s", "-").replace(' ', '-'));
        writer.write('\n');
        close();
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
