package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatartDAO {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    public DatartDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:ucanaccess://Datart.accdb/;columnOrder=DISPLAY");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void selectAllFromTable() {
        String sql = "Select * from Sklad where kategory = 'telephone'";

        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("\n" + resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getInt(4) + "\t" + resultSet.getInt(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> selectCategory() {
        String sql = "Select DISTINCT kategory from Sklad";
        List<String> kategories = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                kategories.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return kategories;
    }

    public static void selectDevicesPodleKategorii(String kategory) throws SQLException {

        String sql = "Select * from Sklad where kategory = " + kategory;
        List<String> devices = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(sql);
        try {

            while (resultSet.next()) {
                System.out.println(" \n" + resultSet.getString(3) + "\t" + resultSet.getInt(4) + "\t" + resultSet.getInt(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Zbozi> selectAllFromTableWhere(String where, String param) {
        String sql = "Select * from Sklad where " + where + " = '" + param + "' and mnozstvi > 0;";
        List<Zbozi> zboziList = new ArrayList<>();

        try {
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Zbozi zbozi = new Zbozi(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5));
                zboziList.add(zbozi);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return zboziList;
    }

    public void changeQuantityOfDevice(String nameOfDevice, int leftQuantity) throws SQLException {
        String sql = "UPDATE Sklad SET mnozstvi = " + leftQuantity + " WHERE  zbozi =  '" + nameOfDevice + "'";
        statement = connection.createStatement();
        statement.executeUpdate(sql);
        System.out.println("table is changed");
    }


    public void close() throws SQLException {
        connection.close();
    }
}

