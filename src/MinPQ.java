//import java.util.*;
//
//public class MinPQ<Key> implements Comparable<Key, Key>{
//    private Key[] pq;
//    private int n;
//    private Comparator<Key> comparator;
//
//    //初始化
//
//    public MinPQ(int max){
//        pq = (Key[]) new Object[max + 1];
//        n = 0;
//    }
//    public MinPQ() {
//        this(1);
//    }
//
//    //插入元素
//    public void insert(Key key) {
//        //当元素个数等于pq数组末尾索引时，将pq数组长度翻倍（通过resize方法）
//        if(n==pq.length-1) resize(2*pq.length);
//
//        //将插入的元素放在数组末尾，然后通过上浮实现堆有序化
//        pq[++n] = key;
//        swim(n);
//    }
//
//    //resize函数，改变数组长度
//    private void resize(int max) {
//        Key[] temp = (Key []) new Object[max];
//        if (n >= 0) System.arraycopy(pq, 1, temp, 1, n);
//        pq = temp;
//    }
//
//    //返回最小值
//    public Key min() {
//        return pq[1];
//    }
//
//    //删除最小值并返回
//    public Key delMin() {
//        Key min = pq[1];
//
//        //交换堆顶与末尾的元素位置，然后将置换后的堆顶元素下沉
//        exch(1,n--);
//        pq[n+1] = null; //防止元素游离
//        sink(1);       //下沉元素
//        if(n<=(pq.length-1)/4) resize(pq.length/2); //当删除最小元素pq数组元素数量远小于数组长度时，将数组长度减半
//        return min;
//    }
//
//    //上浮指定位点，实现堆有序化
//    public void swim(int k) {
//        while(k>1&&less(k,k/2)) {
//            exch(k,k/2);
//            k/=2;
//        }
//
//    }
//
//    //下沉元素实现堆有序
//    public void sink(int k) {
//        while(2*k<=n) {
//            int j = 2*k;
//            if(j<n&&less(j+1,j)) j++;
//            if(less(k,j))break;
//            exch(k,j);
//            k = j;
//        }
//    }
//
//
//    private boolean less(int i,int j) {
////        return pq[i].comparator(pq[j])<0;
//        return comparator.compare(pq[i], pq[j]) < 0;
//    }
//
//    private void exch(int i,int j) {
//        Key temp = pq[i];
//        pq[i] = pq[j];
//        pq[j] = temp;
//    }
//
//    public int size() {return n;}
//    public boolean isEmpty() {return n==0;}
//
//}
