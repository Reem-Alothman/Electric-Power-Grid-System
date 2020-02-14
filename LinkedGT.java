
public class LinkedGT<T> implements GT<T> {
		
		public STNode<T> root , current ;
		
		
		public LinkedGT () {
			root = current = null;
		}
		
	
	// Return true if the tree is empty
		public boolean empty() {
			return root == null;
		}

		// Return true if the tree is full
		public boolean full() {
			return false;
		}

		// Return the data of the current node
		public T retrieve() {
			return current.data;
		}

		// Update the data of the current node
		public void update(T e) {
			current.data = e;
		}

		// If the tree is empty e is inserted as root. If the tree is not empty, e is added as a child of the current node. The new node is made current and true is returned.
		public boolean insert(T e) {
			if(e==null)
				return false;
			
			if (empty()) {
				root = new STNode<T>(e);
				root.setParent(null);
				current = root;
				return true;
			}
			
			else {
				//STNode<T> n = new STNode<T>(e);
				current.addChild(new STNode<T>(e));
				current = current.children.current.data;
				return true;
			}
		}

		// Return the number of children of the current node.
		public int nbChildren() {
			int count = 0;
			if(current.children.empty()) {
				return count;
			}
			else {
				STNode<T> cur = current;
				current.children.findfirst();
				while(!current.children.last()) {
					current.children.findnext();
					count++;
				}
				count++;
				current = cur;
				return count;
			}
		}

		// Put current on the i-th child of the current node (starting from 0), if it exists, and return true. If the child does not exist, current is not changed and the method returns false.
		public boolean findChild(int i) {
			if(current.children.empty() || nbChildren() <= i) {
				return false;
			}
			
			STNode<T> cu = current;
			cu.children.findfirst();
			for(int j = 0; j<i;j++) {
				cu.children.findnext();	
			}
			current = cu.children.current.data;
			return true;
			
		}

		// Put current on the parent of the current node. If the parent does not exist, current does not change and false is returned.
		public boolean findParent() {
			if(current.parent != null) {
				current = current.parent;
				return true;
			}
			return false;
		}

		// Put current on the root. If the tree is empty nothing happens.
		public void findRoot() {
			current = root;
		}

		// Remove the current subtree. The parent of current, if it exists, becomes the new current.
		public void remove() {
			
			if(current.parent != null) {
		    STNode<T> g = current.parent;
		    current.parent.children.remove();
		    current.children.remove();
		    current = g;
			}
			else 
				current = root = null;
			
		}
}
