package algoExpert;

public class QuickSort {

    public static void main(String[] args) {

        int[] array = {1,4,8,2,7,6,5,3};
        printArray(quickSort(array));
    }

    public static int[] quickSort(int[] array){

        sortHelper(array,0,array.length -1);

        return array;
    }

    private static void sortHelper(int[] array,int start,int end){

        if(start >= end){
            return;
        }

        int pivotIndex = start;
        int left = pivotIndex +1;
        int right = end;

        while(left <= right){
            if(array[left] > array[pivotIndex] && array[right] < array[pivotIndex]){
                swap(array,left,right);
            } else if(array[left] < array[pivotIndex]){
                left++;
            } else if(array[right] > array[pivotIndex]){
                right--;
            }

        }

        swap(array,pivotIndex,right);

        sortHelper(array,start,right -1);
        sortHelper(array,right +1,end);
    }

    private static void swap(int[] array,int leftIndex,int rightIndex){
        int temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
    }

    public static void printArray(int[] array){
        for(int i : array){
            System.out.print(i + " ");
        }
    }
}
