import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.UnaryOperator;

public class Main {
    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(1, n)
            .filter(x -> isPrime(x))
            .filter(x -> isPrime(x - 2) || isPrime(x + 2));
    }

    static boolean isPrime(int n) {
        return n > 1 && IntStream
            .range(2, n)
            .noneMatch(x -> n % x == 0);
    }

    static String reverse(String str) {
        return Stream.<String>of(str.split(""))
            .reduce("", (x, y) -> y + x);
    }

    static long countRepeats(List<Integer> list) {
        return IntStream.range(0, list.size() - 1)
            .filter(x -> 
                list.get(x) == list.get(x + 1) && (
                    (x + 2 >= list.size()) || 
                    (list.get(x) != list.get(x + 2))
                )
            )
            .count();
    }

    static UnaryOperator<List<Integer>> generateRule() {
        return list -> IntStream.range(0, list.size())
         .map(x -> Main.nextGeneration(x, list))
         .boxed()
         .toList();
    }

    static int nextGeneration(int x, List<Integer> list) {
        if (list.get(x) == 1) {
            return 0;
        } else if (x == 0) {
            if (list.get(1) == 1) {
                return 1;
            } else {
                return 0;
            }
        } else if (x == list.size() - 1) {
            if (list.get(x - 1) == 1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if ((list.get(x - 1) == 1 && list.get(x + 1) == 0) || 
                 (list.get(x - 1) == 0 && list.get(x + 1) == 1)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static String converter(List<Integer> list) {
        return list.stream()
          .map(x -> {
              if (x == 0) {
                  return ".";
              } else {
                  return "x";
              }
          }
          )
          .reduce("", (x, y) -> x + y);
    }

    static Stream<String> gameOfLife(List<Integer> list, 
            UnaryOperator<List<Integer>> rule, int n) {
        return Stream.iterate(list, x -> rule.apply(x))
            .limit(n)
            .map(x -> Main.converter(x));
    }
}
