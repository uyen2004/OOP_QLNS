package uyen.oop.exc;

import java.util.List;
import java.util.Scanner;

public class Manager extends Profile {
	static Scanner sc = new Scanner(System.in);
	private int numberOfStaff;
	final int managerSalary = 200;

	public Manager() {

	}

	public int getNumberOfStaff() {
		return numberOfStaff;
	}

	public void setNumberOfStaff(int numberOfStaff) {
		this.numberOfStaff = numberOfStaff;
	}

	@Override
	public void input(List<Profile> listProfile) {
		super.input(listProfile);
	}

	@Override
	public int salary() {
		this.salary = managerSalary * this.getWorkingDay() + 100 * this.numberOfStaff;
		return this.salary;
	}

	@Override
	public String chucVu() {
		this.position = "Truong phong";
		return this.position;
	}

	@Override
	public void display() {
		super.display();
	}

	public void managerDisplay() {
		this.display();
		System.out.format("%-12d |", this.numberOfStaff);
		System.out.println("");
	}

}
