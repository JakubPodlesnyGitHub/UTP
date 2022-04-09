package zadania.Zad13;

import java.sql.*;

public class SqlCommands {
    public static void main(String[] args) {
        new SqlCommands();
    }

    Connection con;
    String url;
    String nazwisko;
    String tytul;
    int rok;
    int cena;

    public SqlCommands() {
        try {
            url = "jdbc:derby:C:/DerbyDbs/ksidb";
            con = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String sel = "SELECT NAME,TYTUL,ROK,CENA FROM POZYCJE INNER JOIN AUTOR ON AUTOR.AUTID = POZYCJE.AUTID WHERE ROK > 2000 AND CENA > 30";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sel);
            System.out.println("A");
            System.out.println("DO PRZODU");
            while (rs.next()) {
                nazwisko = rs.getString(1);
                tytul = rs.getString(2);
                rok = rs.getInt(3);
                cena = rs.getInt(4);
                System.out.println("Autor: " + nazwisko + " Tytul Ksiazki: " + tytul + " Rok: " + rok + " Cena: " + cena);

            }
            stmt.close();
            con.close();
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
    }
}