import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Didnt_Playtest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Didnt_Playtest frame = new Didnt_Playtest();
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
	
	
	public interface playable {
		public void playCard(int player);
		public String getName();
	}
	
	ArrayList<playable> cards=new ArrayList<playable>();
	
	public class cardNone implements playable {
		public void playCard(int Player) {
			
		}
		public String getName() {
			return "nothing";
		}
	}
	
	public class cardPc implements playable {
		static final String name = "PC";
		static final String text = "Everybody wins";
		public void playCard(int Player) {
			playerWins(1);
			playerWins(2);
		}
		public String getName() {
			return name;
		}
	}
	
	public class cardILose implements playable {
		static final String name = "I Lose";
		static final String text = "You Lose";
		public void playCard(int Player) {
			playerLoses(Player);
		}
		public String getName() {
			return name;
		}
	}
	
	cardPc kaartPc = new cardPc();
	cardILose kaartILose = new cardILose();
	cardNone kaartNone = new cardNone();
	
	/* Variablelen aanmaken */
	 ArrayList<playable> player1Hand=new ArrayList<playable>();
	 ArrayList<playable> player2Hand=new ArrayList<playable>();
	 ArrayList<playable> library=new ArrayList<playable>(); 

	/* Alles predifineren */
	JLabel l1 = new JLabel("");
	JButton b1 = new JButton("PC");
	
	
	/* Basis Functies */
	public void Draw(int player, int amount) {
		int random;
		for (int i=0; i<amount; i++) {
			random = (int) (Math.random()*library.size());
			switch (player) {
				case 1: player1Hand.add(library.get(random));
				case 2: player2Hand.add(library.get(random));
			}
		}
	}
	
	public void dillHand() {
		for (int i=0; i<20; i++) {
			player1Hand.add(kaartNone);
			player2Hand.add(kaartNone);
		}
	}
	
	public void fillLibrary(int amount) {
		for (int i=0; i<amount; i++) {
			for (i=0; i<cards.size(); i++) {
				library.add(cards.get(i));
			}
		}
	}
	
	public void playerLoses(int player) {
		l1.setText(l1.getText() + "player " + player + " has lost the game.  ");
	}
	
	public void playerWins(int player) {
		l1.setText(l1.getText() + player + " has won the game.  ");
	}
	
	public void startOfGame(){
		fillLibrary(10);
	}
	
	public void runCard(String Name, int Player) {
		for (int i=0; i<cards.size(); i++) {
			playable testObject = cards.get(i);
			String name = testObject.getName();
			if (Name == name) {
				testObject.playCard(Player);
			}
		}
	}
	
	/* Put the Cards in the Deck */
	
	
	
	public Didnt_Playtest() {
		cards.add(kaartPc);
		cards.add(kaartILose);
		startOfGame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 895, 758);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runCard("PC", 1);
			}
		});
		b1.setBounds(10, 514, 125, 139);
		contentPane.add(b1);
		l1.setBounds(10, 21, 768, 74);
		contentPane.add(l1);
		
		
	
		
		
	}
}
