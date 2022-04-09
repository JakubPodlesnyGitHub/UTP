package zadania.Zad11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Cre1 {

    static public void main(String[] args) {
        new Cre1();
    }

    Statement stmt;

    Cre1() {
        Connection con = null;
        String url = "jdbc:derby:C:/DerbyDbs/ksidb";
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
        } catch (Exception exc) {
            System.out.println(exc);
            System.exit(1);
        }

        // metoda dropTable jest naszą własną metodą napisaną dla skrócenia programu
        // usuwa ona tabelę podaną jako argument
        // Aby w każdych okolicznościach stworzyć nową tabelę AUTOR
        // musimy usunąć ew.  już istniejącą tabelę AUTOR
        dropTable("POZYCJE"); // usunięcie tabeli pochodnej, będącej w relacji z tabelą AUTOR
        dropTable("AUTOR");   // usunięcie tabeli AUTOR

        String crestmt = "CREATE TABLE AUTOR (AUTID INTEGER, NAME VARCHAR(255), CONSTRAINT AUTIDPK PRIMARY KEY(AUTID))";

        try {
            stmt.executeUpdate(crestmt); // wykonanie polecenia zapisanego w crestmt

        } catch (SQLException exc) {                      // przechwycenie wyjątku:
            System.out.println("SQL except.: " + exc.getMessage());
            System.out.println("SQL state  : " + exc.getSQLState());
            System.out.println("Vendor errc: " + exc.getErrorCode());
            System.exit(1);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException exc) {
                System.out.println(exc);
                System.exit(1);
            }
        }
    }

    private void dropTable(String tname) {
        try {
            stmt.executeUpdate("DROP TABLE " + tname);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
