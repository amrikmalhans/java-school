/* CustomerRating.java
 * 
 * Amrik Malhans - T00706439
 * COMP 1231 Assignment 3: Exception
 * 
 * This program reads customer ratings from a file, displays them, and allows the user to add new records.
 */

package CompanySurvey;

import java.io.*;
import java.util.ArrayList;

public class CustomerRating {

    // Instance variables
    private static final String FILE_NAME = "rating.txt";
    private ArrayList<Customer> customers = new ArrayList<>();
    private static final int MAX_SIZE = 5;

    // Load ratings from file
    public void loadRatings() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                int age = Integer.parseInt(parts[0]);
                double rating = Double.parseDouble(parts[1]);
                customers.add(new Customer(age, rating));
            }
        }
    }

    // Display records
    public void displayRecords() {
        // Print the header
        System.out.println("Most updated list of customer ratings");
        System.out.println("-------------------------------------");

        // Print column titles
        System.out.println("Age\tRating");
        System.out.println("-------------------------------------");

        // Display records
        for (Customer c : customers) {
            System.out.printf("%d\t%.2f\n", c.getAge(), c.getRating());
        }

        // Print separator line
        System.out.println("-------------------------------------");
    }

    // Add a new record
    public void addRecord(int age, double rating) throws ArrayIndexOutOfBoundsException {
        if (customers.size() >= MAX_SIZE) {
            throw new ArrayIndexOutOfBoundsException("Array out of bound...Record Skipped!");
        }
        customers.add(new Customer(age, rating));
    }

    // Calculate averages and display
    public void displayAverages() {
        double sumAge = 0;
        double sumRating = 0;
        for (Customer c : customers) {
            sumAge += c.getAge();
            sumRating += c.getRating();
        }
        System.out.printf("Average(Age)=%.2f\n", sumAge / customers.size());
        System.out.printf("Average(Rating)=%.2f\n", sumRating / customers.size());
    }

    // Save records back to the file
    public void saveToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer c : customers) {
                bw.write(c.getAge() + "\t" + c.getRating());
                bw.newLine();
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        CustomerRating ratingApp = new CustomerRating();

        try {
            ratingApp.loadRatings();
            ratingApp.displayRecords();

            // Read user input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print(
                        "Enter age[int], followed by ONE [TAB] key, then rating[decimal number] (or type '!' to exit): ");
                String input = reader.readLine();

                // Exit if user enters '!'
                if (input.equals("!")) {
                    ratingApp.saveToFile();
                    ratingApp.displayRecords();
                    ratingApp.displayAverages();
                    break;
                }

                // Add record to the list
                try {
                    String[] parts = input.split("\t");
                    int age = Integer.parseInt(parts[0]);
                    double rate = Double.parseDouble(parts[1]);

                    ratingApp.addRecord(age, rate);
                    System.out.printf("Customer added: Age = %d, Rating = %.2f\n", age, rate);

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number...");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
