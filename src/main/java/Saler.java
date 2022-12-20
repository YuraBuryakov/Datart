import Data.ZakaznikDAO;
import Data.Zbozi;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Saler {
    private Zakaznik zakaznik;
    private ZakaznikDAO zakaznikDAO = new ZakaznikDAO();
    private WorkerOfStorehouse workerOfStorehouse = new WorkerOfStorehouse();

    private int finalPay = 0;


    // Saler meets a customer and starts to ask his name and surname to fill a database
    public void startConversation() throws SQLException {

        System.out.println("Welcome to Datart!");

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String first_name = input.nextLine();

        System.out.print("Enter your last name: ");
        String last_name = input.nextLine();
        zakaznik = new Zakaznik(first_name, last_name);
        checkCustomerInDatabase(zakaznik);

    }


    //Use COBA1.accdb to check a customer
    private void checkCustomerInDatabase(Zakaznik zakaznik) throws SQLException {
        //call ZakaznikDAO
        boolean result = zakaznikDAO.checkEntiyZakaznik(zakaznik.getJmeno(), zakaznik.getPrijmeni());
        if (result) {
            System.out.println("\nYour are registered in our system!\nHello, " + zakaznik.getJmeno() + " " + zakaznik.getPrijmeni() + "\nWhat would You like to get?");
        } else {
            System.out.println("You are not registered. I add Your after Your order.");
        }

        //here sales asks WorkerOfStorehouse to bring a list of categories of devices. And of course sales shows this list
        chooseCategory();
    }

    private void chooseCategory() throws SQLException {
        System.out.println("\nLet's peek what we have:");
        List<String> categories = workerOfStorehouse.bringListOfCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i + ") " + categories.get(i));
        }

        System.out.println("\n\nChoose a number of category of devices");
        int chooseCategory = ifInputInteger(categories.size());

        String chosenCategory = categories.get(chooseCategory);

        //Great. Ask a WorkerOfStoreHouse to bring devices
        chooseDevice(chosenCategory);
    }

    private void chooseDevice(String category) throws SQLException {
        System.out.println("That what we have:");

        List<Zbozi> devices = workerOfStorehouse.bringListOfDevices(category);
        System.out.println("Device" + "\t" + "quantity" + "\t" + "price");

        for (int i = 0; i < devices.size(); i++) {
            System.out.println(i + ") " + devices.get(i).getZbozy() + "\t" + devices.get(i).getMnozstvi() + "\t" + devices.get(i).getCena());
        }

        System.out.println("Choose a number of a device: ");

        int chosenDevice = ifInputInteger(devices.size());

        System.out.println("Your device is: " + devices.get(chosenDevice).getZbozy() + ". We have " + devices.get(chosenDevice).getMnozstvi());
        zakaznik.setNakup_nazev(devices.get(chosenDevice).getZbozy());

        System.out.println("Choose a quantity of " + devices.get(chosenDevice).getZbozy());

        int chooseQuantity = correctQuantity(devices.get(chosenDevice).getMnozstvi());
        zakaznik.setMnozstvi(chooseQuantity);
        zakaznik.setCena(devices.get(chosenDevice).getCena() * chooseQuantity);

        //after salesman asks WrorkerOfStorehouse to change a quantity of chosen device
        sayWorkerToChangeQuantity(devices.get(chosenDevice).getZbozy(), devices.get(chosenDevice).getMnozstvi() - chooseQuantity);

        //Salesman asks, does customer want to buy something else
        finishConversation(zakaznik);

    }

    private void sayWorkerToChangeQuantity(String device, int quantity) throws SQLException {
        workerOfStorehouse.changeQuantityOfChosenDevice(device, quantity);
    }

    private void finishConversation(Zakaznik zakaznik) throws SQLException {
        String finish;
        finalPay += zakaznik.getCena();

        //adding devices into Datart database
        zakaznikDAO.addNewEntity(zakaznik.getJmeno(), zakaznik.getPrijmeni(), zakaznik.getNakup_nazev(), zakaznik.getMnozstvi(), zakaznik.getCena());

        do {
            System.out.println("\n\nDo You want to get something more? (yes/no)");

            Scanner input = new Scanner(System.in);
            finish = input.nextLine();
            if (finish.equals("yes")) {
                chooseCategory();
            } else if (finish.equals("no")) {
                break;
            }
        } while (!finish.equals("yes") | !finish.equals("no"));

    }

    // if we do not have enough devices in a storehouse - do not show them

    //---------------------------
    //control-parameters methods

    //and is in rage of size of list

    private int correctQuantity(int availableQuantity){
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        int input = 0;

        while (!flag) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if ((input >= 0 & input <= availableQuantity)) {
                    flag = true;
                } else {
                    System.out.println("Choose an available quantity of device");
                }
            } catch (Exception e) {
                System.out.println("Please enter a number");
            }
        }

        return input;
    }
    private int ifInputInteger(int listSize ) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        int input = 0;

        while (!flag) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if ((input >= 0 & input <= listSize - 1) | listSize == 0) {
                    flag = true;
                } else {
                    System.out.println(chooseNumber);
                }
            } catch (Exception e) {
                System.out.println(chooseNumber);
            }
        }
        return input;
    }


    public String chooseNumber = "Choose a correct number";

}
