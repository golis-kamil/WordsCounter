import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.*;

/**
 * Created by trot on 20.12.16.
 */
public class WordsCounter {

    public static void main(String[] args) throws FileNotFoundException {

        Map<String, Integer> words = null;
        ClassLoader cl = WordsCounter.class.getClassLoader();

        Integer count = 0;
        words = new HashMap<String, Integer>();

        try {
            File file = new File(cl.getResource("pg100.txt").getFile());
            Scanner input = new Scanner(file);
            input.useDelimiter("\\W+");

            while (input.hasNext()) {
                String line = input.next().toLowerCase();
                count = words.getOrDefault(line, 0);
                count += 1;
                words.put(line, count);

            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("I found top words :");

        words.entrySet().stream()
//                za pomocą funkcji
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
//                lub za pomocą komparatora
//                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(20)
                .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue() + " times"));
    }
}



