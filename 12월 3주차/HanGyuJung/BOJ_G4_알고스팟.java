package M12W2;

import java.io.*;
import java.util.*;


class Node implements Comparable<Node>{
	int x;
	int y;
	int weight;
	public Node(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.weight = w;
    }
	@Override
	public int compareTo(Node o) {
		return this.weight-o.weight;
	}
	
}
public class BOJ_G4_알고스팟 {
	static int C;
    static int R;
    static int[][] map;
    static int[][] dist;
    static PriorityQueue<Node> pq;
    static int[][] vector = {{-1,0},{1,0},{0,-1},{0,1}};
    static int result = 0;
	 public static void main(String[] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//StringBuilder sb = new StringBuilder();
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        
	        R = Integer.parseInt(st.nextToken()); // 행
	        C = Integer.parseInt(st.nextToken()); // 열
	        
	        map = new int[R+1][C+1];
	        dist = new int[R+1][C+1];
	        
	        for (int r = 1; r <= R; r++) {
	            for (int c = 1; c <= C; c++) {
	                dist[r][c] = Integer.MAX_VALUE;
	            }
	        }
	        
	        for (int r = 1; r <= R; r++) {
	        	String s = br.readLine();
	            for (int c = 1; c <= C; c++) {
	                map[r][c] = s.charAt(c-1)-'0';
	            }
	        }
	        
	        pq = new PriorityQueue<>();
	        dijkstra();
	        System.out.println(dist[R][C]);
	    }
	    
	    public static void dijkstra(){
	        
	        pq.add(new Node(1, 1, 0));
	        dist[1][1] = 0;
	        
	        while(!pq.isEmpty()){
	            
	            Node temp = pq.poll();
	            
	            if(temp.x == R && temp.y == C){
	                return;
	            }
	            
	            for (int d = 0; d < 4; d++) {
	            	int nx = temp.x + vector[d][0];
	                int ny = temp.y + vector[d][1];
	                
	                if(ny>0 && nx>0 && ny<=C && nx<=R){ 
	                    
	                    if(dist[nx][ny] > dist[temp.x][temp.y] + map[nx][ny]){
	                        dist[nx][ny] = dist[temp.x][temp.y] + map[nx][ny];
	                        pq.add(new Node(nx, ny, dist[nx][ny]));
	                    }
	                    
	                }
	            }
	        }
	    }
}
