/*
(1922)네트워크 연결
아이디어 : 크루스칼 알고리즘 기본문제로, array sort를 이용해서 가중치를 오름차순으로 정렬한 후, 
unionfind를 통해 간선들을 연결하였습니다.
*/
import java.util.*;
import java.io.*;
public class Main_1922_정우주
{
    //간선 클래스 (comparable을 상속)
    static class Edge implements Comparable<Edge> {
        int from, to, weight; //from : 출발 , to : 도착, weight : 가중치
        //생성자자
        public Edge(int from, int to, int weight){
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        //compareTo 오버라이드 (가중치를 비교하도록)
        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight,o.weight);
        }
    }
    //rootList 초기화하는 메서드
    static void make(){
        for(int i = 0 ; i < rootList.length;i ++){
            if (i==0) continue;
            rootList[i] = i;
        }
    }
    //루트 찾는 메서드
    static int find(int a){
        if (rootList[a] == a) return a; //자기자신이면 자기자신을 루트로 반환
        return rootList[a] = find(rootList[a]); //아니면 find를 재귀호출출
    }
    //union 메서드드
    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA == rootB) return false; //이미 같은 무리면 false반환(union 불가)
        if(rootA > rootB) rootList[rootB] = rootA; //큰 트리 밑으로 작은 트리가 들어가도록록
        else rootList[rootA] = rootB;
        
        return true;
        
    }
    
    static Edge[] eList; //EdgeList
    static int[] rootList; //RootList
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //컴퓨터의 수
		int M = Integer.parseInt(br.readLine()); //간선의 수
		eList = new Edge[M];
		rootList = new int[N+1];
		for(int i = 0 ; i < M ; i++){
		    //간선의 정보 입력받기
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int from = Integer.parseInt(st.nextToken());
		    int to = Integer.parseInt(st.nextToken());
		    int weight = Integer.parseInt(st.nextToken());
		    eList[i] = new Edge(from,to,weight); //Edge 객체 만들어서 넣기
		}
		Arrays.sort(eList); //eList 오름차순 정렬렬
		make();
		int result = 0; //결과 
		int cnt = 0; //연결한 간선의 수
		for(Edge e : eList){
		    if(!union(e.from,e.to)) continue; //union이 불가하면 넘어가기
		    result += e.weight; //weight 누적하기
		    if(++cnt == N-1) break; //cnt가 N-1이 되면 그만하기기
		    
		}
		System.out.println(result);
		
	}
}