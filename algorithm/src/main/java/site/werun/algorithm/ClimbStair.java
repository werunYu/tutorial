package site.werun.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author werun
 * @date 2024/09/26 15:22
 * @version 1.0
 * @description 给定一个共有
 *  n阶的楼梯，你每步可以上
 *  1阶或者
 *  2阶，请问有多少种方案可以爬到楼顶？
 **/
public class ClimbStair {

    public static void main(String[] args){
        List<Integer> choices = Arrays.asList(1, 2);
        List<Integer> solutions = new ArrayList<>();
        solutions.add(0);
        solution1(choices, 0, 3, solutions);
        System.out.println(solutions.getFirst());
    }

    public static void solution1(List<Integer> choices, int state, int n, List<Integer> solutions) {
        if (state == n) {
            solutions.set(0, 1 + solutions.getFirst());
        }

        for (Integer choice : choices) {
            if (state + choice > n) {
                continue;
            }
            solution1(choices, state + choice, n, solutions);
        }
    }
}