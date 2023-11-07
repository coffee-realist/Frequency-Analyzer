package lab2;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Params {
    private final File[] input_files;
    public final File output_file;
    public final boolean only_letters;
    public final boolean orientation;
    public final boolean ignore_case;
    public final boolean value_orientation;
    public final boolean sort_by_letters;

    public Params(File[] inputFiles, File output_file, boolean orientation, boolean only_letters,
                  boolean ignore_case, boolean sort_by_letters, boolean value_orientation) {
        this.input_files = inputFiles;
        this.output_file = output_file;
        this.orientation = orientation;
        this.only_letters = only_letters;
        this.ignore_case = ignore_case;
        this.sort_by_letters = sort_by_letters;
        this.value_orientation = value_orientation;
    }

    public File[] getInputFiles() {
        return Arrays.copyOf(input_files, input_files.length);
    }

    public File getOutputFile() {
        return output_file;
    }

    static class Builder {
        private final ArrayList<File> input_files = new ArrayList<>();
        private File output_file = null;
        private boolean only_letters = false;
        private boolean orientation = false;
        private boolean ignore_case = false;
        private boolean value_orientation = false;
        private boolean sort_by_letters = false;

        public Builder addInputFile(File inputFile) {
            input_files.add(inputFile);
            return this;
        }

        public Builder setOutputFile(File output_file) {
            this.output_file = output_file;
            return this;
        }

        public Builder isVertical(boolean b) {
            this.orientation = b;
            return this;
        }

        public Builder isOnlyLetters(boolean b) {
            this.only_letters = b;
            return this;
        }

        public Builder ignoreCase(boolean b) {
            this.ignore_case = b;
            return this;
        }

        public Builder sortByLetters(boolean b) {
            this.sort_by_letters = b;
            return this;
        }

        public Builder isAscending(boolean b) {
            this.value_orientation = b;
            return this;
        }

        public Params build() {
            if (input_files.isEmpty()) throw new IllegalArgumentException("No files");
            return new Params(input_files.toArray(new File[0]), output_file, orientation, only_letters,
                    ignore_case, sort_by_letters, value_orientation);
        }
    }
}
