import java.util.concurrent.CompletableFuture;

// question 1
class A {
    private final int x;
    
    A() {
        this(0);
    }

    A(int x) {
        this.x = x;
    }

    void sleep() {
        System.out.println(Thread.currentThread().getName() + " " + x); 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("interrupted"); 
        }
    }
    
    A incr() {
        sleep();
        return new A(this.x + 1); 
    }

    A decr() {
        sleep();
        if (x < 0) {
            throw new IllegalStateException();
        }
        return new A(this.x - 1); 
    }

    public String toString() { 
        return "" + x;
    } 

    // part (a)
    // part (a)(i) using supplyAsync only
    CompletableFuture<A> foo1(A a) {
        return CompletableFuture.<A>supplyAsync(() -> a.incr().decr());
    }

    // part (a)(ii) using supplyAsync and thenApply
    CompletableFuture<A> foo2(A a) {
        return CompletableFuture.<A>supplyAsync(() -> a.incr())
            .thenApply(x -> x.decr());
    }

    // part (a)(iii) using supplyAync and thenApplyAsync
    // decr() could be run in another thread
    CompletableFuture<A> foo3(A a) {
        return CompletableFuture.<A>supplyAsync(() -> a.incr())
            .thenApplyAsync(x -> x.decr());
    }

    // to wait for the result
    CompletableFuture<A> a = foo(new A());
    // do something else
    a.join();

    // part (b)
    CompletableFuture<A> bar(A a) {
        return CompletableFuture.<A>supplyAsync(() -> a.incr());
    }
    CompletableFuture<A> b = foo(new A()).thenCompose(x -> bar(x));
    System.out.println(b.join());

    // part (c)
    CompletableFuture<A> baz(A a, int x) {
        if (x == 0) {
            return CompletableFuture.<A>completedFuture(new A(0));
        } else {
            return CompletableFuture.<A>supplyAsync(() -> a.incr().decr());
        }
    }
    CompletableFuture<A> c = baz(new A(), 1);
    System.out.println(c.join());
    /*
     * Note that CompletableFuture is a monad:
     * -> completedFuture is equivalent to of;
     * -> thenCompose is equivalent to flatMap;
     * -> thenApply is equivalent to map
     */

    // part (d)
    CompletableFuture<Void> all = CompletableFuture.<Void>allOf(
        foo(new A());
        bar(new A());
        baz(new A(), 1));
    all.join();
    System.out.println("done!");

    // part (e)
    CompletableFuture<A> exc = CompletableFuture
        .<A>supplyAsync(() -> new A().decr().decr())
        .handle((result, exception) -> {
            if (result == null) {
                System.out.println("ERROR: " + exception);
                return new A();
            } else {
                return reslt;
            }
        });
    System.out.println(exc.join());
}

// question 2
A a = new A();

// part (a)
CompletableFuture<D> cf = CompletableFuture
    .<B>supplyAsync(() -> f(a))
    .thenApply(b -> g(b))
    .thenApply(c -> h(c));
D d = cf.join();

// part (b)
// no return value, thus Void
CompletableFuture<Void> cf = CompletableFuture
    .<B>supplyAsync(() -> f(a))
    .thenApply(b -> g(b))
    .thenAccept(c -> h(c));
cf.join();

// part (c)
// fork then join, return E e
CompletableFuture<B> cfb = CompletableFuture
    .<B>supplyAsync(() -> f(a));
CompletableFuture<C> cfc = cfb
    .thenApply(b -> g(b));
CompletableFuture<D> cfd = cfb
    .thenApply(b -> h(b));
CompletableFuture<E> cfe = cfc
    .thenCombine(cfd, (c, d) -> i(c, d));
E e = cfe.join();