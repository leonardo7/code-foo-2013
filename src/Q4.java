import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;


public class Q4 {

	
	public static  int n  = 10 ;// n is the number of family members (nodes in tree)
	
	public static int parent[] = new int[n]; // using parent array representation of the tree
	
	public static String names[] = new String [n] ;// names of the tree nodes
	
	static TreeMap<String, ArrayList<Integer>> nameMap  = new TreeMap<String, ArrayList<Integer>>();
	
	static ArrayList<ArrayList<Integer>> familyTree = new ArrayList<ArrayList<Integer>>();
	
	
	public static void init(){
		// create the adjacency list
		for (int i = 0; i < n; i++) 
			familyTree.add(new ArrayList<Integer>());
		
		//set the namesMap to make search O(logn)
		ArrayList<Integer> nodes;
		
		for (int i = 0; i < n; i++) {
			if (parent[i]!= i) // or simply if i == 0
				familyTree.get(parent[i]).add(i);
			
			if ((nodes = (ArrayList<Integer>)nameMap.get(names[i])) != null){
				nodes.add(i);
			}else{
				nodes = new ArrayList<Integer>();
				nodes.add(i);
				nameMap.put(names[i],nodes);
			} 
		}
		
		
	}

	static ArrayList<Integer> findByLevel(int generation){
		// returns an arrayList of nodes at level generation
		// using BFS 
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> lev = new LinkedList<Integer>();
		q.add(0);// adding the root
		lev.add(0);
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		ArrayList<Integer> temp;
		int cur, level;
		
		while (!q.isEmpty()){
			cur = q.poll();
			level = lev.poll();
			temp = familyTree.get(cur);
			if (level == generation-1)
					ret.addAll(0, temp );
			
			else for (int i: temp) {
				q.add(i);
				lev.add(level+1);
			}
		}
		return ret;
	}
	
	static ArrayList<Integer> findByName(String name){
		return nameMap.get(name);
	}
	
	public static void main(String[] args) {
		// for testing
		n = 7;
		parent = new int[]{0,0,0,1,1,2,4};
		names = new String[]{"a", "b", "c", "c", "a", "b", "b"};
		
		init();
		
		for (int i :findByLevel(2)) {
			System.out.println(i +" "+names[i]);
		}
		
		System.out.println("---");
		for (int i :findByName("a")) {
			System.out.println(i +" "+names[i]);
		}
	}

}
