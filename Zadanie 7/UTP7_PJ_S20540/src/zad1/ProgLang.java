package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {
    private String fileName;
    private List<String> languagesList;
    private List<String> programmersList;
    private Map<String, Collection<String>> languageProgrammerMAP;
    private Map<String, Collection<String>> programmerLanguageMAP;

    public ProgLang(String fileName) {
        this.fileName = fileName;
        /////////Listy/////////////////
        languagesList = new ArrayList<>();
        programmersList = new ArrayList<>();
        //////////MAPY///////////////////
        languageProgrammerMAP = new LinkedHashMap<>();
        programmerLanguageMAP = new LinkedHashMap<>();
        readFile();
    }

    ///////////////////////////ODCZYTYWANIE Z PLIKU 2 METODY///////////////////////////////////////////////
    public void readFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String textLine;
            while ((textLine = bufferedReader.readLine()) != null) {
                String[] tabTMP = textLine.split("\\t");
                fillLists(tabTMP);
                fillMapLanguageMAP(tabTMP);
                fillProgrammerLanguageMAP(tabTMP);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillLists(String[] tab) {
        languagesList.add(tab[0]);
        for (int i = 1; i < tab.length; i++) {
            if (!programmersList.contains(tab[i])) {
                programmersList.add(tab[i]);
            }
        }
    }

    private void fillMapLanguageMAP(String[] tab) {
        Collection<String> collectionOneLine = new ArrayList<>();
        for (int i = 1; i < tab.length; i++) {
            if (!collectionOneLine.contains(tab[i])) {
                collectionOneLine.add(tab[i]);
            }
        }
        languageProgrammerMAP.put(tab[0], collectionOneLine);
    }

    private void fillProgrammerLanguageMAP(String[] tab) {
        for (int i = 0; i < programmersList.size(); i++) {
            Collection<String> languages = new ArrayList<>();
            if (!programmerLanguageMAP.containsKey(programmersList.get(i))) {
                programmerLanguageMAP.put(programmersList.get(i), languages);
            }
            for (int j = 0; j < languagesList.size(); j++) {
                if (Arrays.asList(tab).contains(programmersList.get(i))) {
                    if (!programmerLanguageMAP.get(programmersList.get(i)).contains(tab[0])) {
                        programmerLanguageMAP.get(programmersList.get(i)).add(tab[j]);
                    }
                }
            }
        }
    }

    ///////////////////////////METODA getLangsPro()////////////////////////////////////////////////////////////////////
    public Map<String, Collection<String>> getLangsMap() {
        return languageProgrammerMAP;
    }

    ///////////////////////////METODA getProgsMap()////////////////////////////////////////////////////////////////////
    public Map<String, Collection<String>> getProgsMap() {
        return programmerLanguageMAP;
    }

    /////////////////////////Metoda  getLangsMapSortedByNumOfProgs() //////////////////////////////////////////
    public Map<String, Collection<String>> getLangsMapSortedByNumOfProgs() {
        Map<String, Collection<String>> listAFTERKey = sorted(languageProgrammerMAP, Comparator.comparing(Map.Entry::getKey));
        return sorted(listAFTERKey, Comparator.comparing(list -> list.getValue().size(), Comparator.reverseOrder()));
    }

    /////////////////////////Metoda  getProgsMapSortedByNumOfLangs() //////////////////////////////////////////
    public Map<String, Collection<String>> getProgsMapSortedByNumOfLangs() {
        Map<String, Collection<String>> listAFTERKey = sorted(programmerLanguageMAP, Comparator.comparing(Map.Entry::getKey));
        return sorted(listAFTERKey, Comparator.comparing(list -> list.getValue().size(), Comparator.reverseOrder()));
    }

    /////////////////////////Metoda  getProgsMapForNumOfLangsGreaterThan(int n) //////////////////////////////////////////
    public Map<String, Collection<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(programmerLanguageMAP, list1 -> list1.getValue().size() > n);
    }

    /////////////////////////Metoda  sorted //////////////////////////////////////////
    public   static <S, T> Map <S,T> sorted(Map<S, T> anyMap, Comparator<Map.Entry<S, T>> comparator) {
        List<Map.Entry<S, T>> valuesTOSort = new ArrayList<>();
        valuesTOSort.addAll(anyMap.entrySet());
        valuesTOSort.sort(comparator);
        Map<S, T> newSortedMap = new LinkedHashMap<>();
        for (Map.Entry<S, T> element : valuesTOSort) {
            newSortedMap.put(element.getKey(), element.getValue());
        }
        return newSortedMap;
    }

    /////////////////////////Metoda filtered //////////////////////////////////////////
    public  static <S, T> Map <S,T> filtered(Map<S, T> anyMap, Predicate<Map.Entry<S, T>> filterFactor) {
        Map<S, T> newFilterMap = new LinkedHashMap<>();
        for (Map.Entry<S, T> element : anyMap.entrySet()) {
            if (filterFactor.test(element)) {
                newFilterMap.put(element.getKey(), element.getValue());
            }
        }
        return newFilterMap;
    }
}

