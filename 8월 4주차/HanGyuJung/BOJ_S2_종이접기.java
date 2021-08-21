import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간 제한 1초
public class Main {
	private static int[][] map;
	private static int[] answer=new int[2];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int r=0;r<N;r++) {
			st= new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		spacing(0,0,N);
		for(int data:answer) {
			System.out.println(data);
		}
	}

	private static void spacing(int r, int c, int len) {
		int temp = map[r][c];
		boolean flag=true;
		label: for(int i=r;i<r+len;i++) {
			for(int j=c;j<c+len;j++) {
				if(temp!=map[i][j]) {
					flag=false;
					break label;
				}
			}
		}
		if(flag) {
			answer[temp]=answer[temp]+1;
		}else {
			len /= 2;
			spacing(r, c, len);
			spacing(r, c+len, len);
			spacing(r+len, c, len);
			spacing(r+len, c+len, len);
		}
		
		
	}
}
