/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2018/1/30
 * @history
 */
public class AddTwoNumbersTest {


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            ListNode cur = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;
            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(4,l3);
        ListNode l1 = new ListNode(2,l2);

        ListNode t3 = new ListNode(4);
        ListNode t2 = new ListNode(6, t3);
        ListNode t1 = new ListNode(5, t2);

        AddTwoNumbersTest t = new AddTwoNumbersTest();
        t.addTwoNumbers(l1,t1);
    }
}
