package educational_assingment.gui;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import educational_assingment.data_base.CurriculumTransaction;
import educational_assingment.models.SettingsCathedraModel;

public class SettingsCathedraTable {
	private SettingsCathedraModel model;
	private JTable table;
	
	public SettingsCathedraTable(String name, int size_column){
		model = new SettingsCathedraModel(name);
		CurriculumTransaction trans = new CurriculumTransaction();
		ArrayList<String> cath = trans.getCathedras();
		Object[][] data = new Object[cath.size()][1];
		int i=0;
		for(String str : cath){
			data[i][0]=str;
			i++;
		}
		model.setData(data);
		table = new JTable(model);
		table.setEditingRow(3);
		table.setRowHeight(25);
		TableColumnModel column_model = table.getColumnModel();
		Enumeration<TableColumn> e = column_model.getColumns();
		int j = 0;
		while (e.hasMoreElements()) {
			TableColumn column = (TableColumn) e.nextElement();
			JTableHeader header_model = table.getTableHeader();
			header_model.setReorderingAllowed(false);
			header_model.setResizingAllowed(false);
			header_model.setDefaultRenderer(new CurriculumHeaderRenderer());
			switch (j) {
			case 0:
				column.setPreferredWidth(50);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				column.setCellRenderer(table.getTableHeader().getDefaultRenderer());
				break;
			case 1:
				column.setPreferredWidth(size_column);
				column.setMaxWidth(column.getPreferredWidth());
				column.setMinWidth(column.getPreferredWidth());
				break;
			}
			j++;
		}
			
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public void setModel(SettingsCathedraModel model) {
		this.model = model;
	}
	public SettingsCathedraModel getModel(){
		return model;
	}
}


