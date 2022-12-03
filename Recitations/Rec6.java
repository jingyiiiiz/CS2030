// question 1
// part (a)
ImList<E> limit(int n) {
    ImList<E> newList = new ImList<E>();
    n = Math.min(Math.max(n, 0), this.size());
    for (int i = 0; i < n; i++) {
        newList.elems.add(this.get(i));
    }
    return newList;
}

// question 2
// part (i)
interface Function<T,R> {
    // part (a)
    R apply(T t);
}

// part (c)
<R> ImList<R> map(Function<? super E, ? extends R> mapper) {
    ImList<R> newList = new ImList<R>();

    for (E t : this) {
        newList = newList.add(mapper.apply(t));
    }
    return newList;
}

// question 3
// part (i)
interface Predicate<T> {
    // part (a)
    boolean test(T t);
}

// part (c)
ImList<E> filter(Predicate<? super E> pred) {
    ImList<E> newList = new ImList<E>();

    for (E t : this.elems) {
        if (pred.test(t)) {
            newList = newList.add(t);
        }
    }
    return newList;
}

// part (ii)
interface Consumer<T> {
    // part (a)
    void accept(T t);
}

// part (c)
public void forEach(Consumer<? super E> consumer) {
    for (E t : this.elems) {
        consumer.accept(t);
    }
    // or simply delegating.. this.elems.forEach(consumer);
}

// part (iii)
interface BiFunction<T,U,R> {
    // part (a)
    R apply(T t, U u);
}

// part (c)
<U> U reduce(U identity, BiFunction<? super U, ? super E, ? extends U> acc) {
    for (E t : this) {
        identity = acc.apply(identity, t);
    }
    return identity;
}

// question 4
<R> ImList<R> flatMap(Function<? super E, ? extends ImList<? extends R>> mapper) {
    ImList<R> newList = new ImList<R>();
    for (E t : this) {
        newList = newList.addAll(mapper.apply(t));
    }
    return newList;
}