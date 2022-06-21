package BJ_Practice;

import java.io.*;

public class BJ_10989 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
			int N = Integer.parseInt(br.readLine());

			int[] cnt = new int[10000];

			for (int i = 0; i < N; i++)
				cnt[Integer.parseInt(br.readLine())-1]++;

			for (int i = 0; i < 10000; i++) {
				for (int j = 0; j < cnt[i]; j++) {
					bw.write(Integer.valueOf(i+1) + "\n");
				}
				bw.flush();

			}
			bw.close();
			br.close();
		
	}
}
