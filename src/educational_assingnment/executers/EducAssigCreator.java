package educational_assingnment.executers;

import java.io.File;
import java.util.ArrayList;
import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

public class EducAssigCreator {

	@SuppressWarnings("deprecation")
	public void createAssignment(String path, ArrayList<ArrayList<Object>> data) {
		try {
			System.out.println(data.size());
			File newXlsFile = new File(path);
			WritableWorkbook workbook = Workbook.createWorkbook(newXlsFile);
			WritableSheet sheet = workbook.createSheet("Educational Assignment", 0);
			createHead(sheet);
			
			WritableFont arial10font = new WritableFont(WritableFont.ARIAL, 10);
			WritableCellFormat arial10format = new WritableCellFormat(arial10font);
			arial10format.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
			
			int row = 3;
			int counter_id = 1;
			for(ArrayList<Object> cur_subject: data){
				//lecture
				if(Integer.parseInt(cur_subject.get(2).toString())!=0){
					Label label = new Label(1, row, new Integer(counter_id).toString(), arial10format);
					sheet.addCell(label);
					label = new Label(2, row, cur_subject.get(0).toString(), arial10format);
					sheet.addCell(label);
					label = new Label(3, row, cur_subject.get(4).toString(), arial10format);
					sheet.addCell(label);
					label = new Label(4, row, cur_subject.get(5).toString(), arial10format);
					sheet.addCell(label);
					label = new Label(5, row, "Лекція", arial10format);
					sheet.addCell(label);
					label = new Label(6, row, "", arial10format);
					sheet.addCell(label);
					label = new Label(7, row, "", arial10format);
					sheet.addCell(label);
					label = new Label(8, row, "", arial10format);
					sheet.addCell(label);
					sheet.setRowView(row, 450);
					row++;
					counter_id++;
					
				//practice
				}if(Integer.parseInt(cur_subject.get(1).toString())!=0){
					int subgroups = Integer.parseInt(cur_subject.get(3).toString());
					for(int i = 0; i<subgroups; i++){
						Label label = new Label(1, row, new Integer(counter_id).toString(), arial10format);
						sheet.addCell(label);
						label = new Label(2, row, cur_subject.get(0).toString(), arial10format);
						sheet.addCell(label);
						label = new Label(3, row, cur_subject.get(4).toString(), arial10format);
						sheet.addCell(label);
						label = new Label(4, row, cur_subject.get(5).toString(), arial10format);
						sheet.addCell(label);
						label = new Label(5, row, "Практика", arial10format);
						sheet.addCell(label);
						label = new Label(6, row, "", arial10format);
						sheet.addCell(label);
						label = new Label(7, row, "", arial10format);
						sheet.addCell(label);
						label = new Label(8, row, "", arial10format);
						sheet.addCell(label);
						sheet.setRowView(row, 450);
						row++;
						counter_id++;
					}
				}
				WritableFont arial12font = new WritableFont(WritableFont.ARIAL, 12);
				WritableCellFormat arial12format = new WritableCellFormat(arial12font);
				Label label = new Label(1, row, path, arial12format);
				
				sheet.addCell(label);
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void createHead(WritableSheet sheet) throws RowsExceededException,
			WriteException {
		WritableFont arial12font = new WritableFont(WritableFont.ARIAL, 12);
		WritableCellFormat arial12format = new WritableCellFormat(arial12font);
		arial12format.setBorder(jxl.format.Border.ALL, BorderLineStyle.MEDIUM);

		Label label = new Label(1, 1, "№", arial12format);
		sheet.mergeCells(1, 1, 1, 2);
		sheet.addCell(label);
		label = new Label(2,1, "Название дисциплины",  arial12format);
		sheet.mergeCells(2, 1, 2, 2);
		sheet.addCell(label);
		sheet.setColumnView(2, 40);
		label = new Label(3,1, "Специальность",  arial12format);
		sheet.mergeCells(3, 1, 3, 2);
		sheet.addCell(label);
		sheet.setColumnView(3, 25);
		label = new Label(4,1, "Семестр",  arial12format);
		sheet.mergeCells(4, 1, 4, 2);
		sheet.addCell(label);
		sheet.setColumnView(4, 15);
		label = new Label(5,1, "Тип",  arial12format);
		sheet.mergeCells(5, 1, 5, 2);
		sheet.addCell(label);
		sheet.setColumnView(5, 9);
		label = new Label(6,1, "Преподаватель",  arial12format);
		sheet.mergeCells(6, 1, 6, 2);
		sheet.addCell(label);
		sheet.setColumnView(6, 20);
		label = new Label(7,1, "Группа",  arial12format);
		sheet.mergeCells(7, 1, 7, 2);
		sheet.addCell(label);
		sheet.setColumnView(7, 10);
		label = new Label(8,1, "Заявка",  arial12format);
		sheet.mergeCells(8, 1, 8, 2);
		sheet.addCell(label);
		sheet.setColumnView(8, 10);
	}
	
}
