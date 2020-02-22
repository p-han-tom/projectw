package com.mygdx.ui;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void test(List<Integer> a) {
		a.add(5);
	}
	
	public static void main(String[] args) {
		List<Integer> b = new ArrayList<Integer>();
		test(b);
		System.out.println(b);
	}
}
