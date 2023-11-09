package gui;


import java.awt.EventQueue;

import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import content.Person;

//import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import content.Trainer;
import init.BreedingHorses;
import utils.Rank;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddTrainer extends JFrame {
	JPanel contentPane;
    JTextField id,FullName;  
    private JFormattedTextField BirthDate;
    private JTextField WorkerId;
    private JTextField salary;
    private Trainer trainer;
    private JButton btnCancel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTrainer window = new AddTrainer(bh,null);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddTrainer(BreedingHorses bh,DefaultListModel ListPeopleModel) {
		super("Add Trainer");
		//ListPeopleModel = new DefaultListModel<>();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		id=new JTextField();
		setContentPane(contentPane);        
		id.setBounds(91,50,123,20);  
		FullName=new JTextField();  
		FullName.setBounds(303,50,123,20);  
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		BirthDate = new JFormattedTextField(df);
		BirthDate.setText(" / / ");
		BirthDate.setBounds(91,93,123,20);  
        contentPane.add(BirthDate);
        contentPane.add(id);
        contentPane.add(FullName);
        contentPane.setSize(300,300);  
        contentPane.setLayout(null);  
        
        JLabel lblNewLabel = new JLabel("Id");
        lblNewLabel.setBounds(30, 53, 45, 13);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("FullName");
        lblNewLabel_1.setBounds(241, 53, 100, 13);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("BirthDate");
        lblNewLabel_2.setBounds(25, 96, 100, 13);
        contentPane.add(lblNewLabel_2);
        
        WorkerId = new JTextField();
        WorkerId.setBounds(303, 93, 123, 20);
        contentPane.add(WorkerId);
        
        JLabel lblNewLabel_3 = new JLabel("workerId");
        lblNewLabel_3.setBounds(241, 96, 100, 13);
        contentPane.add(lblNewLabel_3);
        
        salary = new JTextField();
        salary.setBounds(91, 143, 123, 20);
        contentPane.add(salary);
        
        JLabel lblNewLabel_4 = new JLabel("salary");
        lblNewLabel_4.setBounds(25, 146, 100, 13);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Rank");
        lblNewLabel_5.setBounds(253, 146, 100, 13);
        contentPane.add(lblNewLabel_5);
        Rank ranks[]={Rank.PUNK,Rank.BEGGINER,Rank.JUNIOR,Rank.MASTER, Rank.EXPERT};        
        
        JComboBox comboBox = new JComboBox(ranks);
        comboBox.setBounds(303, 142, 123, 21);
        contentPane.add(comboBox);
        contentPane.setVisible(true);
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        comboBox.setRenderer(listRenderer);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.addMouseListener(new MouseAdapter() {
     	@Override
        	public void mouseClicked(MouseEvent e) {
          	 int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Add","add",JOptionPane.YES_NO_OPTION);
          	String trainer_id = id.getText();
    		String trainer_name = FullName.getText();
    		Date trainer_bdate =(Date)BirthDate.getValue();
    		String trainer_WorkerId = WorkerId.getText();
    		int trianer_salary = Integer.parseInt(salary.getText());
    		int trainer_rank = comboBox.getSelectedIndex();
        	 if(opt == 0) {
        		trainer = new Trainer(trainer_id,trainer_name,trainer_bdate,trainer_WorkerId,trianer_salary,Rank.values()[trainer_rank]);
        		bh.addPerson(trainer);
    			ListPeopleModel.addElement(trainer_id +" "+ trainer_name  +" " +trainer_bdate  +" " +trainer_WorkerId +" " + trianer_salary +" " + trainer_rank );
    			JOptionPane.showMessageDialog(null, "Added Successfully!");
        		 setVisible(false);
        	}
     	}
        });
        
        btnNewButton.setBounds(231, 232, 85, 21);
        contentPane.add(btnNewButton);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(341, 232, 85, 21);
        contentPane.add(btnCancel);
        btnCancel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		setVisible(false);
        	}
        });



        JSeparator separator = new JSeparator();
        separator.setBounds(30, 202, 405, 6);
        contentPane.add(separator);  
	}

	
	
}
