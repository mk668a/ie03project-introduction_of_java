import java.util.PriorityQueue;
import java.util.Scanner;
class Phase1_8{

  static int[] parent, prenum, lowest;
  static int timer=0;
  static boolean[] visited;
  static int V;
  static boolean[][] G;
  static PriorityQueue<State> pq=new PriorityQueue<>();

  static class State implements Comparable<State>{
    int s, t;
    State(int s, int t){
      this.s=s;
      this.t=t;
    }
    public int compareTo(State p) {
      if(p.s==this.s) {
        return this.t-p.t;
      }
      return this.s-p.s;
    }
  }


  static void dfs(int current, int prev) {
    prenum[current]=lowest[current]=timer;
    timer++;
    visited[current]=true;
    int next=0;
    for(int i=0; i<V; i++) {
      if(G[current][i]) {
        next=i;
        if(!visited[next]) {
          parent[next]=current;
          dfs(next, current);
          lowest[current]=Math.min(lowest[current], lowest[next]);
        }
        else if(next != prev) {//currentからnextがbackedgeの時
          lowest[current]=Math.min(lowest[current], prenum[next]);
        }
      }
    }

  }

  static void art_point() {
    for(int i=0; i<V; i++) {
      visited[i]=false;
    }
    timer=1;
    dfs(0, -1);
    //      for(int i=0; i<V; i++) {
    //          System.out.println("prenum["+i+"]="+prenum[i]+" lowest["+i+"]="+lowest[i]);
    //      }

    for(int i=1; i<V; i++) {
      int p=parent[i];
      if(prenum[p]<lowest[i]) {
        pq.add(new State(Math.min(p, i), Math.max(p, i)));
        //System.out.println(Math.min(i, p)+" "+Math.max(i, p));
      }
    }
    System.out.println("幹線道路");
    while(! pq.isEmpty()) {
      State p=pq.remove();
      System.out.println((p.s+1)+"-"+(p.t+1));
    }

  }

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)){
      V=sc.nextInt();
      int E=sc.nextInt();
      int P = sc.nextInt();
      int Q = sc.nextInt();
      parent=new int[V];
      prenum=new int[V];
      lowest=new int[V];
      visited=new boolean[V];
      G=new boolean[V][V];
      for(int i=0; i<V; i++) {
        int s=sc.nextInt();
        int t=sc.nextInt();
      }
      for(int i=0; i<E; i++) {
        int s=sc.nextInt();
        s --;
        int t=sc.nextInt();
        t --;
        G[s][t]=G[t][s]=true;
      }
      art_point();

    }
  }
}