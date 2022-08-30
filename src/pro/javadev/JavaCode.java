package pro.javadev;

public class JavaCode {

    public static void main(String[] args) {


//        System.out.println(
//                appendBefore(10, 'Z', 5)
//        );
//
//        System.out.println(
//                appendBefore(10, 'X', 3)
//        );
//
//        System.out.println(
//                appendBefore(21344, '0', 10)
//        );

        System.out.println(
                appendBefore(11, '0', 2)
        );

        System.out.println(
                appendBefore(10, '0', 2)
        );

        System.out.println(
                appendBefore(2, '0', 2)
        );

        System.out.println(
                appendBefore(2, '0', 10)
        );
    }

    private static String appendBefore(int number, char character, int length) {
        return number < (int) Math.pow(10, length) - 1
                ? new String(new char[(int) (length - Math.floor(Math.log10(number)) - 1)]).replace('\0', character) + number
                : String.valueOf(number);
    }

}
