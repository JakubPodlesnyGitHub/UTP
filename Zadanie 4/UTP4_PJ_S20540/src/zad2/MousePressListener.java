package zad2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
@FunctionalInterface
public interface MousePressListener extends MouseListener {

    @Override
   default void mouseClicked(MouseEvent mouseEvent){

    }

    @Override
    default void mouseExited(MouseEvent mouseEvent){

    }


    @Override
    default void mouseReleased(MouseEvent mouseEvent){

    }

    @Override
    default void mouseEntered(MouseEvent mouseEvent){

    }

}
