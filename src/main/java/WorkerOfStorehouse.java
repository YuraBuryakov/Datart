import Data.DatartDAO;
import Data.Zbozi;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class WorkerOfStorehouse {
    private DatartDAO datartDAO = new DatartDAO();

    public List<String> bringListOfCategories() {
        List<String> categories = datartDAO.selectCategory();
        return categories;

    }

    public List<Zbozi> bringListOfDevices(String category) {
        List<Zbozi> devices = datartDAO.selectAllFromTableWhere("kategory", category);
        return devices;

    }

    public void changeQuantityOfChosenDevice(String device, int quantity) throws SQLException {
        datartDAO.changeQuantityOfDevice(device, quantity);

    }
}
