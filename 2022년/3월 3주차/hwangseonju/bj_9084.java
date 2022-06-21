import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_9084 {
	
	static int T;	// 테스트 케이스
	static int N;	// 동전 수 
	static int M;	// 금액
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 동전
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int[] coins = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				coins[c] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(br.readLine());
			int[] cnt = new int[M+1];
			cnt[0] = 1;
			for(int c=0; c<N; c++) {
				for(int m=coins[c]; m<=M; m++) {
					cnt[m] += cnt[m-coins[c]];
				}
			}
			sb.append(cnt[M]).append("\n");
		}
		System.out.println(sb);
	}
}
