import java.util.*;
import java.io.*;
public class Main
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //도시의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		//도시들까지의 거리 입력받기
		int[] dist = new int[N-1];
		for(int i = 0 ; i < N-1; i++){
		    dist[i] = Integer.parseInt(st.nextToken());
		}
		//각 도시의 기름 가격 입력 받기 
		st = new StringTokenizer(br.readLine());
		int[] oil = new int[N];
		for(int i = 0 ; i < N ; i++){
		    oil[i] = Integer.parseInt(st.nextToken());
		}
		long cost = 0; //비용 담을 변수 
		long minPrice = oil[0];
		//메인 로직
		for(int i = 0 ; i < N-1 ; i++){
		    if(oil[i] < minPrice){
		        minPrice = oil[i];
		        cost += minPrice * dist[i];
		    } else{
		        cost += minPrice * dist[i];
		    }
		    
		}
		//정답 출력 
		System.out.println(cost);
		
	}
}
