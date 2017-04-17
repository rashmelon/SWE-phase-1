package com.playacademy.user.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.playacademy.course.model.CourseAttendance;
import com.playacademy.game.model.ScoreSheet;



@Entity
public class Student extends User{

	
	@JsonIgnore
	private Set<ScoreSheet> scores;
	
	
	@JsonIgnore
    private Set<CourseAttendance> attendance_sheets;
	
	
	public Student(){
		type="Student";
		attendance_sheets = new HashSet<CourseAttendance> ();
	}
	public Student(long id){
		this.setUserId(id);
	}
	
	
	// Setters
	public void setScores(Set<ScoreSheet> scores) {
		this.scores = scores;
	}
	public void setAttendance_sheets(Set<CourseAttendance> attendance_sheets) {
		this.attendance_sheets = attendance_sheets;
	}
	
	// add
	public void addScore(ScoreSheet score) {
		score.setStudent(this);
		scores.add(score);
	}
	
	// Relations
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="student")
	public Set<ScoreSheet> getScores() {
		return scores;
	}
	
	@OneToMany(mappedBy = "student", cascade=CascadeType.ALL)
	public Set<CourseAttendance> getAttendance_sheets() {
		return attendance_sheets;
	}
	public void addAttendance(CourseAttendance attendance) {
		attendance.setStudent(this);
		attendance_sheets.add(attendance);
	}
	
	
	
}
