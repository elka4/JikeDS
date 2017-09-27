package J_6_List_Array.List;

import lib.ListNode;

import java.util.ArrayList;
import java.util.List;

public class _08Merge_k_Sorted_Lists_mergeTwobyTwo {
	/**
	 * @param lists: a list of ListNode
	 * @return: The head of one sorted list.
	 */
	public ListNode mergeKLists(List<ListNode> lists) {  
	    if (lists == null || lists.size() == 0) {
	        return null;
	    }
	    
	    while (lists.size() > 1) {
	        List<ListNode> new_lists = new ArrayList<ListNode>();
	        for (int i = 0; i + 1 < lists.size(); i += 2) {
	            ListNode merged_list = merge(lists.get(i), lists.get(i+1));
	            new_lists.add(merged_list);
	        }
	        if (lists.size() % 2 == 1) {
	            new_lists.add(lists.get(lists.size() - 1));
	        }
	        lists = new_lists;
	    }
	    
	    return lists.get(0);
	}
	
	private ListNode merge(ListNode a, ListNode b) {
	    ListNode dummy = new ListNode(0);
	    ListNode tail = dummy;
	    while (a != null && b != null) {
	        if (a.val < b.val) {
	            tail.next = a;
	            a = a.next;
	        } else {
	            tail.next = b;
	            b = b.next;
	        }
	        tail = tail.next;
	    }
	    
	    if (a != null) {
	        tail.next = a;
	    } else {
	        tail.next = b;
	    }
	    
	    return dummy.next;
	}
}
