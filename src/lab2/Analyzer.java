package lab2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Analyzer {
    public Result analyze(MultiReader reader, boolean countOnlyLetters, boolean ignoreCase) throws IOException {
        Map<Character, Integer> map = new HashMap<>();
        while (true) {
            int character = reader.read();
            if (character == -1)
                break;
            if (countOnlyLetters && !Character.isLetter(character))
                continue;
            if (ignoreCase && Character.isUpperCase(character))
                character = Character.toLowerCase(character);
            map.putIfAbsent((char) character, 0);
            map.put((char) character, map.get((char) character) + 1);
        }
        return new Result(map);
    }
}
