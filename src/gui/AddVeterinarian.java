package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import content.Trainer;
import content.Veterinarian;
import init.BreedingHorses;
import utils.Rank;

public class AddVeterinarian extends JFrame {

	private JPanel contentPane;
	JTextField id,FullName;  
    private JFormattedTextField BirthDate;
    private JTextField serial_id;
    private JTextField salary;
    private JTextField total_treatment_hours;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVeterinarian window = new AddVeterinarian(bh,null);
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
	public AddVeterinarian(BreedingHorses bh,DefaultListModel ListPeopleModel) {
		super("Add Veterianrian");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
        
        JLabel lblNewLabel = new JLabel("Id");
        lblNewLabel.setBounds(30, 53, 45, 13);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("FullName");
        lblNewLabel_1.setBounds(241, 53, 100, 13);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("BirthDate");
        lblNewLabel_2.setBounds(25, 96, 100, 13);
        contentPane.add(lblNewLabel_2);
        
        serial_id = new JTextField();
        serial_id.setBounds(303, 93, 123, 20);
        contentPane.add(serial_id);
        
        JLabel lblNewLabel_3 = new JLabel("serial Id");
        lblNewLabel_3.setBounds(241, 96, 100, 13);
        contentPane.add(lblNewLabel_3);
        
        salary = new JTextField();
        salary.setBounds(91, 143, 123, 20);
        contentPane.add(salary);
        
        JLabel lblNewLabel_4 = new JLabel("salary");
        lblNewLabel_4.setBounds(25, 146, 100, 13);
        contentPane.add(lblNewLabel_4);
        //here
        total_treatment_hours = new JTextField();
        total_treatment_hours.setBounds(280, 142, 130, 21);
        contentPane.add(total_treatment_hours);
        
        JLabel lblNewLabel_5 = new JLabel("total Treatment Hours");
        lblNewLabel_5.setBounds(280, 125, 150, 13);
        contentPane.add(lblNewLabel_5);
     
        
        JButton btnNewButton = new JButton("OK");
        btnNewButton.setBounds(151, 232, 85, 21);
        contentPane.add(btnNewButton);
        
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
             	int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Add","add",JOptionPane.YES_NO_OPTION);
        		String vet_id = id.getText();
        		String vet_name = FullName.getText();
        		Date vet_bdate = (Date)BirthDate.getValue();
        		String vet_serial_id = serial_id.getText();
        		double vet_salary = Double.parseDouble(salary.getText());

        		int vet_total_treatment_hours = Integer.parseInt(total_treatment_hours.getText());
           	 if(opt == 0) {

        		Veterinarian p = new Veterinarian(vet_id,vet_name,vet_bdate,vet_serial_id,vet_salary,vet_total_treatment_hours);
        		bh.addPerson(p);
    			ListPeopleModel.addElement(vet_id +" "+ vet_name  +" " +vet_bdate  +" " +vet_serial_id +" " + vet_salary);
        		JOptionPane.showMessageDialog(null, "Added Successfully!");
       		 	setVisible(false);
           	 }
        	}
        });
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(246, 232, 85, 21);
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
