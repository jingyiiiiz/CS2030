import java.util.function.Function;
import java.util.function.Predicate;
import java.lang.Runnable;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Maybe<T> {
    private final T thing;

    protected Maybe(T thing) {
        this.thing = thing;
    }

    static <U> Maybe<U> of(U thing) {
        if (thing == null) {
            throw new NullPointerException();
        }
        return new Maybe<U>(thing);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.thing == null) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.thing));
        }
    }

    @Override
    public String toString() {
        if (this.thing == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.thing + "]";
        }
    }

    static <U> Maybe<U> ofNullable(U value) {
        return new Maybe<U>(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Maybe<?> other) {
            if (this.thing != null) {
                return this.thing.equals(other.thing);
            } else {
                return (obj.toString() == "Maybe.empty");
            }
        } 
        return false;
    }

    public Maybe<T> filter(Predicate<? super T> predicate) {
        if ((this.thing != null) && (predicate.test(this.thing) == false)) {
            return new Maybe<T>(null);
        } else {
            return this;
        }
    }

    public void ifPresent(Consumer<? super T> action) {
        if (this.thing != null) {
            action.accept(this.thing);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> action, Runnable runnable) {
        if (this.thing != null) {
            action.accept(this.thing);
        } else {
            runnable.run();
        }
    }

    public T orElse(T alternative) {
        if (this.thing == null) {
            return alternative;
        } else {
            return this.thing;
        }
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        T alternative = supplier.get();
        if (this.thing == null) {
            return alternative;
        } else {
            return this.thing;
        }
    }

    public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supp) {
        if (this.thing == null) {
            return new Maybe<T>(supp.get().orElse(null));
        } else {
            return new Maybe<T>(this.thing);
        }
    }

    <R> Maybe<R> flatMap(Function<? super T,? extends Maybe<? extends R>> mapper) {
        if (this.thing == null) {
            return Maybe.<R>empty();
        } else {
            return new Maybe<R>(mapper.apply(this.thing).orElse(null));
        }
    }
}
