package com.example.demo;



import org.springframework.data.util.Pair;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int matrix[][]= new int[][]
                {
                        {0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}
                };
        //mostBooked(1, matrix); [[0,1,5],[1,2,5],[0,3,2],[3,1,2],[1,4,1],[4,2,1]], 0, 2, 2
        isPowerOfTwo(-2147483648);
        rangeBitwiseAnd(5,7);
        findLeastNumOfUniqueInts(new int[]{5,5,4}, 1);
        findJudge(3, new int[][]{{1,3},{2,3}});

        findCheapestPrice(5,matrix,0,2,2);
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] flight : flights) {
            int u = flight[0];
            int v = flight[1];
            int cost = flight[2];

            adj.computeIfAbsent(u, key -> new ArrayList<>()).add(new int[]{v, cost});
        }

        // BFS
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{src, 0});
        distance[src] = 0;

        int steps = 0;

        while (!queue.isEmpty() && steps <= k) {
            int size = queue.size();
            while (size-- > 0) {
                int[] node = queue.poll();
                int u = node[0];
                int p = node[1];

                for (int[] edge : adj.getOrDefault(u, new ArrayList<>())) {
                    int v = edge[0];
                    int cost = edge[1];

                    if (distance[v] > p + cost) {
                        distance[v] = p + cost;
                        queue.offer(new int[]{v, p + cost});
                    }
                }
            }
            steps++;
        }

        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }

    public int countPairs(int[] nums, int low, int high) {
        int r =0;
        for(int i =0;i<nums.length;i++)
        {
            for(int j =i+1;j<nums.length;j++)
            {
                int tmp = nums[i]^nums[j];
                if(tmp>=low&&tmp<=high)
                    r++;
            }
        }
        return r;
    }
    public static int findJudge(int n, int[][] trust) {
        Map<Integer,List<Integer>>map = new HashMap<>();
        Map<Integer,List<Integer>>map1 = new HashMap<>();
        for(int i =1;i<=n;i++)
        {
            map.put(i, new ArrayList<>());
            map1.put(i, new ArrayList<>());
        }
        for(int i =0;i<trust.length;i++)
        {
            map.get(trust[i][0]).add(trust[i][1]);
            map1.get(trust[i][1]).add(trust[i][0]);
        }
        for(int k : map.keySet())
        {
            if(map1.get(k).size()==n-1&&map.get(k).isEmpty())
                return k;
        }
        return -1;
    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer>map = new HashMap<>();
        for(int i =0;i<arr.length;i++)
        {
            map.put(arr[i], map.getOrDefault(arr[i],0)+1);
        }
        List<Integer>l = new ArrayList<>();
        for(int i: map.keySet())
        {
            l.add(map.get(i));
        }
        Collections.sort(l);
        int idx=0;
        while(idx<l.size()&&k>=l.get(idx))
        {
            k-=l.get(0);
            idx++;
        }
        return l.size()-idx;
    }

    public static boolean isPowerOfTwo(int n) {
        long t = n&((long)n-1);
        return n!=0&&t==0;
    }

    public static int rangeBitwiseAnd(int left, int right) {
        while (left<right)
        {
            right&=right-1;
        }
        return left&right;
    }
    public static int mostBooked(int n, int[][] meetings) {
        int t = n&(n-1);
        Arrays.sort(meetings, (a,b)->a[0]-b[0]);
        PriorityQueue<Meet> q = new PriorityQueue<>((a, b) ->a.val==b.val?a.idx- b.idx:Long.compare(a.val,b.val));
        for (int i = 0; i < n; i++) {
            q.offer(new Meet(i,0l));
        }
        long[]rooms = new long[meetings.length];
        int r=meetings.length;long max =-1;
        Set<Integer> vis = new HashSet<>();
        for(int i =0;i<meetings.length;i++) {
            while (!q.isEmpty() && q.peek().val <= meetings[i][0]&&!vis.contains(q.peek().idx)) {
                Meet tmp = q.poll();
                q.offer(new Meet(tmp.idx, meetings[i][0]));
                vis.add(tmp.idx);
            }
            vis.clear();
            Meet top = q.poll();
            rooms[top.idx]++;
            if (max <  rooms[top.idx]||(max== rooms[top.idx]&&r>top.idx))
            {
                r=  top.idx;
                max =  rooms[top.idx];
            }
            q.offer(new Meet(top.idx,Math.max(top.val, meetings[i][0])+meetings[i][1]-meetings[i][0]));
        }
        return r;
    }
    public static class Meet{
        public int idx;
        public long val;

        public Meet(int idx, long val) {
            this.idx = idx;
            this.val = val;
        }
    }





    /*
    *
    *  [1,2], [1,2], [2,4], [5,9]
    *
    * */

    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int[] distance = new int[n];
            Arrays.fill(distance, Integer.MAX_VALUE);

            Map<Integer, List<int[]>> adj = new HashMap<>();
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int cost = flight[2];

                adj.computeIfAbsent(u, key -> new ArrayList<>()).add(new int[]{v, cost});
            }

            // BFS
            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(new int[]{src, 0});
            distance[src] = 0;

            int steps = 0;

            while (!queue.isEmpty() && steps <= k) {
                int size = queue.size();
                while (size-- > 0) {
                    int[] node = queue.poll();
                    int u = node[0];
                    int p = node[1];

                    for (int[] edge : adj.getOrDefault(u, new ArrayList<>())) {
                        int v = edge[0];
                        int cost = edge[1];

                        if (distance[v] > p + cost) {
                            distance[v] = p + cost;
                            queue.offer(new int[]{v, p + cost});
                        }
                    }
                }
                steps++;
            }

            return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
        }
    }

}
