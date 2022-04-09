package zad3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class AccountLimitator implements VetoableChangeListener {
    double debetLimit;

    public AccountLimitator(double debetLimit) {
        this.debetLimit = debetLimit;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent propertyChangeEvent) throws PropertyVetoException {
        if(propertyChangeEvent.getPropertyName().equals("balance") && (Double)propertyChangeEvent.getNewValue() < debetLimit){
            throw new PropertyVetoException("Unacceptable value change: " + propertyChangeEvent.getNewValue(),propertyChangeEvent);
        }

    }
}
