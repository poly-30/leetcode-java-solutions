class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0); 
        ListNode current = head;
        while (current != null) {
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < current.val) {
                prev = prev.next;
            }
            ListNode nextNode = current.next;
            current.next = prev.next;
            prev.next = current;
            current = nextNode;
        }
        return dummy.next;
    }
}