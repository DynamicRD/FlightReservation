package com.kh.flightReservation.model;

public class AirLineVO {
	private int no; // pk
	private String name; // uk
	private String englishName; // uk
	private String country;
	private String grade;

	public AirLineVO() {}

	public AirLineVO(int no, String name, String englishName, String country, String grade) {
		super();
		this.no = no;
		this.name = name;
		this.englishName = englishName;
		this.country = country;
		this.grade = grade;
	}

	public AirLineVO(int no, String country, String grade) {
		super();
		this.no = no;
		this.country = country;
		this.grade = grade;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "[" + no + ", " + name + ", " + englishName + ", " + country + ", " + grade + "]";
	}

}
