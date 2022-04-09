package zadania.Zad12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ins1 {

    static public void main(String[] args) {
        new Ins1();
    }

    Statement stmt;

    Ins1() {
        Connection con = null;
        String url = "jdbc:derby:C:/DerbyDbs/ksidb";
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
        } catch (Exception exc) {
            System.out.println(exc);
            System.exit(1);
        }
        // nazwy wydawców do wpisywania do tabeli
        String[] wyd = {"PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM"};

        // pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
        int beginKey = 15;

        String[] ins = {"INSERT INTO WYDAWCA VALUES(" + beginKey++ + ",'" + wyd[0] + "')",
                "INSERT INTO WYDAWCA VALUES(" + beginKey++ + ",'" + wyd[1] + "')",
                "INSERT INTO WYDAWCA VALUES(" + beginKey++ + ",'" + wyd[2] + "')",
                "INSERT INTO WYDAWCA VALUES(" + beginKey++ + ",'" + wyd[3] + "')",
                "INSERT INTO WYDAWCA VALUES(" + beginKey++ + ",'" + wyd[4] + "')",
                "INSERT INTO WYDAWCA VALUES(" + beginKey++ + ",'" + wyd[5] + "')"}; // ? ... tablica instrukcji SQL do wpisywania rekordów do tabeli: INSERT ...

        int insCount = 0;   // ile rekordów wpisano
        try {
            for (int i = 0; i < ins.length; i++) {
                stmt.executeUpdate(ins[i]);
                insCount++;
            }

        } catch (SQLException throwables) {
            System.out.println(throwables);
            System.exit(1);
        } finally {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Wpisano: " + insCount + " rekorodow.");
        }

    }
}

