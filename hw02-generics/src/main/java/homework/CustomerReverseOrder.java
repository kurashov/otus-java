package homework;

import java.util.LinkedList;

public class CustomerReverseOrder {
    private final LinkedList<Customer> linkedList;

    public CustomerReverseOrder() {
        this.linkedList = new LinkedList<>();
    }

    public void add(Customer customer) {
        linkedList.addFirst(new Customer(customer.getId(), customer.getName(), customer.getScores()));
    }

    public Customer take() {
        Customer polled = linkedList.poll();

        return polled != null
                ? new Customer(polled.getId(), polled.getName(), polled.getScores())
                : null;
    }
}
