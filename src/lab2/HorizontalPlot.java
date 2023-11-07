package lab2;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class HorizontalPlot extends Plot{
    private final int width, height;
    private final Writer writer;

    public HorizontalPlot(Writer writer, int width, int height) {
        this.width = width;
        this.height = height;
        this.writer = writer;
    }

    @Override
    public void write(Result result) throws IOException {
        writer.write(String.format("%" + width + "s__\n", "_").replace(' ', '_'));
        int max = result.getMaxFrequency();
        int[] frequencies = new int[height + 1];
        int[] positions = new int[width - 10];
        int i = 0;
        for (Map.Entry<Character, Integer> current_letter : result.getList()) {
            if (i > width - 11) break;
            int num_of_blocks = (int) ((double) current_letter.getValue() / max * (height - 1)) + 1;
            frequencies[num_of_blocks] = current_letter.getValue();
            positions[i] = num_of_blocks;
            i++;
        }

        for (i = 0; i < height; i++) {
            writer.write(String.format("%-10d|", frequencies[height - i]).replace("0      ", "       "));
            for (int j = 0; j < width - 10; j++) {
                if (positions[j] >= height - i)
                    writer.write('█');
                else writer.write(' ');
            }
            writer.write("|\n");
        }
        writer.write("----------|");
        i = 0;
        int num_of_chars = 0;
        for (Map.Entry<Character, Integer> current_letter : result.getList()) {
            if (i > width-11) break;
            if (current_letter.getKey() == '\n')
                writer.write('¶');
            else if (current_letter.getKey() == '\r')
                writer.write('◄');
            else if (current_letter.getKey() == '\t')
                writer.write('⇥');
            else
                writer.write(current_letter.getKey());
            i++;
            num_of_chars++;
        }
        writer.write(String.format("|%s", "-".repeat(width - 10 - num_of_chars)));
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
