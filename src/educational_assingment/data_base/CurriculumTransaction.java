package educational_assingment.data_base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import educational_assingment.objects.Corteges;
import educational_assingment.objects.Subject;

public class CurriculumTransaction {
	private Connection conn;
	private Statement st;
	private ResultSet rs;

	public CurriculumTransaction() {
		conn = ConnectionSingleton.getInstance();
		try {
			st = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<Object>> loadCurriculumData(List<Corteges> list,
			String specialty) {
		ArrayList<ArrayList<Object>> res = new ArrayList<ArrayList<Object>>();
		try {
			for (Corteges cort : list) {
				List<Subject> sub_list = cort.getSubjects();
				int id_spec = findSpecialty(cort.getSpecialty_name(),
						cort.getSemester());
				for (Subject sub : sub_list) {
					if (isExist(sub, cort.getSpecialty_name(), cort.getSemester()) != true) {
						int id = getMaxSubjectId();
						String s = "insert into subject values (";
						s += id;
						s += ", '";
						s += makeWorkForSQL(sub.getName());
						s += "',";
						if (sub.getPract_hours() != -10)
							s += sub.getPract_hours();
						else
							s += 0;
						s += ",";
						s += sub.getLect_hours();
						s += ",";
						s += sub.getSubgroups();
						s += ",";
						s += id_spec;
						s += ", null);";
						System.out.println(s);
						st.execute(s);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		res = this.getCurriculumData(specialty);
		return res;
	}
	
	public boolean deleteCurriculumData(String specialty){
		try{
			String s = "select id from specialty where specialty_name like '";
			s += specialty+"';";
			rs = st.executeQuery(s);
			ArrayList<Integer> ids = new ArrayList<Integer>();
			while(rs.next())
				ids.add(new Integer(rs.getInt("id")));
			s = "delete from subject where ref_specialty = ";
			for(int i = 0 ; i< ids.size()-1; i++){
				s += ids.get(i);
				s += " or ref_specialty =  ";
			}
			s += ids.get(ids.size()-1);
			s += ";";
			System.out.println(s);
			st.execute(s);
		}catch(Exception e){
			System.out.println("Problem in deleteCurriculumData");
			return false;
		}
		return true;
	}

	public ArrayList<ArrayList<Object>> getCurriculumData(String specialty) {
		ArrayList<ArrayList<Object>> res = new ArrayList<ArrayList<Object>>();
		try {
			String s = "select * from subject, specialty where ref_specialty = specialty.id and specialty_name like '";
			s += specialty + "';";
			rs = st.executeQuery(s);
			int i = 1;
			while (rs.next()) {
				ArrayList<Object> cur = new ArrayList<Object>();
				cur.add(rs.getString("subject_name"));
				cur.add(rs.getInt("prac_hour"));
				cur.add(rs.getInt("lect_hour"));
				cur.add(rs.getInt("subgroup_number"));
				cur.add(rs.getInt("ref_specialty"));
				cur.add(rs.getInt("ref_cathedra"));
				res.add(cur);
				System.out.println(i);
				i++;
			}
			System.out.println("List size**************" + res.size());
			for (ArrayList<Object> record : res) {
				int ref_spec = Integer.parseInt(record.get(4).toString());
				record.remove(4);
				String str = "select specialty_name, semester from specialty where id="
						+ ref_spec + ";";
				rs = st.executeQuery(str);
				while (rs.next()) {
					record.add(rs.getString("specialty_name"));
					record.add(rs.getInt("semester"));
				}
			}
			for (ArrayList<Object> record : res) {
				int ref_cath = Integer.parseInt(record.get(4).toString());
				record.remove(4);
				if (ref_cath != 0) {
					String str = "select cathedra_name from cathedra where id="
							+ ref_cath + ";";
					rs = st.executeQuery(str);
					while (rs.next())
						record.add(rs.getString("cathedra_name"));
				}
				else
					record.add("");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}

	private boolean isExist(Subject sub, String spec, int semester) {
		try {
			String s = "select * from subject, specialty where subject_name like '";
			s += makeWorkForSQL(sub.getName());
			s += "'";
			if (sub.getName().compareTo("Фізичне виховання")!= 0){
				s += " and prac_hour = ";
				s += sub.getPract_hours();
				s += " and lect_hour =";
				s += sub.getLect_hours();
			}
			s+= " and ref_specialty= specialty.id and specialty_name like '";
			s+= spec;
			s+= "' and semester = "+semester;
			s += ";";
			System.out.println(s);
			rs = st.executeQuery(s);
			while (rs.next())
				return true;
			return false;
		} catch (Exception e) {
			System.out.println("EXCEPTION!!!!!!");
			return false;
		}
	}

	private int getMaxSubjectId() {
		int id_sub = -1;
		try {
			String s = "SELECT max(id) as AA FROM subject;";
			System.out.println(s);
			id_sub = -1;
			rs = st.executeQuery(s);
			while (rs.next()) {
				id_sub = rs.getInt("AA");
			}
			id_sub++;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return id_sub;
	}

	private static String makeWorkForSQL(String word) {
		StringBuffer bur = new StringBuffer();
		int i;
		for (i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '\'') {
				bur.append(word.substring(0, i + 1));
				bur.append('\'');
				break;
			}
		}
		if (i != word.length()) {
			i++;
			bur.append(word.substring(i, word.length()));
			return bur.toString();
		}
		return word;
	}

	private int findSpecialty(String name, byte sem) {
		int res = -1;
		try {
			String s = "select id from specialty where specialty_name like '";
			s += name;
			s += "' and semester = ";
			s += sem;
			s += ";";
			rs = st.executeQuery(s);
			while (rs.next())
				res = rs.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return res;
	}

	public boolean insertSpecialiesData(Object[][] data, int course) {
		try {
			String s = "delete from specialty;";
			st.execute(s);
			int id = 1;
			for (int i = 0; i < data.length; i++) {
				int semester = 1;
				for (int y = 0; y < course; y++) {
					s = "insert into specialty values (";
					s += id;
					s += ", '";
					s += data[i][1];
					s += "', ";
					s += semester;
					s += ");";
					System.out.println(s);
					st.execute(s);
					id++;
					s = "insert into specialty values (";
					s += id;
					s += ", '";
					s += data[i][1];
					s += "', ";
					s += semester + 1;
					s += ");";
					System.out.println(s);
					st.execute(s);
					id++;
					semester += 2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private int findInSpecialtySemester(String name, int sem){
		int id = -1;
		try{
			String s = "select id from specialty where specialty_name like'";
			s+= name;
			s+= "' and semester = "+sem+";";
			System.out.println(s);
			rs =st.executeQuery(s);
			while(rs.next()){
				id = rs.getInt("id");
			}
			return id;
		}catch(Exception e){
			return -1;
		}
	}
	private int findIdCathedra(String name){
		int id = -1;
		try{
			String s = "select id from cathedra where cathedra_name like'";
			s+= name;
			s+= "';";
			System.out.println(s);
			rs =st.executeQuery(s);
			while(rs.next()){
				id = rs.getInt("id");
			}
			return id;
		}catch(Exception e){
			return -1;
		}
	}

	public ArrayList<String> getSpecialties() {
		ArrayList<String> res = new ArrayList<String>();
		try {
			String s = "select distinct specialty_name from specialty";
			rs = st.executeQuery(s);
			while (rs.next())
				res.add(rs.getString("specialty_name"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}
	
	public void saveChanges(Object [][] data){
		String ref_cath = new String();
		try{
			for(int i = 0 ; i< data.length; i++){
					if(data[i][6].toString().compareTo("")==0)
						ref_cath = "null";
					else{
						ref_cath = new Integer(this.findIdCathedra(data[i][6].toString())).toString();
					}
					int id_spec = this.findInSpecialtySemester(data[i][2].toString(), Integer.parseInt(data[i][3].toString()));
					String s = "update subject set subgroup_number = ";
					if(data[i][7].toString().compareTo("")==0)
						s+=0;
					else s+=data[i][7];
					s+=", ref_cathedra = ";
					s+=ref_cath;
					s+=" where subject_name like '";
					s+=makeWorkForSQL(data[i][1].toString());
					s+="' and prac_hour = "+data[i][5]+" and lect_hour = "+data[i][4]+" and ref_specialty = "+id_spec+";";
					System.out.println(s);
					st.execute(s);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Problem SaveChanges");
		}
	}
	
	public ArrayList<String> getCathedras() {
		ArrayList<String> res = new ArrayList<String>();
		try {
			String s = "select distinct cathedra_name from cathedra";
			rs = st.executeQuery(s);
			while (rs.next())
				res.add(rs.getString("cathedra_name"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}

	public boolean insertCathedrasNames(Object[][] data) {
		try {
			String s = "delete from cathedra;";
			st.execute(s);
			int id = 1;
			for (int i = 0; i < data.length; i++) {
				s = "insert into cathedra values (";
				s += id;
				s += ", '";
				s += data[i][1];
				s += "');";
				System.out.println(s);
				st.execute(s);
				id++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
