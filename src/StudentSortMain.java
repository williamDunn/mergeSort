
//William Dunn - Assignment 6 - CPSC 323

import java.io.File;
import java.util.*;

public class StudentSortMain {

	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<Student>();

		// Read in file
		try {
			Scanner fileIn = new Scanner(new File("Students.txt"));
			int id;
			String firstName;
			String lastName;
			String birthday;
			String major;
			double gpa;

			while (fileIn.hasNext()) {
				id = fileIn.nextInt();
				firstName = fileIn.next();
				lastName = fileIn.next();
				birthday = fileIn.next();
				major = fileIn.next();
				gpa = fileIn.nextDouble();

				studentList.add(new Student(id, firstName, lastName, birthday, major, gpa));
			}

			fileIn.close();

		} catch (Exception ex) {
			System.out.println("Error reading file");
			return;
		}

		Student[] students = new Student[studentList.size()];
		students = studentList.toArray(students);

		System.out.println("NOT SORTED");
		System.out.println("==========");
		printArray(students);
		sortArray(students);

		System.out.println();
		System.out.println();
		System.out.println("SORTED");
		System.out.println("======");
		printArray(students);
	}

	public static void printArray(Student[] students) {
		for (Student student : students) {
			System.out.println(student.getFullName() + ", " + student.getGpa());
		}
	}

	public static void sortArray(Student[] students) {
		if (students.length <= 1) {
			return;
		} else if (students.length > 1) {
			int fullNum = students.length;
			int halfNum = fullNum / 2;

			int x = 0;
			int y = 0;
			int z = 0;

			Student[] arrayOne = new Student[halfNum];
			Student[] arrayTwo = new Student[fullNum - halfNum];

			for (int a = 0; a < halfNum; a++) {
				arrayOne[a] = students[a];
			}
			for (int b = halfNum; b < fullNum; b++) {
				arrayTwo[b - halfNum] = students[b];
			}

			sortArray(arrayOne);
			sortArray(arrayTwo);

			while (x < halfNum && y < (fullNum - halfNum)) {
				// sort highest to lowest - to sort lowest to highest "arrayOne[x].getGpa() <= arrayTwo[y].getGpa()"
				if (arrayOne[x].getGpa() >= arrayTwo[y].getGpa()) {
					students[z++] = arrayOne[x++];
				} else {
					students[z++] = arrayTwo[y++];
				}
			}
			while (x < halfNum) {
				students[z++] = arrayOne[x++];
			}
			while (y < (fullNum - halfNum)) {
				students[z++] = arrayTwo[y++];
			}
		} else {
			System.err.println("Invalid Entry");
		}
	}
}
