import java.util.*;
//Basics of String Builder
public class l001 {

    public static String toggleString(String str)
    {
        StringBuilder sb = new StringBuilder(str);
        for(int i=0;i<str.length();i++)
        {
            char ch = sb.charAt(i);
            if(ch>= 'A' && ch<='Z')
            {
                char lc = ch + 'a' - 'A';
                sb.setCharAt(i,lc);
            }
            else
            {
                char uc = ch + 'a' - 'A';
                sb.setCharAt(i,uc);
            }
        }
        return sb.toString();
    }

  public static void main(String[] args) {
      //sb.charAt(i);
      //sb.setCharAt(i,ch);
      //sb.insert(i,ch);
      //sb.deleteCharAt(i);
      //sb.toString();
      //sb.length();
      //sb is mutable
  }
}