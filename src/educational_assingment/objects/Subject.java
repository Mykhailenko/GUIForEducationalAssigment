package educational_assingment.objects;

// 1 record from curriculum
public class Subject {
	private String name;
	private byte lect_hours;
	private byte pract_hours;
	private String cathedra_name;
	private byte subgroups;
	
	public Subject(){
	}
	public Subject(String name,byte lect_hours, byte pract_hours, 
			String cathedra_name, byte subgroups){
		this.name = name;
		this.lect_hours = lect_hours;
		this.pract_hours = pract_hours;
		this.cathedra_name = cathedra_name;
		this.subgroups = subgroups;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getLect_hours() {
		return lect_hours;
	}
	public void setLect_hours(byte lect_hours) {
		this.lect_hours = lect_hours;
	}
	public byte getPract_hours() {
		return pract_hours;
	}
	public void setPract_hours(byte pract_hours) {
		this.pract_hours = pract_hours;
	}
	public String getCathedra_name() {
		return cathedra_name;
	}
	public void setCathedra_name(String cathedra_name) {
		this.cathedra_name = cathedra_name;
	}
	public byte getSubgroups() {
		return subgroups;
	}
	public void setSubgroups(byte subgroups) {
		this.subgroups = subgroups;
	}
	public boolean isEqual(Subject subject){
		return this.name.compareTo(subject.name)==0 &&
				this.cathedra_name.compareTo(subject.cathedra_name)==0 &&
				this.lect_hours == subject.lect_hours &&
				this.pract_hours == subject.pract_hours &&
				this.subgroups == subject.subgroups;
	}
}
