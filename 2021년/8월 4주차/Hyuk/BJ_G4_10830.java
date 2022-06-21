package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G4_10830 {

	static int N, A, Size;
	static long B;
	static StringTokenizer st;

	static class Data {
		long step;
		int[][] A;

		public Data(long step, int[][] data) {
			super();
			this.step = step;
			this.A = data;
		}
	}

	static Data exp(Data data1, Data data2) {

		Data data = new Data(data1.step + data2.step, null);
		int[][] A = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int a = 0;
				for (int k = 0; k < N; k++) 
					a += data1.A[i][k] * data2.A[k][j];
				
				A[i][j] = a % 1000;
			}
		}
		data.A = A;

		return data;
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		List<Data> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		int[][] A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		Data input = new Data(1, A);
		list.add(input);
		while (list.get(size).step < B) {

			if (B / 2 >= list.get(size).step) {
				list.add(exp(list.get(size), list.get(size)));
				size++;
			} else {
				long remain = B - list.get(size).step;
				for (int i = 0; i <= size; i++) {
					if (list.get(i).step > remain) {
						list.add(exp(list.get(i - 1), list.get(size)));
						size++;
						break;
					}
				}
			}
		}

		Data newData = list.get(size);
//		for(int[] x : newData.A)
//			System.out.println(Arrays.toString(x));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(newData.A[i][j] + " ");
			}
			System.out.println();
		}
	}
}
