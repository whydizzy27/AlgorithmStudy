package m3w3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2140_지뢰찾기 {
	static int N,cnt;
	static int map[][];
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				if(s.charAt(j)=='#') {
					map[i][j]=-1;
					continue;
				}
				map[i][j]=s.charAt(j)-'0';
			}
		}
		
		for (int i = 2; i < N-2; i++) {
			for (int j = 2; j < N-2; j++) {
				map[i][j]=-2;
			}
		}
		
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < N-1; j++) {
				if(map[i][j]!=-1) continue;
				delta(i,j);
			}
		}
		int remain = 0;
		if(N>4)
			remain = (N-4)*(N-4);

		bw.write(String.valueOf(cnt+remain));

		bw.flush();
		br.close();
		bw.close();
	}
	private static void delta(int i, int j) {
		
		for (int j2 = 0; j2 < 8; j2++) {
			int nx = i+dx[j2];
			int ny = j+dy[j2];
			
			if(map[nx][ny]==0) {
				return;
			}
		}
		
		for (int j2 = 0; j2 < 8; j2++) {
			int nx = i+dx[j2];
			int ny = j+dy[j2];
			
			if(map[nx][ny]<0) continue;
			
			map[nx][ny]-=1;
		}
		cnt++;
	}

}
