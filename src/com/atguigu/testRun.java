package com.atguigu;

public class testRun {
    public static void main(String[] args) {


    }
}

class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;
    //创建构造器
    public ArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=-1;
        front=-1;
    }

    public boolean isFull(){
        return rear>=maxSize-1;
    }

    public boolean isEmpty(){
        return front==rear;
    }

    public void addQueue(int n){
        if (isFull()){
            System.out.println("人家都要溢出来了~");
            return;
        }
        rear++;
        arr[rear]=n;
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，快给人家啦~");
        }
        front++;
        return arr[front];
    }

    public void showQueue(){
        if (isEmpty()){
            System.out.println("人家是空的啦~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("人家是空的啦~");
        }
        return arr[front+1];
    }

}

