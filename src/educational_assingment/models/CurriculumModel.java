package educational_assingment.models;

import javax.swing.table.AbstractTableModel;

public class CurriculumModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	public CurriculumModel(Object[][] list, String name_spec) {
		row_count = list.length;
		System.out.println(row_count + " " + column_count);
		System.out.println("List size**************" + list.length);
		data = new Object[row_count][column_count];
		for(int i = 0; i<list.length;i++){
			for(int j = 0;j<list[i].length; j++){
				System.out.print(list[i][j]+" ");
			}
			System.out.println();
		}
				
		for (int i = 0; i < row_count; i++) {
			data[i][0] = i + 1;
			data[i][1] = list[i][0];
			data[i][2] = list[i][4];
			data[i][3] = list[i][5];
			data[i][4] = list[i][2];
			data[i][5] = list[i][1];
			if (list[i][6].toString().compareTo("0")==0)
				data[i][6] = "";
			else
				data[i][6] = list[i][6];
			if (list[i][3].toString().compareTo("0")==0)
				data[i][7] = "";
			else
				data[i][7] = list[i][3];
			
		}
		this.specialty_name = name_spec;
	}

	public void removeData() {
		data = null;
	}

	public void setData(Object[][] list) {
		row_count = list.length;
		System.out.println(row_count + " " + column_count);
		System.out.println("List size**************" + list.length);
		data = new Object[row_count][column_count];
		for (int i = 0; i < row_count; i++) {
			data[i][0] = i + 1;
			data[i][1] = list[i][0];
			data[i][2] = list[i][5];
			data[i][3] = list[i][6];
			data[i][4] = list[i][2];
			data[i][5] = list[i][1];
			data[i][6] = list[i][4];
			data[i][7] = list[i][3];
		}
	}

	public String getColumnName(int c) {
		return columnNames[c];
	}

	@SuppressWarnings("unchecked")
	public Class<Object> getColumnClass(int c) {
		return (Class<Object>) data[0][c].getClass();
	}

	@Override
	public int getColumnCount() {
		return data[0].length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int r, int c) {
		return data[r][c];
	}

	public void setValueAt(Object obj, int r, int c) {
		data[r][c] = obj;
	}

	public boolean isCellEditable(int r, int c) {
		if(data[r][5].toString().compareTo("0")!=0)			
			return c == SUBGROUPS || c == CATHEDRA;
		else
			return c == CATHEDRA;
	}

	public Object[][] getData() {
		return data;
	}

	public void printData() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++)
				System.out.print(data[i][j] + "    ");
			System.out.println();
		}
	}

	private Object[][] data;
	private String[] columnNames = { "", "Название дисциплины",
			"Специальность", "Семестр", "Лекции", "Практика", "Кафедра",
			"Число Подгрупп" };
	private int row_count;
	private int column_count = 8;
	private String specialty_name;

	public String getSpecialty_name() {
		return specialty_name;
	}

	public void setSpecialty_name(String specialty_name) {
		this.specialty_name = specialty_name;
	}

	public static final int CATHEDRA = 6;
	public static final int SUBGROUPS = 7;
}
