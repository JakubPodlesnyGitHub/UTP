package zad3;

import java.util.function.Function;

public class InputConverter<T> {
    private T fileName;

    public InputConverter(T fileName) {
        this.fileName = fileName;
    }

    public <R> R convertBy(Function <?,?> ... functionTAB){

        Object object = fileName;
        for (Function<?,?> function : functionTAB){
            object = ((Function<Object,Object>)function).apply(object);
        }
        return (R) object;
    }
}
