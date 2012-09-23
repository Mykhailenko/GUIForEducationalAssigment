package educational_assingment.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class CurriculumHeaderRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel label = (JLabel) super.getTableCellRendererComponent(table,
				value, isSelected, hasFocus, row, column);
		label.setBackground(Color.lightGray);
		label.setForeground(Color.darkGray);
		Font font = new Font("sansserif", Font.BOLD, 12);
		label.setFont(font);
		label.setBorder(border);
		return label;
	}
	private Border border = BorderFactory.createBevelBorder(CENTER);
}