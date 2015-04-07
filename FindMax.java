package com.langsin.homework;

public class FindMax {//求数组的连续最大字数组之和 
		public static int sumN2(int [] array){  
	        int sum=0,maxSum=Integer.MIN_VALUE;  
	        int n=array.length;  
	        int count=0;  
	        for(int i=0;i<n;i++){  
	            sum=0;  
	            for(int j=i;j<n;j++){  
	                sum+=array[j];  
	                if(sum>maxSum){  
	                    maxSum=sum;  
	                }  
	                count++;  
	            }  
	        }  
	        System.out.println("length="+n+"||count="+count);  
	        return maxSum;  
	    }  
	    public static void main(String [] args){   
	        //int b[]={1,-2,3,5,-1};
	        int a[]={1,-2,3,-8,5,1};
	        int sum=FindMax.sumN2(a );  
	        System.out.println("最大子数组和="+sum);  
	    }  
	  
		
	}

 
