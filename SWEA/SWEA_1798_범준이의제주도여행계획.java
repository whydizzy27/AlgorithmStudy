package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_1798_범준이의제주도여행계획 {
	static int N, M, arr[][];
	static ArrayList<Point> plist;
	static ArrayList<Point> hlist;
	static Point airport;
	static boolean[] visited;
	static int maxSatis;
	static ArrayList<Integer> maxPath;
	static Stack<Integer> tempPath;

	static class Point {
		int idx, playTime, satis;
		Point nearH;

		public Point(int idx, int playTime, int satis) {
			this.idx = idx;
			this.playTime = playTime;
			this.satis = satis;
		}

		public Point(int idx) {
			this.idx = idx;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int tn = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= tn; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 정점 개수
			N = Integer.parseInt(st.nextToken());
			// 휴가 기간
			M = Integer.parseInt(st.nextToken());

			// 포인트별 소요시간
			arr = new int[N][N];
			visited = new boolean[N];
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = i + 1; j < N; j++) {
					arr[i][j] = arr[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			maxSatis = 0;
			tempPath = new Stack<Integer>();
			plist = new ArrayList<>();
			hlist = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String str = st.nextToken();
				if (str.equals("A"))
					airport = new Point(i);
				else if (str.equals("P"))
					plist.add(new Point(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				else
					hlist.add(new Point(i));

			}
			// 입력 끝
			// 가장 가까운 호텔 정보를 plist에있는 포인트들에 넣기
			// dfs때 매번 찾지말고 메모이제이션 느낌으로 미리 포인트 클래스안에넣어놈
			for (Point p : plist) {
				int min = Integer.MAX_VALUE;
				for (Point h : hlist) {
					if (min > arr[p.idx][h.idx]) {
						min = arr[p.idx][h.idx];
						p.nearH = h;
					}
				}
			}

			dfs(0, 0, airport, 0);

			
			bw.write("#" + tc + " " + String.valueOf(maxSatis) + " ");
			if(maxSatis!=0) {
				for (int i : maxPath) {
					bw.write(String.valueOf(i) + " ");
				}
			}
			
			bw.write("\n");

		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int day, int time, Point start, int satis) {
		if (day == M) {
			if (maxSatis < satis) {
				maxSatis = satis;
				maxPath = new ArrayList<Integer>(tempPath);
			}
			return;
		}
		boolean isPossible = false;
		// 다른 관광명소에 갈수 있다면
		for (Point p : plist) {

			if (!visited[p.idx]) {
				// 공항이나 호텔로 들어가는 시간까지 고려해야함. 매번!
				int tempTime = time + arr[start.idx][p.idx] + p.playTime;
				if (day == M - 1) {
					tempTime += arr[p.idx][airport.idx];
				} else {
					tempTime += arr[p.idx][p.nearH.idx];
				}

				if (tempTime > 540)
					continue;

				isPossible = true;
				tempPath.push(p.idx + 1);
				visited[p.idx] = true;
				dfs(day, time + arr[start.idx][p.idx] + p.playTime, p, satis + p.satis);
				visited[p.idx] = false;
				tempPath.pop();

			}
		}

		// 관광명소 전부 못가면?
		if (!isPossible) {
			if (day == M - 1) {
				tempPath.push(airport.idx + 1);
				dfs(day + 1, time, airport, satis);
				tempPath.pop();
			} else {
				// 현위치가 공항일수도 호텔일수도 있기에 nearH는 사용못하고
				// 심지어 사용한다하더라도 위에서 관광명소 갈수있는지 없는지 검사한 nearH는 최후의 보루. 즉 가장 가까운 호텔기준으로 잡은거지
				// 남은 시간이 여유가 좀 있다면 가장 가까운 호텔 말고도 다른 호텔도 갈 수 있음. 그럼 경로는 또 달라지게된다. 그래서 for each해서
				// 모든 호텔 고려함
				for (Point h : hlist) {
					//현 위치의 현 소요시간 + 호텔까지 가는 시간이 540 이하인 경우
					if (time + arr[start.idx][h.idx] <= 540) {
						tempPath.push(h.idx + 1);
						dfs(day + 1, 0, h, satis);
						tempPath.pop();
					}
				}
			}
		}
	}

}