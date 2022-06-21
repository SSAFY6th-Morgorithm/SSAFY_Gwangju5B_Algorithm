
import java.io.*;
import java.util.*;

public class BJ_G5_9663_NQueen {
	
	static int[] col;
	static int N, cnt=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		col = new int[N];
		
		setQueen(0); // 0행부터 놓기
		System.out.println(cnt);
	}

	private static void setQueen(int row) {
		// 행을 모두 채우면 성공(경우의수 +1)
		if(row == N) {
			cnt++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			col[row]=i;
			
			if(isAvailable(row)) {
				setQueen(row+1);
			}
		}
	}

	private static boolean isAvailable(int row) {
		
		for (int i = 0; i < row; i++) {
			// 같은행?
			if(col[row] == col[i]) return false;
			// 대각선?
			else if(Math.abs(row-i) == Math.abs(col[row]-col[i])) return false;
		}
		
		return true;
	}
}
