package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private T singleValue;

    public Maybe() {

    }

    public Maybe(T singleValue) {
        this.singleValue = singleValue;
    }

    static <T> Maybe<T> of(T x) {
        return new Maybe<>(x);
    }

    public void ifPresent(Consumer<T> cons) {
        if (this.singleValue != null)
            cons.accept(this.singleValue);
    }

    public <S> Maybe<S> map(Function<T, S> fuc) {
        if (this.singleValue != null)
            return new Maybe(fuc.apply(singleValue));
        else
            return new Maybe();
    }

    public T get() {
        if (this.singleValue != null)
            return this.singleValue;
        else
            throw new NoSuchElementException("Mabye is empty");
    }

    boolean isPresent() {
        if (this.singleValue != null)
            return true;
        else
            return false;
    }

    public T orElse(T defVal) {
        if (this.singleValue != null)
            return this.singleValue;
        else {
            return defVal;
        }
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if (pred.test(this.singleValue) || !isPresent()) {
            return this;
        }
        return new Maybe<>();
    }

    @Override
    public String toString() {
        if (singleValue != null)
            return "Maybe has value " + singleValue;
        else
            return "Maybe is empty";
    }
}
