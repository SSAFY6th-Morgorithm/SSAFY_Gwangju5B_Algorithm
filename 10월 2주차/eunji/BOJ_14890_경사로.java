import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 12868	100
 * @author CHO
 * @see https://www.acmicpc.net/problem/14890
 * @category 구현
 * 주의! 방문처리 유의 
 */
public class BOJ_14890_경사로 {
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException, NumberFormatException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[][] map=new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}// 입력완료
		
		// 행에 같은 숫자 있는지 확인
		int row[]=new int[N];
		for (int i = 0; i < N; i++) {
			int first=map[i][0];
			boolean flag=true;
			for (int j = 1; j < N; j++) {
				if(first!=map[i][j]) {
					flag=false;
					break;
				}
			}
			if(flag) row[i]=1;
		}
		// 열에 같은 숫자 있는지 확인
		int col[]=new int[N];
		for (int j = 0; j < N; j++) {
			int first=map[0][j];
			boolean flag=true;
			for (int i = 1; i < N; i++) {
				if(first!=map[i][j]) {
					flag=false;
					break;
				}
			}
			if(flag) col[j]=1;
		}
		
		int result=0;
		boolean visited[];
		// 행 확인
		for (int i = 0; i < N; i++) {
			boolean flag=true;
			visited=new boolean[N];
			if(row[i]!=1) {
				outer: for (int j = 0; j < N-1; j++) {
					int curr=map[i][j];
					int next=map[i][j+1];
					if(curr!=next) {
						if(Math.abs(curr-next)==1) {
							// 뒤에 설치
							if(curr>next) {
								for (int c = j+1; c < j+1+L; c++) {
									if(c>=N || visited[c] || map[i][c]!=next) {
										flag=false;
										break outer;
									}
								}
								if(flag) {
									for (int c = j+1; c < j+1+L; c++) {
										visited[c]=true;
									}
								}
							}else if(curr<next) {
								for (int c = j; c > j-L; c--) {
									if(c<0 || visited[c] || map[i][c]!=curr) {
										flag=false;
										break outer;
									}
								}
								if(flag) {
									for (int c = j; c > j-L; c--) {
										visited[c]=true;
									}
								}
							}
						}else {
							flag=false;
							break;
						}
					}else continue;
				}
			}
			if(flag) result++;
		}
//		System.out.println(result);
		
		// 열 확인
		for (int j = 0; j < N; j++) {
			boolean flag=true;
			visited=new boolean[N];
			if(col[j]!=1) {
				outer: for (int i = 0; i < N-1; i++) {
					int curr=map[i][j];
					int next=map[i+1][j];
					if(curr!=next) {
						if(Math.abs(curr-next)==1) {
							if(curr>next) {
								//아래에 설치
								for (int r = i+1; r < i+1+L; r++) {
									if(r>=N || visited[r] || map[r][j]!=next) {
										flag=false;
										break outer;
									}
								}
								if(flag) {
									for (int r = i+1; r < i+1+L; r++) {
										visited[r]=true;
									}
								}
								
							}else if(curr<next) {
								// 위에 설치
								for (int r = i; r > i-L; r--) {
									if(r<0 || visited[r] || map[r][j]!=curr) {
										flag=false;
										break outer;
									}
								}
								if(flag) {
									for (int r = i; r > i-L; r--) {
										visited[r]=true;
									}
								}
							}
						}else {
							flag=false;
							break;
						}
					}
					
				}
			}
			if(flag) result++;
			
		}
		System.out.println(result);

	}

}
