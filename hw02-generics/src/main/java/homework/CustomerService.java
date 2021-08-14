package homework;


import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

public class CustomerService {
    private final SortedMap<Customer, String> map;

    public CustomerService() {
        this.map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    }

    public Map.Entry<Customer, String> getSmallest() {
        return createMapEntry(map.firstKey(), map.get(map.firstKey()));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Optional<Customer> keyO = map.keySet().stream().filter(c -> c.getScores() > customer.getScores()).findFirst();

        return keyO.map(value -> createMapEntry(value, map.get(value))).orElse(null);
    }

    public void add(Customer customer, String data) {
        Customer key = new Customer(customer.getId(), customer.getName(), customer.getScores());
        map.put(key, data);
    }

    private Map.Entry<Customer, String> createMapEntry(Customer key, String value) {
        return new AbstractMap.SimpleEntry<>(
                new Customer(key.getId(), key.getName(), key.getScores()),
                value
        );
    }
}
