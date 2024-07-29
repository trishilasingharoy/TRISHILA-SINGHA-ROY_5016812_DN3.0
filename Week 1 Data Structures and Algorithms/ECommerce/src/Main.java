import java.util.Arrays;
import java.util.Comparator;

class Product {
    private final String productId;
    private final String productName;
    private final String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }






    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Category: " + category;
    }
}

class Commerce {
    private  final Product[] products;

    public Commerce(Product[] products) {
        this.products = products;
    }

    // Linear search for a product by productId
    public Product linearSearch(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    // Binary search for a product by productId
    public Product binarySearch(String productId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductId().compareTo(productId);

            if (comparison == 0) {
                return products[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    // Method to sort products array by productId for binary search
    public void sortProducts() {
        Arrays.sort(products, Comparator.comparing(Product::getProductId));
    }

    public static void main(String[] args) {
        Product[] productsArray = {
                new Product("003", "Laptop", "Electronics"),
                new Product("001", "Smartphone", "Electronics"),
                new Product("004", "Tablet", "Electronics"),
                new Product("002", "Headphones", "Accessories")
        };

        Commerce com = new Commerce(productsArray);

        // Linear search
        System.out.println("Linear Search:");
        Product result = com.linearSearch("002");
        if (result != null) {
            System.out.println("Product found: " + result);
        } else {
            System.out.println("Product not found");
        }

        // Sort products for binary search
        com.sortProducts();

        // Binary search
        System.out.println("Binary Search:");
        result = com.binarySearch("010");
        if (result != null) {
            System.out.println("Product found: " + result);
        } else {
            System.out.println("Product not found");
        }
    }
}
