package src.ch05;

public class LinkList {
	private Link first;

	public void insert(long value) {
		Link lnk = new Link(value);
		if (first == null) {
			first = lnk;
		} else {
			lnk.setNext(first);
			first = lnk;
		}
	}

	public void displayAll() {
		Link current = first;
		while (current != null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
	}

	// ���ҽ��
	public Link find(long key) {
		Link current = first;
		while (current.getData() != key) {
			if (current.getNext() == null) {
				return null;
			}
			current = current.getNext();
		}
		return current;
	}

	// �����㵽ָ��λ��
	public void insert(long value, int pos) {
		if (pos == 0) {
			insert(value);
		} else {

			Link current = first;
			for (int i = 0; i < pos - 1; i++) {
				current = current.getNext();
			}
			Link lnk = new Link(value);
			lnk.setNext(current.getNext());
			current.setNext(lnk);
		}
	}
	
	// ɾ��ָ�����
	public void delete(long key) {
		Link current = first;
		Link ago = first;
		while(current.getData() != key) {
			if(current.getNext() == null) {
				return;
			} else {
				ago = current;
				current = current.getNext();
			}
		}
		
		if(current == first) {
			first = first.getNext();
		} else {
			ago.setNext(current.getNext());
		}
	}

}
