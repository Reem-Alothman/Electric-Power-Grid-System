public class PowerGridUtils {
	
	// Return the IDs of all elements in the power grid pg in pre-order.
	public static Queue<Integer> collectPreorder(GT<PGElem> pg){
		Queue<Integer> q = new LinkedQueue<Integer>();
		if(pg == null)
			return q;
		
		if(pg.empty())
			return q;
		
		pg.findRoot();
		
		Pre(pg,q);
		
	
		
		return q;
		}
	
	private static void Pre(GT<PGElem> pg,Queue<Integer> q) {
				if(pg == null)
					return;
			q.enqueue(pg.retrieve().getId());
		
			for(int i=0;i<pg.nbChildren();i++) {
				pg.findChild(i);
				Pre(pg,q);
				pg.findParent();
			}		
		
	}
	
	// Searches the power grid pg for the element with ID id. If found, it is made current and true is returned, otherwise false is returned.
	public static boolean find(GT<PGElem> pg, int id) {		
	//	GT<PGElem> s = pg;
	//	Queue<GT<PGElem>> q = new LinkedQueue<GT<PGElem>>();
		boolean found = false;
		pg.findRoot();

		found = ff(pg,id);
		
		/*for(int i=0;i<q.length();i++) {
			s = q.serve();
			if(s.retrieve().getId() == id) {
				found = true;
				pg = s;
				break;
			}
			if(found)
				return true;
			
		}*/	
		
			//System.out.println("found "+id +" : "+found);
			return found;	
	}	

	private static boolean ff(GT<PGElem> pg,int id) {
		
		/*q.enqueue(pg);
		for(int i=0;i<pg.nbChildren();i++) {
			pg.findChild(i);
			ff(pg,q);
			pg.findParent();
		}*/
		if(id== pg.retrieve().getId()) {
			return true;
		}
		for(int i=0;i<pg.nbChildren();i++) {
			pg.findChild(i);
		    boolean f = ff(pg,id);
		    if(f)
		    	return true;
			pg.findParent();
		}
		
		return false;
	}
	
	

	// Add the generator element gen to the power grid pg. This can only be done if the grid is empty. If successful, the method returns true. If there is already a generator, or gen is not of type Generator, false is returned.
	public static boolean addGenerator(GT<PGElem> pg, PGElem gen) {
		if(!(gen.getType() == ElemType.Generator))
			return false;
		if(pg.empty()) {
			pg.insert(gen);
			return true;
		}
		else 
			return false;
	}

	// Attaches pgn to the element id and returns true if successful. Note that a consumer can only be attached to a transmitter, and no element can be be attached to it. The tree must not contain more than one generator located at the root. If id does not exist, or there is already a element with the same ID as pgn, pgn is not attached, and the method retrurns false.
	public static boolean attach(GT<PGElem> pg, PGElem pgn, int id) {
		if(pg == null)
			return false;
		
		if(pgn == null)
			return false;
		
		if(pg.empty()){
			
		if(pgn.getType() == ElemType.Generator ) {
				pg.insert(pgn);
				return true;
			}
		
		}
		//if it's not empty
		else {
			
				if(find(pg,pgn.getId()))
				return false;
			
				if(find(pg,id)) {
					
				if(pg.retrieve().getType() == ElemType.Consumer)
					return false;							
			
				//find(pg,id);
				pg.insert(pgn);
				return true;
				}
			
				
		}
			
		return false;		
		
	}

	// Removes element by ID, all corresponding subtree is removed. If removed, true is returned, false is returned otherwise.
	public static boolean remove(GT<PGElem> pg, int id) {
		if(pg == null)
			return false;
		if(find(pg,id)) {
			pg.remove();
			return true;			
		}
		return false;
	}

	// Computes total power that consumed by a element (and all its subtree). If id is incorrect, -1 is returned.
	public static double totalPower(GT<PGElem> pg, int id) {
		if(pg == null)
			return -1;
		
			if(find(pg,id)) {
				
			double power = 0.0;
			power = calc(pg,power);
			return power;
			
		}
		return -1;
		
	}
	private static double calc(GT<PGElem> pg,double count) {
		
		
		if(pg.retrieve().getType() == ElemType.Consumer) {
			count += pg.retrieve().getPower();
		}
		else {
		for(int i=0;i<pg.nbChildren();i++) {
			pg.findChild(i);
		    count = calc(pg ,count);
			pg.findParent();
		}
		 }
		
		return count;
	}

	// Checks if the power grid contains an overload. The method returns the ID of the first element preorder that has an overload and -1 if there is no overload.
	public static int findOverload(GT<PGElem> pg) {
		pg.findRoot();
		Queue<Integer> ids = collectPreorder(pg);
		for(int i=0;i<ids.length();i++) {
			int iid = ids.serve();
			find(pg,iid);
			double power = pg.retrieve().getPower();
			if(totalPower(pg,iid)>power) {
				return iid;
			}
			}
		return -1;
	}
}
