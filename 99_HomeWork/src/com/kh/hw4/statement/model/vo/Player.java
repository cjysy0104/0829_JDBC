package com.kh.hw4.statement.model.vo;

import java.sql.Date;
import java.util.Objects;

public class Player {

	private int playerId;
	private String fullName;
	private String position;
	private String nation;
	private double height;
	private double weight;
	private String curTeam;
	private Date enrollDate;

	public Player() {
		super();
	}

	public Player(int playerId, String fullName, String position, String nation, double height, double weight,
			String curTeam, Date enrollDate) {
		super();
		this.playerId = playerId;
		this.fullName = fullName;
		this.position = position;
		this.nation = nation;
		this.height = height;
		this.weight = weight;
		this.curTeam = curTeam;
		this.enrollDate = enrollDate;
	}

	public int getPlayerId() {
		return playerId;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPosition() {
		return position;
	}

	public String getNation() {
		return nation;
	}

	public double getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public String getCurTeam() {
		return curTeam;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", fullName=" + fullName + ", position=" + position + ", nation="
				+ nation + ", height=" + height + ", weight=" + weight + ", curTeam=" + curTeam + ", enrollDate="
				+ enrollDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(curTeam, enrollDate, fullName, height, nation, playerId, position, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(curTeam, other.curTeam) && Objects.equals(enrollDate, other.enrollDate)
				&& Objects.equals(fullName, other.fullName)
				&& Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height)
				&& Objects.equals(nation, other.nation) && playerId == other.playerId
				&& Objects.equals(position, other.position)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}

}
