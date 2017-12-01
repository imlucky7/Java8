package com.pranab.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodReference {
	/**
	 * Example of method reference in java8
	 * https://www.codementor.io/eh3rrera/using-java-8-method-reference-du10866vx
	 */
	public static void main(String[] args) {
		Employee[] empLit = {new Employee("Tamal", "Accounce", 10000), new Employee("Rakesh", "Craftman", 200000), new Employee("Emon", "Audit", 54388)};
		
		/*
		 * 1. Static method reference
		 */
		System.out.println("1. =====================================");
		Employee base = new Employee("Ashok", "Security", 10050);
		List<Employee> empList = Arrays.asList(empLit);
		BiFunction<List<Employee>, Employee, List<Employee>> f = Utility::findLargeFromBase; //static method reference
		List<Employee> highSalList = f.apply(empList, base);
		highSalList.forEach(System.out::println);
		
		/*
		 * 2. Instance method reference from instance
		 */
		System.out.println("2. =====================================");
		Function<Employee, String> fn = Employee::getEmplBrand; //Instance method reference from instance
		empList.forEach(emp -> System.out.println(emp.getName()+" belogns to "+fn.apply(emp)));
		
		/*
		 * 3. Instance method reference from another instance
		 */
		System.out.println("3. =====================================");
		Consumer<String> c = System.out::println; //Instance method reference from another instance
		c.accept("This is example of instance method reference from another instance");
		
		/*
		 * 4. Method reference for instance creation
		 */
		System.out.println("4. =====================================");
		TriFunction<String, String, Integer, Employee> triFn = Employee::new; //Method reference for constructor
		Employee empl = triFn.apply("TriFunction Employee", "Technology", 20000);
		Consumer<Employee> emplC = System.out::println;
		emplC.accept(empl);
	}

}

@FunctionalInterface
interface TriFunction<F, S, T, R> {
	public R apply(F f, S s, T t);
}

class Utility {
	public static <T extends Employee> List<T> findLargeFromBase(List<T> list, T t) {
		List<T> highSalList = list.stream().filter(s -> s.getSalary() > t.getSalary()).collect(Collectors.toList());
		return highSalList;
	}
}

class Employee {
	private String name;
	private String dept;
	private int salary;
	
	public Employee(String name, String dept, int salary) {
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", dept=" + dept + ", salary=" + salary + "]";
	}
	
	public String getEmplBrand() {
		if(this.salary<12999)
			return "Low level band";
		else if(this.salary >= 130000 && this.salary<19999)
			return "Mid level band";
		else
			return "High level band";
	}
}