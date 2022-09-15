package uyen.oop.exc;

import java.util.List;

public class Director extends Profile {
	private int share;
	final int directorSalary = 300;
	private long tongLuong;

	public long getTongLuong() {
		return tongLuong;
	}

	public void setTongLuong(long tongLuong) {
		this.tongLuong = tongLuong;
	}

	public Director() {
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	@Override
	public int salary() {
		this.salary = directorSalary * this.getWorkingDay();
		return this.salary;
	}

	@Override
	public void input(List<Profile> listProfile) {
		super.input(listProfile);
		inputShare();
	}

	public int inputShare() {
		System.out.print("Nhap vao % co phan: ");
		this.share = checkCoPhan(Integer.parseInt(sc.nextLine()));
		return this.share;
	}

	@Override
	public String chucVu() {
		this.position = "Giam Doc";
		return this.position;
	}

	@Override
	public void display() {
		super.display();
	}

	public void directorDisplay() {
		this.display();
		System.out.format("%-8d |", this.share);
		System.out.println("");
	}

	public int checkCoPhan(int share) {
		if (share > 100 || share < 0) {
			System.out.print("Co phan khong hop le, nhap lai: ");
			share = Integer.parseInt(sc.nextLine());
			share = checkCoPhan(share);
		}
		return share;
	}

	public void displayDirector() {
		this.display();
		System.out.format("%-8d |", this.share);
		System.out.println("");
	}
}
