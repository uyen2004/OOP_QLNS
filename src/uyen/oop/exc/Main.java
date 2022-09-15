package uyen.oop.exc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static StaffManagement staffManagement = new StaffManagement();
	static List<Profile> listProfile = new ArrayList<Profile>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Company company = new Company();
		Staff staff = new Staff();
		boolean option = true;
		while (option) {
			menu();
			int n = Integer.parseInt(sc.nextLine());
			switch (n) {
			case 1:
				company.input(company);

				break;
			case 2:
				staffManagement.inputStaff(listProfile);
				// staff.input();
				break;
			case 3:
				staffManagement.phanBoNhanVien(listProfile);
				break;
			case 4:
				staffManagement.xoaNhanSu(listProfile);
				break;
			case 5:
				System.out
						.println("===================================================================================");
				System.out.println("			             DANH SACH NHAN VIEN                         ");
				System.out.println(
						"====================================================================================");
				staffManagement.xuatDS(listProfile);
				break;
			case 6:
				staffManagement.xuatDS(listProfile);
				System.out.println("\t Tong luong toan cong ty: " + staffManagement.tongLuong(listProfile));
				break;
			case 7:
				System.out.println("-------------Danh sach nhan vien co luong cao nhat-----------------");
				staffManagement.displayNvMax(listProfile);
				break;
			case 8:
				System.out.println("Danh sach truong phong co nhieu nhan vien nhat");
				staffManagement.soLuongNhanVienMax(listProfile);
				break;
			case 9:
				System.out.println("-----------Danh sach cong ty theo thu tu ten--------------");
				staffManagement.xuatDS(sortByName(listProfile));
				break;
			case 10:
				System.out.println("-----------Danh sach cong ty theo luong-------------------");
				staffManagement.xuatDS(newList(listProfile));
				break;
			case 11:
				System.out.println("------------Giam doc co co phan cao nhat------------------");
				staffManagement.displayShareMax();

			case 12:
				for (var i : listProfile) {
					if (i instanceof Director) {
						((Director) i).displayDirector();
					}
				}
			default:
				System.out.print("Exit");
				option = false;
			}

		}

	}

	private static List<Profile> newList(List<Profile> listProfile2) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void menu() {
		System.out.println("-----------------------------QUAN LY NHAN SU----------------------------");
		System.out.println("1.  |Nhap thong tin cong ty");
		System.out.println("2.  |Them thong tin nhan su");
		System.out.println("3.  |Phan bo nhan vien vao truong phong");
		System.out.println("4.  |xoa thong tin mot nhan su, co the la nhan vien truong phong hoac giam doc");
		System.out.println("5.  |Xuat ra thong tin toan bo cong ty");
		System.out.println("6.  |Tinh va xuat tong luong cho cong ty");
		System.out.println("7.  |Tim nhan vien co luong cao nhat");
		System.out.println("8.  |Tim so luong truong phong co so luong nhan vien duoi quyen nhieu nhat");
		System.out.println("9.  |Sap xep nhan vien toan cong ty theo thu tu abc");
		System.out.println("10. |Sap xep nhan vien doan cong ty theo thu tu luong giam dan");
		System.out.println("11. |Tim giam doc co so luong co phan nhieu nhat");
		System.out.println("12. |Tinh va xuat tong thu nhap cua tung giam doc");
		System.out.println("Nhan phim bat ki de thoat");
		System.out.println("Chon mot so: ");
	}

	// case 9
	// split string tu 1 chuoi ho va ten de lay ten nhan vien de so sanh
	public static String splitString(String name) {
		String nameStr = new String();
		for (int i = 0; i < listProfile.size(); i++) {
			String[] subStr = name.split(" ");
			nameStr = subStr[subStr.length - 1];
		}
		return nameStr;
	}

	// so sanh ten nhan vien lay ra tu chuoi da duoc split
	public static List<Profile> sortByName(List<Profile> listProfile) {
		Profile[] newList = listProfile.toArray(new Profile[listProfile.size()]);
		Profile temp = new Profile();
		for (int i = 0; i < newList.length; i++) {
			for (int j = i + 1; j < newList.length; j++) {
				if ((splitString(newList[i].getName()).compareTo(splitString(newList[j].getName())) > 0)) {
					temp = newList[j];
					newList[j] = newList[i];
					newList[i] = temp;
				}

			}
		}
		List<Profile> newArrayList = new ArrayList<>(Arrays.asList(newList));
		return newArrayList;
	}

}
