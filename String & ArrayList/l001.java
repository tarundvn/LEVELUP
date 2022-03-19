import java.util.*;

//Basics of String
public class l001 {

  public static void printPalindrome(String str) {
    for (int i = 0; i < str.length(); i++) {
      for (int j = i + 1; j <= str.length(); j++) {
        String s = str.substring(i, j);
        if (isPalindrome(s)) System.out.println(s);
      }
    }
  }

  public static boolean isPalindrome(String str) {
    int i = 0;
    int j = str.length() - 1;
    while (i <= j) {
      if (str.charAt(i) != str.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static void stringCompression(String str) {
    char curr = str.charAt(0);
    int val = 1;
    String nstr = "";
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == curr) {
        val++;
      } else {
        nstr += curr;
        if (val > 1) nstr += val;
        curr = str.charAt(i);
        val = 1;
      }
    }
    nstr += curr;
    if (val > 1) nstr += val;
    System.out.println(nstr);
  }

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    String str = scn.nextLine();
    printPalindrome(str);
    stringCompression(str);
  }
}
// 1. Interning
//     1.1 What is Interning?
//         1.1.1 multiple strings point at same instance
//         1.1.2 intern pool in heap
//     1.2 Why Interning?
//         1.2.1 To Save Space
//     1.3 Implications:
//         1.3.1 equals : compares only adrress
//         1.3.2 Immuatbility
// 2. Immuatbility
//     2.1 What is Immuatbility? 
//         2.1.1 Reference is mutable
//         2.1.2 Instance is immuatbile
//     2.2 Why Immuatbility?
//         2.2.1 Interning
//         2.2.2 Change in One is reflected in other strings as well
//     2.3 Implications:
//         2.3.1 Lack of functions
//         2.3.2 Performance Issues 
//             //s = "hello";
//             //s+= "world";
//             // nya bna dega ye
//         2.3.3 Use String Builder instead