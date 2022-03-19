import java.util.*;

// 0 AND 1 KA CHECK
// if(head==null || head.next ==null)
// return head;
//ODD EVEN DONO PE DRY RUN
// BHAR BHAR KE SPACE USE KARNA
public class l001 {

  //Mathematical proof is imp == SPEED DISTANCE FORMULA
  //TC : O(N)
  public ListNode middleNode(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode slow = head;
    ListNode fast = head;

    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }

  //Reverse
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode forw = curr.next;
      curr.next = prev;
      prev = curr;
      curr = forw;
    }
    return prev;
  }

  //Palindrome
  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) return true;
    ListNode mid = middleNode(head);
    ListNode rev = reverseList(mid);

    ListNode curr1 = head;
    ListNode curr2 = rev;

    while (curr1 != null && curr2 != null) {
      if (curr1.val == curr2.val) {
        curr1 = curr1.next;
        curr2 = curr2.next;
      } else break;
    }
    return curr1 == null || curr2 == null;
  }

  //Fold Linked List
  public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;

    ListNode mid = middleNode(head);
    ListNode temp = mid.next;
    mid.next = null;
    ListNode rev = reverseList(temp);

    ListNode curr1 = head;
    ListNode curr2 = rev;

    while (curr2 != null) {
      ListNode forw1 = curr1.next;
      ListNode forw2 = curr2.next;

      curr1.next = curr2;
      curr1 = forw1;
      curr2.next = curr1;
      curr2 = forw2;
    }
  }
  //

  public static void main(String[] args) {
    //
  }
}
