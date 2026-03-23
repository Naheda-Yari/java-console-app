// Src is the name of the folder where CsvFilesManager.java file is inside
package Src;

// These imports needed to read from and write to CSV files
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvFilesManager {

    // 1. LOAD CLIENTS. These methods load data from CSV files into arrays
    public static int loadClients(Client[] clients) {
        int loadedClients = 0;

        try {
            File clientsFile = new File("data/clients.csv");

            if (!clientsFile.exists()) {
                clientsFile.createNewFile();
                return 0;
            }

            Scanner fileScanner = new Scanner(clientsFile);

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine() && loadedClients < clients.length) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];

                clients[loadedClients] = new Client(id, name, email);
                loadedClients++;
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading clients file.");
        }

        return loadedClients;
    }

    // 2. LOAD GOODS. These methods read goods data from goods.CSV files into arrays
    public static int loadGoods(Goods[] goodsList) {
        int loadedGoods = 0;

        try {
            File goodsFile = new File("data/goods.csv");

            if (!goodsFile.exists()) {
                goodsFile.createNewFile();
                return 0;
            }

            Scanner scanner = new Scanner(goodsFile);

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine() && loadedGoods < goodsList.length) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String category = parts[2];
                double price = Double.parseDouble(parts[3]);
                int stock = Integer.parseInt(parts[4]);

                goodsList[loadedGoods] = new Goods(id, name, category, price, stock);
                loadedGoods++;
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error loading goods file.");
        }

        return loadedGoods;
    }

    // 3. LOAD SELLINGS. These methods load selling
    // data from sellin.CSV files into arrays
    public static int loadSellings(Selling[] sellings) {
        int loadedSellings = 0;

        try {
            File sellingFile = new File("data/sellings.csv");

            if (!sellingFile.exists()) {
                sellingFile.createNewFile();
                return 0;
            }

            Scanner fileScanner = new Scanner(sellingFile);

            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine() && loadedSellings < sellings.length) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                int clientId = Integer.parseInt(parts[1]);
                int goodsId = Integer.parseInt(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                double unitPrice = Double.parseDouble(parts[4]);

                sellings[loadedSellings] = new Selling(id, clientId, goodsId, quantity, unitPrice);
                loadedSellings++;
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading sellings file.");
        }

        return loadedSellings;
    }

    // These methods save all clients back into clients.csv.
    // 4. SAVE CLIENTS
    public static void saveClients(Client[] clients, int clientCount) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("data/clients.csv"));

            writer.println("id,name,email");

            for (int i = 0; i < clientCount; i++) {
                writer.println(clients[i].toCSV());
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving clients.");
        }
    }

    // 5. SAVE GOODS data into goods.csv.
    public static void saveGoods(Goods[] goodsList, int goodsCount) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("data/goods.csv"));

            writer.println("id,name,category,price,stock");

            for (int i = 0; i < goodsCount; i++) {
                writer.println(goodsList[i].toCSV());
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving goods.");
        }
    }

    // 6. Saves selling records into sellings.csv.
    public static void saveSellings(Selling[] sellings, int sellingCount) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("data/sellings.csv"));

            writer.println("id,clientId,goodsId,quantity,unitPrice,totalPrice");

            for (int i = 0; i < sellingCount; i++) {
                writer.println(sellings[i].toCSV());
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving sellings.");
        }
    }
}