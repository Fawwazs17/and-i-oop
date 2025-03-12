// ADAM FAWWAZ BIN SAZALIZAM
// 2416969
import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a string : ");
        String s = input.nextLine();
        
        int low = 0;
        int high = s.length()-1; 
        boolean isPalindrom = true;
        
        while (low<high){
            if (s.charAt(low) != s.charAt(high)){
            isPalindrom = false;
            break;
        }
            low++;
            high--;
    }
        if(isPalindrom){
            System.out.println("is palindrome");
            
        }
        else{
            System.out.println("is not palindrome");
        }

        input.close();
    }
    
}
