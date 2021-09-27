package week9_4;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
 
public class BOJ_S3_스타트와링크 {
    static int[][] map;
    static int[] combiA,combiB;
    static boolean[] visited;
    static int N,MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
            N = Integer.parseInt(br.readLine());
            map = new int[N+1][N+1];
            MIN = Integer.MAX_VALUE;
            visited = new boolean[N+1];
            combiA= new int[N/2+1];
            combiB= new int[N/2+1];
            for (int r = 1; r <= N; r++) {
                StringTokenizer st =  new StringTokenizer(br.readLine());
                for (int c = 1; c <= N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }
             
            com(0,1);
            System.out.println(MIN);
        
    }
    private static void com(int count, int start) {
        if(count==N/2) {
            int x=1,y=1;
            for(int i=1;i<=N;i++) {
                if(visited[i]) {
                    combiA[x]=i;
                    x++;
                }else {
                    combiB[y]=i;
                    y++;
                }
            }
            calc();
            return;
        }
         
        for(int i=start;i<=N;i++) {
            visited[i]=true;
            com(count+1,i+1);
            visited[i]=false;
        }
    }
    private static void calc() {
        int sumA=0;
        int sumB=0;
     
        for(int i=1;i<combiA.length;i++) {
            for(int j=i;j<combiA.length;j++) {
                sumA += map[combiA[i]][combiA[j]];
                sumA += map[combiA[j]][combiA[i]];
                sumB += map[combiB[i]][combiB[j]];
                sumB += map[combiB[j]][combiB[i]];
            }
        }
            MIN = Math.min(Math.abs(sumA-sumB),MIN);
    }
}