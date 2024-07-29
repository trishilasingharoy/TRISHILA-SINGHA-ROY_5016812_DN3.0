import java.util.ArrayList;
import java.util.List;

interface Stock {
    void register(Observer observer);

    void deregister(Observer observer);

    void notifyObservers();
}



class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockData;

    public StockMarket() {
        observers = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockData);
        }
    }

    public void setStockData(String stockData) {
        this.stockData = stockData;
        notifyObservers();
    }
}
interface Observer {
    void update(String stockData);
}
class MobileApp implements Observer {
    @Override
    public void update(String stockData) {
        System.out.println("MobileApp received stock update: " + stockData);
    }
}
class WebApp implements Observer {
    @Override
    public void update(String stockData) {
        System.out.println("WebApp received stock update: " + stockData);
    }
}
class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.register(mobileApp);
        stockMarket.register(webApp);

        stockMarket.setStockData("Update of Stock data 1");

        stockMarket.deregister(mobileApp);

        stockMarket.setStockData("Update Stock data 2");
    }
}