package com.atguigu.Stack;

import javax.management.StringValueExp;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {


        //完成将一个中缀表达式转换成后缀表达式的功能
        //说明
        //1. 1+((2+3)x4)-5 =>转成 1 2 3 + 4 x + 5 -
        //2. 因为直接对str 进行操作，不方便，因此，先将”1+((2+3)x4)-5“ =>中缀的表达式对应的List
        //      即”1+((2+3)x4)-5“ => ArrayList [1,+,(,(,2,+,3),*,4,),-,5]
        //3. 将得到的中缀表达式对应的 List => 后缀表达式对应的 List
        //      即 ArrayList [1,+,(,(,2,+,3,)4,),-,5] => ArrayList [1,2,3,+,4,*,+,5,-]

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List="+infixExpressionList);    // [1, +, (, (, 2, +, 3, ), x, 4, ), -, 5]
        List<String > suffixExperssionList=parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List"+suffixExperssionList);    // [1,2,3,+,4,*,+,5,-]

        System.out.printf("expression=%d",calculate(suffixExperssionList));

        /*
        //先定义逆波兰表达式
        //4x5-8+60+8 => 4 5 * 8 - 60 + 8 2 / + => 164
        String suffixExpression = "4 5 x 8 - 60 + 8 2 / +";
        //思路
        //1.先将“3 4 + 5 x 6 -” =>放入到ArrayList中
        //2.将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈完成

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);

        int res = calculate(rpnList);
        System.out.println("计算的结果是=" + res);
        */
    }


    //方法：将 中缀表达式转换成对应的List
    //s="1+((2+3)x4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List，存放中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;  //指针，用于遍历中缀表达式字符串
        String str;     //对多位数的拼接
        char c;     //每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add(String.valueOf(c));
                i++;    //i 后移
            } else { //如果是一个数，需要考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }

        } while (i < s.length());
        return ls;
    }


    //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] => ArrayList [1,2,3,+,4,*,+,5,-]
    //方法：将得到的中缀表达式对应的List =>后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //创建两个栈
        Stack<String> s1 = new Stack<String>();
        //说明：因为s2这个栈，再整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用Stack<String> 直接使用List<String> s2
        //Stack<String> s2 = new Stack<String>();   //存储中间结果的栈 s2
        List<String> s2 = new ArrayList<>();

        //遍历ls
        for (String item:ls){
            //如果一个数，加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();   //！！！ 将（弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的元素安抚弹出并加入到s2中，与s1中新的栈顶运算符相比较
                //问题：缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }

        return s2;  //因为是存放到List中，因此按顺序输出就是对应的后缀表达式

    }


    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }


    //完成对逆波兰表达式的运算
    /*
    1）从左至右扫描，将3和4压入堆栈；
    2）遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算3+4的值，得7，再将7入栈；
    3）将5入栈；
    4）接下来是x运算符，因此弹出5和7，计算出7x5=35，将35入栈；
    5）将6入栈；
    7）最后是-运算符，计算出35-6的值，即29，因此得出最终结果
    */


    public static int calculate(List<String> ls) {
        //创建一个栈
        Stack<String> stack = new Stack<String>();
        //遍历 ls
        for (String item : ls) {
            if (item.matches("\\d+")) {  //匹配的是多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(String.valueOf(res));
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}


//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result=ADD;
                break;
            case "-":
                result=SUB;
                break;
            case "*":
                result=MUL;
                break;
            case "/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}