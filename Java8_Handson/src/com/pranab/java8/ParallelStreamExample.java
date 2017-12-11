package com.pranab.java8;

import java.util.stream.Stream;

public class ParallelStreamExample {

	public static void main(String[] args) {
		Stream.<Integer>of(1, 2, 3, 4, 5)
		.parallel()
		.forEach(System.out::print);
		
		System.out.println("\n------------------------------");
		
		Stream.<Integer>of(1, 2, 3, 4, 5)
		.parallel()
		.unordered()
		.forEachOrdered(System.out::print);
	}

}
