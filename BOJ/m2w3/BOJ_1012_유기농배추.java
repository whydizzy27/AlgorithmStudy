package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class YooDongGyun_BOJ_1012_�������� {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int row, col;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int Tnum = Integer.parseInt(br.readLine());

		for (int k = 0; k < Tnum; k++) {
			String[] str = br.readLine().split(" ");
			row = Integer.parseInt(str[0]);
			col = Integer.parseInt(str[1]);
			int Bnum = Integer.parseInt(str[2]);

			map = new int[row][col];

			// ���� �ɱ�
			for (int u = 0; u < Bnum; u++) {
				String[] str2 = br.readLine().split(" ");
				map[Integer.parseInt(str2[0])][Integer.parseInt(str2[1])] = 1;
			}

			Queue<Integer> q = new LinkedList<>();
			//���� ���� ���ž�
			int cnt=0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (map[i][j] == 0)
						continue;
					cnt++;
					q.add(i);
					q.add(j);
					//visited �迭 ��� 0���� ġȯ�Ұž�
					map[i][j]=0;
					while(!q.isEmpty()) {
						int x = q.poll();  
						int y = q.poll();  
						for(int w=0;w<4;w++) {
							int nx = x + dx[w];
							int ny = y + dy[w];
							
							if(nx>=0&&nx<row&&ny>=0&&ny<col)
								if(map[nx][ny]==1) {
									q.add(nx);
									q.add(ny);
									map[nx][ny]=0;
								}
						}
					}
				}
			}
			bw.write(String.valueOf(cnt)+"\n");
			//
		}

		bw.flush();
		br.close();
		bw.close();
	}

}
