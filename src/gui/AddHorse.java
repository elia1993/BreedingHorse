package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import content.Horse;
import content.ValidateInput;
import content.Veterinarian;
import init.BreedingHorses;
import utils.Genre;
import utils.Rank;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class AddHorse extends JFrame {

	private JPanel contentPane;
	private JTextField serial;
	private JLabel lblNewLabel;
	private JTextField NickName;
	private JLabel lblNewLabel_1;
	private JFormattedTextField birthDate;
	private JLabel lblNewLabel_2;
	private JTextField weight;
	private JLabel lblNewLabel_3;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton1;
	private JLabel lblNewLabel_4;
	private JTextField color;
	private JLabel lblNewLabel_6;
	private JTextField revenue;
	private JLabel lblNewLabel_7;
	private JTextField textField;
	private JLabel lblNewLabel_8;
	private JButton cancel_button;
	private JButton ok_button_1;
	private JTextField textField_1;
	private JLabel price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddHorse window = new AddHorse(bh);
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
	public AddHorse(BreedingHorses bh) {
		super("Add Horse");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblNewLabel = new JLabel("Serial");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		serial = new JTextField();
		GridBagConstraints gbc_serial = new GridBagConstraints();
		gbc_serial.anchor = GridBagConstraints.WEST;
		gbc_serial.insets = new Insets(0, 0, 5, 5);
		gbc_serial.gridx = 1;
		gbc_serial.gridy = 0;
		contentPane.add(serial, gbc_serial);
		serial.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Color");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 0;
		contentPane.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		color = new JTextField();
		GridBagConstraints gbc_Color = new GridBagConstraints();
		gbc_Color.anchor = GridBagConstraints.WEST;
		gbc_Color.insets = new Insets(0, 0, 5, 5);
		gbc_Color.gridx = 4;
		gbc_Color.gridy = 0;
		contentPane.add(color, gbc_Color);
		color.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Nick Name");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		NickName = new JTextField();
		GridBagConstraints gbc_NickName = new GridBagConstraints();
		gbc_NickName.anchor = GridBagConstraints.WEST;
		gbc_NickName.insets = new Insets(0, 0, 5, 5);
		gbc_NickName.gridx = 1;
		gbc_NickName.gridy = 1;
		contentPane.add(NickName, gbc_NickName);
		NickName.setColumns(10);
		
		  JLabel lblNewLabel_5 = new JLabel("Genre");
		  lblNewLabel_5.setBounds(253, 146, 100, 13);
		  GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		  gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		  gbc_lblNewLabel_5.gridx = 3;
		  gbc_lblNewLabel_5.gridy = 1;
		  contentPane.add(lblNewLabel_5, gbc_lblNewLabel_5);
		  
		  Genre genre[]= {Genre.STANDARDBRED,Genre.THOROUGHBRED,Genre.APPALOOSA,Genre.ARABIAN,Genre.PAINT,Genre.CANADIAN
				  ,Genre.QUARTER,Genre.HANOVERIAN,Genre.PUREBRED_SPANISH,Genre.IRISH_HUNTER,Genre.FRIESIAN,Genre.HACKNEY,Genre.UNKNOWN};        
	        contentPane.setVisible(true);
	        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
	        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
	      
		JComboBox comboBox = new JComboBox(genre);
		comboBox.setBounds(303, 142, 123, 21);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);
		comboBox.setRenderer(listRenderer);
		
		lblNewLabel_2 = new JLabel("birthDate");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		birthDate = new JFormattedTextField(df);
		birthDate.setBounds(91,93,123,20);   
		GridBagConstraints gbc_birthDate = new GridBagConstraints();
		gbc_birthDate.anchor = GridBagConstraints.WEST;
		gbc_birthDate.insets = new Insets(0, 0, 5, 5);
		gbc_birthDate.gridx = 1;
		gbc_birthDate.gridy = 2;
		birthDate.setColumns(10);
        contentPane.add(birthDate,gbc_birthDate);

		/*
		birthDate = new JFormattedTextField();
		GridBagConstraints gbc_birthDate = new GridBagConstraints();
		gbc_birthDate.anchor = GridBagConstraints.WEST;
		gbc_birthDate.insets = new Insets(0, 0, 5, 5);
		gbc_birthDate.gridx = 1;
		gbc_birthDate.gridy = 2;
		contentPane.add(birthDate, gbc_birthDate);
		birthDate.setColumns(10);
		*/
		lblNewLabel_7 = new JLabel("Revenue");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.gridx = 3;
		gbc_lblNewLabel_7.gridy = 2;
		contentPane.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		revenue = new JTextField();
		GridBagConstraints gbc_Revenue = new GridBagConstraints();
		gbc_Revenue.anchor = GridBagConstraints.WEST;
		gbc_Revenue.insets = new Insets(0, 0, 5, 5);
		gbc_Revenue.gridx = 4;
		gbc_Revenue.gridy = 2;
		contentPane.add(revenue, gbc_Revenue);
		revenue.setColumns(10);
		
		lblNewLabel_8 = new JLabel("TotalParticipatesTimes");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.gridx = 4;
		gbc_lblNewLabel_8.gridy = 3;
		contentPane.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		lblNewLabel_3 = new JLabel("Weight");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 5;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		weight = new JTextField();
		GridBagConstraints gbc_weight = new GridBagConstraints();
		gbc_weight.anchor = GridBagConstraints.WEST;
		gbc_weight.insets = new Insets(0, 0, 5, 5);
		gbc_weight.gridx = 1;
		gbc_weight.gridy = 5;
		contentPane.add(weight, gbc_weight);
		weight.setColumns(10);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 5;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Gender");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 6;
		contentPane.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		rdbtnNewRadioButton = new JRadioButton();
		rdbtnNewRadioButton.setText("Male");
		rdbtnNewRadioButton.setToolTipText("");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 1;
		gbc_rdbtnNewRadioButton.gridy = 6;
		contentPane.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

		rdbtnNewRadioButton1 = new JRadioButton();
		rdbtnNewRadioButton1.setText("Female");
		rdbtnNewRadioButton1.setToolTipText("");
		GridBagConstraints gbc_rdbtnNewRadioButton1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton1.anchor = GridBagConstraints.EAST;
		gbc_rdbtnNewRadioButton1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton1.gridx = 1;
		gbc_rdbtnNewRadioButton1.gridy = 6;
		contentPane.add(rdbtnNewRadioButton1, gbc_rdbtnNewRadioButton1);
		
		// create logical relationship between JRadioButtons
		 ButtonGroup radioGroup = new ButtonGroup(); // create ButtonGroup
		 radioGroup.add( rdbtnNewRadioButton ); // add Male to group
		 radioGroup.add( rdbtnNewRadioButton1 ); // add Female to group		 	  
	  	       			 
		 price = new JLabel("Price");
		 price.setHorizontalAlignment(SwingConstants.LEFT);
		 GridBagConstraints gbc_price = new GridBagConstraints();
		 gbc_price.insets = new Insets(0, 0, 5, 5);
		 gbc_price.gridx = 3;
		 gbc_price.gridy = 6;
		 contentPane.add(price, gbc_price);
		 
		 textField_1 = new JTextField();
		 GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		 gbc_textField_1.anchor = GridBagConstraints.WEST;
		 gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		 gbc_textField_1.gridx = 4;
		 gbc_textField_1.gridy = 6;
		 contentPane.add(textField_1, gbc_textField_1);
		 textField_1.setColumns(10);
		 
		 ok_button_1 = new JButton("Ok");
		 GridBagConstraints gbc_ok_button_1 = new GridBagConstraints();
		 gbc_ok_button_1.insets = new Insets(0, 0, 5, 5);
		 gbc_ok_button_1.gridx = 1;
		 gbc_ok_button_1.gridy = 9;
		 contentPane.add(ok_button_1, gbc_ok_button_1);
		 ok_button_1.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	             	 int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Add","add",JOptionPane.YES_NO_OPTION);
	        		String horse_serial = serial.getText();
	        		String horse_nickname = NickName.getText();
	        		Date horse_bdate = (Date)birthDate.getValue();
	        		double horse_weight = Double.parseDouble(weight.getText());
	        		char horse_gender = '1';
	        		   if (rdbtnNewRadioButton.isSelected()) {
	        				  
	        			   horse_gender = 'm';
		                }
		  
		                else if (rdbtnNewRadioButton1.isSelected()) {
		  
		                	horse_gender = 'f';
		                }
	        		Color horse_color = color.getCaretColor();
	        		double horse_price = Double.parseDouble(textField_1.getText());
	        		int Horse_genre = comboBox.getSelectedIndex();
	        		double horse_revenue = Double.parseDouble(revenue.getText());
	        		int horse_totalParticipatesTimes = Integer.parseInt(textField.getText());
	        		if(!ValidateInput.validate_addHorseName(horse_nickname)) {
	        			 JOptionPane.showMessageDialog(null,"The first letter should Be Capital And Only Characters", "Invalid Name Input",
	        				        JOptionPane.WARNING_MESSAGE);
	        			 opt = 1;
	        			 }
	           	 if(opt == 0) {

	        		bh.addHorse(horse_serial,horse_nickname,horse_bdate,horse_weight,horse_gender,horse_color,horse_price
	        				,Genre.values()[Horse_genre],horse_revenue,horse_totalParticipatesTimes);
	        		 HorseFrame at = new HorseFrame(bh);
		        	 at.setVisible(true);
	    			JOptionPane.showMessageDialog(null, "Added Successfully!");
	        		 setVisible(false);
	           	 }
	        		
	        	}
	        });
		 cancel_button = new JButton("Cancel");
		 GridBagConstraints gbc_cancel_button = new GridBagConstraints();
		 gbc_cancel_button.insets = new Insets(0, 0, 5, 5);
		 gbc_cancel_button.gridx = 4;
		 gbc_cancel_button.gridy = 9;
		 contentPane.add(cancel_button, gbc_cancel_button);
		 cancel_button.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		setVisible(false);
	        	}
	        });
		
		 
		
		 
		
	}

}
