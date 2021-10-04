package com.atguigu.Stack;

public class Caculator {

    public static void main(String[] args) {

    }

}

class ArrayStack2{
    private int maxSize;    //栈的大小
    private int[] stack;    //数组模拟栈
    private int top=-1;     //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }

    //判断栈满
    public boolean isFull(){
        return top==maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top==-1;
    }

    //入栈-push
    public void push(int value){
        //先判断是否为满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    //出栈-pop,返回栈顶元素
    public int pop(){
        if (isEmpty()){ //判断是否为空
            throw new RuntimeException("栈空，没有数据");  //抛出异常
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据~");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);

        }
    }


}
