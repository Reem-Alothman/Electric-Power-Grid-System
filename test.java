
public class test {

	public static void main(String[] args) {
		LinkedGT<Integer> pg = new LinkedGT<Integer>();
		pg.insert(1);
		pg.insert(2);
		pg.insert(8);
		pg.insert(9);
		pg.insert(11);
		//System.out.println(pg.retrieve());
		System.out.println(pg.findParent());
		//System.out.println(pg.retrieve());
		System.out.println("-------------------------");
		pg.insert(10);
		//System.out.println(pg.retrieve());
		System.out.println(pg.findParent());
		System.out.println(pg.retrieve());
		pg.insert(12);
		pg.findRoot();
		pg.insert(3);
		pg.insert(4);
		pg.findRoot();
		pg.insert(5);
		pg.insert(6);
		pg.findParent();
		pg.findParent();
		System.out.println("number of children "+pg.nbChildren());
		System.out.println("find ch "+pg.findChild(1));
		System.out.println(pg.retrieve());
		pg.remove();
		System.out.println("find ch "+pg.findChild(1));
		System.out.println(pg.retrieve());
		System.out.println("-------------------------");


	}

}
