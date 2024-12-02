package com.kh.flightReservation.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.flightReservation.model.FlightVO;

public class FlightRegisterManager {
	public Scanner sc = new Scanner(System.in);

	public void insertManager() {
		FlightDAO fdao = new FlightDAO();
		// 항공사 번호,출발날짜,출발공항,도착공항,가격,좌석
		AirLineResigterManager arm = new AirLineResigterManager();
		arm.selectManager();
		System.out.print("항공사 번호를 입력하세요 >>");
		int aNo = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("출발날짜 입력하세요 (예: yyyy-MM-dd) >> ");
		String dateString = (sc.nextLine()).trim();
		Date departmentDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date utilDate = sdf.parse(dateString);
			departmentDate = new Date(utilDate.getTime());
			System.out.println("출발 날짜는: " + departmentDate);
		} catch (Exception e) {
			System.out.println("잘못된 날짜 형식입니다. 올바른 형식(yyyy-MM-dd)으로 입력해주세요.");
		}
		System.out.print("출발공항을 입력하세요 >>");
		String departmentAirport = (sc.nextLine()).trim();
		System.out.print("도착공항을 입력하세요 >>");
		String arrivalAirport = (sc.nextLine()).trim();
		System.out.print("가격을 입력하세요 >>");
		int price = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("좌석 선택을(이코노미, 비즈니스, 퍼스트) 선택하세요 >>");
		String seat = (sc.nextLine()).trim();

		FlightVO fvo = new FlightVO(0, aNo, departmentDate, departmentAirport, arrivalAirport, price, seat);
		boolean successFlag = fdao.flightInsert(fvo);

		if (successFlag == true) {
			System.out.println("항공편 정보를 입력 하였습니다.");
		} else {
			System.out.println("항공편 정보를 입력 실패 하였습니다.");
		}
	}

	public void salaryUpProcManager() {
		FlightDAO fdao = new FlightDAO();
		FlightVO fvo = new FlightVO();

		ArrayList<FlightVO> flightList = fdao.flightSelect(fvo);
		if (flightList.size() != 0) {
			printflightList(flightList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}

		System.out.print("인상할 항공사 번호를 입력하세요 >>");
		int aNo = Integer.parseInt(sc.nextLine());
		boolean successFlag = fdao.flightSalaryUpProc(aNo);

		// 화면출력
		if (successFlag == true) {
			System.out.println("항공사 번호가 " + aNo + "인 항공편의 가격을 10% 인상하였습니다.");
		} else {
			System.out.println("항공사 번호가 " + aNo + "인 항공편의 가격을 10% 인상하였습니다.");
		}

	}

	public void selectManager() {
		FlightDAO fdao = new FlightDAO();
		FlightVO fvo = new FlightVO();
		ArrayList<FlightVO> flightList = fdao.flightSelect(fvo);
		if (flightList.size() != 0) {
			printflightList(flightList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
	}

	public void deleteManager() {
		FlightDAO fdao = new FlightDAO();
		FlightVO fvo = new FlightVO();

		ArrayList<FlightVO> flightList = fdao.flightSelect(fvo);
		if (flightList.size() != 0) {
			printflightList(flightList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
		System.out.print("삭제할 항공편 일련번호를 입력하세요 >>");
		int no = Integer.parseInt((sc.nextLine()).trim());
		System.out.println("no=" + no);
		FlightVO fvo2 = new FlightVO();
		fvo2.setNo(no);
		System.out.println("fvo2=" + fvo2.toString());
		boolean successFlag = fdao.flightDelete(fvo2);

		if (successFlag == true) {
			System.out.println("항공편 일련번호가 " + no + "인 항공편을 삭제 하였습니다.");
		} else {
			System.out.println("항공편 일련번호가 " + no + "인 항공편을 삭제 실패하였습니다.");
		}
	}

	public void updateManager() {
		FlightDAO fdao = new FlightDAO();
		FlightVO fvo = new FlightVO();

		ArrayList<FlightVO> flightList = fdao.flightSelect(fvo);
		if (flightList.size() != 0) {
			printflightList(flightList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}

		System.out.print("수정할 항공편 일련번호를 입력하세요 >>");
		int no = Integer.parseInt(sc.nextLine());
		System.out.print("항공사 번호를 입력하세요 >>");
		int aNo = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("출발날짜를 (예: yyyy-MM-dd) 입력하세요 >>");
		String dateString = (sc.nextLine()).trim();
		Date departmentDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date utilDate = sdf.parse(dateString);
			departmentDate = new Date(utilDate.getTime());
			System.out.println("출발 날짜는: " + departmentDate);
		} catch (Exception e) {
			System.out.println("잘못된 날짜 형식입니다. 올바른 형식(yyyy-MM-dd)으로 입력해주세요.");
		}
		System.out.print("출발공항을 입력하세요 >>");
		String departmentAirport = (sc.nextLine()).trim();
		System.out.print("도착공항을 입력하세요 >>");
		String arrivalAirport = (sc.nextLine()).trim();
		System.out.print("가격 입력을 입력하세요 >>");
		int price = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("좌석 입력을(이코노미/비즈니스/퍼스트) 입력하세요 >>");
		String seat = (sc.nextLine()).trim();

		fvo = new FlightVO(no, aNo, departmentDate, departmentAirport, arrivalAirport, price, seat);
		boolean successFlag = fdao.flightUpdate(fvo);

		// 화면출력
		if (successFlag == true) {
			System.out.println("항공편 일련번호가 " + no + "인 항공편을 수정 하였습니다.");
		} else {
			System.out.println("항공편 일련번호가 " + no + "인 항공편을 수정실패 하였습니다.");
		}
	}

	public void selectSortManager() {
		FlightDAO fdao = new FlightDAO();
		FlightVO fvo = new FlightVO();
		ArrayList<FlightVO> flightList = fdao.flightSelectSort(fvo);
		if (flightList.size() != 0) {
			printflightList(flightList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
		}
	}

	public void printflightList(ArrayList<FlightVO> flightList) {
		System.out.println("===============================================================");
		System.out.println(String.format("%-10s %-7s %-10s %-5s %-5s %-6s %-10s", "항공편일련번호", "항공사번호", "출발날짜",
				"출발공항", "도착공항", "가격", "좌석"));

		// 데이터 출력
		for (FlightVO data : flightList) {
			System.out.println(String.format("%-12s %-9s %-11s %-5s %-5s %-6d %-10s", data.getNo(), data.getaNo(),
					data.getDepartmentDate(), data.getDepartmentAirport(), data.getArrivalAirport(), data.getPrice(),
					data.getSeat()));
		}
		System.out.println("===============================================================");
	}

}