package com.kh.hw2.statement.model.vo;

import java.sql.Date;
import java.util.Objects;

public class Player {

	private int playerNo;
	private String playerName;
	private String teamCode;
	private int gamesPlayed;
	private double average;
	private Date debutDate;
	private Date retireDate;
	private char retireYN;
	
	public Player() {
		super();
	}
	
	public Player(int playerNo, String playerName, String teamCode, int gamesPlayed, double average) {
		super();
		this.playerNo = playerNo;
		this.playerName = playerName;
		this.teamCode = teamCode;
		this.gamesPlayed = gamesPlayed;
		this.average = average;
	}

	public Player(int playerNo, String playerName, String teamCode, int gamesPlayed, double average, Date debutDate,
			Date retireDate, char retireYN) {
		super();
		this.playerNo = playerNo;
		this.playerName = playerName;
		this.teamCode = teamCode;
		this.gamesPlayed = gamesPlayed;
		this.average = average;
		this.debutDate = debutDate;
		this.retireDate = retireDate;
		this.retireYN = retireYN;
	}
	public int getPlayerNo() {
		return playerNo;
	}
	public void setPlayerNo(int playerNo) {
		this.playerNo = playerNo;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public Date getDebutDate() {
		return debutDate;
	}
	public void setDebutDate(Date debutDate) {
		this.debutDate = debutDate;
	}
	public Date getRetireDate() {
		return retireDate;
	}
	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}
	public char getRetireYN() {
		return retireYN;
	}
	public void setRetireYN(char retireYN) {
		this.retireYN = retireYN;
	}
	@Override
	public String toString() {
		return "Player [playerNo=" + playerNo + ", playerName=" + playerName + ", teamCode=" + teamCode
				+ ", gamesPlayed=" + gamesPlayed + ", average=" + average + ", debutDate=" + debutDate + ", retireDate="
				+ retireDate + ", retireYN=" + retireYN + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(average, debutDate, gamesPlayed, playerName, playerNo, retireDate, retireYN, teamCode);
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
		return Double.doubleToLongBits(average) == Double.doubleToLongBits(other.average)
				&& Objects.equals(debutDate, other.debutDate) && gamesPlayed == other.gamesPlayed
				&& Objects.equals(playerName, other.playerName) && playerNo == other.playerNo
				&& Objects.equals(retireDate, other.retireDate) && retireYN == other.retireYN
				&& Objects.equals(teamCode, other.teamCode);
	}
	
	
}
