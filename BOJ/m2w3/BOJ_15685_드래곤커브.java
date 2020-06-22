package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Stack;

public class BOJ_15685_드래곤커브 {
	static LinkedList<Integer> list;
	static int startX,startY,startD,gen;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	static boolean[][] visited;
	static int cnt;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//커브 개수
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[101][101];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			
			//시작 좌표 시작 방향 세대
			startY =  Integer.parseInt(str[0]);
			startX =  Integer.parseInt(str[1]);
			startD =  Integer.parseInt(str[2]);
			gen =  Integer.parseInt(str[3]);
			
			//방향 담을 리스트
			//방향 전부 담고 그에 따라 시작좌표 움직일 것임
			list = new LinkedList<Integer>();
			makeDir();
			marking();
			
		}
		find();
		bw.write(String.valueOf(cnt));
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static void makeDir() {
		//시작 방향 입력
		list.add(startD);
		
		//세대 수만큼 반복
		for (int i = 0; i < gen; i++) {
			
			//방향은 반시계방향으로 변하고 순서는 반대.
			for (int j = list.size()-1; j >=0 ; j--) {
				list.add((list.get(j)+1)%4);
			}
			
		}
	}
	
	//만든 좌표 대로 지나가면서 마킹
	public static void marking() {
		visited[startX][startY]=true;
		
		for (int i = 0; i < list.size(); i++) {
			
			startX += dx[list.get(i)];
			startY += dy[list.get(i)];
			
			visited[startX][startY]=true;

		}
	}
	
	public static void find() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(visited[i][j]&&visited[i+1][j]&&visited[i][j+1]&&visited[i+1][j+1])
					cnt++;
			}			
		}
	}

}
