// Src is the name of the folder where Client.java file are inside
package Src;

public class Client {
    // These variables store the client personal data
    private int id;
    private String name;
    private String email;

    // Constructor.
    // Used this to create a Client object
    // when adding or loading data.
    public Client(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters
    // These methods allow safe access to private data

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Display client data (Readable format)
    public String toString() {
        return "Client ID: " + id +
                ", Name: " + name +
                ", Email: " + email;
    }

    // converts to CSV format
    public String toCSV() {
        return id + "," + name + "," + email;
    }
}