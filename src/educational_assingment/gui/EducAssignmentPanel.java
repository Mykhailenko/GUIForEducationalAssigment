package educational_assingment.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import educational_assingment.data_base.CurriculumTransaction;
import educational_assingnment.executers.Dispatcher;

public class EducAssignmentPanel extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton create;
	private JButton cancel;
	private JFileChooser chooser;
	private JComboBox<String> semester_combo;
	private ArrayList<JCheckBox> checks;
	
	public EducAssignmentPanel(){
		super("Формирование учебных поручений");
		List<Image> icons = new Vector<Image>();
		icons.add(new ImageIcon("images/уп.png").getImage());
		setIconImages(icons);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
		this.setResizable(false);
		Dimension dim_screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) dim_screen.getWidth()/2-DEFAULT_WIDTH/2, (int) dim_screen.getHeight()/2-DEFAULT_HEIGHT/2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel content_top = BoxLayoutUtils.createHorizontalPanel();
		
		//----------------------------------------------------------
		JPanel semester = BoxLayoutUtils.createHorizontalPanel();
		semester.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JLabel lab_semester = new JLabel("Вид семестра:");
		semester_combo =new JComboBox<String>();
		semester_combo.setBackground(Color.white);
		semester_combo.addItem("летний");
		semester_combo.addItem("зимний");
		lab_semester.setPreferredSize(new Dimension(100,27));
		lab_semester.setMaximumSize(lab_semester.getPreferredSize());
		lab_semester.setMinimumSize(lab_semester.getPreferredSize());
		semester_combo.setPreferredSize(new Dimension(100,27));
		semester_combo.setMaximumSize(semester_combo.getPreferredSize());
		semester_combo.setMinimumSize(semester_combo.getPreferredSize());
		semester.add(lab_semester);
		semester.add(semester_combo);
		
		JPanel bevel_semester = BoxLayoutUtils.createHorizontalPanel();
		bevel_semester.setBorder(BorderFactory.createLoweredBevelBorder());
		
		JPanel out_semester = BoxLayoutUtils.createHorizontalPanel();
		out_semester.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		 
		bevel_semester.add(semester);
		out_semester.add(bevel_semester);
		//----------------------------------------------------------
		
		JPanel cath = BoxLayoutUtils.createVerticalPanel();
		cath.setBackground(Color.white);
		semester.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		CurriculumTransaction tr = new CurriculumTransaction();
		ArrayList<String> cath_names = tr.getCathedras();
		checks = new ArrayList<JCheckBox>();
		this.checks.add(new JCheckBox("выбрать все..."));
		for(String name: cath_names)
			checks.add(new JCheckBox(name));
		for(JCheckBox cur_check:checks){
			 cath.add(Box.createHorizontalStrut(5));
			 cath.add(cur_check);
			 cur_check.setBackground(Color.white);
		}
		checks.get(0).addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JCheckBox check = (JCheckBox)e.getSource();
				if(check.isSelected()){
					for(JCheckBox cur:checks)
						cur.setSelected(true);
				}else
					for(JCheckBox cur:checks)
						cur.setSelected(false);
			}
		});
		cath.add(Box.createGlue());
		
		JPanel bevel_cath = BoxLayoutUtils.createHorizontalPanel();
		bevel_cath.setBorder(BorderFactory.createLoweredBevelBorder());
		
		JPanel out_cath = BoxLayoutUtils.createHorizontalPanel();
		out_cath.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
		 
		bevel_cath.add(new JScrollPane(cath));
		out_cath.add(bevel_cath);
		//----------------------------------------------------------
		JPanel right = BoxLayoutUtils.createVerticalPanel();
		right.add(out_semester);
		right.add(out_cath);
		
		content_top.add(right);
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		content_top.add(chooser);
		//----------------------------------------------------------
		JPanel content_create = BoxLayoutUtils.createHorizontalPanel();
		content_create.add(Box.createHorizontalGlue());
		create = new JButton("Сформировать поручения");
		create.setPreferredSize(new Dimension(200,27));
		create.setMinimumSize(create.getPreferredSize());
		create.setMaximumSize(create.getPreferredSize());
		cancel = new JButton("Отмена");
		cancel.setPreferredSize(new Dimension(100,27));
		cancel.setMinimumSize(cancel.getPreferredSize());
		cancel.setMaximumSize(cancel.getPreferredSize());
		content_create.add(create);
		content_create.add(Box.createHorizontalStrut(5));
		content_create.add(cancel);
		content_create.add(Box.createHorizontalStrut(4));
		
		JPanel create_empty = BoxLayoutUtils.createHorizontalPanel();
		create_empty.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		JPanel content_bottom = BoxLayoutUtils.createHorizontalPanel();
		content_bottom.setBorder(BorderFactory.createEtchedBorder());
		create_empty.add(content_create);
		content_bottom.add(create_empty);
		
		JPanel content = BoxLayoutUtils.createVerticalPanel();
		content.add(content_top);
		content.add(content_bottom);
		
		create.addActionListener(this);
		cancel.addActionListener(this);
		setContentPane(content);
		setVisible(true);		
	}
	static public void main(String args[]){
		new EducAssignmentPanel();
	}
	final private int DEFAULT_WIDTH = 800;
	final private int DEFAULT_HEIGHT = 393;
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==create){
		boolean flag = false;
		for(JCheckBox cur:checks)
			if(cur.isSelected())
				flag = true;
		if(flag){
			File path = chooser.getCurrentDirectory();
			ArrayList<String> cath_selected = new ArrayList<String>();
			for(JCheckBox cur:checks)
				if(cur.isSelected())
					if(cur.getText().compareTo("выбрать все...")!=0)
						cath_selected.add(cur.getText());
			String mode = new String();
			if(semester_combo.getSelectedItem().toString().compareTo("летний")==0)
				mode = "summer";
			else 
				mode = "winter";
			Dispatcher.createEducAssignment(cath_selected, path, mode);
			this.dispose();
		}else
			JOptionPane.showMessageDialog(null,
					"Для формирования учебных поручений необходимо определить кафедры.");
		}
		if(e.getSource()==cancel)
			this.dispose();
	}
	
}
