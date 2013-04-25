import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

public class Q6 {

	static ArrayList<String> dictionary = new ArrayList<String>(1020);
	static String dict[];
	static int parent[];

	// instead of building the graph (the graph size may grow enormously)
	// traverse the list on each query which is the same order O(n^2)

	static int bfs(int s, int t) {
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> level = new LinkedList<Integer>();
		// for path reconstruction for every node in q keep the index if the
		// words in the dic
		BitSet visited = new BitSet(dict.length);
		
		parent[s] = s;
		q.add(s);
		level.add(0);
		visited.set(s);
		
		int cur, curLevel, diff;

		while (!q.isEmpty()) {
			cur = q.poll();
			curLevel = level.poll();

			if (cur == t)
				return curLevel;

			for (int i = 0; i < dict.length; i++) {
				if (visited.get(i))
					continue;
				diff = 0;
				for (int j = 0; j < dict[cur].length(); j++)
					diff += ((dict[i].charAt(j) != dict[cur].charAt(j)) ? 1 : 0);

				if (diff == 1) {
					q.add(i);
					level.add(curLevel + 1);
					visited.set(i);
					parent[i] = cur;
				}

			}
		}
		return -1;
	}

	static void reconstructPath(int i) {
		// reconstructs the path from the parent array
		if (parent[i] != i)
			reconstructPath(parent[i]);
		System.out.print(dict[i] + "->");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(
				"three-letter-words.txt"));

		String s;
		while ((s = br.readLine()) != null)
			dictionary.add(s);

		dict = new String[dictionary.size()];
		dict = dictionary.toArray(dict);
		parent = new int[dict.length];

		br = new BufferedReader(new InputStreamReader(System.in));
		String ss[];
		int st, tr, sol;
		while ((s = br.readLine()) != null && !s.isEmpty()) {
			ss = s.split(" ");
			st = Arrays.binarySearch(dict, ss[0].toUpperCase());
			tr = Arrays.binarySearch(dict, ss[1].toUpperCase());
			sol = bfs(st, tr);
			if (sol != -1) {
				reconstructPath(tr);
				System.out.println("  " + sol + " moves.");
			} else
				System.out.println(ss[0] + " -> " + ss[1] + " No Answer");
		}
	}

}
