package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;



public class SWEA_1251_하나로_프림 {
	static int N;
	static long[][] island;
	static long[][] graph;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			// 섬 개수
			N = Integer.parseInt(br.readLine());
			long[][] island = new long[N][2];

			String[] str = br.readLine().split(" ");
			String[] str2 = br.readLine().split(" ");
			
			for (int i = 0; i < N; i++) {
				island[i] = new long[] {Long.parseLong(str[i]),Long.parseLong(str2[i])};
			}

			double E = Double.parseDouble(br.readLine());
			// 입력 종료

			//입력된 자료 기반 섬 간의 가중치 그래프 구하자
			//정점들 간의 거리 그래프
			graph = new long[N][N];
			long[] from, to;
			
			for (int r = 0; r < N; r++) {
				from = island[r];
				for (int c = r+1; c < N; c++) {
					to = island[c];
					graph[c][r]=graph[r][c]=(from[0]-to[0]) * (from[0]-to[0]) + (from[1]-to[1]) * (from[1]-to[1]);
				}
			}
			
			double cost = prim(0) * E;
			
			// 소수 첫째자리에서 반올림
			bw.write("#" + tc + " " + String.valueOf(Math.round(cost)) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	//BFS와 매우 흡사. 가중치 있는 BFS다
	private static long prim(int start) {
		//MST에 들어가지 않은 녀석들
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		//모든 정점들을 다 관리....
		Edge[] dynamicGraph = new Edge[N];
		
		//모든 정점 키값 스타트 지점만 0주고 나머지 맥스밸류줌
		//그리고 다 큐에 넣음
		for (int n = 0; n < dynamicGraph.length; n++) {
			dynamicGraph[n] = new Edge(n, Long.MAX_VALUE);
			if(n==start) {
				dynamicGraph[n].cost=0;
			}
			pq.offer(dynamicGraph[n]);
		}
		
		long cost = 0;
		while(!pq.isEmpty()) {
			Edge front = pq.poll(); //MST에 포함되는 녀석 중 가장 키값 작은녀석 poll
			cost += front.cost; //cost가 정점에 저장된 key값
			
			//자식 탐색
			for (int i = 0; i < dynamicGraph.length; i++) {
				Edge child = dynamicGraph[i];
				//pq : 비 MST
				//이게 다 연결되서 그렇지 간선이 없는곳도 있으면 여기에 두정점 연결 유무 확인하는 조건도 들어감.
				//큐에 있나 확인하는 이유 : 이미 진행한 정점은 큐에서 빼줌으로서 비지티드 역할.
				if(pq.contains(child)) {
					//poll한 정점과 탐색 대상 정점과의 거리 즉 가중치
					long tempCost = graph[front.idx][child.idx];
					//가중치 비교. 처음엔 맥스밸류로 child것들이 저장돼있어서 가중치가 더작으므로 바로 가중치를 child의 key값으로 넣는다.
					//계속 이 기준으로 갱신하는거다. 최소뽑기위해
					if(tempCost < child.cost) {
						//key값저장
						child.cost = tempCost;
						//우선순위 큐 특성상 안에 힙구조 갱신하려면 다시 넣어야함 그래서 값 바꾸는거로 안끝내고 다시 뺐다 넣어준다.
						pq.remove(child); //remove(Object)메소드.
						pq.offer(child);
					}
				}
			}
			
		}
		return cost;
	}
	
	static class Edge implements Comparable<Edge>{
		int idx;
		long cost;
		public Edge(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
		
		
	}

}
