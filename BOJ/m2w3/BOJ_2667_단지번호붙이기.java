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

public class BOJ_2667_단지번호붙이기 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		int[][] visited = new int[n][n];

		//입력
		for(int i=0;i<n;i++) {
			String[] str = br.readLine().split("");
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(str[j]);
			}
		}
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		int cnt =0; //단지 
		ArrayList<Integer> list = new ArrayList<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]==0||visited[i][j]==1)
					continue;
				int size=0;
				q.add(i);
				q.add(j);
				cnt++;
				
				while(!q.isEmpty()) {
					int x = q.poll();
					int y = q.poll();
					size++;
					map[x][y]=cnt;
					visited[x][y]=1;
					for(int k=0;k<4;k++) {
						int _x = x + dx[k];
						int _y = y + dy[k];
						
						if(_x>=0&&_y>=0&&_x<n&&_y<n)
							if(visited[_x][_y]==0&&map[_x][_y]==1) {
								map[_x][_y]=cnt;
								visited[_x][_y]=1;
								q.add(_x);
								q.add(_y);
							}
					}
				}
				list.add(size);
			}
		}
		Collections.sort(list);
		bw.write(String.valueOf(cnt)+"\n");
		for(int i=0;i<cnt;i++)
			bw.write(String.valueOf(list.get(i))+"\n");
		bw.flush();
		br.close();
		bw.close();
	}

}
