package zadania.Zad13;

import java.sql.*;

public class SqlCommands1 {
    public static void main(String[] args) {
        new SqlCommands1();
    }

    Connection con;
    String url;
    String nazwisko;
    String tytul;
    int rok;
    int cena;

    public SqlCommands1() {
        try {
            url = "jdbc:derby:C:/DerbyDbs/ksidb";
            con = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sel = "SELECT NAME,TYTUL,ROK,CENA FROM POZYCJE INNER JOIN AUTOR ON AUTOR.AUTID = POZYCJE.AUTID WHERE ROK > 2000 AND CENA > 30";
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sel);
            ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            for (int i = 1; i <= cc; i++)
                System.out.print(rsmd.getColumnLabel(i) + "     ");

            System.out.println("\n------------------------------ przewijanie do góry");
            rs.afterLast();
            while (rs.previous()){
                nazwisko = rs.getString(1);
                tytul = rs.getString(2);
                rok = rs.getInt(3);
                cena = rs.getInt(4);
                System.out.println("Autor: " + nazwisko + " Tytul Ksiazki: " + tytul + " Rok: " + rok + " Cena: " + cena);
            }

            System.out.println("\n----------------------------- pozycjonowanie abs.");
            int[] poz = {3, 7, 9};
            for (int p = 0; p < poz.length; p++) {
                System.out.print("[ " + poz[p] + " ] ");
                rs.absolute(poz[p]);
                for (int i = 1; i <= cc; i++) System.out.print(rs.getString(i) + ", ");
                System.out.println("");
            }
            stmt.close();
            con.close();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
