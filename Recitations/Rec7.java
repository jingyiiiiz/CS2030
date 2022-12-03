import java.util.function.Predicate;

// question 1
// using lambda
<T> Predicate<T> and(Predicate<T> p1, Predicate<T> p2) {
    return x -> p1.test(x) && p2.test(x);
}

// using anonymous class
<T> Predicate<T> and(Predicate<T> p1, Predicate<T> p2) {
    public boolean test(T x) {
        return p1.test(x) && p2.test(x);
    }
}

// question 3
// part (a)
abstract class Func {
    abstract int apply(int x);

    // part (b)
    Func compose(Func other) {
        return new Func() {
            int apply(int x) {
                return Func.this.apply(other.apply(x));
            }
        };
    }

    // part (c)
    <U> Func<U,R> compose(Func<U,T> other) {
        return new Func<U,R> () {
            R apply(U x) {
                return Func.this.apply(other.apply(x));
            }
        };
    }
}

Func f = new Func() {
    int apply(int x) {
        return 2 * x;
    }
}

Func g = new Func() {
    int apply(int x) {
        return 2 + x;
    }
}

f.apply(10);
g.apply(10);

