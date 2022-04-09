/**
 *
 *  @author Podleśny Jakub S20540
 *
 */

package zad1;


public interface Mapper <T,S> { // Uwaga: interfejs musi być sparametrtyzowany
    public S map(T arg);
}
