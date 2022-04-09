package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AccountChange implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if ((Double) propertyChangeEvent.getNewValue() < 0) {
            System.out.println("Value changed from: " + propertyChangeEvent.getOldValue() + " to " + propertyChangeEvent.getNewValue() + " Balance < 0");
        } else if ((Double) propertyChangeEvent.getNewValue() > 0) {
            System.out.println("Value changed from: " + propertyChangeEvent.getOldValue() + " to " + propertyChangeEvent.getNewValue() + " Balance > 0");
        } else {
            System.out.println("Value changed from: " + propertyChangeEvent.getOldValue() + " to " + propertyChangeEvent.getNewValue());
        }
    }
}
