package lab2;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class VerticalPlot extends Plot{
    private final int width, height;
    private final Writer writer;

    public VerticalPlot(Writer writer, int width, int height) {
        this.width = width;
        this.height = height;
        this.writer = writer;
    }
    @Override
    public void write(Result result) throws IOException {
        writer.write(String.format("%" + width + "s\n", "-").replace(' ', '-'));
        int max = result.getMaxFrequency();
        int i = 0;
        for (Map.Entry<Character, Integer> current_letter : result.getList()) {
            if (i > height - 3) break;
            int num_of_blocks = (int) ((double) current_letter.getValue() / max * (width - 12));
            num_of_blocks = (num_of_blocks == 0) ? 1 : num_of_blocks;
            if (current_letter.getKey() == '\n')
                writer.write('¶');
            else if (current_letter.getKey() == '\r')
                writer.write('◄');
            else if (current_letter.getKey() == '\t')
                writer.write('⇥');
            else
                writer.write(current_letter.getKey());
            writer.write(String.format(":  %-8d|", current_letter.getValue()));
            writer.write("█".repeat(num_of_blocks));
            writer.write('\n');
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
