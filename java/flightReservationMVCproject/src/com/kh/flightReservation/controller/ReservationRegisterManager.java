package com.kh.flightReservation.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.flightReservation.model.ReservationAllVO;
import com.kh.flightReservation.model.ReservationVO;

public class ReservationRegisterManager {
	public Scanner sc = new Scanner(System.in);

	public void selectManager() {
		ReservationDAO rdao = new ReservationDAO();
		ArrayList<ReservationVO> reservationList = new ArrayList<ReservationVO>();

		reservationList = rdao.reservationSelect();
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
	}

	public void insertManager() {
		System.out.println("예약 가능한 항공편 정보");
		FlightRegisterManager frm = new FlightRegisterManager();
		CustomerResigterManager crm = new CustomerResigterManager();
		frm.selectManager();
		crm.selectManager();
		ReservationDAO rdao = new ReservationDAO();
		System.out.print("고객 아이디를 입력하세요 >>");
		String id = (sc.nextLine()).trim();
		System.out.print("항공편 일련번호를 입력하세요 >>");
		int flight_no = Integer.parseInt((sc.nextLine()).trim());
		System.out.print("예매매수를 입력하세요 >>");
		int quantity = Integer.parseInt((sc.nextLine()).trim());

		ReservationVO rvo = new ReservationVO(id, flight_no, quantity);
		boolean successFlag = rdao.reservationInsert(rvo);

		if (successFlag == true) {
			System.out.println("입력 성공하였습니다.");
		} else {
			System.out.println("입력 실패하였습니다.");
		}
	}

	public void deleteManager() {
		ReservationDAO rdao = new ReservationDAO();
		ReservationVO rvo = new ReservationVO();

		ArrayList<ReservationVO> reservationList = new ArrayList<ReservationVO>();

		reservationList = rdao.reservationSelect();
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}

		System.out.print("삭제할 데이터의 예약번호를 입력해주세요 >>");
		int no = Integer.parseInt((sc.nextLine()).trim());
		rvo.setNo(no);

		boolean successFlag = rdao.reservationDelete(rvo);

		if (successFlag == true) {
			System.out.println("삭제 완료 하였습니다.");
		} else {
			System.out.println("삭제에 실패하였습니다.");
		}
	}

	public void updateManager() {
		ReservationDAO rdao = new ReservationDAO();
		ReservationVO rvo = new ReservationVO();
		ArrayList<ReservationVO> reservationList = new ArrayList<ReservationVO>();

		reservationList = rdao.reservationSelect();
		if (reservationList.size() != 0) {
			printReservationList(reservationList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}

		System.out.print("수정할 데이터의 예약번호를 입력해주세요 : ");
		int no = Integer.parseInt(sc.nextLine().trim());

		System.out.print("고객 아이디를 입력해주세요 >>");
		String id = (sc.nextLine()).trim();

		System.out.print("항공편 일련번호를 입력해주세요 >>");
		int flight_no = Integer.parseInt(sc.nextLine().trim());

		System.out.print("예매 매수를 입력해주세요 >>");
		int quantity = Integer.parseInt(sc.nextLine().trim());

		rvo = new ReservationVO(no, id, flight_no, quantity);
		boolean successFlag = rdao.reservationUpdate(rvo);

		if (successFlag == true) {
			System.out.println("수정 완료하였습니다.");
		} else {
			System.out.println("수정에 실패하였습니다.");
		}
	}

	public void selectAllManager() {
		ReservationDAO rdao = new ReservationDAO();
		ArrayList<ReservationAllVO> reservationAllList = new ArrayList<ReservationAllVO>();

		reservationAllList = rdao.reservationAllSelect();
		if (reservationAllList.size() != 0) {
			printAllReservationList(reservationAllList);
		} else {
			System.out.println("출력할 데이터가 없습니다.");
			return;
		}
	}

	private void printAllReservationList(ArrayList<ReservationAllVO> reservationAllList) {
		System.out.println(
				"=======================================================================================================================");
		System.out.println(String.format(
				"%-5s %-5s %-5s %-8s %-10s %-5s %-8s %-5s %-5s %-8s %-5s %-5s %-5s %-5s", "예약번호", "항공편일련번호",
				"항공사번호", "고객아이디", "비밀번호", "고객이름", "여권번호", "마일리지", "좌석", "출발날짜", "출발공항", "도착공항", "예매매수", "가격"));

		// 데이터 출력
		for (ReservationAllVO rv : reservationAllList) {
			System.out.println(
					String.format("%-6s %-10s %-6s %-10s %-12s %-5s %-5s %-6d %-5s %-5s %-5s %-3s %-7d %-10d",
							rv.getNo(), rv.getFlight_no(), rv.getA_no(), rv.getId(), rv.getPasswd(), rv.getName(),
							rv.getPassport_number(), rv.getMileage(), rv.getSeat(), rv.getDeparture_date(),
							rv.getDeparture_airport(), rv.getArrival_airport(), rv.getQuantity(), rv.getPrice()));
		}
		System.out.println(
				"=======================================================================================================================");
	}

	private void printReservationList(ArrayList<ReservationVO> reservationList) {
		System.out.println("=================================");
		System.out.println(String.format("%-5s %-10s %-5s %-5s", "예약번호", "고객아이디", "항공편", "예매매수"));

		// 데이터 출력
		for (ReservationVO rv : reservationList) {
			System.out.println(
					String.format("%-7s %-11s %-6s %-5d", rv.getNo(), rv.getId(), rv.getFlight_no(), rv.getQuantity()));
		}
		System.out.println("=================================");
	}
}
