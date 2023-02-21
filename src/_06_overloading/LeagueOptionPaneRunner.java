package _06_overloading;

import java.awt.Color;

import javax.swing.JPanel;

public class LeagueOptionPaneRunner {
	public static void main(String[] args) {
		LeagueOptionPane.showMessageDialog("Hi Jaden");
		LeagueOptionPane.showMessageDialog("Hi Daniel", "lol");
		JPanel p = LeagueOptionPane.showMessageDialog("hello", "qwertyuiop", "leagueDark.png");
		LeagueOptionPane.showMessageDialog("goodbye", "no title", "leagueDark.png", Color.CYAN);
		p.setBackground(Color.YELLOW);
	}
}
