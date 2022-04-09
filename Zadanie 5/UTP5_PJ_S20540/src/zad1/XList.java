package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XList<T> extends ArrayList<T> {
    ///////////////KONSTRUKTORY////////////////////////////
    public XList(Collection<T> collection) {
        this.addAll(collection);
    }

    public XList(T... arrayTMP) {
        this.addAll(Arrays.asList(arrayTMP));
    }

    ///////////////////METODY OF////////////////////////////
    static <T> XList<T> of(Collection<T> collection) {
        return new XList<>(collection);
    }

    static <T> XList<T> of(T... arrayTMP) {
        return new XList<>(arrayTMP);
    }

    ////////////METODY CHARS OF I TOKEN OF//////////////////////
    static <T> XList<T> charsOf(String s) {
        List<Character> newListTMP = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            newListTMP.add(s.charAt(i));
        }
        return new XList(newListTMP);
    }

    static <T> XList<T> tokensOf(String s) {
        String[] stringTMP = s.split("\\s");
        List<String> newListTMP = new ArrayList<>(Arrays.asList(stringTMP));
        return new XList(newListTMP);
    }

    static <T> XList<T> tokensOf(String s, String separator) {
        String[] stringTMP = s.split(separator);
        List<String> newListTMP = new ArrayList<>(Arrays.asList(stringTMP));
        return new XList(newListTMP);
    }

    ///////////////METODY UNION////////////////////////
    public XList<T> union(Collection<T> collectionANYTYPE) {
        List<T> listFrom2Lists = Stream.concat(this.stream(), collectionANYTYPE.stream())
                .collect(Collectors.toList());
        return new XList(listFrom2Lists);
    }

    public XList<T> union(T... arrayANYTYPE) {
        List<T> listFrom2Lists = new ArrayList<>();
        listFrom2Lists.addAll(this);
        listFrom2Lists.addAll(Arrays.asList(arrayANYTYPE));
        return new XList(listFrom2Lists);
    }


    //////////////////METODA DIFF/////////////////////////////////
    public XList diff(Collection<T> collectionANYTYPE) {
        List<T> listTmp = new ArrayList<>(this);
        if (collectionANYTYPE.getClass().equals(XList.class))
            listTmp.removeAll(this);
        else
            listTmp.removeAll(collectionANYTYPE);
        return new XList(listTmp);
    }

    /////////////////////////METODA UNIQE///////////////////////////////////
    public XList unique() {
        List<T> uniqueList = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (!uniqueList.contains(this.get(i))) {
                uniqueList.add(this.get(i));
            }
        }
        return new XList(uniqueList);
    }

    ///////////////////////METODA COLLECT/////////////////////////////////
    public <S> XList<S> collect(Function<T, S> function) {
        List<S> newCollectList = new ArrayList<>();

        for (int i = 0; i < this.size(); i++) {
            newCollectList.add(function.apply(this.get(i)));
        }

        return new XList(newCollectList);
    }

    //////////////////METODA JOIN/////////////////////////////////////
    public String join(String separator) {
        StringBuilder stringBuilder = new StringBuilder();
        List<T> newList = new ArrayList<>(this);

        for (int i = 0; i < newList.size(); i++) {
            stringBuilder.append(newList.get(i));
            if (i < newList.size() - 1) {
                stringBuilder.append(separator);
            }
        }

        return stringBuilder.toString();
    }

    public String join() {
        StringBuilder stringBuilder = new StringBuilder();
        List<T> newList = new ArrayList<>(this);
        for (T element : newList) {
            stringBuilder.append(element);
        }
        return stringBuilder.toString();
    }

    //////////////////////METODA COMBINE/////////////////////////////
    public XList<XList<T>> combine() {
        XList<XList<T>> finalCombinationList = new XList<>();
        if (this.isEmpty()) {
            return new XList<>();
        } else {

            Collection<T> firsElementLISTOFTheList = (Collection<T>) this.get(0);

            XList<T> newListTMP = new XList<>(this);
            newListTMP.remove(0);

            XList<XList<T>> newXLISTAfterRFElement = newListTMP.combine();

            if (newXLISTAfterRFElement.isEmpty()) {

                for (T firstElement : firsElementLISTOFTheList) {

                    XList<T> finalOneXLIST = new XList<>();
                    finalOneXLIST.add(firstElement);
                    finalCombinationList.add(finalOneXLIST);
                }

                return finalCombinationList;
            }

            for (int i = 0; i < newXLISTAfterRFElement.size(); i++) {
                for (T firstElement : firsElementLISTOFTheList) {
                    XList<T> newElementList = newXLISTAfterRFElement.get(i);
                    XList<T> elementList = new XList<>(newElementList);
                    elementList.add(0, firstElement);
                    finalCombinationList.add(elementList);
                }
            }
        }
        return finalCombinationList;
    }


    //////////////////METODA FOREACHWITHINDEX//////////////////////////
    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }
    }
}

