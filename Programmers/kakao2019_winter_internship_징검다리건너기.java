import java.util.List;

public class kakao2019_winter_internship_징검다리건너기 {

	public static void main(String[] args) {
		
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		System.out.println(Solution(stones,3));
	}

	private static int Solution(int[] stones, int k) {
		int min = Integer.MAX_VALUE;
		int max = 0;
		int ans = 0;
		int len = stones.length;
		for (int i : stones) {
			max = Math.max(max, i);
			min = Math.min(min, i);
		}
		
		while(min<=max) {
			
			int mid = (min+max)/2;
			if(isPossible(k,len,mid,stones.clone())) {
				ans =mid;
				min=mid+1;
			}else {
				max=mid-1;
			}
		}
				
		return ans;		
	}

	private static boolean isPossible(int k, int len, int mid, int[] clone) {
		for (int i = 0; i < len; i++) {
			clone[i]-=mid;
		}
		
		int cnt=0;
		for (int i = 0; i < len; i++) {
			if(clone[i]<0) {
				cnt++;
				if(cnt==k)
					return false;
			}else {
				cnt=0;
			}
		}
		
		return true;
	}

}
