package cn.itrip.auth.test;

import org.junit.Test;

public class xiaoshuo {
         int temp=3;
    xiaoshuo increment(){
             temp++;
             return this;
         }
         public void changeValue (int temp){
             temp+=temp;
             this.temp=temp;
         }
         private  void print(){
             System.out.println("temp="+temp);
         }
    public static void main(String[] args) {
        xiaoshuo test = new xiaoshuo();
            int temp1 =5;
            test.increment();
            test.changeValue(temp1);
        System.out.println("temp1="+temp1);
        test.increment().increment().increment().print();
    }
}
