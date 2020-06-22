package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_1941_소문난칠공주 {
	static char[][] map;
	static int cnt,temp[],tempcnt;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static boolean[][] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new char[5][5];
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		temp = new int[7];
		
		combination(0,0);
		bw.write(String.valueOf(cnt) + " ");
		bw.flush();
		br.close();
		bw.close();
	}

	private static void combination(int idx,int cur) {
		if(idx==7) {
			int n=0;
			visited = new boolean[5][5];
			for (int i = 0; i < 7; i++) {
				int x = temp[i]/5;
				int y = temp[i]%5;
				if(map[x][y]=='S')
					n++;
				visited[x][y]=true;
			}
			if(n<4) return;
			
			check = new boolean[5][5];
			
			int nx = temp[0]/5;
			int ny = temp[0]%5;
			//뽑은 7명이 인접한지 검사
			tempcnt=1;
			isPrincess(nx,ny);
//			System.out.println(Arrays.toString(temp));
			return;
		}
		
		for (int i = cur; i < 25; i++) {
			//나중에 가지 한번 더추자
			temp[idx]=i;
			combination(idx+1, i+1);
		}
	}

	private static void isPrincess(int x, int y) {
		if(tempcnt==7) {
			cnt++;
			return;
		}
		check[x][y]=true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=5||ny<0 || ny>=5 || check[nx][ny]) continue;
			if(!visited[nx][ny]) continue;
			tempcnt++;
			isPrincess(nx,ny);
		}
		

	}


}
