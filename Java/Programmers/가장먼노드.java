//다익스트라 버전

import java.util.*;
class Solution {
    class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost){
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost,o.cost);
        }
    }

    static List<Node>[] graph;
    static int[] dist;
    static int INF = Integer.MAX_VALUE;
    public void Dijkstra(int n, int start){
        boolean[] check = new boolean[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));

        while(!pq.isEmpty()){
            int now = pq.poll().index;

            if(check[now]) continue;
            check[now] = true;

            //연결된 정점 비교
            for(Node next : graph[now]){
                if(dist[next.index] > dist[now] + 1){
                    dist[next.index] = dist[now] + 1;
                    pq.offer(new Node(next.index,dist[next.index]));
                }
            }
        }

    }
    public int solution(int n, int[][] edge) {
        //간선 개수 담을 배열 
        dist = new int[n+1];
        //배열 무한으로 초기화하기
        Arrays.fill(dist,INF);
        //시작이 1이므로 1->1은 비용 0
        dist[1] = 0;
        //그래프 관계 리스트 배열 초기화
        graph = new ArrayList[n+1];
        //배열 속 리스트 할당
        for(int i = 0 ; i <= n ; i++) graph[i] = new ArrayList<>();
        //그래프 관계 넣기
        for(int[] e : edge){
            graph[e[0]].add(new Node(e[1],1));
            graph[e[1]].add(new Node(e[0],1));
        }
        //다익스트라 수행 
        Dijkstra(n,1);

        //가장 멀리 떨어진 노드 개수 구하기
        int max = -1;
        int cnt = 0;
        for(int i = 1 ; i <= n ; i++){
            if(dist[i] != INF && dist[i] >= max){
                if(dist[i] == max) cnt++;
                else cnt = 1;
                max = dist[i];
            }
        }


        return cnt;
    }
}
