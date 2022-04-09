/**
 * @author Podleśny Jakub S20540
 */

package zad1;


public class Purchase{
    private String idClient;
    private String name;
    private String familyName;
    private String product;
    private double amount;
    private double priceOfProduct;
    private double generalPrice;

    public Purchase(String idClient, String name, String familyName, String product, double amount, double priceOfProduct) {
        this.idClient = idClient;
        this.name = name;
        this.familyName = familyName;
        this.product = product;
        this.amount = amount;
        this.priceOfProduct = priceOfProduct;
        generalPrice = amount * priceOfProduct;
}

    public String getIdClient() {
        return idClient;
    }

    public String getFamilyName() {
        return familyName;
    }

    public double getGeneralPrice() {
        return generalPrice;
    }

    @Override
    public String toString() {
        return idClient + ";" + familyName + " " + name + ";" + product + ";" + amount + ";" + priceOfProduct;
    }
}
