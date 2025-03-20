//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.text.DecimalFormat;

public class WrapperCharAndStringClasses {
    public static void main(String[] args) {
        //wrapperClassExamples();
        //characterInfo('C');
        //moreOnString();
        demoStringBuilder();
    }

    public static void wrapperClassExamples() {

        /*parser
        * parse methods return return primitive values;
        * may throw a NullPointerException if the input string is null
        * more suitable for obtaining primitive values directly.
        * */
        int anInt = Integer.parseInt("649");
        double doubleValue = Double.parseDouble("147.82");
        System.out.println("Parser addition: " + (anInt + doubleValue));
        double result = anInt + doubleValue;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4); //set the maximum number of digits allowed in the fractional part of a number.
        System.out.println("Parser addition after formatting: " + df.format(result));
        System.out.println(df.format(12.3456789));

        System.out.println("\nUsing valueOf():\n");

        /* valueof - data conversion
        * valueOf methods return objects; handle null strings gracefully
        * preferred when working with objects or when handling null strings
        * */
        Byte B = Byte.valueOf("123");
        System.out.println(B);

        Short S = Short.valueOf("25");
        System.out.println(S);

        Integer I = Integer.valueOf("46");
        System.out.println(I);

        Long L = Long.valueOf("235");
        System.out.println(L);

        Float F = Float.valueOf("23.5");
        System.out.println(F);

        Double D = Double.valueOf("15.4");
        System.out.println(D);

        Boolean BLN = Boolean.valueOf("true");
        System.out.println(BLN);

        BLN = Boolean.valueOf("false");
        System.out.println(BLN);

        BLN = Boolean.valueOf("abc");
        System.out.println(BLN);

        System.out.println("Integer + Float: " + (I + F));
    }

    public static void characterInfo(char ch) {
        char aChar = ch;
        System.out.println("The character is " + aChar);

        if(Character.isUpperCase(aChar))
            System.out.println(aChar + " is uppercase");
        else
            System.out.println(aChar + " is not uppercase");

        if(Character.isLowerCase(aChar))
            System.out.println(aChar + " is lowercase");
        else
            System.out.println(aChar + " is not lowercase");

        aChar = Character.toLowerCase(aChar);
        System.out.println("After tolowerCase(), aChar is " + aChar);

        aChar = Character.toUpperCase(aChar);
        System.out.println("After toUpperCase(), aChar is " + aChar);

        if(Character.isLetterOrDigit(aChar))
            System.out.println(aChar + " is a letter or digit");
        else
            System.out.println(aChar + " is neither a letter nor a digit");

        if(Character.isWhitespace(aChar))
            System.out.println(aChar + " is whitespace");
        else
            System.out.println(aChar + " is not whitespace");

    }

    public static void moreOnString() {

        String aWord = "heLLo!";
        System.out.println( aWord.toUpperCase() + " " + aWord.toLowerCase());
        System.out.println("original aWord: " + aWord);
        System.out.println("Index of e: " + aWord.indexOf('e'));
        System.out.println(aWord.startsWith("heL"));
        System.out.println(aWord.endsWith("heL"));
        System.out.println();

        //string is immutable
        String newWord = aWord.replace('L', '*');
        System.out.println("After replaced: " + newWord);
        newWord = aWord.replaceAll("[h!]", "OOP");
        System.out.println(newWord);
        System.out.println("original aWord: " + aWord);
        System.out.println();

        /* convert int/double to string objects */
        String theString;
        int someInt = 4;
        theString = Integer.toString(someInt);

        String anotherString;
        double someDouble = 8.25;
        anotherString = Double.toString(someDouble);

        System.out.println(theString + " " + anotherString);
        System.out.println();

        /* compareTo: based on Unicode value
         * returns zero if the values of two Strings are exactly the same
         * returns a negative number if the calling object is “less than” the argument
         * returns a positive number if the calling object is “more than” the argument
         */

        System.out.println("Compare Roger to Robert:" +
                "Roger".compareTo("Robert"));
        System.out.println();

        /* region matches */
        String firstString = "abcde";
        String secondString = "xxbcdef";

        System.out.println(firstString.regionMatches(1, secondString, 2, 4));
        System.out.println(firstString.regionMatches(0, secondString, 3, 2));
        System.out.println();

        /* interned string:
         * If you declare two String objects and initialize both to the same value,
         * the value is stored only once in memory and the two object references
         * hold the same memory address.*/
        String s1 = "Interned string";
        String s2 = new String("Interned string");
        String s3 = "Interned string";
        System.out.println("s1 == s2 is " + (s1 == s2));
        System.out.println("s1 == s3 is " + (s1 == s3));
        System.out.println();

        /* splitting string */
        String splitString = "CSCI 1301#Object-Oriented#Programming";
        for (String s: splitString.split("-"))
            System.out.println(s);
        System.out.println();
        String[] splitStringArr = "Java,C?C#,C++".split("[.,:;?]");
        for (String s:splitStringArr)
            System.out.println(s);
        System.out.println("Array size: " + splitStringArr.length);
        System.out.println("Language at index 1: " + splitStringArr[1]);

    }

    public static void demoStringBuilder() {

        // must use the keyword new for StringBuilder
        StringBuilder nameString = new StringBuilder("Barbara");

        /*
         * contains a memory block called a buffer
         * if it contains a string, the string might not occupy the entire buffer
         * The actual length of the buffer is the capacity
         * 16 "extra positions" allow for reasonable modification
         */
        int nameStringCapacity = nameString.capacity();
        System.out.println("Capacity of nameString is " + nameStringCapacity);

        StringBuilder addressString = null; //does not yet hold a memory address
        addressString = new StringBuilder("6311 Hickory Nut Grove Road");

        int addStringCapacity = addressString.capacity();
        System.out.println("Capacity of addressString is " + addStringCapacity);
        System.out.println("The address is " + addressString);

        addressString.setLength(20);
        System.out.println("The address is [after changing capacity] " + addressString);

        System.out.println("The replace name is " + nameString.replace(3, 6, "OOP"));
        System.out.println("The deleted char name is " + nameString.delete(2, 4));
        System.out.println("The reverse name is " + nameString.reverse());
        System.out.println("The original name after applying StringBuilder method " + nameString);
        System.out.println();

        StringBuilder str = new StringBuilder("singing");
        System.out.println(str);
        System.out.println("Capacity: " + str.capacity());
        str.append(" in the dead of ");
        System.out.println(str);
        str.insert(0, "Black ");
        System.out.println(str);
        str.insert(5, " bird");
        System.out.println(str);
        str.append("night");
        System.out.println(str);
        System.out.println("Capacity: " + str.capacity());
    }
}