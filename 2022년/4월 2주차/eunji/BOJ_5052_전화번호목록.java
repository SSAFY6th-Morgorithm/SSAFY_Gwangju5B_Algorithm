package algo;
import java.io.*;
import java.util.*;

public class BOJ_5052_전화번호목록 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] list = new String[N];
			boolean flag = true;
			for (int n = 0; n < N; n++) {
				String num = br.readLine();
				list[n] = num;
			} // 입력 완료
			Arrays.sort(list);
			for (int i = 0; i < N-1; i++) {
				String cur=list[i];
				String next=list[i+1];
				if(next.startsWith(cur)) {
					sb.append("NO\n");
					flag=false;
					break;
				}
			}
			if(flag) sb.append("YES\n");
		}
		System.out.println(sb);
	}
}