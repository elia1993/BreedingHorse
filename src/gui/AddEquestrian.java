package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import content.Equestrian;
import content.Trainer;
import init.BreedingHorses;
import utils.Rank;

import javax.swing.JTextField;

public class AddEquestrian extends JFrame {

	private JPanel contentPane;
	JTextField id,FullName;
    private JFormattedTextField BirthDate;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEquestrian window = new AddEquestrian(bh,null);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public AddEquestrian(BreedingHorses bh,DefaultListModel ListPeopleModel) {
		super("Add Equestrian");
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
		BirthDate.setBounds(91,93,123,20);  
        contentPane.add(id);
        contentPane.add(FullName);
        contentPane.add(BirthDate);
        contentPane.setSize(300,300);  
        contentPane.setLayout(null);  
        Format shortTime = DateFormat.getTimeInstance(DateFormat.SHORT);
        
        JLabel lblNewLabel = new JLabel("Id");
        lblNewLabel.setBounds(30, 53, 45, 13);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("FullName");
        lblNewLabel_1.setBounds(241, 53, 100, 13);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("BirthDate");
        lblNewLabel_2.setBounds(25, 96, 100, 13);
        contentPane.add(lblNewLabel_2);
        
       
        JFormattedTextField equestHours = new JFormattedTextField(shortTime);
        equestHours.setBounds(91, 143, 123, 20);
        contentPane.add(equestHours);
      
        
        JLabel lblNewLabel_4 = new JLabel("equestHours");
        lblNewLabel_4.setBounds(15, 146, 100, 13);
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
        btnNewButton.setBounds(151, 232, 85, 21);
        contentPane.add(btnNewButton);
        
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
             	 int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Add","add",JOptionPane.YES_NO_OPTION);

        		String equ_id = id.getText();
        		String equ_name = FullName.getText();
        		Date equ_bdate = (Date)BirthDate.getValue();
        		int equ_rank = comboBox.getSelectedIndex();
        		Time equest_Hours = (Time) equestHours.getValue();
        		Equestrian p = new Equestrian(equ_id,equ_name,equ_bdate,Rank.values()[equ_rank],equest_Hours);
           	 if(opt == 0) {
        		bh.addPerson(p);
    			ListPeopleModel.addElement(equ_id + " " + equ_name + " " + equ_bdate + " " + equ_rank + " " + equest_Hours);
           	 }JOptionPane.showMessageDialog(null, "Added Successfully!");
    		 setVisible(false);
        	}
        });
        
        JButton btnCancel = new JButton("Cancel");
   
        btnCancel.setBounds(246, 232, 85, 21);
        contentPane.add(btnCancel);
        
      
        
        JSeparator separator = new JSeparator();
        separator.setBounds(30, 202, 405, 6);
        contentPane.add(separator);
        
      
	}

}
