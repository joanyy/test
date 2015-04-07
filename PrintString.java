package com.langsin.homework;

import java.util.Scanner;

public class PrintString {//倒序输出一个英语句子
	public static void main(String[] args) {
		System.out.println("请输入一个英语句子：");
		Scanner input=new Scanner(System.in);
		String s=input.nextLine(); 
		String s1[]=s.split(" "); 
		for(int i=s1.length-1;i>=0;i--){
			System.out.print(s1[i]+" ");
		}
		System.out.println("\n sno:201303011121");
	} 
}
