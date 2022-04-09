package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTableIconReaderRenderer extends DefaultTableCellRenderer {
    JLabel jLabelFlag = new JLabel();
    ImageIcon imageIconFlag = new ImageIcon("src\\zad1\\Image\\EU_STAR.png");

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        jLabelFlag.setIcon(imageIconFlag);
        return jLabelFlag;
    }

}
