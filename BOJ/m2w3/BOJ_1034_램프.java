package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class YooDongGyun_BOJ_1034_���� {
	static int M;
	static int N;
	static int K;
	static int[][] arr;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		arr = new int[N][M];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			String[] str2 = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(str2[j]);
			}
		}
		
		K = Integer.parseInt(br.readLine());
		// �Է� ��

		// �� Ž��
		// �� ���� �� � ���� ���� ���Ұ� ���� �ٸ� �� ������ ���Ѱ���Ƿ� ���� ���� ������ ����.
		// ù��� ���� �͵� �� �ִ븦 ã�´�.
		boolean Pass;
		int x = 0;
		int zeroCnt=0;
		int sameCnt=0;
		int max=0;
		
		while (x != N) {
			// ���� �� ���µ� ���θ� �ٲ� �� �ִ� ��ȸ K���� ũ�� ������ ���� �� �ȵ����Ƿ� ���� �� Ž������ �н���Ų��
			
			// �ѹ� �鸥 �ֵ� ����ģ��.
			if (visited[x] == true) {
				x++;
				continue;
			}
			Pass = false;
			//�ڱ��ڽ�����
			sameCnt = 1;
			zeroCnt = 0;
			
			//���� ���� 0���� ����
			for (int j = 0; j < M; j++) {
				if (arr[x][j] == 0)
					zeroCnt++;
			}
			//0 ������ �� ����. K���� 0������ ũ�� �ٲ㵵 �� �ȵ���
			if (zeroCnt > K) {
				x++;
				continue;
			}
			//K�� �� ũ�ų� ������ Ȧ¦ �ȸ����� �ᱹ ��Ų��
			else if ((zeroCnt % 2 == 0 && K % 2 == 0)==false && (zeroCnt % 2 == 1 && K % 2 == 1)==false) {
				x++;
				continue;
			}
			
			if(x!=N-1) {
				// ���� �� ������ ���� ���� ���� �� Ž�� ����
				for (int i = x + 1; i < N; i++) {
					Pass =false;
					for (int j = 0; j < M; j++) {
						if (arr[x][j] != arr[i][j]) {
							Pass = true;
							break;
						}
					}
					if (Pass == true)
						continue;
					visited[i] = true;
					sameCnt++;
				}
			}
			
			
			if(sameCnt>max)
				max=sameCnt;
			
			x++;

		}

		bw.write(String.valueOf(max));
		bw.flush();
		br.close();
		bw.close();
	}

}
