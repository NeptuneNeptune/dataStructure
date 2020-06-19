package data.structure.sample.sort;

import com.sun.scenario.effect.Brightpass;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

/**
 * 经典排序
 *
 * @author Neptune
 * @date 2020/6/19 13:40
 */
public class Sort {
    /**
     * 冒泡排序算法实现
     *
     * @param array 要排序的数组
     */
    public static void bubbleSort(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    exchange(array, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序算法实现
     *
     * @param array 要排序的数组
     */
    public static void selectSort(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIndex].compareTo(array[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                exchange(array, minIndex, i);
            }
        }
    }

    /**
     * 快速排序算法实现
     *
     * @param array 要排序的数组
     * @param start 要排序部分的起始坐标
     * @param end   要排序部分的结束坐标
     */
    public static void quickSort(Comparable[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        // 记录数据，后面要用
        int startFlag = start;
        int endFlag = end;
        // 下面的做法是为了让key取到相对来说大小适中的数
        // 有可能会减少扫描过程中交换的次数，提高效率
        int mid = (start + end) / 2;
        if (array[mid].compareTo(array[end]) > 0) {
            exchange(array, mid, end);
        }
        if (array[start].compareTo(array[end]) > 0) {
            exchange(array, start, end);
        }
        if (array[mid].compareTo(array[start]) > 0) {
            exchange(array, mid, start);
        }
        Comparable<?> key = array[start];

        while (start < end) {
            // 从后往前扫描找到比基准值小的数
            while ((start < end) && (array[end].compareTo(key) > 0)) {
                end--;
            }
            array[start] = array[end];
            // 从前往后扫描找到比基准值大的数
            while ((start < end) && (array[start].compareTo(key) <= 0)) {
                start++;
            }
            array[end] = array[start];
        }
        // 将基准值复位，否则这个数据就丢失了
        array[start] = key;
        quickSort(array, startFlag, start - 1);
        quickSort(array, start + 1, endFlag);
    }

    private static void exchange(Comparable[] array, int i, int j) {
        Comparable<?> temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 插入排序算法实现
     *
     * @param array 要排序的数组
     */
    public static void insertSort(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertIndex = i - 1;
            Comparable<?> temp = array[i];
            while (insertIndex >= 0 && array[insertIndex].compareTo(temp) > 0) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = temp;
        }
    }

    /**
     * 希尔排序算法实现，交换式，采用交换，速度反而比插入排序更慢
     */
    public static void shellSort(Comparable[] array) {
        int stepLen = array.length;
        while ((stepLen = stepLen / 2) > 0) {
            for (int i = stepLen; i < array.length; i++) {
                for (int j = i - stepLen; j >= 0; j -= stepLen) {
                    if (array[j].compareTo(array[j + stepLen]) > 0) {
                        exchange(array, j, j + stepLen);
                    }
                }
            }
        }
    }

    /**
     * 希尔排序算法实现，移动式，对每一组使用插入排序算法，效率大大提升
     */
    public static void shellSort1(Comparable[] array) {
        int stepLen = array.length;
        while ((stepLen = stepLen / 2) > 0) {
            for (int i = stepLen; i < array.length; i++) {
                int insertIndex = i - stepLen;
                Comparable<?> temp = array[i];
                while (insertIndex >= 0 && array[insertIndex].compareTo(temp) > 0) {
                    array[insertIndex + stepLen] = array[insertIndex];
                    insertIndex -= stepLen;
                }
                array[insertIndex + stepLen] = temp;
            }
        }
    }

    /**
     * 归并排序算法实现
     *
     * @param array 原始数组
     * @param start 左边有序序列的初始索引
     * @param end   右边索引
     * @param temp  中转数组
     */
    public static void mergeSort(Comparable[] array, int start, int end, Comparable[] temp) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid, temp);
            mergeSort(array, mid + 1, end, temp);
            merge(array, start, end, temp);
        }
    }

    /**
     * 合并
     *
     * @param array 原始数组
     * @param start 左边有序序列的初始索引
     * @param end   右边索引
     * @param temp  中转数组
     */
    public static void merge(Comparable[] array, int start, int end, Comparable[] temp) {
        int mid = (start + end) / 2;
        // 左边有序序列的初始索引
        int leftFlag = start;
        // 右边有序序列的初始索引
        int rightFlag = mid + 1;
        // temp数组的当前索引
        int tempFlag = 0;
        // 把左右两边有序的数据按规则填充到temp
        // 直到直到左右两边有序序列有一边处理完
        while (leftFlag <= mid && rightFlag <= end) {
            // 小的那一边数据拷贝到temp，并把小的那一边的索引和temp的索引加一
            if (array[leftFlag].compareTo(array[rightFlag]) <= 0) {
                temp[tempFlag] = array[leftFlag];
                tempFlag++;
                leftFlag++;
            } else {
                temp[tempFlag] = array[rightFlag];
                tempFlag++;
                rightFlag++;
            }
        }
        // 把有剩余数据的一边的数据依次填充到temp
        // 如果左边还有剩余
        while (leftFlag <= mid) {
            temp[tempFlag] = array[leftFlag];
            leftFlag++;
            tempFlag++;
        }
        // 如果右边还有剩余
        while (rightFlag <= end) {
            temp[tempFlag] = array[rightFlag];
            rightFlag++;
            tempFlag++;
        }
        // 将temp的元素copy到array
        System.arraycopy(temp, 0, array, start, end - start + 1);
    }

    /**
     * 基数排序  radixSort或者binSort
     */
    public static void radixSort(Integer[] array) {
        // 得到最大的位数
        Integer max = array[0];
        for (Integer integer : array) {
            if (max < integer) {
                max = integer;
            }
        }
        int maxLen = String.valueOf(max).length() + 1;
        for (int i = 1; i < maxLen; i++) {
            // 容器，用来按照位存储数据，对应0-9
            Integer[][] containers = new Integer[10][array.length];
            // 计数器，为了方便后面的运行，定义一个一维数组存储每个容器的有效数据个数
            int[] counter = new int[10];
            for (Integer integer : array) {
                int digit = (int) (integer % Math.pow(10, i) / Math.pow(10, i - 1));
                containers[digit][counter[digit]] = integer;
                counter[digit]++;
            }
            int index = 0;
            for (int j = 0; j < containers.length; j++) {
                System.arraycopy(containers[j], 0, array, index, counter[j]);
                index += counter[j];
            }
        }
    }

}

class Test {
    public static void main(String[] args) {
        int len = 80000;
        Integer[] array = new Integer[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            array[i] = random.nextInt(len);
        }
        Instant start = Instant.now();

//        Sort.bubbleSort(array);  // 70516
//        Sort.selectSort(array);  // 8094
//        Sort.quickSort(array, 0, array.length - 1);  // 43
//        Sort.insertSort(array);  // 9346
//        Sort.shellSort(array);  // 58586
//        Sort.shellSort1(array);  // 69
//        Sort.mergeSort(array, 0, array.length - 1, new Integer[array.length]);  // 72
//        Sort.radixSort(array);  // 81

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
        System.out.println(Arrays.toString(array));
    }
}