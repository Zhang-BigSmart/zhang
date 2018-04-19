package sort;

/**
 * @author Edison
 * @ClassName:
 * @Desc:最大堆
 * @date 2017/10/6
 * @history
 */
public class MaxHeapSortTest {


    //构建最大堆
    private int[] buildMaxHeap(int[] array){
        //最后一个非叶子节点array.length-1的父节点下标(array.length-1-1)/2开始
        for (int i = (array.length-2)/2; i >= 0; i--){
            adjustDownToUp(array,i,array.length);
        }
        return array;
    }

    //调整结构
    private void adjustDownToUp(int[] array, int k, int length){
        int temp = array[k];
        //i初始化为节点k的左孩子节点
        for (int i = 2*k+1; i < length-1; i = 2*i+1){
            if (i+1 < length && array[i] < array[i+1]){//比较左右孩子，取最大值(i+1 确保有右孩子)
                i++;
            }
            if (temp >= array[i]){  //根节点大于孩子节点，结束调整
                break;
            }else{ // 根节点<孩子节点
                array[k] = array[i]; //将孩子节点赋值根节点
                k = i;  //修改k值，继续调整
            }
        }
        array[k] = temp;  //将调整的节点的值放入最终位置
    }

    //堆排序
    public int[] heapSort(int[] array){
        //初始化堆 array[0]为第一堂值最大的元素
        array = buildMaxHeap(array);
        for (int i = array.length-1; i>=1; i++){
            //将堆顶元素和堆底元素交换，即得到当前最大元素的正确位置
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            adjustDownToUp(array,0,i);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        MaxHeapSortTest t= new MaxHeapSortTest();
        t.heapSort(a);

    }
}
