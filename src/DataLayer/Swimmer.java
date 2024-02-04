package DataLayer;

import java.util.*;

public class Swimmer {

	private int yearOfBirth;

	private String name;
	private double height;
	private Nationality nationality;
	private double totalJumpDifficulty;
	private double totalPoints;
	private boolean isMale;
	private ArrayList<Dive> diveList;
	private ArrayList<Rating> ratingList;

	public Swimmer(int yearOfBirth, String name, Nationality nationality, double totalJumpDifficulty,
			double totalPoints, boolean isMale) {
		this.yearOfBirth = yearOfBirth;
		this.name = name;
		this.nationality = nationality;
		this.totalJumpDifficulty = totalJumpDifficulty;
		this.totalPoints = totalPoints;
		this.isMale = isMale;
		this.diveList = new ArrayList<>();
		this.ratingList = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public double getTotalJumpDifficulty() {
		return totalJumpDifficulty;
	}

	public double getTotalPoints() {
		return totalPoints;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public Nationality getNationality() {
		return nationality;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public void setTotalJumpDifficulty(double totalJumpDifficulty) {
		this.totalJumpDifficulty = totalJumpDifficulty;
	}

	public void setTotalPoints(double totalPoints) {
		this.totalPoints = totalPoints;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public void addDive(Dive dive) {
		this.diveList.add(dive);
	}

	public void addRating(Rating rating) {
		this.ratingList.add(rating);
	}

}
