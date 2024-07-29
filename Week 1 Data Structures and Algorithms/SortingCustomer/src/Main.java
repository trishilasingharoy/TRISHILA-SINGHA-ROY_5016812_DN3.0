class Order {
    private final String orderId;
    private final String customerName;
    private final double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }





    public double getTotalPrice() {
        return totalPrice;
    }


    public String toString() {
        return "Order ID: " + orderId + ", Customer Name: " + customerName + ", Total Price: " + totalPrice;
    }
}

 class OrderSorter {

    // Bubble Sort to sort orders by totalPrice
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j + 1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort to sort orders by totalPrice
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);

            // Recursively sort elements before and after partition
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1); // Index of smaller element
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;

                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap orders[i + 1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Order[] orders = {
                new Order("1", "Maahi", 900.50),
                new Order("2", "Meera", 750.75),
                new Order("3", "Chandan", 800.20),
                new Order("4", "Diana", 500.00)
        };

        System.out.println("Original Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Bubble Sort
        bubbleSort(orders);
        System.out.println("\nOrders sorted by Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Reinitialize the array to original order for Quick Sort
        orders = new Order[]{
                new Order("1", "Maahi", 900.50),
                new Order("2", "Meera", 750.75),
                new Order("3", "Chandan", 800.20),
                new Order("4", "Diana", 500.00)
        };

        // Quick Sort
        quickSort(orders, 0, orders.length - 1);
        System.out.println("\nOrders sorted by Quick Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
