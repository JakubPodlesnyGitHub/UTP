package zad1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CountryTable {

    String fileName;
    File countriesDataFile;
    //////////////////////////////////
    CountryTableModel countryTableModel;
    public CountryTable(String fileName) throws IOException {
        this.fileName = fileName;
        countriesDataFile = new File(fileName);
        countryTableModel = new CountryTableModel(countriesDataFile);
    }

    public JTable create() {
        JTable jTable = new JTable(countryTableModel);
        jTable.setRowHeight(145);
        jTable.getColumn("Population").setCellRenderer(new MyTableRedNumberRenderer());
        jTable.getColumn("Flag").setCellRenderer(new MyTableIconReaderRenderer());
        return jTable;
    }
}
