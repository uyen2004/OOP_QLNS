package uyen.oop.exc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StaffManagement {
	List<Staff> listStaff = new ArrayList<Staff>();
	List<Manager> listManager = new ArrayList<Manager>();
	List<Director> listDirector = new ArrayList<Director>();
	List<Profile> listProfile = new ArrayList<Profile>();

	static Scanner sc = new Scanner(System.in);

	public void checkChucVu() {
		System.out.println("Nhap 1. Nhan vien thuong");
		System.out.println("Nhap 2. Truong phong");
		System.out.println("Nhap 3. Giam doc");
		System.out.print("Chon chuc vu: ");
		int n = checkInput(sc.nextInt());
		switch (n) {
		case 1:
			Staff staff = new Staff();
			staff.input(listProfile);
			listProfile.add(staff);
			listStaff.add(staff);
			break;
		case 2:
			Manager manager = new Manager();
			manager.input(listProfile);
			listManager.add(manager);
			listProfile.add(manager);
			break;
		case 3:
			Director director = new Director();
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

	public void inputStaff() {
		System.out.print("Nhap vao so luong nhan su: ");
		int n = sc.nextInt();
		int soLuong = listProfile.size() + n;
		for (int i = listProfile.size(); i < soLuong; i++) {
			System.out.println("Nhap vao nhan su thu " + (i + 1));
			checkChucVu();
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
	public void phanBoNhanVien() {
		if (listStaff.size() > 0) {
			System.out.print("Nhap vao ma nhan vien duoc phan bo len lam truong phong: ");
			String ID = checkIdExist(sc.next());
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
						deleteStaff();
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

	public void deleteStaff() {
		for (int i = 0; i < listStaff.size(); i++) {
			listStaff.remove(i);
		}
	}

	// case 7
	public void displayNvMax() {
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
	public void soLuongNhanVienMax() {

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
	public void xoaNhanSu() {
		System.out.println("Nhap vao ma nhan vien can xoa: ");
		String id = checkIdExist(sc.next());
		switch (ChucVu(id)) {
		case "Truong phong":
			for (int manager = 0; manager < listManager.size(); manager++) {
				if (listManager.get(manager).getId().equals(id)) {
					for (var staff : listStaff) {
						if (staff.getManagerId().equals(listManager.get(manager).getId())) {
							staff.setManagerId(null);
						}
					}
					listManager.remove(manager);
					xoaListProfile(id);
					System.out.println("-------DA XOA THANH CONG-------------");
				}
			}
			break;
		case "Nhan vien thuong":
			for (int staff = 0; staff < listStaff.size(); staff++) {
				if (listStaff.get(staff).getId().equals(id)) {
					for (var manager : listManager) {
						int numberOfStaff = manager.getNumberOfStaff();
						if (listStaff.get(staff).getManagerId().equals(manager.getId())) {
							numberOfStaff--;
							manager.setNumberOfStaff(numberOfStaff);
						}
					}
					xoaListProfile(id);
					listStaff.remove(staff);
					System.out.println("-------DA XOA THANH CONG-------------");
				}
			}
			break;
		case "Giam Doc":
			for (int director = 0; director < listDirector.size(); director++) {
				if (listDirector.get(director).getId().equals(id)) {
					listDirector.remove(director);
					xoaListProfile(id);
					System.out.println("-------DA XOA THANH CONG-------------");
				}
			}
			break;
		}

	}

	public void xoaListProfile(String id) {
		for (int i = 0; i < listProfile.size(); i++) {
			if (listProfile.get(i).getId().equals(id)) {
				listProfile.remove(i);
			}
		}
	}

	public String ChucVu(String id) {
		String chucVu = "";
		for (int i = 0; i < listProfile.size(); i++) {
			if (listProfile.get(i).getId().equals(id)) {
				chucVu = listProfile.get(i).chucVu();
			}
		}
		return chucVu;
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
	public List<Profile> newList() {
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

	public String checkIdExist(String Id) {
		boolean isExist = false;
		for (int i = 0; i < listProfile.size(); i++) {
			if (Id.equals(listProfile.get(i).getId())) {
				isExist = true;
			}
		}
		if (isExist == false) {
			System.out.print("ID khong ton tai trong danh sach, nhap lai: ");
			Id = sc.next();
			Id = checkIdExist(Id);
		}
		return Id;
	}

	public String checkIdManagerExist(String Id) {
		boolean isExist = false;
		// if (listManager.size() > 0) {
		for (int i = 0; i < listManager.size(); i++) {
			if (listManager.get(i).getId().equals(Id)) {
				isExist = true;
			}
		}
		if (isExist == false) {
			System.out.print("ID khong ton tai trong danh sach, nhap lai: ");
			Id = sc.next();
			Id = checkIdManagerExist(Id);
		}
		// }
		return Id;
	}

	// case 9
	// split string tu 1 chuoi ho va ten de lay ten nhan vien de so sanh
	public String splitString(String name) {
		String nameStr = new String();
		for (int i = 0; i < listProfile.size(); i++) {
			String[] subStr = name.split(" ");
			nameStr = subStr[subStr.length - 1];
		}
		return nameStr;
	}

	// so sanh ten nhan vien lay ra tu chuoi da duoc split
	public List<Profile> sortByName() {
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

	// case 12
	public void displayDirector() {
		if (listDirector.size() > 0) {
			header();
			System.out.format("%-8s", "Co phan");
			System.out.println();
			for (int i = 0; i < listDirector.size(); i++) {
				System.out.format("%-5d|", (i + 1));
				listDirector.get(i).directorDisplay();
			}

		} else {
			System.out.println("\t Khong co giam doc nao trong danh sach");
		}
	}
}
