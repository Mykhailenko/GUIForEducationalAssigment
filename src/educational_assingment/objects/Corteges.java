package educational_assingment.objects;

import java.util.ArrayList;
import java.util.List;

public class Corteges {
	private String specialty_name;
	private byte semester;
	private List<Subject> subjects;
	
	public Corteges(){
	}
	public Corteges(String specialty_name, byte semester,
			List<Subject> subjects){
		this.specialty_name = specialty_name;
		this.semester = semester;
		this.subjects = subjects;
	}
	public String getSpecialty_name() {
		return specialty_name;
	}
	public void setSpecialty_name(String specialty_name) {
		this.specialty_name = specialty_name;
	}
	public byte getSemester() {
		return semester;
	}
	public void setSemester(byte semester) {
		this.semester = semester;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public void addSubject(Subject subject){
		if(subjects == null){
			subjects = new ArrayList<Subject>();
			subjects.add(subject);
		}else{
			subjects.add(subject);
		}
	}
	public void removeSubject(Subject subject){
		for(Subject sub: this.subjects)
			if(sub.isEqual(subject))
				subjects.remove(sub);
	}
}
