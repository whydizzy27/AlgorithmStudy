import java.util.Arrays;

public class kakao2020_블라인드채용_자물쇠와열쇠 {

	public static void main(String[] args) {

		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		System.out.println(solution(key, lock));
	}

	public static boolean solution(int[][] key, int[][] lock) {
		int N = key[0].length;
		int L = lock[0].length;

		int co = 0;
		int S = L+2*N-2;
		int[][] map = new int[S][S];
		
		for (int i = N-1; i <= N+L-2; i++) {
			for (int j = N-1; j <= N+L-2; j++) {
				map[i][j]=lock[i-N+1][j-N+1];
			}
		}
		while (co < 4) {
			
			
			for (int i = 0; i <= S-N; i++) {
				for (int j = 0; j <= S-N; j++) {
					int[][] temp =  new int[S][S]; 
					for (int k = 0; k < S; k++) {
						temp[k]=map[k].clone();
					}
					if(check(i,j,key,N,temp,S,L)) {
						return true;
					}
				}
			}
			key = rotate(key, N);
			

			co++;
		}

		return false;
	}

	private static boolean check(int x, int y, int[][] key, int N, int[][] map, int S,int L) {
		
		for (int i = x; i < x+N; i++) {
			for (int j = y; j < y+N; j++) {
				map[i][j]+=key[i-x][j-y];
			}
		}
		
		for (int i = N-1; i <= N+L-2; i++) {
			for (int j = N-1; j <= N+L-2; j++) {
				if(map[i][j]==2 || map[i][j]==0) {
					return false;
				}
			}
		}
		
		return true;
		
	}

	private static int[][] rotate(int[][] key, int N) {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			temp[i] = key[i].clone();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[j][N - i - 1] = key[i][j];
			}
		}
		return temp;

	}

}
