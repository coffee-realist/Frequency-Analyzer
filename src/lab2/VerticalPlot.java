package lab2;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class VerticalPlot extends Plot {
    private final int width, height;

    public VerticalPlot(Writer writer, int width, int height) {
        super(writer);
        this.width = width;
        this.height = height;
    }

    @Override
    public void write(Result result) throws IOException {
        writer.write(String.format("%" + width + "s\n", "-").replace(' ', '-'));
        int max = result.getMaxFrequency();
        // переменная, отвечающая за то, сколько символов будет отведено для записи частоты каждой буквы;
        int size_of_frequency_for_one_number = String.valueOf(max).length();
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
            writer.write(String.format(":  %-" + size_of_frequency_for_one_number + "d|", current_letter.getValue()));
            writer.write("█".repeat(num_of_blocks));
            writer.write('\n');
            i++;
        }
        writer.write(String.format("%" + width + "s", "-").replace(' ', '-'));
        writer.write('\n');
        close();
    }
}
