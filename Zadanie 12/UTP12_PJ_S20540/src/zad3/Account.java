package zad3;

import java.beans.*;

public class Account {
    private double balance;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
    //---------------NUMEROWANIE KONT--------------
    private static int counter = 1;
    private int number;
    public Account(double balance) {
        this.balance = balance;
        number = counter;
        counter++;
    }

    public Account() {
        number = counter;
        counter++;
    }

    public synchronized void deposit(double valueDDD) throws PropertyVetoException {
        double oldBalance = 0;
        oldBalance = balance;
        vetoableChangeSupport.fireVetoableChange("balance", oldBalance, balance + valueDDD);
        balance += valueDDD;
        propertyChangeSupport.firePropertyChange("balance", oldBalance, balance);
    }

    public synchronized void transfer(Account account, double valueDDD) throws PropertyVetoException {
        double oldBalance = 0;
        oldBalance = balance;
        vetoableChangeSupport.fireVetoableChange("balance", oldBalance, balance - valueDDD);
        account.deposit(valueDDD);
        this.balance -= valueDDD;
        propertyChangeSupport.firePropertyChange("balance", oldBalance, balance);
    }

    public synchronized void withdraw(double valueDDD) throws PropertyVetoException {

        double oldBalance = 0;
        oldBalance = balance;
        vetoableChangeSupport.fireVetoableChange("balance", oldBalance, balance - valueDDD);
        balance -= valueDDD;
        propertyChangeSupport.firePropertyChange("balance", oldBalance, balance);
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener p) {
        propertyChangeSupport.addPropertyChangeListener(p);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener p) {
        propertyChangeSupport.removePropertyChangeListener(p);
    }

    public synchronized void addVetoableChangeListener(VetoableChangeListener vetoP) {
        vetoableChangeSupport.addVetoableChangeListener(vetoP);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener vetoP) {
        vetoableChangeSupport.removeVetoableChangeListener(vetoP);
    }

    @Override
    public String toString() {
        return "Account: " + number + " Balance " + balance;
    }
}
