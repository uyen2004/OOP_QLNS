package uyen.oop.exc;

import java.util.Scanner;

public class Company {
	private String name;
	private String maSoThue;
	private long revenue;
	StaffManagement sm = new StaffManagement();
	static Scanner sc = new Scanner(System.in);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	public long getRevenue() {
		return revenue;
	}

	public void setRevenue(long revenue) {
		this.revenue = revenue;
	}

	public void input(Company company) {
		System.out.print("\tNhap vao ten cong ty: ");
		company.setName(sc.nextLine());
		System.out.print("\tNhap vao ma so thue: ");
		company.setMaSoThue(sc.next());
		System.out.print("\tNhap vao doanh thu: ");
		company.setRevenue(sc.nextLong());
	}

	public void display() {
		System.out.println("Ten cong ty" + "\t|" + "ma so thue" + "\t|" + "doanh thu" + "\t|");
		System.out.println(this.name + "\t|" + this.maSoThue + "\t|" + this.revenue + "\t|");
	}

	// case 6
	public long tongLuong() {
		int tongLuong = 0;
		for (int i = 0; i < sm.listProfile.size(); i++) {
			tongLuong += sm.listProfile.get(i).salary();
		}
		for (var director : sm.listDirector) {
			director.setTongLuong((tongLuong + this.getRevenue()) * director.getShare() / 100 + director.salary());
		}
		return tongLuong;
	}
}
