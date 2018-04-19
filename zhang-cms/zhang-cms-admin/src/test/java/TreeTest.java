import org.junit.Test;

import java.util.*;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/30
 * @history
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
public class TreeTest {

    TreeNode head = null;
    TreeNode realHead = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        ConverSub(pRootOfTree);
        return realHead;
    }
    public void ConverSub(TreeNode node){
        if(node == null) return;
        ConverSub(node.left);
        if(head == null){
            head = node;
            realHead = node;
        }else{
            head.right = node;
            node.left = head;
            head = node;
        }
        ConverSub(node.right);
    }

    public static void main(String[] args) {
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);

        t5.left = t3;t5.right=t7;
        t3.left=t2;t3.right=t4;
        t7.left=t6;t7.right=t8;

        TreeTest test = new TreeTest();
        /*TreeNode t = test.Convert(t5);
        while(t.right!=null){
            System.out.println(t.val);
            t=t.right;
        }*/
        TreeNode node = test.QueueConvert(t5);
        while(node.right!=null){
            System.out.println(node.val);
            node=node.right;
        }
    }


    public TreeNode QueueConvert(TreeNode pRootOfTree) {
        TreeNode root = null;
        Queue<TreeNode> q = new LinkedList<>();
        queyesub(q, pRootOfTree);
        if(root == null){
            root = q.peek();
        }
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            temp.right = q.peek();
            if (q.peek()!=null)
                q.peek().left = temp;
        }
        return root;
    }
    public void queyesub(Queue q, TreeNode node){
        if (node==null)return;
        queyesub(q, node.left);
        q.offer(node);
        queyesub(q,node.right);
    }

    //@Test
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<String>();
        String[] list = str.split("");
        int n = list.length;
        Arrays.sort(list);


        return result;
    }

    @Test
    public void PermutationTest(){
        String str="abc";
        List<String> list = Permutation(str);
        for (String l : list){
            System.out.println(l);
        }
    }

}
