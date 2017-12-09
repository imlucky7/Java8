package com.pranab.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LambdaExpression {

	public static void main(String[] args) {
		/*
		 * 1. Simple example of lambda expression
		 */
		System.out.println("1. =====================================");
		Task task = name -> 	System.out.println("Task assigned for " + name);
		task.assignTask("Pranab");
		
		/*
		 * 2 .Foreach Loop lambda expression
		 */
		System.out.println("2. =====================================");
		String[] name = {"Helen", "Joie", "Tom" };
		List<String> list = Arrays.asList(name);
		
		list.forEach(
				//nm -> System.out.println(nm)
				System.out::println
		);
		
		/*
		 * 3. Thread using lambda expression
		 */
		System.out.println("3. =====================================");
		new Thread(
				() -> System.out.println("Thread is running...")
		).start();
		
		/*
		 * 4. Comparator using lambda expression
		 */
		System.out.println("4. =====================================");
		Student[] students = {new Student("Tamal", "Seven", 540), new Student("Rakesh", "Seven", 490), new Student("Emon", "Eight", 543)};
		List<Student> studentList = Arrays.asList(students);
		
		studentList.forEach(std -> System.out.println(".."+std));
		
		Collections.sort(studentList, (s1, s2) -> {
			return s1.getName().compareTo(s2.getName());
		});
		
		studentList.forEach(std -> System.out.println("......"+std));
		
		/*
		 * 5. Filter collection using lambda expression
		 */
		System.out.println("5. =====================================");
		Stream<Student> stream = studentList.stream().filter(s -> s.getMarks()>500);
		stream.forEach(s -> System.out.println(".........."+s));
		
		/*
		 * 6. Passing lambda expression in method argument
		 * java.util.function package enlists a lot of functional interface 
		 * for lambda expression use
		 */
		System.out.println("6. =====================================");
		Student s = new Student("Elsa", "Six", 334);
		s.greeting(400, (passMark) -> {
			return s.getMarks() > passMark ? "Congratulations "+s.getName()+" !! you are promoted" : "Sorry "+s.getName()+" !! you are not promoted";
		});
		
		/*
		 * 7. Passing lambda expression in method argument
		 */
		System.out.println("7. =====================================");
		List<Student> marksList = searchEmployee(studentList, e -> e.getMarks() > 500);
		marksList.forEach(System.out::println);
		
		List<Student> standardList = searchEmployee(studentList, e -> "Seven".equals(e.getStd()));
		standardList.forEach(System.out::println);
		
		// Using predicate instead of custom functional interface
		Predicate<Student> p = ssss -> ssss.getMarks()>500;
		List<Student> predicateList = searchEmployeeUsingPredicate(studentList, p);
		predicateList.forEach(System.out::println);
	}
	
	static List<Student> searchEmployee(List<Student> stdList, Searchable search) {
		List<Student> list = new ArrayList<Student>();
		for(Student s : stdList) {
			if(search.search(s))
				list.add(s);
		}
		return list;
		
	}
	
	static List<Student> searchEmployeeUsingPredicate(List<Student> stdList, Predicate<Student> search) {
		return stdList.stream().filter(search).collect(Collectors.toList());
		
	}
}

@FunctionalInterface
interface Task {
	public void assignTask(String name);
}
 
@FunctionalInterface
interface Searchable {
	public boolean search(Student e);
}

class Student {
	private String name;
	private String std;
	private int marks;
	
	public Student(String name, String std, int marks) {
		this.name = name;
		this.std = std;
		this.marks = marks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", std=" + std + ", marks=" + marks + "]";
	}
	
	public void greeting(int marks, IntFunction<String> fn) {
		System.out.println(fn.apply(marks));
	}
}
