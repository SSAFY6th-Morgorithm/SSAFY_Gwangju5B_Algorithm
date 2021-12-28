import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ1022_소용돌이예쁘게출력하기 {

	static class Position {
		int r, c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + r;
			result = prime * result + c;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			if (r != other.r)
				return false;
			if (c != other.c)
				return false;
			return true;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		Map<Position, Integer> grid = new HashMap<>();
		String[] token = input.readLine().split(" ");
		StringBuilder answer = new StringBuilder();

		int r1 = Integer.parseInt(token[0]);
		int c1 = Integer.parseInt(token[1]);
		int r2 = Integer.parseInt(token[2]);
		int c2 = Integer.parseInt(token[3]);

		for (int r = r1; r <= r2; r++) {
			for (int c = c1; c <= c2; c++) {
				grid.put(new Position(r, c), 0);
			}
		}
		for (Position key : grid.keySet()) {
			if (grid.get(key) != 0)
				continue;
			int outer = Math.max(Math.abs(key.r),Math.abs(key.c));
			int leftTop = (int) Math.pow((2 * outer), 2) + 1; // (-outer , -outer)
			int rightTop = leftTop - 2 * outer;
			int leftBottom = leftTop + 2 * outer;
			int rightBottom = leftBottom + 2 * outer;
			
			if(outer == Math.abs(key.r)) {
				if(key.r > 0) {
					grid.put(key, rightBottom - (outer - key.c));
				}else {
					grid.put(key, leftTop - (outer + key.c));
				}
			} else {
				if(key.c > 0) {
					grid.put(key, rightTop - (outer + key.r));
				}
				else {
					grid.put(key, leftBottom - (outer - key.r));
				}
			}
			

		}

		int max = Integer.MIN_VALUE;
		for (Position key : grid.keySet()) {
			if (max < grid.get(key))
				max = grid.get(key);
		}
		int maxLength = Integer.toString(max).length();
		for (int r = r1; r <= r2; r++) {
			for (int c = c1; c <= c2; c++) {
				int number = grid.get(new Position(r, c));
				for (int i = 0; i < maxLength - Integer.toString(number).length(); i++)
					answer.append(" ");
				answer.append(number).append(" ");

			}
			answer.append("\n");
		}
		System.out.println(answer);
	}
}
