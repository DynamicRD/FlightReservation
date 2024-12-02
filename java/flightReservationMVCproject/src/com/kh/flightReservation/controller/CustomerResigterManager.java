package com.kh.flightReservation.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.flightReservation.model.CustomerVO;
import com.kh.flightReservation.model.FlightVO;

public class CustomerResigterManager {
	public static Scanner sc = new Scanner(System.in);

	public void selectManager() {
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = new CustomerVO();
		ArrayList<CustomerVO> customerList = cdao.customerSelect(cvo);
		if (customerList.size() != 0) {
			printCustomerList(customerList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
	}

	public void printCustomerList(ArrayList<CustomerVO> customerList) {
		System.out.println("============================================================");
		System.out.println(String.format("%-9s %-13s %-10s %-14s %-10s", "아이디", "비밀번호", "이름", "여권번호", "마일리지"));

		// 데이터 출력
		for (CustomerVO data : customerList) {
			System.out.println(String.format("%-10s %-15s %-10s %-15s %-10d", data.getId(), data.getPasswd(),
					data.getName(), data.getPassportNumber(), data.getMileage()));
		}
		System.out.println("============================================================");
	}

	public void insertManager() {
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = null;

		System.out.print("아이디를 입력하세요 >>");
		String id = sc.nextLine();

		System.out.print("비밀번호를 입력하세요 >>");
		String passwd = sc.nextLine();

		System.out.print("이름을 입력하세요 >>");
		String name = sc.nextLine();

		System.out.print("여권번호를 입력하세요 >>");
		String passportNumber = sc.nextLine();

		cvo = new CustomerVO(id, passwd, name, passportNumber, 0);
		boolean successFlag = cdao.customerInsert(cvo);
		if (successFlag == true) {
			System.out.println("고객 등록이 완료되었습니다.");
		} else {
			System.out.println("고객 등록을 실패하였습니다. 다시 시도해주세요.");
		}
	}

	public void updateManager() {
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = null;
		selectManager();
		System.out.print("수정할 아이디를 입력하세요 >>");
		String id = sc.nextLine();

		System.out.print("비밀번호를 입력하세요 >>");
		String passwd = sc.nextLine();

		System.out.print("이름을 입력하세요 >>");
		String name = sc.nextLine();

		System.out.print("여권번호를 입력하세요 >>");
		String passportNumber = sc.nextLine();

		cvo = new CustomerVO(id, passwd, name, passportNumber, 0);
		boolean successFlag = cdao.customerUpdate(cvo);

		if (successFlag == true) {
			System.out.println("고객 정보 수정이 완료되었습니다.");
		} else {
			System.out.println("고객 정보 수정을 실패하였습니다. 다시 시도해주세요.");
		}
	}

	public void deleteManager() {
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = null;
		selectManager();
		System.out.println("고객 정보를 삭제합니다.");
		System.out.print("삭제할 아이디를 입력하세요 >>");
		String id = sc.nextLine();

		cvo = new CustomerVO();
		cvo.setId(id);

		boolean successFlag = cdao.customerDelete(cvo);

		if (successFlag == true) {
			System.out.println("고객 정보 삭제가 완료되었습니다.");
		} else {
			System.out.println("고객 정보 삭제 실패하였습니다. 다시 시도해주세요.");
		}
	}

	public void gradePrintManager() {
		CustomerDAO cdao = new CustomerDAO();
		CustomerVO cvo = null;
		boolean successFlag = false;
		selectManager();
		System.out.println("고객 등급을 조회합니다.");
		System.out.print("조회할 아이디를 입력하세요 >>");
		String id = sc.nextLine();

		cvo = new CustomerVO();
		cvo.setId(id);

		successFlag = cdao.customerGradePrint(cvo);
		if (successFlag == false) {
			System.out.println("해당 고객을 찾을 수 없습니다.");
		}
	}

}
