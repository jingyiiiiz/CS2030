import java.lang.Comparable;
import java.util.function.BinaryOperator;

class Operator<T> implements Comparable<Operator<T>> {
    private final BinaryOperator<T>  biOp;
    private final int preVal;

    protected Operator(BinaryOperator<T> biOp, int preVal) {
        this.biOp = biOp;
        this.preVal = preVal;
    }

    static <T> Operator<T> of(BinaryOperator<T> operator, int pre) {
        return new Operator<T>(operator, pre);
    }

    @Override
    public String toString() {
        return String.format("Operator of precedence %d", this.preVal);
    }

    public T apply(T first, T second) {
        return this.biOp.apply(first, second);
    }

    public int compareTo(Operator<T> other) {
        if (this.preVal > other.preVal) {
            return 1;
        } else if (this.preVal < other.preVal) {
            return -1;
        } else {
            return 0;
        }
    }

}
