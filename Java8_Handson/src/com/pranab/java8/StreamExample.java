package com.pranab.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {
		Stream.<String>of("sun", "pool", "beach", "kid", "island", "sea", "sand")
	    .map(String::length)
	    .filter(i -> i > 3)
	    .limit(2)
	    .forEach(System.out::println);
		
		/*
		 * Populate list from another list using matching data
		 */
	    List<A> listA = Arrays.asList(new A("Bill", "billRef"), new A("Tom", "tomRef"), new A("Shik", "shikRef"));
		List<B> listB = Arrays.asList(new B("billRef"), new B("tomRef"), new B("shikRef"));
		
		Map<String, A> mapA = listA.stream().collect(Collectors.toMap(A::getRefB, s->s));
		//mapA.forEach((key, obj) -> System.out.println(key +" - " + obj.getName()));
	    
		listB.stream()
		.filter(b -> mapA.containsKey(b.getRef()))
		.peek(b -> {
			A a = mapA.get(b.getRef());
			b.setDerivedName(a.getName());
		})
		.forEach(System.out::println);
		
		/*
		 *  What is output ?
		 */
		IntStream.range(1, 10)
        .filter(i -> {
            System.out.print("1");
            return i % 2 == 0;
        })
        .filter(i -> {
            System.out.print("0");
            return i > 3;
        })
        .limit(1)
        .forEach(System.out::println);
		
	}
}

class A {
	private String name;
	private String refB;
	
	public A(String name, String refB) {
		this.name = name;
		this.refB = refB;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRefB() {
		return refB;
	}
	public void setRefB(String refB) {
		this.refB = refB;
	}
	@Override
	public String toString() {
		return "A [name=" + name + ", refB=" + refB + "]";
	}
}

class B {
	private String derivedName;
	private String ref;
	
	public B(String ref) {
		this.ref = ref;
	}
	
	public String getDerivedName() {
		return derivedName;
	}
	public void setDerivedName(String derivedName) {
		this.derivedName = derivedName;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	@Override
	public String toString() {
		return "B [derivedName=" + derivedName + ", ref=" + ref + "]";
	}
	
}
