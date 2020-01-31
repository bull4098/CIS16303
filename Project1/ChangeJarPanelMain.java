package Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;


public class ChangeJarPanelMain extends JPanel {

	private JButton mutateButton;
	private JMenuItem quitItem;
	private JMenuItem suspendItem;

	public static ChangeJar staticJar = new ChangeJar(100,2,3,4);
	NumberFormat fmt = NumberFormat.getCurrencyInstance();

	public ChangeJarPanelMain (JMenuItem quitItem, JMenuItem suspendItem) {

		JPanel panel = new JPanel();
		panel.add(new ChangeJarPanel());
		panel.add(new ChangeJarPanel());
		panel.add(new ChangeJarPanel());
		add(panel);

		mutateButton = new JButton("Mutation On");
		add(mutateButton);

		add(new JLabel("Static Jar: " + fmt.format(staticJar.getAmount())));


		this.quitItem = quitItem;
		this.suspendItem = suspendItem;

		quitItem.addActionListener(new Mylistener());
		suspendItem.addActionListener(new Mylistener());
		mutateButton.addActionListener(new Mylistener());
	}

	private class Mylistener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == quitItem){
				System.exit(1);
			}
			if(e.getSource() == suspendItem){
				ChangeJar.mutate(suspendItem.isSelected());
			}
			if(e.getSource() == mutateButton){
				ChangeJar.mutate(!ChangeJar.getMutationStatus());
				if(ChangeJar.getMutationStatus())
					mutateButton.setText("Mutation On");
				else
					mutateButton.setText("Mutation Off");
			}
		}
	}
}
