import java.util.function.Supplier;

// question 1
class A {
    private final int x;
    private final boolean isPositive;

    private A(int x, boolean isPositive) { 
        this.x = x;
        this.isPositive = isPositive; 
    }

    static A of(int x) { 
        return new A(x, x > 0);
    }
    
    A foo(Function<Integer, A> map) { 
        return map.apply(this.x);
    }
    
    A bar(Function<Integer, A> map) { 
        if (this.isPositive) {
            return map.apply(this.x);
        } else {
            return A.of(this.x);
        }
    }
}

// question 2
interface Compute<T> {
    boolean isRecursive();

    Compute<T> recurse();

    T evaluate();
}

class Base<T> implements Compute<T> {
    private final Supplier<T> supplier;

    Base(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public boolean isRecursive() {
        return false;
    }

    public Compute<T> recurse() {
        throw new IllegalStateException();
    }

    public T evaluate() {
        return this.supplier.get();
    }
}

class Recursive<T> implements Compute<T> {
    private final Supplier<Compute<T>> supplier;

    Recursive(Supplier<Compute<T>> supplier) {
        this.supplier = supplier;
    }

    public boolean isRecursive() {
        return true;
    }

    public Compute<T> recurse() {
        return this.supplier.get();
    }

    public T evaluate() {
        throw new IllegalStateException("Evaluating a recursive case");
    }
}

long evaluate(Compute<Long> compute) {
    while (compute.isRecursive()) {
        compute = compute.recurse();
    }
    return compute.evaluate();
}

Compute<Long> sum(long n, long s) {
    if (n == 0) {
        return new Base<Long>(() -> s);
    } else {
        return new Recursive<Long>(() -> sum(n - 1, n + s));
    }
}

long sum(long n) {
    return evaluate(sum(n, 0));
}

Compute<Long> factorial(long n, long s) {
    if (n == 0) {
        return new Base<Long>(() -> s);
    } else {
        return new Recursive<Long>(() -> factorial(n - 1, n * s));
    }
}

long factorial(long n) {
    return evaluate(factorial(n, 1));
}