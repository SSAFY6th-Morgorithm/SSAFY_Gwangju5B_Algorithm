package conquer;

import java.io.*;

/**
 * println쓰면 시간초과
 * BufferedReader와 BufferedWriter 사용 -> 16212	244
 * BufferedReader와 StringBuilder 사용 -> 20324		228
 * StringBuilder는 계속 쌓이기때문에 setLength로 비워줘야함*/

public class BJ_S3_4779_칸토어집합 {

	static int N;
	static char[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s;

		while ((s = br.readLine()) != null) {
			N = (int) Math.pow(3, Integer.parseInt(s));

			arr = new char[N];
			for (int i = 0; i < N; i++) {
				arr[i] = ' ';
			}

			sol(0, 0, N);

			for (int i = 0; i < N; i++) {
				bw.write(arr[i]);
				//sb.append(arr[i]);
			}
			bw.newLine();
			bw.flush();
			//sb.append("\n");
			//System.out.print(sb);
			//sb.setLength(0);
		}
		br.close();
		
	}

	private static void sol(int r, int c, int len) {
		if (len == 1) {
			arr[r] = '-';
			return;
		}

		int next = len / 3;

		sol(r, c + 2 * next, next);
		sol(r + 2 * next, c, next);
	}

}
