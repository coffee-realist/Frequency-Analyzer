package lab2;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class HorizontalPlot extends Writer {
    private final int width, height;
    private final Result result;
    private final Writer writer;

    public HorizontalPlot(Result result, Writer writer, int width, int height) {
        this.width = width;
        this.height = height;
        this.result = result;
        this.writer = writer;
    }

    public void write() throws IOException {
        writer.write(String.format("%" + width + "s__\n", "_").replace(' ', '_'));
        int max = result.getMaxFrequency();
        int[] frequencies = new int[height + 1];
        int[] positions = new int[width - 6];
        StringBuilder chars = new StringBuilder();
        int i = 0;
        for (Map.Entry<Character, Integer> current_letter : result.getList()) {
            if (i > width - 7) break;
            int num_of_blocks = (int) ((double) current_letter.getValue() / max * (height - 1)) + 1;
            frequencies[num_of_blocks] = current_letter.getValue();
            positions[i] = num_of_blocks;
            chars.append(current_letter.getKey());
            i++;
        }

        for (i = 0; i < height; i++) {
            writer.write(String.format("%-6d|", frequencies[height - i]).replace("0    ", "     "));
            for (int j = 0; j < width - 6; j++) {
                if (positions[j] >= height - i)
                    writer.write('█');
                else writer.write(' ');
            }
            writer.write("|\n");
        }
        writer.write(String.format("------|%s|%s", chars, "-".repeat(width - 6 - chars.length())));
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
