package J_6_LinkedList;

/*第一遍扫的时候巧妙运用next指针， 开始数组是1->2->3->4  。 
 * 然后扫描过程中 先建立copy节点 1->1`->2->2`->3->3`->4->4`, 
 * 然后第二遍copy的时候去建立边的copy， 拆分节点, 一边扫描一边拆成两个链表，
 * 这里用到两个dummy node。第一个链表变回  1->2->3 , 然后第二变成 1`->2`->3`  
 * */

//No HashMap version
public class _09Copy_List_with_Random_Pointer_No_HashMap {
  private void copyNext(RandomListNode head) {
      while (head != null) {
          RandomListNode newNode = new RandomListNode(head.label);
          newNode.random = head.random;
          newNode.next = head.next;
          head.next = newNode;
          head = head.next.next;
      }
  }

  private void copyRandom(RandomListNode head) {
      while (head != null) {
          if (head.next.random != null) {
              head.next.random = head.random.next;
          }
          head = head.next.next;
      }
  }

  private RandomListNode splitList(RandomListNode head) {
      RandomListNode newHead = head.next;
      while (head != null) {
          RandomListNode temp = head.next;
          head.next = temp.next;
          head = head.next;
          if (temp.next != null) {
              temp.next = temp.next.next;
          }
      }
      return newHead;
  }

  public RandomListNode copyRandomList(RandomListNode head) {
      if (head == null) {
          return null;
      }
      copyNext(head);
      copyRandom(head);
      return splitList(head);
  }
}