package Project1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ChangeJarPanelMain extends JPanel {

	private JButton mutateButton;
	private JLabel mutateOnOff;
	private JMenuItem quitItem;
	private JMenuItem suspendItem;

	public ChangeJarPanelMain (JMenuItem quitItem, JMenuItem suspendItem) {

		JPanel panel = new JPanel();
		panel.add(new ChangeJarPanel());
		panel.add(new ChangeJarPanel());
		panel.add(new ChangeJarPanel());
		add(panel);

		mutateButton = new JButton("Mutation on/off Switch");
		add(mutateButton);

		add(new JLabel("Mutation: "));
		mutateOnOff = new JLabel();
		add(mutateOnOff);
		if(ChangeJar.getMutationStatus())
			mutateOnOff.setText("On");
		else
			mutateOnOff.setText("Off");

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
					mutateOnOff.setText("On");
				else
					mutateOnOff.setText("Off");
			}
		}

	}
}
