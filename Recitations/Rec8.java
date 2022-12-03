import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.math.BigInteger;

// question 1
// prime checker
boolean isPrime(int n) {
    return n > 1 && IntStream
        .range(2, n)
        .nonMatch(x -> n % x == 0);
}

// extract factors
IntStream factors(int x) {
    return IntStream
        .rangeClosed(1, x)
        .filter(d -> x % d == 0);
}

// extract prime factors
IntStream primeFactorsOf(int x) {
    return factors(x)
        .filtered(d -> isPrime(d));
}

// find omega numbers
LongStream omega(int n) {
    return IntStream
        .rangeClosed(1, n)
        .mapToLong(x -> primeFactorsOf(x).count());
}

omega(10).forEach(x -> System.out.println(x + " "));

// question 2
class Pair<T, U> {
    private final T t;
    private final U u;

    Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    T first() {
        return this.t;
    }

    U second() {
        return this.u;
    }
}

// implementation of fibonacci
Stream<BigInteger> fibonacci(int n) {
    Pair<BigInteger, BigInteger> startPair = 
        new Pair<BigInteger, BigInteger>(BigInteger.ZERO, BigInteger.ONE);
    
    UnaryOperator<Pair<BigInteger, BigInteger>> nextPair = pr ->
        new Pair<BigInteger, BigInteger>(pr.second(),
            pr.first().add(pr.second()));
    
    return Stream.iterate(startPair, nextPair)
        .map(pr -> pr.second())
        .limit(n);
}

// question 3
<T,U,R> Stream<R> product(List<? extends T> list1, 
        List<? extends U> list2,
        BiFunction<? super T, ? super U, ? extends R> func) {
            return list1.stream()
                .flatMap(x -> list2.stream()
                        .map(y -> func.apply(x, y)));
        }
