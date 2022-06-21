import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BOJ23629_이얼마나끔찍하고무시무시한수식이니 {
	private final static String[] NUMBER = { "ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT",
			"NINE" };

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String expr = input.readLine();
		Queue<String> q = new LinkedList<>();
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i <= 9; i++) {
			map.put(NUMBER[i], Integer.toString(i));
			map.put(Integer.toString(i), NUMBER[i]);
		}
		int startIndex = 0;
		StringBuilder current = new StringBuilder();
		for (int endIndex = 1; endIndex <= expr.length(); endIndex++) {
			String slice = expr.substring(startIndex, endIndex);
			if(slice.length() > 5) {
				System.out.println("Madness!");
				return;
			}
			if (map.containsKey(slice)) {
				current.append(map.get(slice));
				startIndex = endIndex;
			} else if (checkIsOperator(slice)) {
				if (current.length() != 0)
					q.offer(current.toString());
				q.offer(slice);
				current = new StringBuilder();
				startIndex = endIndex;
			}
		}
		
		
		boolean lastIsOperator = false;
		expr = "";
		long result = 0;
		String lastOperator = "+";
		
		while (!q.isEmpty()) {
			String str = q.poll();
			boolean currentIsOperator = checkIsOperator(str);
			if (lastIsOperator && currentIsOperator) {
				System.out.println("Madness!");
				return;
			}
			lastIsOperator = currentIsOperator;
			expr += str;
			if (!currentIsOperator) {
				switch (lastOperator) {
				case "+":
					result += Long.parseLong(str);
					break;
				case "-":
					result -= Long.parseLong(str);
					break;
				case "x":
					result *= Long.parseLong(str);
					break;
				case "/":
					double tmp = 1.0 * result / Long.parseLong(str);
					if (tmp >= 0)
						result = (long) Math.floor(tmp);
					else
						result = (long) Math.ceil(tmp);
					break;
				}
			} else {
				lastOperator = str;
			}

		}
		System.out.println(expr);
		expr = Long.toString(result);
		String answer = "";
		for (int i = 0; i < expr.length(); i++) {
			String s = expr.charAt(i) + "";
			if (checkIsOperator(s))
				answer += s;
			else
				answer += map.get(s);
		}
		System.out.println(answer);
	}

	static boolean checkIsOperator(String op) {
		return op.equals("+") || op.equals("-") || op.equals("x") || op.equals("/") || op.equals("=");
	}
}
