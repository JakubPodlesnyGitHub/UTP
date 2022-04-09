package pakiet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class ListCreator <T> {
    List<T> lista = new ArrayList<>();
    List<T> listaSelCheked = new ArrayList<>();

    static <T> ListCreator<T> collectFrom(List<T> listaNowa) {
        ListCreator listCreator = new ListCreator();
        listCreator.lista = listaNowa;
        return listCreator;
    }

    ListCreator<T> when(Predicate<T> sel) {
        for (int i = 0; i < lista.size(); i++) {
            if(sel.test(lista.get(i))){
                listaSelCheked.add(lista.get(i));
            }
        }
        return this;
    }

    <S> List<S> mapEvery(Function<T, S> map) {
        List<S> finalList = new ArrayList<>();
        for (int i = 0; i < listaSelCheked.size(); i++) {
            finalList.add(map.apply(listaSelCheked.get(i)));
        }
        return finalList;
    }
}
