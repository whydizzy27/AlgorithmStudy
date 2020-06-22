package BaekJoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BOJ_�Ը������ {
	static int n, dfsCnt;
	static boolean[] visited;
	static boolean[] dfsVisited;
	static int[][] conArr;
	static int dif;
	static int min,chk;
	static int[] popArr;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		min=10000;
		n = Integer.parseInt(br.readLine());
		visited = new boolean[n];
		dfsVisited = new boolean[n + 1];
		popArr = new int[n + 1];
		String[] str = br.readLine().split(" ");

		for (int i = 1; i <= n; i++) {
			popArr[i] = Integer.parseInt(str[i - 1]);
		}

		conArr = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			String[] str2 = br.readLine().split(" ");
			for (int j = 1; j <= Integer.parseInt(str2[0]); j++) {
				conArr[i][Integer.parseInt(str2[j])] = 1;
			}
		}
		permu(0);
		if(chk==0)
			min=-1;
		bw.write(String.valueOf(min));
		bw.flush();
		br.close();
		bw.close();
	}

	public static void permu(int idx) {
		if (idx == n) {
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i])
					cnt++;
			}
			// ���⼭ ���� ���ű� ��
			if (cnt == 0 || cnt == n)
				return;

			ArrayList<Integer> Alist = new ArrayList<>();
			ArrayList<Integer> Blist = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					Alist.add(i + 1);
//					System.out.print(i + 1 + " ");
				} 
				else
					Blist.add(i + 1);
			}
//			System.out.println(Alist);
//			System.out.println(Blist);
			//A����Ƽ�� �ʱ�ȭ
			for (int i = 1; i <= n; i++) {
				dfsVisited[i] = false;
			}
			//�����ŵ� �湮�Ѱŷ� �ļ� ������ ����

			for (int i = 0; i < Blist.size(); i++) {
				dfsVisited[Blist.get(i)] = true;
			}
			// �̰� �Ѵ� �¾ƾ� ��������
			dfsCnt=0;

			dfs(Alist.get(0));
//			System.out.println(dfsCnt + " ==? " + Alist.size());

			if(dfsCnt!=Alist.size()) 
				return;
			
			
			
			///////////////////////////////
			//B�� ����ϹǷ� �ʱ�ȭ
			for (int i = 1; i <= n; i++) {
				dfsVisited[i] = false;
			}
			//����ó��
			for (int i = 0; i < Alist.size(); i++) {
				dfsVisited[Alist.get(i)] = true;
			}
			dfsCnt=0;

			dfs(Blist.get(0));
//			System.out.println(dfsCnt + " ==? " + Blist.size());
			
			if(dfsCnt!=Blist.size()) 
				return;
			
			sumGap(Alist, Blist);
			
			return;
		}

		visited[idx] = true;
		permu(idx + 1);
		visited[idx] = false;
		permu(idx + 1);

	}

	public static void dfs(int x) {
		dfsCnt++;
		dfsVisited[x] = true;

		for (int i = 1; i <= n; i++) {
			//����� �κ��̰� �湮 �������Ѱ�.
			if (conArr[x][i] == 1 && !dfsVisited[i]) {
				dfsVisited[i] = true;
				dfs(i);
			}
		}

	}
	
	public static void sumGap(ArrayList<Integer> list1,ArrayList<Integer> list2) {
		int sum1 = 0;
		for (int i = 0; i < list1.size(); i++) {
			sum1+=popArr[list1.get(i)];
		}
		int sum2 = 0;
		for (int i = 0; i < list2.size(); i++) {
			sum2+=popArr[list2.get(i)];
		}
		dif = Math.abs(sum1-sum2);
		if(dif<min)
			min=dif;
		chk++;
	}

}
