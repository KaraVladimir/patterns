package patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kara.vladimir2@gmail.com.
 */
enum Event{NEW_BOOK,NEW_MOVIE}

interface Customer {
    void notify(Event event);
}

interface Observable {
    void addObserver(Customer customer);
    void removeObserver(Customer customer);
}

class EntertainmentPortal implements Observable{
    List<Customer> customers = new ArrayList<>();
    public void addObserver(Customer customer) {
        customers.add(customer);
    }
    public void removeObserver(Customer customer) {
        customers.remove(customer);
    }
    public void addNewBook() {
        for (Customer customer : customers) {customer.notify(Event.NEW_BOOK);}
    }
    public void addNewMovie() {
        for (Customer customer : customers) {customer.notify(Event.NEW_MOVIE);}
    }
}

class MovieFan implements Customer{
    public void notify(Event event) {
        if (event==Event.NEW_MOVIE)orderNewMovie();
    }
    private void orderNewMovie() {System.out.println("Buy movie");}
}

class UsualCustomer implements Customer {
    public void notify(Event event) {findOutAboutNewEntertain(event);}
    private void findOutAboutNewEntertain(Event event) {
        System.out.println("Find out about "+event);
    }
}

public class ObserverExample {
    public static void main(String[] args) {
        Customer customer1 = new MovieFan();
        Customer customer2 = new UsualCustomer();
        Observable entertainmentPortal = new EntertainmentPortal();
        entertainmentPortal.addObserver(customer1);
        entertainmentPortal.addObserver(customer2);
        ((EntertainmentPortal)entertainmentPortal).addNewMovie();
        ((EntertainmentPortal)entertainmentPortal).addNewBook();
    }
}
