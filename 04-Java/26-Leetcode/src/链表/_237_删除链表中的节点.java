package 链表;

public class _237_删除链表中的节点 {

	/**
	 * 从链表里删除一个节点 node 的最常见方法是修改之前节点的 next 指针，使其指向之后的节点
	 * 
	 * 因为，我们无法访问我们想要删除的节点 之前 的节点，我们始终不能修改该节点的 next 指针。相反，我们必须将想要删除的节点的值替换为它后面节点中的值，然后删除它之后的节点。
	 * @param node
	 */
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
    }
}
