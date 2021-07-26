package Clear;

import java.io.*;

public class BJ_2750 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		try {
			int N = Integer.parseInt(br.readLine());

			int[] cnt = new int[2001];

			for (int i = 0; i < N; i++)
				cnt[Integer.parseInt(br.readLine())+1000]++;

			for (int i = 0; i < 2001; i++) {
				for (int j = 0; j < cnt[i]; j++) {
					bw.write(Integer.valueOf(i-1000)+"\n");
				}
				bw.flush();
				
			}
			bw.close();
			br.close();
		} catch (Exception e) {
		}
	}
}
