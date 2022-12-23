//Gabriel Souza

public class TreapTest {
  public static void main(String[] args) {

		// TEST CASES - create new treap
		Treap <Integer> testTree  =  new Treap <Integer>();  
		
		// add nodes to treap
		testTree.add (4, 19); 
		testTree.add (2, 31); 
		testTree.add (6, 70);
		testTree.add (1, 84);
		testTree.add (3, 12); 
		testTree.add (5, 83);  
		testTree.add (7, 26); 
	
		// output treap as String 
		System.out.println("Initial treap:");
		testTree.toString();		

		// searching for nodes in treap
		
		System.out.println();
		System.out.print("Searching for int '2' in treap: ");
		if(testTree.find(2)) { //search for node 2 (in treap)
			System.out.println("Int '2' in treap");
		}
		else {
			System.out.println("Int '2' not in treap");
		}
		
		System.out.println();
		System.out.print("Searching for int '8' in treap: ");
		if(testTree.find(8)) { //search for node 8 (not in treap)
			System.out.println("Int '8' in treap");
		}
		else {
			System.out.println("Int '8' not in treap");
		}
				
		// delete nodes from treap
		
		System.out.println();
		if (testTree.delete(5)) { // delete node 5 from treap if found
			System.out.println("Int '5' deleted from treap");
		}
		else {
			System.out.println("Int '5' not deleted from treap (not found)");
		}
	
		System.out.println("New Treap:");
		testTree.toString();
		
		System.out.println();
		if (testTree.delete(1)) { // delete node 1 from treap if found
			System.out.println("Int '1' deleted from treap");
		}
		else {
			System.out.println("Int '1' not deleted from treap (not found)");
		}
		
		System.out.println("New Treap:");
		testTree.toString();
		
		System.out.println();
		if (testTree.delete(1)) { // delete node 10 from treap if found
			System.out.println("Int '10' deleted from treap");
		}
		else {
			System.out.println("Int '10' not deleted from treap (not found)");
		}
		
		System.out.println("New Treap:");
		testTree.toString();
		
		System.out.println();
		System.out.println();
		System.out.println();
		// EDGE CASES 
		Treap <Integer> newTree  =  new Treap <Integer>(0); // initializes treap with seed for random number generator
		// newTree.toString(); // Illegal Argument Exception
		
		if(newTree.add(5)) {  // generating random priority
			System.out.println("Int '5' added to new treap");
		}
		
		if(!newTree.add(5)) { // adding duplicate key
			System.out.println("Cannot add int '5' again");
		}
		
		newTree.add(2, 7);
		
		System.out.println("New Treap:");
		newTree.toString();
		
		if (!newTree.add(1, 7)) { // adding duplicate priority
			System.out.println("Parent/child nodes cannot have duplicate priorities");
		}
		
		newTree.add(10, 7);
		System.out.println(); 
		System.out.println("New Treap:");
		newTree.toString();
		
	}
	
}
