package educational_assingment.models;

import javax.swing.table.AbstractTableModel;

public class SettingsSpecialtyModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	public SettingsSpecialtyModel(String name_column) {
		super();
		this.columnNames[1] = name_column;
		data = new Object[row_count][column_count];
		for (int i = 0; i < row_count; i++) {
			data[i][0] = i + 1;
			data[i][1] = "";
		}
	}
	public void setData(Object[][] list){
		data = new Object[row_count][column_count];
		int i;
		for(i=0; i<list.length; i++){
			data[i][0]=i+1;
			data[i][1]=list[i][0];
		}
		for(int j = i; j<row_count; j++){
			data[j][0]=j+1;
			data[j][1]="";
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
		return c == RINGS;
	}

	public Object[][] getData() {
		int i=0;
		while(data[i][1].toString().compareTo("")!=0)
			i++;
		Object [][]result = new Object[i][2];
		for(int j=0;j<i;j++)
			result[j][1]=data[j][1];
		return result;
	}

	public void printData() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++)
				System.out.print(data[i][j] + "    ");
			System.out.println();
		}
	}

	private Object[][] data;
	private String[] columnNames = { "", "" };
	private int row_count = 20;
	private int column_count = 2;

	public static final int RINGS = 1;
}
