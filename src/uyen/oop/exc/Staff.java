package uyen.oop.exc;

import java.util.List;
import java.util.Scanner;

public class Staff extends Profile {
	final int staffSalary = 100;
	static Scanner sc = new Scanner(System.in);
	private String managerName;
	private String managerId;
	StaffManagement staffManagement = new StaffManagement();

	// check lai size listManager
	public void soNhanVien(String managerID, List<Profile> listProfile) {
		boolean found = false;
		for (var i : listProfile) {
			if (i instanceof Manager) {
				found = true;
				int numberOfStaff = ((Manager) i).getNumberOfStaff();
				if (managerID.equals(i.getId())) {
					numberOfStaff++;
					this.managerName = i.getName();
				}
				((Manager) i).setNumberOfStaff(numberOfStaff);
			}
		}
		if (found == false) {
			System.out.println("Khong co truong phong nao trong danh sach");
		}
	}

	@Override
	public int salary() {
		this.salary = staffSalary * this.getWorkingDay();
		return this.salary;
	}

	public void managerIdInput(List<Profile> listProfile) {
		System.out.print("Nhap vao ID truong phong: ");
		this.managerId = staffManagement.checkIdManagerExist(sc.next());
		soNhanVien(managerId, listProfile);
	}

	@Override
	public String chucVu() {
		this.position = "Nhan vien thuong";
		return this.position;
	}

	@Override
	public void input(List<Profile> listProfile) {
		super.input(listProfile);
		managerIdInput(listProfile);
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Override
	public void display() {
		super.display();
	}

	public void staffDisplay() {
		this.display();
		System.out.format("%-18s |", this.managerName);
		System.out.println("");
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
