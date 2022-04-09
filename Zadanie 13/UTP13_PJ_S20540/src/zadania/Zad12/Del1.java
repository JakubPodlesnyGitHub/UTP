package zadania.Zad12;

import java.sql.*;

public class Del1 {
    static public void main(String[] args) {
        new Del1();
    }

    PreparedStatement stmt;

    Del1() {
        Connection con = null;
        String url = "jdbc:derby:C:/DerbyDbs/ksidb";
        try {
            con = DriverManager.getConnection(url);
        } catch (Exception exc) {
            System.out.println(exc);
            System.exit(1);
        }
        // nazwy wydawców do wpisywania do tabeli
        String[] wyd = {"PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM"};

        // pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
        int beginKey = 15;
        int delCount =  0;
        try  {
            // przygotowanie instrukcji prekompilowanej
            stmt = con.prepareStatement("DELETE FROM WYDAWCA WHERE WYDID = ? OR NAME = ?");	// usunięcie z tabeli WYDAWCA rekordu o podanej nazwie wydawcy z tablicy wyd lub o podanym numerze wydawcy zaczynającym się od beginKey
            for (int i=0; i < wyd.length; i++)   {
                stmt.setInt(1,beginKey++);
                stmt.setString(2,wyd[i]);
                stmt.executeUpdate();
                delCount++;
            }
            con.close();
        } catch(SQLException exc)  {
            System.out.println(exc);
        } finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Usunieto: " + delCount + " rekorodow.");
        }

    }
}
