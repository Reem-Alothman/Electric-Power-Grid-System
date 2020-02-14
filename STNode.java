
public class STNode<T> {
	
		public T data;
		public STNode<T> parent;
		public LinkedList<STNode<T>> children;
		//public STNode<T> next;
		

		/** Creates a new instance of STNode */
		public STNode(T val) {
			data = val;
			parent  = null;
			children = new LinkedList<STNode<T>>();
			
		}
		
		public void addChild(STNode<T> child) {
			child.setParent(this);
			children.insert(child);
		}
		
		public void setParent(STNode<T> parent) {
			this.parent = parent;
		}

}
