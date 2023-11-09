package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import content.Gambler;
import content.Veterinarian;
import init.BreedingHorses;

public class AddGambler extends JFrame {

	private JPanel contentPane;
	JTextField id,FullName;  
    private JFormattedTextField BirthDate;
    private JTextField account;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGambler frame = new AddGambler(bh,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddGambler(BreedingHorses bh,DefaultListModel ListPeopleModel) {
		super("Add Gambler");
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
        
       
        
        account = new JTextField();
        account.setBounds(91, 143, 123, 20);
        contentPane.add(account);
        
        JLabel lblNewLabel_4 = new JLabel("Account");
        lblNewLabel_4.setBounds(25, 146, 100, 13);
        contentPane.add(lblNewLabel_4);
    
        
        JButton btnNewButton = new JButton("OK");
        btnNewButton.setBounds(151, 232, 85, 21);
        contentPane.add(btnNewButton);
        
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
             	int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Add","add",JOptionPane.YES_NO_OPTION);

        		String gambler_id = id.getText();
        		String gambler_name = FullName.getText();
        		Date gambler_bdate = (Date)BirthDate.getValue();
        		double gambler_account = Double.parseDouble(account.getText());
        		Gambler p = new Gambler(gambler_id,gambler_name,gambler_bdate,gambler_account);
              	 if(opt == 0) {
        		bh.addPerson(p);
    			ListPeopleModel.addElement(gambler_id +" "+ gambler_name  +" " +gambler_bdate  +" " +gambler_account);
    			JOptionPane.showMessageDialog(null, "Added Successfully!");
       		 	setVisible(false);
       		 	}
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
