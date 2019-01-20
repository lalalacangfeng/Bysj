package com.test;

import java.util.Arrays;
import java.util.Comparator;

public class testLambda {
	public static void main(String[] args) {
		// 注意，要想改变默认的排列顺序，不能使用基本类型（int,double, char）
		// 而要使用它们对应的类
		Integer[] a = { 9, 8, 7, 2, 3, 4, 1, 0, 6, 5 };
		// 定义一个自定义类MyComparator的对象
		Comparator cmp = new MyComparator();
		Arrays.sort(a, cmp);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}

class MyComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		// 如果n1小于n2，我们就返回正值，如果n1大于n2我们就返回负值，
		// 这样颠倒一下，就可以实现反向排序了
		if (o1 < o2) {
			return 1;
		} else if (o1 > o2) {
			return -1;
		} else {
			return 0;
		}
	}

}