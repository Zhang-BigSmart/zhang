/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/10/25
 * @history
 */

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }
}

public class ListNodeTest {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int balance = getLength(pHead1) - getLength(pHead2);
        if (balance > 0){
            for(int i=0; i<balance; i++)
                pHead1 = pHead1.next;
        }else{
            for(int i=0; i<balance; i++)
                pHead2 = pHead2.next;
        }
        while(pHead1 != null && pHead1 != pHead2) {
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return pHead1;
    }

    public int getLength(ListNode node){
        int i = 0;
        while (node != null){
            i++;
            node = node.next;
        }
        return i;
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n6;
        n6.next = n3;
        n2.next = n4;
        n3.next = n5;
        n4.next = n5;

        ListNodeTest test = new ListNodeTest();
        ListNode result = test.FindFirstCommonNode(n1,n2);
        System.out.println(result.val);
    }


    public int GetNumberOfK(int [] array , int k) {
        int length = array.length;
        if(length == 0){
            return 0;
        }
        int firstK = getFirstK(array, k, 0, length-1);
        int lastK = getLastK(array, k, 0, length-1);
        if(firstK != -1 && lastK != -1){
            return lastK - firstK + 1;
        }
        return 0;
        }
        //递归写法
        private int getFirstK(int [] array , int k, int start, int end){
            if(start > end){
                return -1;
            }
            int mid = (start + end) >> 1;
            if(array[mid] > k){
                return getFirstK(array, k, start, mid-1);
            }else if (array[mid] < k){
                return getFirstK(array, k, mid+1, end);
            }else if(mid-1 >=0 && array[mid-1] == k){
                return getFirstK(array, k, start, mid-1);
            }else{
                return mid;
            }
        }
        //循环写法
        private int getLastK(int [] array , int k, int start, int end){
            int length = array.length;
            int mid = (start + end) >> 1;
            while(start <= end){
                if(array[mid] > k){
                    end = mid-1;
                }else if(array[mid] < k){
                    start = mid+1;
                }else if(mid+1 < length && array[mid+1] == k){
                    start = mid+1;
                }else{
                    return mid;
                }
                mid = (start + end) >> 1;
            }
                return -1;
        }
}
