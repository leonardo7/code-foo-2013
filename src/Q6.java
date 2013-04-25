import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Q6 {

	static ArrayList<String> dict = new ArrayList<String>(1005);

	
	// instead of building the graph (the graph size may grow enormously)
	// traverse the list on each query which is the same order O(n^2)
	static int bfs(String s, String t){
		Queue<String> q = new LinkedList<String>();
		Queue<Integer> level = new LinkedList<Integer>();
		
		q.add(s);
		level.add(0);
		
		String cur ;
		int curLevel;
		int diff;
		
		while (!q.isEmpty()){
			cur = q.poll();
			curLevel = level.poll();
			
			if (cur.equals(t))
				return curLevel;
			
			
			for (String word : dict) {
				diff = 0;
				for (int i = 0; i < cur.length(); i++)
					diff += ((word.charAt(i)!=cur.charAt(i))?1:0);
				
				if (diff==1){
					q.add(word);
					level.add(curLevel+1);
				}

			}
		}
		return -1 ;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("three-letter-words.txt"));
		
		String s;
		while ((s = br.readLine())!= null)
			dict.add(s);
		
		br = new BufferedReader( new InputStreamReader(System.in));
		String ss[];
		int sol ;
		while ((s = br.readLine())!= null && !s.isEmpty()){
			ss = s.split(" ");
			sol = bfs(ss[0].toUpperCase(),ss[1].toUpperCase());
			System.out.print(ss[0] +" -> " + ss[1]+"  ");
			if (sol!=-1)
				System.out.println("# steps = "+sol);
			else 
				System.out.println("No Answer");
		}
	}

}
