import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Q3 {

	static class MatchedToken {
		int sy, sx, ey, ex;// start x,y end x,y
		String direction;

		// TODO change direction to enum
		public MatchedToken(int sy, int sx, int ey, int ex, String s) {
			this.sy = sy;
			this.sx = sx;
			this.ey = ey;
			this.ex = ex;
			direction = s;
		}

		@Override
		public String toString() {
			return "[ (" + sy + ", " + sx + "), (" + ey + ", " + ex + "), "
					+ direction + "]";
		}

	}

	static String directions[] = new String[] { "S", "SE", "E", "NE", "N",
			"NW", "W", "SW" };
	static char[][] map;

	static int dx[] = { 1, 1, 0, -1, -1, -1, 0, 1 }; // S,SE,E,NE,N,NW,W,SW
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	// static int dx[] = { 1, 0, 1, 1 }; // E,S,SE,NE
	// static int dy[] = { 0, 1, 1, -1};

	static MatchedToken find(String s) {
		ArrayList<Integer> starts;
		starts = loc.get(s.charAt(0) - 'a');
		int i, j;
		boolean found;
		for (int k = 0; k < starts.size(); k++) {
			int si = starts.get(k++);
			int sj = starts.get(k);
			for (int d = 0; d < dx.length; d++) {
				i = si;
				j = sj;
				found = true;
				for (int l = 1; l < s.length(); l++) {
					if (s.charAt(l) == ' ')
						continue;
					i = i + dy[d];
					j = j + dx[d];
					if (i < 0 || i >= map.length || j < 0 || j >= map[i].length
							|| map[i][j] != s.charAt(l)) {// outside the map ||
															// not a match,
															// prune
						found = false;
						break;
					}
				}
				if (found)
					return new MatchedToken(si, sj, i, j, directions[d]);
			}
		}
		return new MatchedToken(-1, -1, -1, -1, "");
	}

	// contains the occurrences of each char (row,col)
	static ArrayList<ArrayList<Integer>> loc = new ArrayList<ArrayList<Integer>>(
			26);

	public static void main(String[] args) throws IOException {
		// we can replace the usage of loc array with a 2d loop to find the
		// starting char of each word in the grid
		// initializing loc
		for (int i = 0; i < 26; i++)
			loc.add(new ArrayList<Integer>());

		int n, m;
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("word-search.txt"));
		String s;
		n = 25 ; m = 20;
		map = new char[n][m];
		char c;

		for (int i = 0; i < n; i++) {
			s = br.readLine().trim();
			for (int j = 0; j < m * 2 - 1; j += 2) { // m*2-1 assuming each char
														// is followed by a tab
														// except the last char
				c = s.charAt(j);
				c = (char) ((c < 'a') ? (c - 'A' + 'a') : c);// to lower case,
																// assuming all
				// letters
				loc.get(c - 'a').add(i);
				loc.get(c - 'a').add(j / 2);
				map[i][j / 2] = c;
			}
		}

		br.readLine();// empty line
		br.readLine();// words to find:
		while ((s=br.readLine())!=null){
			s = s.trim().toLowerCase();
			System.out.println(s + " " + find(s).toString());
		}

	}

}
