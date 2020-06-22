package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2583_영역구하기 {
	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 }; // 행
	static int[] dy = { 0, 0, -1, 1 }; // 열

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		

		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		// 큐 하나 써도 될듯

		int r = Integer.parseInt(str[0]);// 행 열 사각형수
		int c = Integer.parseInt(str[1]);
		int n = Integer.parseInt(str[2]);
//		System.out.println(r+" "+c+" "+n);
//		int[][] map = new int[r][c];
		int[][] visited = new int[r][c];
		for (int i = 0; i < n; i++) {
			String[] str2 = br.readLine().split(" ");
			int x1 = Integer.parseInt(str2[0]);
			int y1 = Integer.parseInt(str2[1]);
			int x2 = Integer.parseInt(str2[2]);
			int y2 = Integer.parseInt(str2[3]);
//			System.out.println(x1+""+y1+""+x2+""+y2);
			// 인덱스 거꾸로 생각하자 x가 열이고 y가 행이다 헷갈리네
			for (int j = y1; j < y2; j++) {
				for (int k = x1; k < x2; k++) {
//					map[j][k] = 1;
					visited[j][k]=1;
				}
			}
		}
		int cnt = 0; // 공간 개수
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				System.out.print(visited[i][j]);
//			}
//			System.out.println();
//		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (visited[i][j] == 1)
					continue;
				int size = 0;
				cnt++;
				visited[i][j] = 1;
				qx.add(i);
				qy.add(j);

				while (!qx.isEmpty() && !qy.isEmpty()) {
					int x = qx.poll();
					int y = qy.poll();
					size++; // 크기 세자
					for (int k = 0; k < 4; k++) {
						// 상하좌우 확인
						int _x = x + dx[k];
						int _y = y + dy[k];
						if (_x >= 0 && _y >= 0 && _x < r && _y < c) {
							if (visited[_x][_y] == 0) {
								qx.add(_x);
								qy.add(_y);
								visited[_x][_y] = 1;
							}
						}
							
					}
				}
				list.add(size);
			}
		}
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				System.out.print(visited[i][j]);
//			}
//			System.out.println();
//		}
		Collections.sort(list);// 오름차순
		bw.write(String.valueOf(cnt) + "\n");
		for (int i = 0; i < list.size(); i++)
			bw.write(String.valueOf(list.get(i)) + " ");
		
//		for (int i = 0; i < r; i++) {
//			for (int j = 0; j < c; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		bw.flush();
		br.close();
		bw.close();
	}

}
