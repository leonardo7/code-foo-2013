public class Q5 {

	static class Player implements Comparable<Player> {
		float score;
		String name;

		public Player(float score, String name) {
			this.score = score;
			this.name = name;
		}

		@Override
		public int compareTo(Player o) {
			int i = Float.compare(this.score, o.score);
			return (i == 0) ? (this.name.compareTo(o.name)) : (i);
		}

		@Override
		public String toString() {
			return name + " : " + score;
		}

	}

	public static final int N = 100;

	Player[] players = new Player[N];

	static int partition(Player[] a, int p, int r) {// using the same notation
													// as Cormen
		// assuming pivot a[r]
		int i = p - 1;
		Player temp;
		for (int j = p; j < r; j++) {
			if (a[j].compareTo(a[r]) < 0) {
				i++;
				// swap (a[i], a[j])
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
		// insert pivot in place swap (a[i+1], a[r])
		temp = a[++i];
		a[i] = a[r];
		a[r] = temp;
		return i;
	}

	public static void RandQuickSort(Player[] a, int p, int r) {
		if (p < r) {
			int pivotIndex = p + (int) (Math.random() * (r-p));
//          swap(a[pivotIndx], a[r])
			Player temp;
			temp = a[pivotIndex];
			a[pivotIndex] = a[r];
			a[r] = temp;
			
			pivotIndex = partition(a, p, r);
			
			
			RandQuickSort(a, p, pivotIndex - 1);
			RandQuickSort(a, pivotIndex + 1, r);
		}
	}

	public static void RandQuickSortIterative(Player[] a, int p, int r) {
		int top = -1;
		int[] stack = new int[r - p + 1];
		int pivotIndex;
		stack[++top] = p;
		stack[++top] = r;
		
		while (top >= 0) {
			
			r = stack[top--];
			p = stack[top--];
			pivotIndex = p + (int) (Math.random() * (r-p));

			// swap(a[pivotIndx], a[r])
			Player temp;
			temp = a[pivotIndex];
			a[pivotIndex] = a[r];
			a[r] = temp;

			pivotIndex = partition(a, p, r);

			if (r - pivotIndex > 1) {
				stack[++top] = pivotIndex + 1;
				stack[++top] = r;
			}
			if (pivotIndex - p > 1) {
				stack[++top] = p;
				stack[++top] = pivotIndex - 1;
			}

		}
	}

	public static void main(String[] args) {
		Player users[] = new Player[20];
		for (int i = 0; i < users.length; i++)
			users[i] = new Player((float) (Math.random() * 100),
					"player_" + i);

		RandQuickSortIterative(users, 0, users.length - 1);

		for (int i = 0; i < users.length; i++)
			System.out.println(users[i]);
	}

}
