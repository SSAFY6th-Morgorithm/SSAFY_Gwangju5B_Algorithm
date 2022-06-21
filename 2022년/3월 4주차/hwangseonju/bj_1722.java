import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1722 {
	
	static int N;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 순열의 순서
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken()); 
		
		boolean[] used = new boolean[N+1];
		if(k==1) {	// 순열 구하기
			long num = Long.parseLong(st.nextToken());
			for(int i=1; i<=N; i++) {
				long factorial = 1L;
				for(int f=1; f<=N-i; f++) {	// 각 자리수 팩토리얼값 구하기 -> n번째 자리수 고정이면 (N-n)!
					factorial *= f;
				}
				
				for(int j=1; j<=N; j++) {
					if(!used[j]) {
						if(num-factorial>0) {	// 순서 내에 있을 경우
							num -= factorial;
						} else {	// 순서에서 벗어날 경우
							sb.append(j).append(" ");
							used[j] = true;
							break;
						}
					}
				}
			}
			System.out.println(sb);
		} else {		// 순서 구하기
			long result = 1L;
			for(int i=1; i<=N; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				int cnt = 0;
				for (int j = 1; j <= N; j++) {
					if (!used[j]) {
						if (j == num) {
							long factorial = 1L;
							for (int f = 1; f <= N - i; f++) { // 각 자리수 팩토리얼값 구하기
								factorial *= f;
							}
							result += (cnt * factorial);
							break;
						} else {
							cnt++; // 오름차순에서 벗어난 숫자만큼 더하기
						}
					}
				}
				used[num] = true;
			}
			System.out.println(result);
		}
	}
}
