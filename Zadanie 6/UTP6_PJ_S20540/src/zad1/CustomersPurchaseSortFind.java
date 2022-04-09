/**
 * @author Podleśny Jakub S20540
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Collator;
import java.util.*;

public class CustomersPurchaseSortFind {

    private List<Purchase> purchaseList;
    private List<Purchase> purchaseListForClient;

    public CustomersPurchaseSortFind() {
        purchaseList = new ArrayList<>();
        purchaseListForClient = new ArrayList<>();
    }

    //////////////////////////METODA READFILE//////////////////////////////////////////////////////////////
    public void readFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String textLine;
            while ((textLine = bufferedReader.readLine()) != null) {
                purchaseList.add(getPurchase(textLine));
                purchaseListForClient.add(getPurchase(textLine));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////METODA SHOWSORTEDBY//////////////////////////////////////////////////////////////
    public void showSortedBy(String byWhat) {
        if (byWhat.equals("Nazwiska")) {
            Collections.sort(purchaseList, comperatorFamilyName());
            System.out.println("Nazwiska ");
            purchaseList.forEach(purchaseElement -> System.out.println(purchaseElement));
            System.out.println();
        } else if (byWhat.equals("Koszty")) {
            Collections.sort(purchaseList, comperatorGeneralPrice());
            System.out.println("Koszty ");
            purchaseList.forEach(purchaseElement -> System.out.println(purchaseElement + " (koszt: " + purchaseElement.getGeneralPrice() + ")"));
            System.out.println();
        }
    }

    //////////////////////////METODA SHOWPURCHASEFOR////////////////////////////////////////////////////////////////////
    public void showPurchaseFor(String idClient) {
        System.out.println("Klient " + idClient);
        purchaseListForClient.stream().filter(clientE -> {
            if (clientE.getIdClient().equals(idClient))
                return true;
            else
                return false;
        }).forEach(System.out::println);
        System.out.println();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Purchase getPurchase(String element) {
        String[] tabTMP = element.split(";");
        String[] tabTMPNameFamilyName = tabTMP[1].split("\\s");
        Purchase purchase = new Purchase(tabTMP[0], tabTMPNameFamilyName[1], tabTMPNameFamilyName[0], tabTMP[2], Double.parseDouble(tabTMP[3]), Double.parseDouble(tabTMP[4]));
        return purchase;
    }

    ////////////////////////////////////COMPERATORY///////////////////////////////////////////////////////////////////
    private Comparator comperatorFamilyName() {
        Comparator<Purchase> familyNameComparator = (s1,s2) -> {
            Collator collatorComparator = Collator.getInstance(new Locale("pl", "PL"));
            return  collatorComparator.compare(s1.getFamilyName(),s2.getFamilyName());
        };
        return familyNameComparator.thenComparing(Purchase::getIdClient);
    }

    private Comparator comperatorGeneralPrice() {
        Comparator<Purchase> generalPriceComparator = Comparator.comparing(Purchase::getGeneralPrice).reversed().thenComparing(Purchase::getIdClient);
        return generalPriceComparator;
    }
}

