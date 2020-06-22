package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_7699_수지의수지맞는여행 {
	// 사방 탐색을 위한 델타
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static char[][] map;
	static int max, R, C;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 테스트 케이스 수
		int tn = Integer.parseInt(br.readLine());

		// 테스트 케이스만큼 반복
		for (int tc = 1; tc <= tn; tc++) {
			String[] strr = br.readLine().split(" ");
			// 행 수
			R = Integer.parseInt(strr[0]);
			// 열 수
			C = Integer.parseInt(strr[1]);
			// 캐릭터형 2차원 배열 생성
			map = new char[R][C];
			visited = new boolean[26];
			// 맵에 값 넣어주기
			for (int i = 0; i < R; i++) {
				String str = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			// max 초기화
			max = 0;
			visited[map[0][0] - 'A'] = true;
			dfs(0, 0, 1);

			bw.write("#" + tc + " " + String.valueOf(max) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static void dfs(int x, int y, int cnt) {
		if(max==26) return;
		if (cnt > max)
			max = cnt;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < R && ny >= 0 && ny < C)
				if(!visited[map[nx][ny]-'A']) {
					visited[map[nx][ny]-'A']=true;
					dfs(nx,ny,cnt+1);
					visited[map[nx][ny]-'A']=false;
				}
				
		}

	}

}
