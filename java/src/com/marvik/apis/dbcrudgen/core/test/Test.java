package com.marvik.apis.dbcrudgen.core.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		new Test();
	}

	public Test() {
		List<Person> personInfos = new ArrayList<Person>();

		personInfos.add(new Person("Victor 1", 6));
		personInfos.add(new Person("Victor 2 ", 5));
		personInfos.add(new Person("Victor 3 ", 4));
		personInfos.add(new Person("Victor 4 ", 3));
		personInfos.add(new Person("Victor 5 ", 2));
		personInfos.add(new Person("Victor 6 ", 1));

		long []  personInfoDOB = new long[personInfos.size()];
		
		for (int i = 0; i < personInfos.size(); i++) {
			personInfoDOB[i] = (personInfos.get(i).dateOfBirth);
		}
		
		Arrays.sort(personInfoDOB);
		
		for (int i = 0; i < personInfoDOB.length; i++) {
			System.out.println(personInfoDOB[i]);
		}

		List<Person> personInfoSorted = new ArrayList<Person>();

		System.out.println("********** THE LIST BELOW SHOWS THE EFFECTS OF SORTING");
		for (int i = 0; i < personInfos.size(); i++) {
			/*System.out.println("INDEX : " + i + " OLD LIST : " + personInfos.get(i).name + "("
					+ personInfos.get(i).dateOfBirth + ")" + " NEW LIST : " + personInfoSorted.get(i).name + "("
					+ personInfoSorted.get(i).dateOfBirth + ")");*/
		}
	}

	class Person {
		String name;
		long dateOfBirth;

		Person(String name, long dateOfBirth) {
			this.name = name;
			this.dateOfBirth = dateOfBirth;
		}
	}
}
