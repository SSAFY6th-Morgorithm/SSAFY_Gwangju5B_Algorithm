import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list_arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        list_arr=new ArrayList[N+1];
        visited=new boolean[N+1];
        for(int i=1;i<N+1;i++){
            list_arr[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list_arr[x].add(y);
            list_arr[y].add(x);

        }
        for (int i = 1; i < N + 1; i++) {
            Collections.sort(list_arr[i]);
        }

        dfs(start);
        sb.append("\n");
        visited=new boolean[N+1];
        bfs(start);
        System.out.println(sb);
    }

    private static void dfs(int start) {
        sb.append(start+" ");
        visited[start]=true;
        if(list_arr[start].isEmpty())
            return;
        for(int data:list_arr[start]){
            if(!visited[data]){
                dfs(data);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start]= true;

        while(!q.isEmpty()){
            int temp = q.poll();
            sb.append(temp+" ");
            for(int data:list_arr[temp]){
                if(!visited[data]) {
                    visited[data] = true;
                    q.add(data);
                }
            }

        }
    }
}
