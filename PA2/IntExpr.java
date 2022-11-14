import java.util.stream.IntStream;
class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> subtraction = 
        Operator.<Integer>of((x,y) -> x-y, 4);
    private static final Operator<Integer> division = 
        Operator.<Integer>of((x,y) -> x/y, 3);
    private static final Operator<Integer> expo = 
        Operator.<Integer>of((x,y) -> IntStream.range(0,y).reduce(1,(z,a)->(z*x)), 2);
    
    protected IntExpr(Integer value) {
        super(new Expr<Integer>(value));
    }

    IntExpr(Expr<Integer> ex) {
        super(ex);
    }

    static IntExpr of(Integer i) {
        return new IntExpr(i);
    }

    public IntExpr add(Integer other) {
        return new IntExpr(this.op(AbstractIntExpr.addition, other));
    }

    public IntExpr mul(Integer other) {
        return new IntExpr(this.op(AbstractIntExpr.multiplication, other));
    }

    public IntExpr sub(Integer other) {
        return new IntExpr(this.op(IntExpr.subtraction, other));
    }

    public IntExpr div(Integer other) {
        return new IntExpr(this.op(IntExpr.division, other));
    }

    public IntExpr exp(Integer other) {
        return new IntExpr(this.op(IntExpr.expo, other));
    }
}
