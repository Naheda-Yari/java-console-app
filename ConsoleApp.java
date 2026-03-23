// Src is the folder name where this file are inside
package Src;

import java.util.Scanner;

public class ConsoleApp {

    // THESE ARRAYS store all the data and setting
    // a fixed limit in the number of entries allowed.
    static Client[] clients = new Client[10];
    static Goods[] goodsList = new Goods[5];
    static Selling[] sellings = new Selling[25];

    // THESE VARIABLES to track how many items
    // are actually used inside each array
    static int clientCount = 0;
    static int goodsCount = 0;
    static int sellingCount = 0;

    public static void main(String[] args) {

        // Load saved data from CSV files when program start
        clientCount = CsvFilesManager.loadClients(clients);
        goodsCount = CsvFilesManager.loadGoods(goodsList);
        sellingCount = CsvFilesManager.loadSellings(sellings);

        Scanner scanner = new Scanner(System.in);
        int choice;

        // Do-while loop to display the menu at least once
        // and repeat it until the user selects Exit
        do {
            System.out.println("\n Computer Store Management System ");
            System.out.println("1) List clients");
            System.out.println("2) List computer goods");
            System.out.println("3) Add new client");
            System.out.println("4) Add new computer goods");
            System.out.println("5) Record a selling");
            System.out.println("6) List all sellings");
            System.out.println("7) Report: total sellings by client");
            System.out.println("8) Report: top-selling goods by revenue");
            System.out.println("9) Save & Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showAllClients();
                case 2 -> showAllGoods();
                case 3 -> addNewClient();
                case 4 -> addNewGoods();
                case 5 -> recordNewSelling();
                case 6 -> showAllSellings();
                case 7 -> reportTotalSellingsByClient();
                case 8 -> reportTopSellingGoods();
                case 9 -> {
                    saveAndExit();
                    System.out.println("Data saved. Goodbye!");
                }
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 9);

        scanner.close();
    }

    // These methods, showAllClients and addNewClient manage
    // client, list of stored clients and add new client
    static void showAllClients() {
        System.out.println("\n Clients List ");
        for (int i = 0; i < clientCount; i++) {
            System.out.println(clients[i]);
        }
    }

    static void addNewClient() {

        if (clientCount >= clients.length) {
            System.out.println("Client list is full.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Client ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Client Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Client Email: ");
        String email = scanner.nextLine();

        clients[clientCount] = new Client(id, name, email);
        clientCount++;

        System.out.println("Client added successfully.");
    }

    // These methods, ShowAllGoods and addNewGoods manage
    // goods, list of stored Goods and add new Goods
    static void showAllGoods() {
        System.out.println("\n Goods List ");
        for (int i = 0; i < goodsCount; i++) {
            System.out.println(goodsList[i]);
        }
    }

    static void addNewGoods() {

        if (goodsCount >= goodsList.length) {
            System.out.println("Goods list is full.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Goods ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Goods Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Stock: ");
        int stock = scanner.nextInt();

        goodsList[goodsCount] = new Goods(id, name, category, price, stock);
        goodsCount++;

        System.out.println("Goods added successfully.");
    }

    // This method records a selling and updates stock
    static void recordNewSelling() {

        if (sellingCount >= sellings.length) {
            System.out.println("Selling list is full.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Selling ID: ");
        int sellingId = scanner.nextInt();

        System.out.print("Enter Client ID: ");
        int clientId = scanner.nextInt();

        Client selectedclient = null;
        for (int i = 0; i < clientCount; i++) {
            if (clients[i].getId() == clientId) {
                selectedclient = clients[i];
                break;
            }
        }

        if (selectedclient == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.print("Enter Goods ID: ");
        int goodsId = scanner.nextInt();

        Goods selectedGoods = null;
        for (int i = 0; i < goodsCount; i++) {
            if (goodsList[i].getId() == goodsId) {
                selectedGoods = goodsList[i];
                break;
            }
        }

        if (selectedGoods == null) {
            System.out.println("Goods not found.");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        if (quantity > selectedGoods.getStock()) {
            System.out.println("Not enough stock.");
            return;
        }

        selectedGoods.setStock(selectedGoods.getStock() - quantity);

        sellings[sellingCount] = new Selling(
                sellingId,
                clientId,
                goodsId,
                quantity,
                selectedGoods.getPrice());

        sellingCount++;
        System.out.println("Selling recorded successfully.");
    }

    // This method shows all sellings
    static void showAllSellings() {

        System.out.println("\n Sellings List ");

        if (sellingCount == 0) {
            System.out.println("No sellings recorded.");
            return;
        }

        for (int i = 0; i < sellingCount; i++) {
            System.out.println(sellings[i]);
        }
    }

    // Calculates and report how much each client spent in total
    static void reportTotalSellingsByClient() {

        System.out.println("\n Total Sellings by Client ");

        for (int i = 0; i < clientCount; i++) {

            int clientId = clients[i].getId();
            double total = 0;

            for (int j = 0; j < sellingCount; j++) {
                if (sellings[j].getClientId() == clientId) {
                    total += sellings[j].getTotalPrice();
                }
            }

            System.out.println("Client ID: " + clientId +
                    ", Total Sellings: " + total);
        }
    }

    // Show top selling goods by revenue
    static void reportTopSellingGoods() {

        System.out.println("\n Top-Selling Goods by revenue ");

        double[] goods = new double[goodsCount];

        for (int i = 0; i < goodsCount; i++) {

            int goodsId = goodsList[i].getId();
            double total = 0;

            for (int j = 0; j < sellingCount; j++) {
                if (sellings[j].getGoodsId() == goodsId) {
                    total += sellings[j].getTotalPrice();
                }
            }
            goods[i] = total;
        }

        for (int i = 0; i < goodsCount - 1; i++) {
            for (int j = i + 1; j < goodsCount; j++) {

                if (goods[j] > goods[i]) {

                    double tempRev = goods[i];
                    goods[i] = goods[j];
                    goods[j] = tempRev;

                    Goods tempGoods = goodsList[i];
                    goodsList[i] = goodsList[j];
                    goodsList[j] = tempGoods;
                }
            }
        }

        int limit = Math.min(5, goodsCount);

        for (int i = 0; i < limit; i++) {
            System.out.println((i + 1) +
                    ". Goods ID: " + goodsList[i].getId() +
                    ", Total Sellings: " + goods[i]);
        }
    }

    // Saves all data back to CSV files before exiting
    static void saveAndExit() {
        CsvFilesManager.saveClients(clients, clientCount);
        CsvFilesManager.saveGoods(goodsList, goodsCount);
        CsvFilesManager.saveSellings(sellings, sellingCount);
    }
}