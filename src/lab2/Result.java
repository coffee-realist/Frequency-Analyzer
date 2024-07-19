package lab2;

import java.util.*;

public final class Result {
    private final List<Map.Entry<Character, Integer>> list;

    public Result(Map<Character, Integer> map) {
        this.list = new LinkedList<>(map.entrySet());
    }

    public int getMaxFrequency() {
        int max = 0;
        for (Map.Entry<Character, Integer> entry : list) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }

    public List<Map.Entry<Character, Integer>> getList() {
        return List.copyOf(list);
    }

    public Result sortByLetters() {
        list.sort(Map.Entry.comparingByKey());
        return this;
    }

    public Result sortByFrequency() {
        list.sort(Map.Entry.comparingByValue());
        return this;
    }

    public Result reverse() {
        Collections.reverse(list);
        return this;
    }

}
