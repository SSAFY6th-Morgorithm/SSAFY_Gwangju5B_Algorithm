import java.io.*;
import java.util.*;

/**
 * 14068   140   
 * @author CHO
 * @see https://www.acmicpc.net/problem/17836
 * @category BFS
 * 그람을 잡았을때 vs 안잡았을때 -> visited 3차원 배열
 * 반례    4 4 100
      0 1 1 1
      0 1 2 0
      0 0 0 0
      1 1 1 1
 */
public class BOJ_17836_공주님을구해라 {
   
   static class Position{
      int x;
      int y;
      int attack;

      public Position(int x,int y, int attack) {
         this.x=x;
         this.y=y;
         this.attack=attack;
      ...