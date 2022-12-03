import java.util.function.Function;

// question 1
class Log<T> {
    private final T t; 
    private final String log;

    private Log(T t, String log) { 
        this.t = t;
        this.log = log;
    }

    // part (a) defining of method
    static <T> Log<T> of(T t, String log) { 
        return new Log<T>(t, "\n" + log);
    }

    static <T> Log<T> of(T t) { 
        return new Log<T>(t, "");
    }

    // part (b) defining map method
    <R> Log<R> map(Function<? super T, ? extends R> mapper) { 
        return new Log<R>(mapper.apply(this.t), this.log);
    }

    // part (d) defining flatMap method
    <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) { 
        Log<? extends R> logr = mapper.apply(t);
        return new Log<R>(logr.t, this.log + logr.log);
    }

    // part (c) demonstrating identity and associativity of map
    // part (e) demonstrating identity and associativity of flatMap
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Log<?> other) { 
            return this.t.equals(other.t);
        } else {
            return false;
        } 
    }

    // part (f) defining sum method
    Log<Integer> sum(int n) {
        if (n == 0) {
            return Log.of(0, "hit base case!"); 
        } else {
            return sum(n - 1).flatMap(x -> Log.of(n + x, "adding " + n));
        }
    }

    // part (g) defining f method
    Log<Integer> f(int n) {
        if (n == 1) {
            return Log.<Integer>of(1, "one");
        } else if (n % 2 == 0) {
            return Log.<Integer>of(n / 2, n + " / 2")
                .flatMap(x -> f(x));
        } else {
            return Log.<Integer>of(3 * n + 1, "3(" + n + ") + 1")
                .flatMap(x -> f(x));
        }
    }

    @Override
    public String toString() {
        return "Log[" + this.t + "]" + this.log; 
    }
}
