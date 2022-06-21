import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1038_감소하는수 {
	static int N;
	static Integer pick[];
	static int cnt;
	static Set<Long> set;
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// N이 0~9이면 N과 감소하는 수는 무조건 같음
		if(0<=N && N<=9) {
			System.out.println(N);
			System.exit(0);
		}
		if(N>1023) {
			System.out.println(-1);
			System.exit(0);
		}
		set=new HashSet<>();
		for (int i = 2; i <= 10; i++) {
			pick=new Integer[i];
			visited=new boolean[10];
			permutation(0,i);
			if(set.size()>=N) break;
		}
		ArrayList<Long> list=new ArrayList<>(set);
		Collections.sort(list);
		System.out.println(list.get(N-10));
	}

	private static void permutation(int cnt, int R) {
		if(cnt==R) {
			String str="";
			Arrays.sort(pick,Collections.reverseOrder());
			System.out.println(Arrays.toString(pick));
			for (int i : pick) {
				str+=Integer.toString(i);
			}
			set.add(Long.parseLong(str));
			return;
		}
		for (int i = 0; i <=9; i++) {
			if(visited[i]) continue;
			pick[cnt]=i;
			visited[i]=true;
			permutation(cnt+1, R);
			visited[i]=false;
		}
		
	}
}
