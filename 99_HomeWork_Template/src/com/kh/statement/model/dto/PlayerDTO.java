package com.kh.statement.model.dto;

public class PlayerDTO {

	private String fullName;
	private String position;
	private String nation;
	private double height;
	private double weight;
	private String curTeam;
	
	public PlayerDTO() {
		super();
	}

	public PlayerDTO(String fullName, String position, String nation, double height, double weight, String curTeam) {
		super();
		this.fullName = fullName;
		this.position = position;
		this.nation = nation;
		this.height = height;
		this.weight = weight;
		this.curTeam = curTeam;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCurTeam() {
		return curTeam;
	}

	public void setCurTeam(String curTeam) {
		this.curTeam = curTeam;
	}
	
	
}
