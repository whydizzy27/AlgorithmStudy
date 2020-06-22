package my.solve;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i]=new ArrayList<Integer>();
		}
		//자기가 화살표 받는 개수. 즉 자기가 목적지인 녀석들 수
		int indegree[] = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			indegree[y]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		//자기가 가르침 받는 수가 0인 애들 큐에 오퍼
		for (int i = 1; i < N+1; i++) {
			if(indegree[i]==0)
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current+" ");
			
			for (int i = 0; i < list[current].size(); i++) {
				int next = list[current].get(i);
				indegree[next]--;
				//자기를 가리키는 녀석들이 indegree를 점점 줄여 0이되면 next를 뽑는데 필요한 녀석들을 이미 전부 뽑았단 것이므로
				//next를 순서에 집어넣어 된다. 즉 간선 조건 성립하는거다.
				//ex 1 3 / 2 3 이면 indegree[3]은 2다
				//indegree가 0인 1 2 가 포문진행되면서 indegree[3] 깎고 먼저 출력됨.
				//이 코드는 위상정렬의 여러경우의수중 딱 하나만 뽑는거다.
				if(indegree[next]==0)
					q.offer(next);
			}
		}
		
		br.close();
	}

}
