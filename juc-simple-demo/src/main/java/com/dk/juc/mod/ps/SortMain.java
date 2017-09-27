package com.dk.juc.mod.ps;

import java.util.Arrays;

/**
 * Created with MyNetty
 *
 * @author dk05408
 * @Description:
 * @create 2017-09-21 15:08
 **/
public class SortMain {
    static int[] arr={1,4,2,6,35,3};

    //冒泡排序
    public static void bubbleSort(int[] arr){
        for (int i = arr.length - 1; i >0 ; i--) {
            for (int j = 0; j < i; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    System.out.println(Arrays.toString(arr));
                }
            }
        }

    }

    //奇偶交换排序的串行实现
    public static void oddEventSort(int[] arr){
        int exchFlag = 1,start = 0;
        while (exchFlag == 1 || start == 1){
            exchFlag = 0;
            for (int i = start; i <arr.length - 1 ; i+=2) {
                if(arr[i]>arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    exchFlag = 1;
                }
            }
            if(start == 0){
                start = 1;
            }else {
                start = 0;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(arr));
//        bubbleSort(arr);
        oddEventSort(arr);
    }
}
