package 链表;

public class _086_分割链表 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ListNode partition(ListNode head, int x) {
		if (head == null) return null;
		
		ListNode lHead = new ListNode(0);
		ListNode lTail = lHead;
		ListNode rHead = new ListNode(0);
		ListNode rTail = rHead;
		while (head != null) {
			if (head.val < x) { // 放在 lTail 后面
				lTail.next = head;
				lTail = head;
			} else { // 放在 rTail 后面
				rTail.next = head;
				rTail = head;
			}
			head = head.next;
		}
		/**
		 * 此句不能少，因为可能会出现这样的情况：
		 * 原链表倒数第 N 个节点 A 的值 >= x 的，A 后所有的节点都是 < x 的
		 * 然后 rTail.next 其实就是 A.next 
		 */
		rTail.next = null;
		// 将 rHead.next 拼接到 lTail后面
		lTail.next = rHead.next;
		
		return lHead.next;
    }

}
