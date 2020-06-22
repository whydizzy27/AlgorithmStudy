package m3w3;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1707_이분그래프 {
	static boolean isBipartite;
	static int[] visited;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tn = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[V+1];
			
			for (int i = 1; i <= V; i++) {
				list[i]=new ArrayList<Integer>();
			}
			
			visited = new int[V+1];
			
			for (int i = 0; i < E; i++) {
				st=new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list[x].add(y);
				list[y].add(x);
			}
			
			isBipartite = true;
			
			for (int i = 1; i <= V; i++) {
				if(!isBipartite) break;
				if(visited[i]!=0)continue;
				dfs(i,1);
			}
			
			bw.write((isBipartite?"YES":"NO")+"\n");
			
			
			
		}

		bw.flush();
		br.close();
		bw.close();
	}
	private static void dfs(int ver, int value) {
		if(!isBipartite) return;
		
		visited[ver]=value;
		
		for (int i  : list[ver]) {
			if(visited[i]==value) {
				isBipartite=false;
				return;
			}
			if(visited[i]!=0) continue;
			dfs(i,-(value));
		}
	}

}
