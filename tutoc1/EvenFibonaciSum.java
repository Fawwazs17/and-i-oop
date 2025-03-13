// ADAM FAWWAZ BIN SAZALIZAM
// 2416969
public class EvenFibonaciSum {
    public static void main(String[] args) {
        int a = 1, b = 2;
        int sum = 0;
        
        while (b <= 4_000_000) {
            if (b % 2 == 0) {
                sum += b;
            }
            int next = a + b;
            a = b;
            b = next;
        }
        
        System.out.println("Sum of even Fibonacci numbers up to 4 million: " + sum);
    }
}
