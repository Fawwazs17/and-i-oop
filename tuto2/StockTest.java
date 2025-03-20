/*
 * Author: ADAM FAWWAZ BIN SAZALIZAM, MAT NUM 24116969, SECT 5
 */
public class StockTest {
    public static void main(String[] args) {
        Stock stock = new Stock("ORCL", "Oracle Corporation");
        stock.setPreviousClosingPrice(34.5);
        stock.setCurrentPrice(34.35);
        
        System.out.printf("Price change percentage: %.2f%%", stock.getChangePercent());
    }
}
