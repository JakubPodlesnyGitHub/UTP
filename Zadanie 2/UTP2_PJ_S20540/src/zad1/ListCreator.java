/**
 * @author Podleśny Jakub S20540
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator<T> { // Uwaga: klasa musi być sparametrtyzowana
    List<T> lista = new ArrayList<>();
    List<T> listaSelCheked = new ArrayList<>();

    static <T> ListCreator<T> collectFrom(List<T> listaNowa) {
        ListCreator listCreator = new ListCreator();
        listCreator.lista = listaNowa;
        return listCreator;
    }

    ListCreator<T> when(Selector<T> sel) {
        for (int i = 0; i < lista.size(); i++) {
            if (sel.select(lista.get(i))) {
                listaSelCheked.add(lista.get(i));
            }
        }
        return this;
    }

    <S> List<S> mapEvery(Mapper<T, S> map) {
        List<S> finalList = new ArrayList<>();
        for (int i = 0; i < listaSelCheked.size(); i++) {
            finalList.add(map.map(listaSelCheked.get(i)));
        }
        return finalList;
    }
}  
