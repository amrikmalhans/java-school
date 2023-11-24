/* Customer.java
 * 
 * Amrik Malhans - T00706439
 * COMP 1231 Assignment 3: Exception
 * 
 * This file defines a Customer class that stores a customer's age and rating.
 */

package CompanySurvey;

// Customer class
public class Customer {
    // Instance variables
    private int age;
    private double rating;

    // Constructor
    public Customer(int age, double rating) {
        this.age = age;
        this.rating = rating;
    }

    // Getters and Setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
