package zad1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CountryTableModel extends AbstractTableModel {
    File file;
    BufferedReader bufferedReader;
    //////////////////
    String[] columnNames;
    final Class[] columnClasses = {String.class, String.class, Integer.class, ImageIcon.class, LocalDateTime.class};
    Object[][] countriesDataTab;
    ArrayList<Country> countriesList;

    public CountryTableModel(File file) throws IOException {
        ////////POLA////////////////////
        this.file = file;
        this.bufferedReader = new BufferedReader(new FileReader(file));
        /////////TAB-DANE///////////////
        columnNames = getColumnNamesData();
        this.countriesList = addCountriesToList();
        countriesDataTab = addDataRowCountryToTab();
    }

    @Override
    public int getRowCount() {
        return countriesList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return countriesDataTab[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Country row = countriesList.get(rowIndex);
        if (getColumnName(columnIndex).equals("Population")) {
            row.setChangeTime(LocalDateTime.now());
            countriesDataTab[rowIndex][4] = row.getChangeTime();
            countriesDataTab[rowIndex][columnIndex] = (Integer) aValue;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (getColumnName(columnIndex).equals("Population"))
            return true;
        else
            return false;
    }

    ////////////////////////////TAB_LIST_DATA///////////////////////////////////

    private String[] getColumnNamesData() throws IOException {
        String firstLine = bufferedReader.readLine();
        String[] tmpDataColumns = firstLine.split("\\t");
        return tmpDataColumns;
    }

    private ArrayList<Country> addCountriesToList() throws IOException {
        String line;
        ArrayList<Country> readDataCountries = new ArrayList<>();
        bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            String[] TMPTabDataCountries = line.split("\\t");
            readDataCountries.add(new Country(TMPTabDataCountries[0], TMPTabDataCountries[1], Integer.valueOf(TMPTabDataCountries[2])));
        }
        return readDataCountries;
    }

    private Object[][] addDataRowCountryToTab() {
        Object[][] countriesDataTabTMP = new Object[countriesList.size()][getColumnCount()];
        for (int i = 0; i < countriesList.size(); i++) {
            countriesDataTabTMP[i][0] = countriesList.get(i).getName();
            countriesDataTabTMP[i][1] = countriesList.get(i).getCapital();
            countriesDataTabTMP[i][2] = countriesList.get(i).getPopulation();
            countriesDataTabTMP[i][4] = countriesList.get(i).getChangeTime();
        }
        return countriesDataTabTMP;
    }

}
