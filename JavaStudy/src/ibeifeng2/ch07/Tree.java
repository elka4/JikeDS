package ibeifeng2.ch07;

public class Tree {
	// ��
	private Node root;

	// ���뷽��
	public void insert(int keyData, int otherData) {
		Node newNode = new Node(keyData, otherData);

		// ���˵û�нڵ�
		if (root == null) {
			root = newNode;
		} else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (keyData < current.getKeyData()) {
					current = current.getLeftNode();
					if (current == null) {
						parent.setLeftNode(newNode);
						return;
					}
				} else {
					current = current.getRightNode();
					if (current == null) {
						parent.setRightNode(newNode);
						return;
					}
				}
			}
		}
	}

	// ���ҷ���
	public Node find(int keyData) {
		Node current = root;
		while (current.getKeyData() != keyData) {
			if (keyData < current.getKeyData()) {
				current = current.getLeftNode();
			} else {
				current = current.getRightNode();
			}
			if (current == null) {
				return null;
			}
		}
		return current;
	}

	// �޸ķ���
	public void change(int keyData, int newOtherData) {
		Node findNode = find(keyData);
		findNode.setOtherData(newOtherData);
	}

	// �������
	public void preOrder(Node node) {
		if (node != null) {
			node.display();
			preOrder(node.getLeftNode());
			preOrder(node.getRightNode());
		}
	}

	// �������
	public void inOrder(Node node) {
		if (node != null) {
			inOrder(node.getLeftNode());
			node.display();
			inOrder(node.getRightNode());
		}
	}

	// �������
	public void endOrder(Node node) {
		if (node != null) {
			endOrder(node.getLeftNode());
			endOrder(node.getRightNode());
			node.display();
		}
	}

	public Node getRoot() {
		return root;
	}
}
