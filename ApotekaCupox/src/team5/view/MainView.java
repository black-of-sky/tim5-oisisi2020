package team5.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import team5.controller.UserController;
import team5.model.User;
import team5.view.tables.TableFactory;

public class MainView extends JPanel {
	
	public MainView() {
	//	setLayout(new GridBagLayout());
		JPanel toolbar=new JPanel();
		setLayout(new BorderLayout());
		GridBagConstraints c=new GridBagConstraints();
		c.weightx=c.weighty=1;
		for (int i = 0; i < 10; i++) {
			c.gridx++;
			c.gridy++;
	//		add(javax.swing.Box.createGlue(), c);
		}


		toolbar.setBackground(new Color(255,20,48));
		
		JButton b=new JButton("logout");
		toolbar.add(b);
		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserController.getInstance().logout();
				
			}
		});
		
		JButton ab=new JButton("reg");
		toolbar.add(ab);
	ab.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddUser().setVisible(true);
				
			}
		});
		
		
		c.fill=GridBagConstraints.BOTH;
		
		c.gridx=c.gridy=0;
		c.gridheight=3;
		c.gridwidth=10;
	//	add(toolbar,c);
		   
		
		c.gridx=0;
		c.gridy=3;
		c.gridheight=7;
		JPanel tableview=new JPanel();
		tableview.setBackground(new Color(5,20,48));
		tableview.setLayout(new BorderLayout());
		tableview.setBorder(new EmptyBorder(10, 10, 10, 10));

		tableview.add((new JScrollPane(TableFactory.getTable(User.class))));
		//add(tableview,c);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                toolbar, tableview);
		splitPane.setDividerSize(0);
		
		int h=(MainFrame.getInstance().getHeight());
		System.out.println(h);
	
	
		add(splitPane);
		splitPane.setDividerLocation(120);
		splitPane.setResizeWeight(0.1);
        splitPane.setEnabled(false);
	}

}
