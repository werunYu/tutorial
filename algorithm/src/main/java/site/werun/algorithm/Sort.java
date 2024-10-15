package site.werun.algorithm;

import java.util.Arrays;

/**
 * @author werun
 * @date 2024/09/27 14:39
 * @version 1.0
 * @description 排序算法.
 */
public class Sort {

//  public static void main(String[] args) {
//    int[] arrays = {6, 5, 9, 2, 1, 0, 4, 7, 3, 8};
//    //        insertSort(arrays);
//    quickSort(arrays, 0, arrays.length - 1);
//    System.out.println(Arrays.toString(arrays));
//  }

  /**
   * 选择排序.
   *
   * @param numbers 待排序数组
   */
  public static void selectSort(int[] numbers) {
    int n = numbers.length;
    for (int i = 0; i < n - 1; i++) {
      int k = i;
      for (int j = i + 1; j < n; j++) {
        if (numbers[j] < numbers[k]) {
          k = j;
        }
      }

      int tmp = numbers[i];
      numbers[i] = numbers[k];
      numbers[k] = tmp;
    }
  }

  /**
   * 冒泡排序.
   *
   * @param numbers 待排序数组
   */
  public static void bubbleSort(int[] numbers) {
    int n = numbers.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (numbers[j] > numbers[j + 1]) {
          int tmp = numbers[j];
          numbers[j] = numbers[j + 1];
          numbers[j + 1] = tmp;
        }
      }
    }
  }

  /**
   * 插入排序.
   *
   * @param numbers 待排序数组
   */
  public static void insertSort(int[] numbers) {
    for (int i = 1; i < numbers.length; i++) {
      int base = numbers[i];
      int j = i - 1;
      while (j >= 0 && numbers[j] > base) {
        numbers[j + 1] = numbers[j];
        j--;
      }

      numbers[j + 1] = base;
    }
  }

  /* 元素交换 */
  private static void swap(int[] numbers, int i, int j) {
    int tmp = numbers[i];
    numbers[i] = numbers[j];
    numbers[j] = tmp;
  }

  /* 哨兵划分 */
  private static int partition(int[] numbers, int left, int right) {
    int i = left;
    int j = right;
    while (i < j) {
      while (i < j && numbers[j] >= numbers[left]) {
        j--;
      }

      while (i < j && numbers[i] <= numbers[left]) {
        i++;
      }

      swap(numbers, i, j);
    }

    swap(numbers, i, left);
    return i;
  }

  /**
   * 快速排序.
   *
   * @param numbers 待排序数组.
   * @param left 排序起点
   * @param right 排序终点
   */
  public static void quickSort(int[] numbers, int left, int right) {
    // 子数组长度为 1 时终止递归
    if (left >= right) return;

    // 哨兵划分
    int pivot = partition(numbers, left, right);

    // 递归左子数组、右子数组
    quickSort(numbers, left, pivot - 1);
    quickSort(numbers, pivot + 1, right);
  }
}
