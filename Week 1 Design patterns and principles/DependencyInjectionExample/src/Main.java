class DependencyInjectionExample {
    public static void main(String[] args) {
        // Create repository instance
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        // Inject repository into service
        CustomerService customerService = new CustomerService(customerRepository);

        // Use the service to find a customer
        Customer customer = customerService.getCustomerById(1);
        System.out.println(customer);
    }
}

// CustomerRepository.java
interface CustomerRepository {
    Customer findCustomerById(int id);
}

// CustomerRepositoryImpl.java
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(int id) {
        // For demonstration, return a dummy customer
        return new Customer(id, "Reena Mandal", "reenamandal@gmail.com");
    }
}

// Customer.java
class Customer {
    private final int id;
    private final String name;
    private final String email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}

// CustomerService.java
class CustomerService {
    private final CustomerRepository customerRepository;

    // Constructor injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }
}