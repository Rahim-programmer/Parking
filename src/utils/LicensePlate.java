package utils;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LicensePlate {
    private static final Random RND = new Random();
    private static final List<String> numbers;
    private static final List<String> letters;
    private static String pattern = "LNNNNLL";
    private static final Set<String> issuedPlates = new HashSet<>();

    static {
        numbers = IntStream.rangeClosed(0, 9)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        letters = IntStream.range(0, 26)
                .map(e -> e + 65)
                .mapToObj(e -> Character.toString((char) e))
                .collect(Collectors.toList());
    }

    private static String makePart(String patternElement){
        switch (patternElement){
            case "N":
                return numbers.get(RND.nextInt(numbers.size()));
            case "L":
                return letters.get(RND.nextInt(letters.size()));
            default:
                return patternElement;
        }
    }

    private static String makePlateNumber(){
        StringBuilder sb = new StringBuilder();
        for (var s : pattern.split("")) {
            String makePart = makePart(s);
            sb.append(makePart);
        }
        return sb.toString();
    }

    public static String issue(){
        while (true) {
            String number = makePlateNumber();
            if (!issuedPlates.contains(number)) {
                issuedPlates.add(number);
                return number;
            }
        }
    }
}
