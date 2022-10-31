import java.util.Optional;

class Fraction extends AbstractNum<Frac> {
    private Fraction(AbstractNum<Frac> fraction) {
        super(fraction.opt);
    }

    private Fraction(Optional<Frac> opt) {
        super(opt);
    }

    static Fraction of(int n, int d) {
        Num numerator = Num.of(n);
        Num denominator = Num.of(d);
        if (!numerator.isValid() || 
            !denominator.isValid()) {
            return new Fraction(Optional.empty());
        } else if (denominator.equals(Num.zero())) {
            return new Fraction(Optional.empty());
        } else {
            Frac frac = Frac.of(numerator, denominator);
            return new Fraction(new AbstractNum<Frac>(frac));
        }
    }

    public Fraction add(Fraction another) {
        if (this.equals(new Fraction(Optional.empty())) ||
            another.equals(new Fraction(Optional.empty()))) {
            return new Fraction(Optional.empty());
        } else {
            Num a = new Num(this.opt.map(x -> x.first()).flatMap(x -> x.opt));
            Num b = new Num(this.opt.map(x -> x.second()).flatMap(x -> x.opt));
            Num c = new Num(another.opt.map(x -> x.first()).flatMap(x -> x.opt));
            Num d = new Num(another.opt.map(x -> x.second()).flatMap(x -> x.opt));
            Num ad = a.mul(d);
            Num bc = b.mul(c);
            Num bd = b.mul(d);
            Num adbc = ad.add(bc);
            Frac result = Frac.of(adbc, bd);
            return new Fraction(new AbstractNum<Frac>(result));
        }
    }

    public Fraction sub(Fraction another) {
        if (this.equals(new Fraction(Optional.empty())) ||
            another.equals(new Fraction(Optional.empty()))) {
            return new Fraction(Optional.empty());
        } else {
            Num a = new Num(this.opt.map(x -> x.first()).flatMap(x -> x.opt));
            Num b = new Num(this.opt.map(x -> x.second()).flatMap(x -> x.opt));
            Num c = new Num(another.opt.map(x -> x.first()).flatMap(x -> x.opt));
            Num d = new Num(another.opt.map(x -> x.second()).flatMap(x -> x.opt));
            Num ad = a.mul(d);
            Num bc = b.mul(c);
            Num bd = b.mul(d);
            Num adbc = ad.sub(bc);
            if (!adbc.isValid() || !bd.isValid()) {
                return new Fraction(Optional.empty());
            } else {
                Frac result = Frac.of(adbc, bd);
                return new Fraction(new AbstractNum<Frac>(result));
            }
        }
    }

    public Fraction mul(Fraction another) {
        if (this.equals(new Fraction(Optional.empty())) ||
            another.equals(new Fraction(Optional.empty()))) {
            return new Fraction(Optional.empty());
        } else {
            Num a = new Num(this.opt.map(x -> x.first()).flatMap(x -> x.opt));
            Num b = new Num(this.opt.map(x -> x.second()).flatMap(x -> x.opt));
            Num c = new Num(another.opt.map(x -> x.first()).flatMap(x -> x.opt));
            Num d = new Num(another.opt.map(x -> x.second()).flatMap(x -> x.opt));
            Num ac = a.mul(c);
            Num bd = b.mul(d);
            Frac result = Frac.of(ac, bd);
            return new Fraction(new AbstractNum<Frac>(result));
        }
    }
    
}
