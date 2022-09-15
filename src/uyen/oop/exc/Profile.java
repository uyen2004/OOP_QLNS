package uyen.oop.exc;

import java.util.List;
import java.util.Scanner;

public class Profile {
	private String id;
	private String name;
	private String phone;
	private int workingDay;
	protected int salary;
	protected String position;
	static Scanner sc = new Scanner(System.in);

	public Profile() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(int workingDay) {
		this.workingDay = workingDay;
	}

	public int salary() {
		this.salary = 0;
		return salary;
	}

	public String chucVu() {
		this.position = null;
		return position;
	}

	public void input(List<Profile> listProfile) {
		System.out.print("\tNhap vao ma so nhan vien: ");
		this.setId(checkID(listProfile, sc.nextLine()));
		System.out.print("\tNhap vao ho ten nhan vien: ");
		this.setName(sc.nextLine());
		System.out.print("\tNhap vao so dien thoai: ");
		this.setPhone(sc.nextLine());
		System.out.print("\tNhap vao so ngay lam viec: ");
		this.setWorkingDay(checkWorkingDay(Integer.parseInt(sc.nextLine())));
	}

	public void display() {
		String format = "%-6s| %-25s| %-12s| %-14d| %-22s| %-6d|";
		System.out.format(format, this.id, this.name, this.phone, this.workingDay, this.chucVu(), this.salary());
	}

	public String checkID(List<Profile> listProfile, String id) {
		for (var i : listProfile) {
			if (i.getId().equals(id)) {
				System.out.print("Ma nhan vien da ton tai, nhap lai: ");
				id = sc.nextLine();
				id = checkID(listProfile, id);
			}
		}
		return id;
	}

	public int checkWorkingDay(int workingDay) {
		if (workingDay > 31 || workingDay < 0) {
			System.out.print("Nhap lai ngay lam viec: ");
			workingDay = Integer.parseInt(sc.nextLine());
			workingDay = checkWorkingDay(workingDay);
		}
		return workingDay;
	}
}
