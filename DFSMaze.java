package wht.wyw.recursion;

import java.util.*;
public class DFSMaze {
    HashMap<int[][], Integer> hm = new HashMap<>(); // 引用数据Integer  基本数据类型 （int）
    int[][] map; // 迷宫，0表示空地,1表示障碍物，
    int[][] access; // 访问数组, 0表示未访问，1表示已访问
    int min = 100000; // 初始化最小步数
    int count = 0; // 总路径

    public DFSMaze(int[][] map) {
        this.map = map; // 初始化
        this.access = new int[map.length][map.length];
        access[0][0] = 1;
    }

    public int[][] arrCopy(int[][] arr) {
        int[][] newArr = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }

    public void dfsFind(int x, int y, int step) {
        if (x == map.length - 1 && y == map.length - 1) { // 走通
            count++; // 总路径
            int[][] arr = arrCopy(access); //
            hm.put(arr, step); //
            if (step <= min) {
                min = step;
            }
            return;
        }
        // 右
        if (y < map.length - 1 && map[x][y + 1] == 0 && access[x][y + 1] == 0) {
            access[x][y + 1] = 1; // 0
            dfsFind(x, y + 1, step + 1); //
            access[x][y + 1] = 0;
        }
        // 下
        if (x < map.length - 1 && map[x + 1][y] == 0 && access[x + 1][y] == 0) {
            access[x + 1][y] = 1;
            dfsFind(x + 1, y, step +1);
            access[x + 1][y] = 0;
        }
        // 左
        if (y > 0 && map[x][y - 1] == 0 && access[x][y - 1] == 0) {
            access[x][y - 1] = 1;
            dfsFind(x, y - 1, step +1);
            access[x][y - 1] = 0;
        }
        //上
        if (x > 0 && map[x - 1][y] == 0 && access[x - 1][y] == 0) {
            access[x - 1][y] = 1;
            dfsFind(x - 1, y, step +1);
            access[x - 1][y] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] map = {      // 这里可以传入任意数组(迷宫)
                {0, 0, 1, 1},
                {1, 0, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        DFSMaze dfsMaze = new DFSMaze(map);
        dfsMaze.dfsFind(0, 0, 0);
        int minStep = dfsMaze.min; // 最小步数
        System.out.println("总路径 = " + dfsMaze.count);
        HashMap<int[][], Integer> hashMap = dfsMaze.hm;
        Set<Map.Entry<int[][], Integer>> entries = hashMap.entrySet();
        System.out.println("最小步数为 " + minStep);
        System.out.println("路径为： ");
        for (Map.Entry<int[][], Integer> entry : entries) { //
            if (entry.getValue() == minStep) {
                int[][] keyArry = entry.getKey();
                printArray(keyArry);
                System.out.println("--------------------------");
            }
        }

    }

    public static void printArray(int[][] arr) {
        for (int[] row : arr) {
            for (int column : row) {
                System.out.print(column + "  ");
            }
            System.out.println();
        }
    }
}
