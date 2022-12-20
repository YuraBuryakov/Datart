package Data;

import java.sql.*;

public class ZakaznikDAO {
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public ZakaznikDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://COBA1.accdb/;columnOrder=DISPLAY");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void selectAllFromTable() {
        String sql = "Select * from Zakaznik";
        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("\n" + resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getInt(5) + "\t" + resultSet.getInt(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addNewEntity(String jmeno, String prijmeni, String zbozi, int mnozstvi, int cena) throws SQLException {
        String sql = "insert into Zakaznik (jmeno,prijmeni,nakup,mnozstvi,cena) values (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, jmeno);
        preparedStatement.setString(2, prijmeni);
        preparedStatement.setString(3, zbozi);
        preparedStatement.setInt(4, mnozstvi);
        preparedStatement.setInt(5, cena);
        preparedStatement.execute();

    }

    public void updateEntity(String nazev_zbozi, int o_kolik_zmenit, int mnozstvi) throws SQLException {
        String sql = "UPDATE Zakaznik SET mnozstvi = ? WHERE  nakup = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, mnozstvi - o_kolik_zmenit);
        preparedStatement.setString(2, nazev_zbozi);
        preparedStatement.execute();

    }

    public boolean checkEntiyZakaznik(String firtstName, String secondName) throws SQLException {
        String sql = "Select count(*) from Zakaznik where jmeno = '" + firtstName + "' and prijmeni = '" + secondName + "' ";
        resultSet = statement.executeQuery(sql);
        boolean flag = true;

        while (resultSet.next()) {
            if (resultSet.getInt(1) == 0) {
                flag = false;
            }
        }

        return flag;
    }

    public void close() throws SQLException {
        connection.close();
    }

}
