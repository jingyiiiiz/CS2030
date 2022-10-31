import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

class Num extends AbstractNum<Integer> {
    private Num(AbstractNum<Integer> abstractNum) {
        super(abstractNum.opt);
    }

    public Num(Optional<Integer> opt) {
        super(opt);
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    static Num of(int i) {
        if (AbstractNum.valid.test(i)) {
            return new Num(new AbstractNum<Integer>(i));
        } else {
            return new Num(new AbstractNum<Integer>(Optional.empty()));
        }
    }

    static Num one() {
        return Num.zero().succ();
    }

    public Num succ() {
        return new Num(this.opt.map(AbstractNum.s));
    }

    public Num add(Num another) {
        if (this.isValid() && another.isValid()) {
            if (another.equals(Num.zero())) {
                return this;
            } else {
                Num check = Num.zero();
                Num result = this;
                while (!check.equals(another)) {
                    check = check.succ();
                    result = result.succ();
                }
                return result;
            }
        } else if (!this.isValid()) {
            return this;
        } else {
            return another;
        }
    }

    public Num mul(Num another) {
        if (!this.isValid()) {
            return this;
        } else if (!another.isValid()) {
            return another;
        } else if (this.equals(Num.zero())) {
            return this;
        } else if (another.equals(Num.zero())) {
            return another;
        } else {
            Num check = Num.zero();
            Num result = Num.zero();
            while (!check.equals(this)) {
                check = check.succ();
                result = result.add(another);
            }
            return result;
        }
    }

    public Num sub(Num another) {
        if (this.isValid() && another.isValid()) {
            if (another.equals(Num.zero())) {
                return this;
            } else {
                Num negative = new Num(another.opt.map(AbstractNum.n));
                Num result = negative.add(this);
                if (result.opt.filter(AbstractNum.valid).equals(Optional.empty())) {
                    return new Num(Optional.empty());
                } else {
                    return result;
                }
            }
        } else if (!this.isValid()) {
            return this;
        } else {
            return another;
        }
    }


}
