/**
 * @author Podleśny Jakub S20540
 */

package zad2;


import java.io.*;
import java.util.*;

public class Anagrams {
    private String fileName;
    private List<String> stringsFromFile;
    private List<List<String>> listOFANGRAMS;


    public Anagrams(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        stringsFromFile = readFile();
        listOFANGRAMS = new ArrayList<>();
    }

    public List<List<String>> getSortedByAnQty() {
        addListsOFANAGRAMSToList();
        return listOFANGRAMS;
    }

    public String getAnagramsFor(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> listOFAnagramsForWord = new ArrayList<>();
        stringBuilder.append(word).append(": ");
        if (!stringsFromFile.contains(word)) {
            return stringBuilder.append(nullVersion()).toString();
        } else {
            for (String element : stringsFromFile) {
                addListToSortedListOfLists(word, element, listOFAnagramsForWord);
            }
            listOFAnagramsForWord.sort(getAlphabeticalComparator());
            stringBuilder.append(listOFAnagramsForWord);
            return stringBuilder.toString();
        }
    }

    //////////////////////////////////////////METODA DODAJE DO LISTY ANAGRAMY WZGLEDEM JAKIEGOS SLOWA//////////////////////
    public void addListToSortedListOfLists(String word, String element, List<String> listOFAnagramsForWord) {
        char[] array1 = word.toCharArray();
        char[] array2 = element.toCharArray();
        sortArray(array1, array2);
        if (!element.equals(word)) {
            if (new StringBuilder(String.valueOf(array1)).toString().equals(new StringBuilder(String.valueOf(array2)).toString()))
                listOFAnagramsForWord.add(element);
        }
    }

    ///////////////////////////////////METODA DODAJE DO LISTY LISTY ANAGRAMOW//////////////////////////////////////////////////////////////////
    public void addListsOFANAGRAMSToList() {
        List<String> checkList = new ArrayList<>();
        for (String element1 : stringsFromFile) {
            if (!checkList.contains(element1)) {
                List<String> oneList = new ArrayList<>();
                for (String element2 : stringsFromFile) {
                    char[] array1 = element1.toCharArray();
                    char[] array2 = element2.toCharArray();
                    sortArray(array1, array2);
                    if (new StringBuilder(String.valueOf(array1)).toString().equals(new StringBuilder(String.valueOf(array2)).toString())) {
                        oneList.add(element2);
                        checkList.add(element2);
                    }
                }
                oneList.sort(getAlphabeticalComparator());
                listOFANGRAMS.add(oneList);
            }
        }
        listOFANGRAMS.sort(getSizeComparator().thenComparing(getAlphabeticalONEComparator()));
    }

    /////////////////////////////////////METODA ZWRACA STRING Z NULL JESLI NIE MA SLOWA///////////////////////////////////
    public String nullVersion() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("null");
        return stringBuilder.toString();
    }

    //////////////////////////////////////////////ODCZYTYWANIE Z PLIKU///////////////////////////////////////////////////////////
    private List<String> readFile() throws FileNotFoundException {
        List<String> tmpArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String[] textFromFile = scanner.nextLine().split("\\s");
            tmpArrayList.addAll(Arrays.asList(textFromFile));
        }
        return tmpArrayList;
    }

    ////////////////////////////////////SORTOWANIE POJEDYNCZYCH WYRAZOW///////////////////////////////////////////////////////////////////
    private void sortArray(char[] firstArray, char[] secoondArray) {
        Arrays.sort(firstArray);
        Arrays.sort(secoondArray);
    }

    ////////////////////////////////////COMPERATORY///////////////////////////////////////////////////////////////////
    private Comparator getSizeComparator() {
        Comparator<List<String>> comparatorINTEGER = Comparator.comparingInt(List::size);
        return comparatorINTEGER.reversed();
    }

    private Comparator getAlphabeticalComparator() {
        Comparator<String> comparatorSTRING = String::compareTo;
        return comparatorSTRING;
    }

    private Comparator getAlphabeticalONEComparator() {
        Comparator<List<String>> comapratorOneElement = (list1, list2) -> {
            int comapareValue = list1.get(0).compareTo(list2.get(0));
            if (comapareValue < 0) {
                return -1;
            } else if (comapareValue > 0) {
                return 1;
            } else
                return 0;
        };
        return comapratorOneElement;
    }


}
