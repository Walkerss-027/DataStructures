package com.atguigu.Stack;

public class Caculator {

    public static void main(String[] args) {
        //测试
        String exression = "3+2*6-2";
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index=0;    //索引，用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch =' ';   //将每次扫描到的char保存到ch
        String keepNum="";  //用于拼接多位数

        //while循环扫描expression
        while (true){
            //依次得到每个expression的每一个字符
            //substring返回字符串,charAt返回指定索引处的字符
            ch = exression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后对ch进行处理
            if (operStack.isOper(ch)){  //如果ch是运算符
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()){  //如果符号栈非空
                    //进行比较，如果当前操作符的优先级小于或者等于栈中操作符，就需要从数栈中pop出两个数，
                    //再从符号栈中pop出一个符号，进行运算，将得到结果，把结果放到数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        //把运算结果入数栈
                        numStack.push(res);
                        //把当前操作符入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前操作符优先级大于栈中的操作符就直接将当前操作符入符号栈
                        operStack.push(ch);
                    }

                }else{
                    //如果当前栈为空直接入符号栈
                    operStack.push(ch);
                }
            }else { //如果是数，则直接入数栈
                // numStack.push(ch-48);   //这里ch是字符ASIC码，数字1是从第49开始
                //分析思路
                //1.当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2.在处理数，需要向expression的表达式的index后再看一位，如果是数就进行扫描，如果是符号才入栈
                //3.因此我们需要定义一个变量字符串用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression最后一位，就直接入栈
                if (index == exression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {

                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(exression.substring(index + 1, index + 2).charAt(0))) {  //如果后一位是运算符则入栈 keepNim="1"或者"123"
                        numStack.push(Integer.parseInt(keepNum));
                        //重要！！！ keepNum清空
                        keepNum = "";

                    }
                }
            }
            //让index+1，并判断是否扫描到expression最后
            index++;
            if (index>=exression.length()){
                break;
            }
        }   //while的括号

        //当表达式扫描完毕，就顺序从数栈和符号栈中pop出对应的数和符号，并运行。
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //将数栈的最后数pop出就是结果
        int res2=numStack.pop();
        System.out.printf("表达式%s=%d",exression,res2);
    }

}

class ArrayStack2 {
    private int maxSize;    //栈的大小
    private int[] stack;    //数组模拟栈
    private int top = -1;     //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }


    //返回栈顶元素peek
    public int peek(){
        return stack[top];
    }


    //判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }


    //栈空
    public boolean isEmpty() {
        return top == -1;
    }


    //入栈-push
    public void push(int value) {
        //先判断是否为满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }


    //出栈-pop,返回栈顶元素
    public int pop() {
        if (isEmpty()) { //判断是否为空
            throw new RuntimeException("栈空，没有数据");  //抛出异常
        }
        int value = stack[top];
        top--;
        return value;
    }


    //显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);

        }
    }


    //返回运算符的优先级，优先级使用数字表示，数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }


    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }


    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;  //res用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }


}
