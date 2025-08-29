/*
난이도 : 실버 3, 메모리 : 59536KB, 시간 : 1140ms
아이디어 : 누적합과 구간합을 이용해서 처음 배열을 입력받을때 바로 누적합 배열을 만들고,
구간합을 구했습니다.
*/
import java.util.*;
import java.io.*;

public class Main_11659_정우주주
{
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    int[] nums = new int[n]; //숫자 배열
	    int[] prefix = new int[n+1]; //누적합 배열
	    st = new StringTokenizer(br.readLine());
	    //배열의 숫자 입력받으면서 누적합 만들기기
	    for (int i = 0 ; i < n; i++){
	        nums[i] = Integer.parseInt(st.nextToken());
	        prefix[i+1] = prefix[i] + nums[i];
	    }
	    //m만큼 구간합 구하기
	    for(int i = 0 ; i < m ; i++){
	        st = new StringTokenizer(br.readLine());
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	        System.out.println(prefix[b] - prefix[a-1]);
	    }

	}
}
