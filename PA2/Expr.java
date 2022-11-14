import java.util.PriorityQueue;
import java.util.Optional;
import java.util.function.Supplier;

class Expr<T> {
    private final Supplier<T> initial;
    private final Supplier<Optional<Expr<T>>> finVal;
    private final Optional<Operator<T>> operator;

    protected Expr(T initial) {
        this.initial = () -> initial;
        this.finVal = () -> Optional.empty();
        this.operator = Optional.empty();
    }

    protected Expr(Supplier<Optional<Expr<T>>> expr, Optional<Operator<T>> operator, Supplier<T> another) {
        this.initial = another;
        this.finVal = expr;
        this.operator = operator;
    }

    protected Expr(Expr<T> expr) {
        this.initial = expr.initial;
        this.finVal = expr.finVal;
        this.operator = expr.operator;
    }

    static <T> Expr<T> of (T initial) {
        return new Expr<T>(initial);
    }

    public Expr<T> op (Operator<T> operator, T another) {
        return op(operator, () -> Optional.of(Expr.of(another)));
    }

    public Expr<T> op(Operator<T> operator, Expr<T> expression) {
        return op(operator, () -> Optional.of(Expr.of(expression.ev())));
    }

    Expr<T> op(Operator<T> oper, Supplier<Optional<Expr<T>>> a) {
        return this.operator.map(x -> x.compareTo(oper) <= 0 ?
            new Expr<T>(a, Optional.of(oper),() -> this.ev()) :
            new Expr<T>(() -> this.finVal.get().map(y -> y.op(oper, a)), this.operator,this.initial))
        .orElse(new Expr<T>(a, Optional.of(oper),initial));
    }

    public T ev() {
        return operator.map(x -> x.apply(this.initial.get(), this.finVal.get().map(y -> y.ev()).orElseThrow()))
        .orElseGet(this.initial);
    }

    @Override
    public String toString() {
        return String.format("(%s)", this.ev());
    }
}
