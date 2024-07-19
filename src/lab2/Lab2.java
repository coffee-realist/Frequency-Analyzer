package lab2;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Lab2 {
    public static void main(String[] args) throws IOException {
        int width = 80, height = 20;
        Iterator<String> iterator = Arrays.stream(args).iterator();
        Params.Builder builder = new Params.Builder();
        while (iterator.hasNext()) {
            String param = iterator.next();
            if (param.contentEquals("-v")) {
                builder.isVertical(true);
            } else if (param.contentEquals("-ol")) {
                builder.isOnlyLetters(true);
            } else if (param.contentEquals("-ic")) {
                builder.ignoreCase(true);
            } else if (param.contentEquals("-sbl")) {
                builder.sortByLetters(true);
            } else if (param.contentEquals("-sbf")) {
                builder.sortByLetters(false);
            } else if (param.contentEquals("-a")) {
                builder.isAscending(true);
            } else if (param.contentEquals("-d")) {
                builder.isAscending(false);
            } else if (param.contentEquals("-w")) {
                width = Integer.parseInt(iterator.next());
                continue;
            } else if (param.contentEquals("-h")) {
                height = Integer.parseInt(iterator.next());
                continue;
            } else if (param.contentEquals("-op")) {
                builder.setOutputFile(new File(iterator.next()));
                continue;
            }
            if (!param.startsWith("-")) {
                File file = new File(param);
                if (!file.exists())
                    throw new FileNotFoundException(String.format("File %s not found", file.getAbsolutePath()));
                builder.addInputFile(file);
            }
        }
        Params params = builder.build();
        Writer writer;
        if (params.getOutputFile() == null)
            writer = new BufferedWriter(new OutputStreamWriter(System.out));
        else
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(params.getOutputFile())));
        Analyzer analyzer = new Analyzer();
        MultiReader reader = new MultiReader(params.getInputFiles());
        Result result = analyzer.analyze(reader, params.only_letters, params.ignore_case);
        Plot plot;
        if (params.orientation)
            plot = new VerticalPlot(writer, width, height);
        else
            plot = new HorizontalPlot(writer, width, height);
        if (params.sort_by_letters)
            result.sortByLetters();
        else
            result.sortByFrequency();
        if (!params.value_orientation)
            result.reverse();
        plot.write(result);
    }
}
