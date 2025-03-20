public class Stock {
    private String symbol;
    private String name;
    private double previousClosingPrice;
    private double currentPrice;

    public Stock(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public double getChangePercent() {
        return ((currentPrice - previousClosingPrice) / previousClosingPrice) * 100;
    }

    public void setPreviousClosingPrice(double price) {
        previousClosingPrice = price;
    }

    public void setCurrentPrice(double price) {
        currentPrice = price;
    }
}
