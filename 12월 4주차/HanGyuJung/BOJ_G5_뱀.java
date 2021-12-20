package M12W3;

import java.io.*;
import java.util.*;

public class BOJ_G5_뱀 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 1][N + 1];
		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
		}
		int D = Integer.parseInt(br.readLine());
		HashMap<Integer, Character> hm = new HashMap<>();
		for (int d = 0; d < D; d++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			hm.put(sec, c);
		}
		int[][] vector = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		Deque<int[]> dq = new LinkedList<>();
		dq.add(new int[] {1,1});
		int dir=0;
		int sec=0;
		label: while(true) {
			sec++;
			int nr = dq.peekLast()[0] + vector[dir][0];
			int nc = dq.peekLast()[1] + vector[dir][1];
			if(nr<1 || nr >N || nc < 1|| nc > N) break; //범위 넘어가면 겜 끝
			
			//큐 내부 돌면서 겹치는거 있으면 겜 끝
			Iterator<int[]> iterator = dq.iterator();   
			while (iterator.hasNext()) {
				int[] temp =iterator.next();
//	        	System.out.println(Arrays.toString(temp));
	            if(temp[0]==nr && temp[1]==nc) {
//	            	System.out.println("뱀꼬리잡힘");
	            	break label;
	            }
	        }
//	        System.out.println("=============");
	        
	        //종료조건 해당안되면 큐(뱀)에 삽입
			dq.addLast(new int[] {nr,nc});
			
			//사과없으면 마지막 꼬리는 짜른다(큐 최초값 제거)
			if(map[nr][nc]==0) {
//				System.out.println("qweqwe");
				dq.removeFirst();
			}else if(map[nr][nc]==1) {
				map[nr][nc]=0;
			}
			//시간초가 입력받은 시간초면 방향 바꿈
			if(hm.containsKey(sec)) {
				char dd = hm.get(sec);
				if(dd=='D') dir++;
				else if(dd=='L') dir--;
				if(dir==4) dir=0;
				else if(dir==-1) dir=3;
			}
		}
		System.out.println(sec);
	}
}
