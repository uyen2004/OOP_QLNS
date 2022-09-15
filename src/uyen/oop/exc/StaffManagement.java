package uyen.oop.exc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StaffManagement {
	List<Staff> listStaff = new ArrayList<Staff>();
	List<Manager> listManager = new ArrayList<Manager>();
	List<Director> listDirector = new ArrayList<Director>();
	Company company = new Company();

	static Scanner sc = new Scanner(System.in);

	public void checkChucVu(List<Profile> listProfile) {
		System.out.println("Nhap 1. Nhan vien thuong");
		System.out.println("Nhap 2. Truong phong");
		System.out.println("Nhap 3. Giam doc");
		System.out.print("Chon chuc vu: ");
		int n = checkInput(sc.nextInt());
		Profile profile = new Profile();
		switch (n) {
		case 1:
			profile = new Staff();
			Staff staff = (Staff) profile;
			staff.input(listProfile);
			listProfile.add(staff);
			listStaff.add(staff);
			break;
		// profile.input();
		case 2:
			profile = new Manager();
			Manager manager = (Manager) profile;
			manager.input(listProfile);
			listManager.add(manager);
			listProfile.add(manager);
			break;
		// profile.input();
		case 3:
			profile = new Director();
			Director director = (Director) profile;
			director.input(listProfile);
			listDirector.add(director);
			listProfile.add(director);
			break;
		}
	}

	public int checkInput(int n) {
		if (n > 3 || n < 1) {
			System.out.print("Invalid Input! Nhap lai: ");
			n = Integer.parseInt(sc.nextLine());
			n = checkInput(n);
		}
		return n;
	}

	public void inputStaff(List<Profile> listProfile) {
		System.out.print("Nhap vao so luong nhan su: ");
		int n = sc.nextInt();
		int soLuong = listProfile.size() + n;
		for (int i = listProfile.size(); i < soLuong; i++) {
			System.out.println("Nhap vao nhan su thu " + (i + 1));
			checkChucVu(listProfile);
			System.out.println("---------------------------");
		}
	}

	public void header() {
		String format = "%-5s| %-4s| %-25s| %-12s| %-14s| %-22s| %-6s|";
		System.out.format(format, "STT", "Ma NV", "Ho ten Nv", "So DT", "Ngay lam viec", "Chuc vu", "Luong");
	}

	public void xuatDS(List<Profile> listProfile) {
		header();
		System.out.println();
		for (int i = 0; i < listProfile.size(); i++) {
			System.out.format("%-5d|", (i + 1));
			listProfile.get(i).display();
			System.out.println();
		}
	}

	// case 3
	public void phanBoNhanVien(List<Profile> listProfile) {
		if (listStaff.size() > 0) {
			System.out.print("Nhap vao ma nhan vien duoc phan bo len lam truong phong: ");
			String ID = checkIdExist(sc.next(), listProfile);
			for (int i = 0; i < listProfile.size(); i++) {
				if (listProfile.get(i) instanceof Staff) {
					if (listProfile.get(i).getId().equals(ID)) {
						Manager manager = new Manager();
						manager.setId(listProfile.get(i).getId());
						manager.setName(listProfile.get(i).getName());
						manager.setPhone(listProfile.get(i).getPhone());
						manager.setWorkingDay(listProfile.get(i).getWorkingDay());
						manager.setNumberOfStaff(0);
						listProfile.add(manager);
						listManager.add(manager);
						listProfile.remove(i);
						for (int j = 0; j < listStaff.size(); j++) {
							listStaff.remove(j);
						}
						System.out.println("Phan bo thanh cong");
					} else {
						System.out.println("khong phai doi tuong duoc phan bo");
					}
				}
			}
		} else {
			System.out.println("khong co nhan vien nao trong danh sach");
		}
	}

	// case 6
	public long tongLuong(List<Profile> listProfile) {
		int tongLuong = 0;
		for (int i = 0; i < listProfile.size(); i++) {
			tongLuong += listProfile.get(i).salary();
		}
		for (var director : listDirector) {
			director.setTongLuong((tongLuong + company.getRevenue()) * director.getShare() / 100 + director.salary());
		}
		return tongLuong;
	}

	// case 7
	public void displayNvMax(List<Profile> listProfile) {
		if (listStaff.size() > 0) {
			header();
			System.out.format("%-18s |", "Ten truong phong");
			System.out.println();
			for (int i = 0; i < listStaff.size(); i++) {
				if (listStaff.get(i).salary() == nvMax().salary()) {
					System.out.format("%-5d|", (i + 1));
					listStaff.get(i).staffDisplay();
				}
			}
		} else {
			System.out.println("\t Khong co nhan vien nao trong danh sach" + "\n");
		}
	}

	public Staff nvMax() {
		Staff nvMax;
		nvMax = listStaff.get(0);
		for (int i = 0; i < listStaff.size(); i++) {
			if (listStaff.get(i).salary() > nvMax.salary()) {
				nvMax = listStaff.get(i);
			}
		}
		return nvMax;
	}

	// case 8
	public void soLuongNhanVienMax(List<Profile> listProfile) {

		Manager memberMax = listManager.get(0);
		for (var i : listManager) {
			if (i.getNumberOfStaff() > memberMax.getNumberOfStaff()) {
				memberMax = i;
			}
		}
		header();
		System.out.format("%-12s", "So luong NV|");
		System.out.println();
		for (int i = 0; i < listManager.size(); i++) {
			if (listManager.get(i).getNumberOfStaff() == memberMax.getNumberOfStaff()) {
				System.out.format("%-5d|", (i + 1));
				listManager.get(i).managerDisplay();
				System.out.println();
			}
		}
	}

	// case 4
	public void xoaNhanSu(List<Profile> listProfile) {
		System.out.println("Nhap vao ma nhan vien can xoa: ");
		String id = checkIdExist(sc.next(), listProfile);

		for (int i = 0; i < listProfile.size(); i++) {
			if (listProfile.get(i).getId().equals(id)) {
				switch (listProfile.get(i).chucVu()) {
				case "Truong phong":
					for (int manager = 0; manager < listManager.size(); i++) {
						if (listManager.get(manager).getId().equals(id)) {
							for (var staff : listStaff) {
								if (staff.getManagerId().equals(listManager.get(i).getId())) {
									staff.setManagerId(null);
								}
							}
							listManager.remove(manager);
							listProfile.remove(i);
							System.out.println("-------DA XOA THANH CONG-------------");
						}
					}
					break;
				case "Nhan vien thuong":
					for (int staff = 0; staff < listStaff.size(); i++) {
						if (listStaff.get(staff).getId().equals(id)) {
							for (var manager : listManager) {
								int numberOfStaff = manager.getNumberOfStaff();
								if (listStaff.get(i).getManagerId().equals(manager.getId())) {
									numberOfStaff--;
									manager.setNumberOfStaff(numberOfStaff);
								}
							}
							listProfile.remove(i);
							listStaff.remove(staff);
							System.out.println("-------DA XOA THANH CONG-------------");
						}
					}
					break;
				case "Giam Doc":
					for (int director = 0; director < listDirector.size(); i++) {
						if (listDirector.get(director).getId().equals(id)) {
							listDirector.remove(director);
							listProfile.remove(i);
							System.out.println("-------DA XOA THANH CONG-------------");
						}
					}
				}
				break;
			}
		}
	}

	// case 11
	public void displayShareMax() {
		if (listDirector.size() > 0) {
			header();
			System.out.format("%-8s", "Co phan");
			System.out.println();
			for (int i = 0; i < listDirector.size(); i++) {
				if (listDirector.get(i).getShare() == shareMax().getShare()) {
					System.out.format("%-5d|", (i + 1));
					listDirector.get(i).directorDisplay();
				}

			}
		} else {
			System.out.println("\t Khong co giam doc nao trong danh sach");
		}
	}

	public Director shareMax() {
		Director shareMax = listDirector.get(0);
		for (var i : listDirector) {
			if (i.getShare() > shareMax.getShare()) {
				shareMax = i;
			}
		}
		return shareMax;
	}

	// case 10
	public List<Profile> newList(List<Profile> listProfile) {
		Profile temp = new Profile();

		Profile[] newList = listProfile.toArray(new Profile[listProfile.size()]);
		for (int i = 0; i < newList.length; i++) {
			for (int j = i + 1; j < newList.length; j++) {
				if (newList[j].salary() > newList[i].salary()) {
					temp = newList[i];
					newList[i] = newList[j];
					newList[j] = temp;
				}
			}
		}
		List<Profile> newArrayList = new ArrayList<>(Arrays.asList(newList));
		return newArrayList;
	}

	public String checkIdExist(String Id, List<Profile> listProfile) {
		boolean isExist = false;
		for (int i = 0; i < listProfile.size(); i++) {
			if (Id.equals(listProfile.get(i).getId())) {
				isExist = true;
			}
		}
		if (isExist == false) {
			System.out.print("ID khong ton tai trong danh sach, nhap lai: ");
			Id = sc.next();
			Id = checkIdExist(Id, listProfile);
		}
		return Id;
	}

}
