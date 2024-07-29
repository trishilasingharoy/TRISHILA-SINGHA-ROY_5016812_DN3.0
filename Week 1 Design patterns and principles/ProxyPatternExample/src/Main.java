// Image.java
interface Image {
    void display();
}

// RealImage.java
class RealImage implements Image {
    private final String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromServer();
    }

    private void loadFromServer() {
        System.out.println("Loading image: " + filename);
    }


    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// ProxyImage.java
class ProxyImage implements Image {
    private RealImage realImage;
    private final String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }


    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// ProxyPatternTest.java
class ProxyPatternTest {
    public static void main(String[] args) {
        // Create ProxyImage instances
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        // Display images
        // The image will be loaded and displayed only when `display` is called for the first time.
        image1.display();

        image1.display();

        image2.display();

        image2.display();
    }
}
