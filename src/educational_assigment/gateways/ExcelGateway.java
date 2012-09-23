package educational_assigment.gateways;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import educational_assingment.data_base.CurriculumTransaction;
import educational_assingment.objects.Corteges;
import educational_assingment.objects.Subject;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;


public class ExcelGateway {
	private static int semester_row = 2;
	private static int for_count_of_lectPract = 4;
	private static byte semester_from_curriculum = -1;
	//private static byte second_semester_in_one_word = -1;

	public static ArrayList<ArrayList<Object>> firstLoadCurriculum(File file, String specialty) {

		List<Corteges> list = new ArrayList<Corteges>();
		File inputWorkbook = file;
		Workbook w;

		try {
			boolean flag = false;
			int step_semester = -1;
			int step_practice = -1;
			w = Workbook.getWorkbook(inputWorkbook);
			// Get the first sheet
			Sheet sheet = w.getSheet(0);
			int bound_subject = 0; // count of subjects
			Cell cell = sheet.getCell(1, bound_subject);
			while (cell.getContents().compareTo("1 дисципліна") != 0
					&& cell.getContents().compareTo(
							"Кількість годин учбових занять") != 0) {
				cell = sheet.getCell(1, ++bound_subject);
				// System.out.println(cell.getContents());
			}
			if (sheet.getCell(1, bound_subject - 1).getContents()
					.compareTo("Усього") == 0)
				bound_subject--; // curriculum of special
			// System.out.println("bound_subject " + bound_subject);
			if (cell.getContents().compareTo("Кількість годин учбових занять") == 0) {
				System.out.println("flag"); // curriculum of master
				flag = true;
			}
			int bound_week = 32; // count of weeks
			int week = 32;
			// differences of curriculums
			if (sheet.getCell(bound_week, semester_row).getContents()
					.compareTo("Самост. робота") == 0) {
				bound_week = 33;
				week = 33;
			}

			if (flag == true) {
				bound_week += 2;
				while (sheet.getCell(bound_week, for_count_of_lectPract)
						.getType() != CellType.EMPTY) {
					bound_week += 2;
					System.out.println(sheet.getCell(bound_week,
							for_count_of_lectPract).getContents()
							+ "+");
				}
				step_semester = 4;
				step_practice = 2;
			} else {
				while (cell.getType() != CellType.EMPTY)
					cell = sheet.getCell(++bound_week, 8);
				step_semester = 2;
				step_practice = 1;
			}
			System.out.println("bound_week " + bound_week);
			for (int j = week; j < bound_week; j += step_semester) {
				Corteges cur_cortege = new Corteges();
				cur_cortege.setSpecialty_name(specialty);
				System.out.println("semester "
						+ sheet.getCell(j, semester_row).getContents());
				getSemester(sheet.getCell(j, semester_row).getContents());
				cur_cortege.setSemester(semester_from_curriculum);

				List<Subject> subjects = new ArrayList<Subject>();

				for (int i = 11; i < bound_subject; i++) {

					Subject cur_subject = new Subject();

					Cell cell_lecture = sheet.getCell(j, i);
					Cell cell_practice = sheet.getCell(j + step_practice, i);
					if ((cell_lecture.getType() != CellType.EMPTY || cell_practice
							.getType() != CellType.EMPTY)
							&& sheet.getCell(0, i).getType() != CellType.EMPTY) {

						cur_subject.setName(sheet.getCell(1, i).getContents());
						if (cell_lecture.getType() != CellType.EMPTY)
							cur_subject
									.setLect_hours(verifNumberFormat(cell_lecture
											.getContents()));
						else
							cur_subject.setLect_hours(Byte.parseByte("0"));
						if (cell_practice.getType() != CellType.EMPTY)
							cur_subject
									.setPract_hours(verifNumberFormat(cell_practice
											.getContents()));
						else
							cur_subject.setPract_hours(Byte.parseByte("0"));
						cur_subject.setCathedra_name("");
						cur_subject.setSubgroups(Byte.parseByte("0"));

						subjects.add(cur_subject);
					}
				}
				cur_cortege.setSubjects(subjects);
				list.add(cur_cortege);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		CurriculumTransaction tr = new CurriculumTransaction();
		return tr.loadCurriculumData(list, specialty);
	}
	
	private static byte verifNumberFormat(String number) {
		byte result = -1;
		try {
			result = Byte.parseByte(number);
		} catch (Exception e) { // attention!
			// int i=0;
			// while(new String(number.charAt(i)).compareTo("\")!=0)
			// i++;
			result = Byte.parseByte("-10");
		}
		return result;
	}
	
	private static void getSemester(String content) {
		int i = 0;
		boolean flag = false;
		while (Character.isDigit(content.charAt(i)))
			i++;
		if(content.charAt(i)==',')
			flag = true;
		if(flag){
			int j = i+1;
			while (Character.isDigit(content.charAt(j)))
				j++;
			//second_semester_in_one_word = Byte.parseByte(content.substring(i+1, j));
		}
		semester_from_curriculum = Byte.parseByte(content.substring(0, i));
	}
	
	public static void printListOfCorteges(List<Corteges> list) {
		for (Corteges c : list) {
			System.out.println("SPECIALTY: " + c.getSpecialty_name() + "   "
					+ "SEMESTER: " + c.getSemester());
			for (Subject s : c.getSubjects())
				System.out.println(s.getName() + " " + s.getLect_hours() + " "
						+ s.getPract_hours() + " " + s.getCathedra_name() + " "
						+ s.getSubgroups());
			System.out.println();
		}
	}
}
