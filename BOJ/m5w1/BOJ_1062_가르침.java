package my.solve;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


//백준 가르침
public class BOJ_1062_가르침 {
	static boolean[] visited;
	static int max,temp[],N,K;
	static String[] strArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		strArr = new String[N];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			//anta tica 제거한 스트링 저장
			strArr[i]=temp.substring(4,temp.length()-4);
		}
		
		//알파벳 사용 배열
		visited = new boolean[26];
		//필수
		visited['a'-'a']=true;
		visited['n'-'a']=true;
		visited['t'-'a']=true;
		visited['i'-'a']=true;
		visited['c'-'a']=true;
		
		//K가 5개보다 작으면 필수조건도 만족못하므로 배울수있는 단어개수 0개
		if(K<5) {
			bw.write(0+"\n");
		}else if(K==26){
			bw.write(N+"\n");
		}
		else {
			temp = new int[K-5];
			combination(0,0);
			bw.write(max+"\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}
	private static void combination(int idx, int cur) {
		if(idx==K-5) {
			boolean[] tvisited = Arrays.copyOf(visited, 26);
			
			for (int i = 0; i < K-5; i++) {
				tvisited[temp[i]]=true;
			}
			int cnt=0;
			L:for (int i = 0; i < N; i++) {
				for (int j = 0; j < strArr[i].length(); j++) {
					if(!tvisited[strArr[i].charAt(j)-'a']) continue L;
				}
				cnt++;
			}
			
			max = Math.max(max, cnt);
			return;
		}
		
		for (int i = cur; i < 26; i++) {
			if(visited[i])continue;
			temp[idx]=i;
			combination(idx+1, i+1);
		}
	}
}
