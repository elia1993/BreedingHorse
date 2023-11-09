package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import content.Competition;
import content.Gambler;
import content.Horse;
import content.HorseInCompetition;
import content.Trainer;
import init.BreedingHorses;
import utils.HorseInCompetitionByFinishingTime;
import utils.Rank;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTable;

public class HorsesCompetitions extends JFrame {
	
	private JFrame frame;
	private BreedingHorses bh = new BreedingHorses();
	private DefaultListModel<String> ListCompetition = new DefaultListModel<>();
	private JList<String> list_comp = new JList<>(ListCompetition);
	private String horse_id = "",horse_name = "";
	private DefaultListModel<String> ListHorseInCompetition = new DefaultListModel<>();
	private JList<String> list_horse_in_comp = new JList<>(ListHorseInCompetition);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BreedingHorses bh = new BreedingHorses();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HorsesCompetitions window = new HorsesCompetitions(bh);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public HorsesCompetitions(BreedingHorses bh) {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame = new JFrame("Manage Horses In Competitions");
		frame.setBounds(100, 100, 950, 550);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		JPanel p1=new JPanel();
	    JTabbedPane tp=new JTabbedPane();  
	    tp.setBounds(10,10,920,450);  
	    tp.add("Horses Competitions",p1);
	    p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
	    
	    JLayeredPane layeredPane = new JLayeredPane();
	    p1.add(layeredPane);
	    
	    JButton competition_registration = new JButton("Competition Registration");
	    competition_registration.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JFrame frame2 = new JFrame("Add Competitions");

	    		frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    		JTextField serialNum=new JTextField();
	    		serialNum.setBounds(91,50,123,20);  
	    		JTextField place_comp=new JTextField();  
	    		place_comp.setBounds(303,50,123,20);  
	    		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    		JFormattedTextField startDate = new JFormattedTextField(df);
	    		startDate.setText(" / / ");
	    		startDate.setBounds(91,93,123,20);  
	    		frame2.getContentPane().add(startDate);
	    		frame2.getContentPane().add(serialNum);
	    		frame2.getContentPane().add(place_comp);
	    		frame2.setSize(300,300);  
	    		frame2.getContentPane().setLayout(null);
	    		
	    		JLabel serialNumLabel = new JLabel("SerialNum");
	    		serialNumLabel.setBounds(10, 53, 60, 13);
	    	    frame2.getContentPane().add(serialNumLabel);
	    	        
	    	    JLabel place = new JLabel("Place");
	    	    place.setBounds(241, 53, 100, 13);
	    	    frame2.getContentPane().add(place);
	    	        
	    	    JLabel lblNewLabel_2 = new JLabel("Start Date");
	    	    lblNewLabel_2.setBounds(25, 96, 100, 13);
	    	    frame2.getContentPane().add(lblNewLabel_2);
	    	        
	    	
	    
	    	    JTextField level = new JTextField();
	    	    level.setBounds(91, 143, 123, 20);
	    	    frame2.getContentPane().add(level);
	    	        
	    	    JLabel level_label = new JLabel("Level");
	    	    level_label.setBounds(25, 146, 100, 13);
	    	    frame2.getContentPane().add(level_label);
	    	    
	    	    JLabel lblNewLabel_5 = new JLabel("Rank");
	    	    lblNewLabel_5.setBounds(253, 146, 100, 13);
	    	    frame2.getContentPane().add(lblNewLabel_5);
	    	    utils.Type type[] = {utils.Type.BARELL,utils.Type.CROSS_COUNTRY,utils.Type.SHOW_JUMPING,utils.Type.TROT,utils.Type.GALLOP} ;       
	    	        
	    	    JComboBox comboBox = new JComboBox(type);
	    	    comboBox.setBounds(303, 142, 123, 21);
	    	    frame2.getContentPane().add(comboBox);
	    	    frame2.setVisible(true);
	    	    DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
	            listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
	            comboBox.setRenderer(listRenderer);

	            
	    	   
	    	       
	    	    JButton btnNewButton = new JButton("OK");
	            btnNewButton.setBounds(231, 232, 85, 21);
	            frame2.getContentPane().add(btnNewButton);

	            btnNewButton.addMouseListener(new MouseAdapter() {
	         	@Override
	            	public void mouseClicked(MouseEvent e) {
	              	 int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Add","add",JOptionPane.YES_NO_OPTION);
	              	int serial = Integer.parseInt(serialNum.getText());
	        		String place = place_comp.getText();
	        		int comp_type = comboBox.getSelectedIndex();
	        		Date date =(Date)startDate.getValue();
	        		short lvl = Short.valueOf(level.getText());
	            	 if(opt == 0 && place != null && serial != 0 && date != null ) {
	            		 Competition comp = new Competition(serial, place, utils.Type.values()[comp_type], date, lvl);
	            		 ListCompetition.addElement(serial +" "+ place  +" " +utils.Type.values()[comp_type]  +" " +date +" " + lvl);
	        			JOptionPane.showMessageDialog(null, "Added Successfully!");
	            		 frame2.setVisible(false);
	            	}
	            	 else {
	            		 JOptionPane.showMessageDialog(null,"Please Fill in All Details", "Details Are Missing",
	        				        JOptionPane.WARNING_MESSAGE);
	            	 }
	         	}
	            });
	            JButton btnCancel = new JButton("Cancel");
	            btnCancel.setBounds(341, 232, 85, 21);
	            frame2.getContentPane().add(btnCancel);
	            btnCancel.addMouseListener(new MouseAdapter() {
	            	@Override
	            	public void mouseClicked(MouseEvent e) {
	            		frame2.setVisible(false);
	            	}
	            });
	    		frame2.setBounds(150, 150, 450, 400);

	    		frame2.setVisible(true);

	    	}
	    });
	    competition_registration.setBounds(10, 10, 180, 21);
	    layeredPane.add(competition_registration);
	    
	 


	    JLabel active_comp = new JLabel("Active Competition");
	    active_comp.setHorizontalAlignment(SwingConstants.CENTER);
	    active_comp.setBounds(132, 152, 114, 13);
	    layeredPane.add(active_comp);
	    
	 
	 
	
	    
	    JLabel horse_comp = new JLabel("Horse Competition");
	    horse_comp.setHorizontalAlignment(SwingConstants.CENTER);
	    horse_comp.setBounds(678, 152, 114, 13);
	    layeredPane.add(horse_comp);
		
	    add(new JScrollPane(list_horse_in_comp));
	    list_horse_in_comp.setBounds(483, 179, 422, 234);
	    layeredPane.add(list_horse_in_comp);
	    
	    add(new JScrollPane(list_comp));
	    list_comp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list_comp.setBounds(10, 175, 349, 238);
	    layeredPane.add(list_comp);
	    
		Object col[] = {"identifierSerial","nickName"};
		JTable table = new JTable(bh.printAllHorses(),col);
		table.setBounds(20, 41, 885, 100);
	    layeredPane.add(table);
	    table.setAutoCreateRowSorter(true);
	    TableRowSorter<?> sorter = (TableRowSorter<?>) table.getRowSorter();
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    ListSelectionModel model = table.getSelectionModel();
	    model.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					return;
				}
				if(model.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "No selection");
				}
				else {
					String compititions = "";
					int selectedRow = model.getMinSelectionIndex();
					horse_id =  table.getValueAt(selectedRow, 0).toString();
					horse_name =  table.getValueAt(selectedRow, 1).toString();
					
					
				}
			}
		});
	    
	    frame.getContentPane().add(tp);  
	    frame.getContentPane().setLayout(null); 
	    JButton register = new JButton("Register");
	    register.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Add This Horse to This Competition","Add",JOptionPane.YES_NO_OPTION);
	        	 if(opt == 0) {

		        	 DefaultListModel<String> model = (DefaultListModel<String>) list_comp.getModel();
		        	 int selectedIndex = list_comp.getSelectedIndex();
		        	 List<String> list1 = list_comp.getSelectedValuesList();
		        	 String[] selectedVal = list1.get(selectedIndex).split(" ");
		        	 String serialNum = selectedVal[0];
		        	 String placeOfCompetitions = selectedVal[1];
		        	 String type = selectedVal[2];
		        	 String date = selectedVal[3];
		        	 String level = selectedVal[9];
		        	 bh.addHorseToCompetition(horse_id, horse_name, Integer.parseInt(serialNum), placeOfCompetitions, utils.Type.valueOf(type), null, Short.parseShort(level));
		        	 JOptionPane.showMessageDialog(null, "Added Successfully!" );
	          
	        	    }
	         }	 
	        	 
	        
	      });	
	
	    register.setBounds(369, 234, 104, 21);
	    layeredPane.add(register);
	    JButton un_register = new JButton("UnRegister");
	    un_register.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 int opt = JOptionPane.showConfirmDialog(null, "Are You sure to Remove This Horse In This Competition","Remove",JOptionPane.YES_NO_OPTION);
	        	 if(opt == 0) {
	        	 DefaultListModel<String> model = (DefaultListModel<String>) list_comp.getModel();
	        	 int selectedIndex = list_comp.getSelectedIndex();
	        	 List<String> list1 = list_comp.getSelectedValuesList();
	        	 String[] selectedVal = list1.get(selectedIndex).split(" ");
	        	 String serialNum = selectedVal[0];
	        	 String type = selectedVal[2];
	        	 bh.removeHorseFromCompetition(horse_id,horse_name,Integer.parseInt(serialNum),utils.Type.valueOf(type));

	        	 JOptionPane.showMessageDialog(null, "Removed Successfully!" );
	        	    }
	    	}
	    });
	    un_register.setBounds(369, 312, 104, 21);
	    layeredPane.add(un_register);
	    JButton cancel_btn = new JButton("Cancel");
	    cancel_btn.setBounds(823, 470, 85, 21);
	    frame.getContentPane().add(cancel_btn);
	    cancel_btn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		frame.setVisible(false);
        	}
        });
	
	    		
	    
	    JButton ok_btn = new JButton("Ok");
	    ok_btn.setBounds(716, 470, 85, 21);
	    frame.getContentPane().add(ok_btn);
	    
	    
	}
}
