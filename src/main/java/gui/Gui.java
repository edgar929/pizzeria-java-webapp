package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Client;
import model.PizzaConfig;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class Gui extends JFrame
{

	private JPanel contentPane;
	private JTextField txt_basePrice;
    private double basePrice;
    JComboBox comboBox = new JComboBox();
    ArrayList<String> pizzerias = new ArrayList<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try
				{
					Client client = new Client("localhost", 1111);
					Gui frame = new Gui(client);
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	public Gui(Client client)
	{
		populateComboBox(client);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txt_basePrice = new JTextField();
		txt_basePrice.setBounds(159, 48, 125, 20);
		panel.add(txt_basePrice);
		txt_basePrice.setColumns(10);
		
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    basePrice= Double.parseDouble(txt_basePrice.getText());
				Boolean isUpdated = client.updateBasePrice((String) comboBox.getSelectedItem(), basePrice);
				if (isUpdated)
				    JOptionPane.showMessageDialog(panel, " Base price updated successfully");
			}
		});
		btnNewButton.setBounds(294, 47, 100, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("update base price:");
		lblNewLabel.setBounds(159, 32, 157, 14);
		panel.add(lblNewLabel);

		comboBox.setBounds(24, 45, 125, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Available pizzerias");
		lblNewLabel_1.setBounds(24, 26, 125, 14);
		panel.add(lblNewLabel_1);
		
		JLabel pizzaDetail = new JLabel("");
		pizzaDetail.setBounds(24, 100, 260, 39);
		panel.add(pizzaDetail);
		
		JButton btn_refresh = new JButton("Refresh");
		btn_refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBox.removeAllItems();
                pizzerias = client.availablePizzerias();
                for (String string : pizzerias)
                {
                    comboBox.addItem(string);
                }
            }
          });
		btn_refresh.setBounds(294, 115, 100, 23);
		panel.add(btn_refresh);
		
		JButton btnNewButton_1 = new JButton("PRINT");
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       PizzaConfig pizza =  client.printPizzeria((String) comboBox.getSelectedItem());
		       
		       pizzaDetail.setText("pizzeria: "+pizza.getName()+", "+"base price: "+pizza.getBasePrice());
		    }
		});
		btnNewButton_1.setBounds(293, 81, 100, 23);
		panel.add(btnNewButton_1);
	}
	
	public void populateComboBox(Client client)
	{
	    comboBox.removeAllItems();
		pizzerias = client.availablePizzerias();
		for (String string : pizzerias)
		{
			comboBox.addItem(string);
		}
	}
}

