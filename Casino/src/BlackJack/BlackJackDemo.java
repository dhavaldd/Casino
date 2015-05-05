package BlackJack;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import ClientServer.Client;
import ClientServer.Server;




public class BlackJackDemo extends JFrame {

	int client = 0;
	//int noofPlayers;
	String line = null;
	private JPanel contentPane, panel;
	private ArrayList<ImageIcon> slotImages = new ArrayList<ImageIcon>();
	JLabel lblDealerCard1, lblDealerCard2;
	private JLabel lblTotal, lblDealer;
	private JLabel textTotal;
	private JPanel panel_1;
	private JLabel lblPlayer1;
	private JLabel lblPlayer1total, textPlayer1Total;
	private JLabel lblPlayer1card1;
	public JLabel lblPlayer1card2, lblPlayer1card3;
	public  Deck d;
	private JButton btnStayPlayer1, btnHitPlayer1, btnRefresh;
	private JLabel lblResultPlayer1;
	private JLabel lblDealerCard3;
	public static BlackJackDemo frame;
	public static String playerName;
	private JLabel lblCreditPlayer1;
	private JLabel lblAmountPlayer1;
	String players = "Players.txt";
	BlackJackPlayer dealer, player1;

	boolean once = true;
	

	
	  
	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		playerName = args;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new BlackJackDemo();
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
	public BlackJackDemo() {

		d= new Deck();
		d.shuffle();
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 644, 692);
		contentPane =new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setLayout(null);
		
		
		setDealerVariables();//only once 
		setPlayer1Variables();//only once
		setDealerPanel();
		setDealerCard();
		
		setPlayer1Panel();
		setPlayer1Card();
		//btnHitPlayer2.setEnabled(false);
		//btnStayPlayer2.setEnabled(false);
		
	}


	private void setPlayer1Variables() {
		// TODO Auto-generated method stub
		player1 = new BlackJackPlayer();
		panel_1 = new JPanel();
		panel_1.setBounds(98, 0, 102, 653);
		panel_1.setBackground(new Color(0, 128, 0));
		contentPane.add(panel_1);
		lblAmountPlayer1 = new JLabel("Amount");
		lblAmountPlayer1.setText(100+"");
		lblPlayer1 = new JLabel(playerName);
		player1.name = playerName;
		lblPlayer1total = new JLabel("Total");
		lblPlayer1card1 = new JLabel("Player1Card1");
		lblPlayer1card2 = new JLabel("Player1Card2");
		lblPlayer1card3 = new JLabel("Player1Card3");
		textPlayer1Total = new JLabel("0");
		btnHitPlayer1 = new JButton("Hit");
		btnStayPlayer1 = new JButton("Stay");
		lblResultPlayer1 = new JLabel("ResultPlayer1");
		lblCreditPlayer1 = new JLabel("Credit");
		btnHitPlayer1.addActionListener(new HitHandler());
		btnStayPlayer1.addActionListener(new StayHandler());
	}

	private void setDealerVariables() {
		// TODO Auto-generated method stub
	
		dealer = new BlackJackPlayer();
		dealer.name = "Dealer";
		panel = new JPanel();
		lblDealer = new JLabel("Dealer");
		lblDealerCard1 = new JLabel("Card1");
		lblDealerCard2 = new JLabel("Card2");
		lblTotal = new JLabel("Total");
		textTotal = new JLabel("0");
		btnRefresh = new JButton("Refresh");
		lblDealerCard3 = new JLabel("Card3");
		
	}

	private void setPlayer1Panel() {
		// TODO Auto-generated method stub
		
		lblResultPlayer1.setVisible(false);
		
		
		btnHitPlayer1.setEnabled(true);
		btnStayPlayer1.setEnabled(true);
		lblPlayer1card3.setVisible(false);
		textPlayer1Total.setText(0+"");
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(19)
							.addComponent(lblPlayer1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPlayer1total)
							.addGap(18)
							.addComponent(textPlayer1Total, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnHitPlayer1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnStayPlayer1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(20)
							.addComponent(lblResultPlayer1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPlayer1card3, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCreditPlayer1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAmountPlayer1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPlayer1card1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPlayer1card2)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPlayer1)
					.addGap(18)
					.addComponent(lblPlayer1card1)
					.addGap(18)
					.addComponent(lblPlayer1card2)
					.addGap(8)
					.addComponent(lblPlayer1card3, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addGap(65)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlayer1total)
						.addComponent(textPlayer1Total))
					.addGap(18)
					.addComponent(btnHitPlayer1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStayPlayer1)
					.addGap(18)
					.addComponent(lblResultPlayer1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCreditPlayer1)
						.addComponent(lblAmountPlayer1))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
	}

	private void setDealerPanel() {
		// TODO Auto-generated method stub
		
		panel.setBounds(0, 0, 88, 653);
		panel.setBackground(new Color(0, 128, 0));
		contentPane.add(panel);
		//panel.setBackground(new Color(0, 128, 0));
		
		
		
		btnRefresh.addActionListener(new Refresh());
		
		
		
		lblDealerCard2.setVisible(false);
		lblDealerCard3.setVisible(false);
		textTotal.setVisible(false);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addComponent(lblDealer))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDealerCard1))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDealerCard2))
						.addComponent(btnRefresh)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTotal)
							.addGap(8)
							.addComponent(textTotal, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDealerCard3, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDealer)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDealerCard1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDealerCard2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDealerCard3, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotal)
						.addComponent(textTotal))
					.addGap(18)
					.addComponent(btnRefresh)
					.addGap(157))
		);
		panel.setLayout(gl_panel);
		
	}

	private int calculateTotal(int i) {
		String str = Card.RANK_SYMBOLS[i];
		if(str.length() <=2 )
		{
			return Integer.parseInt(str);
		}
		else if(str.equals("ace")) 
		{
			return 11;
		}
		else 
			return 10;
		
	}

	private void setDealerCard() {
		// TODO Auto-generated method stub
		int sum = 0;
		int i = randInt();
		lblDealerCard1.setIcon(d.cards[i].slotImage.get(0));
		sum += calculateTotal(d.cards[i].getRank());
		dealer.rankCard1 = d.cards[i].getRank();
		dealer.suitCard1 = d.cards[i].getSuit();
		
		i = randInt();
		lblDealerCard2.setIcon(d.cards[i].slotImage.get(0));
		sum += calculateTotal(d.cards[i].getRank());
		dealer.rankCard2 = d.cards[i].getRank();
		dealer.suitCard2 = d.cards[i].getSuit();
		textTotal.setText(sum+"");
   }
	
	private void setPlayer1Card() {
		// TODO Auto-generated method stub
		int sum = 0;
		int i = randInt();
		lblPlayer1card1.setIcon(d.cards[i].slotImage.get(0));
		sum += calculateTotal(d.cards[i].getRank());
		player1.rankCard1 = d.cards[i].getRank();
		player1.suitCard1 = d.cards[i].getSuit();
		
		i = randInt();
        lblPlayer1card2.setIcon(d.cards[i].slotImage.get(0));
        sum += calculateTotal(d.cards[i].getRank());
        player1.rankCard2 = d.cards[i].getRank();
		player1.suitCard2 = d.cards[i].getSuit();
        
       textPlayer1Total.setText(sum+"");
	}
	

	
	  public ImageIcon createImageIcon(String path, String description) {
	        java.net.URL imgURL = getClass().getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL, description);
	            } else {
	            System.err.println("Couldn't find file: " + path);
	            return null;
	        }
	    }


	  class StayHandler implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			  hit();
			  result();
			  
		  }
	  }
	  
	  class StayHandler2 implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			  hit2();
			 
			  
		  }
	  }
	  
	  class HitHandler implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			  hit();
			  lblPlayer1card3.setVisible(true);
			  int i = randInt();
			  lblPlayer1card3.setIcon(d.cards[i].slotImage.get(0));
			  int sum = Integer.parseInt(textPlayer1Total.getText());
			  sum += calculateTotal(d.cards[i].getRank());
			  
			  player1.rankCard3 = d.cards[i].getRank();
			  player1.suitCard3 = d.cards[i].getSuit();
			  textPlayer1Total.setText(sum+"");
			  result();
			  
		  }
	  }
	  

	  
		public void hit() {
			// TODO Auto-generated method stub
			
			int i = randInt();
			  lblDealerCard3.setIcon(d.cards[i].slotImage.get(0));
			  int sum = Integer.parseInt(textTotal.getText());
			  sum += calculateTotal(d.cards[i].getRank());
			  dealer.rankCard3 = d.cards[i].getRank();
			  dealer.suitCard3 = d.cards[i].getSuit();
			  textTotal.setText(sum+"");
			 
		}
		public void hit2() {
			// TODO Auto-generated method stub
			
			int i = randInt();
			  lblDealerCard3.setIcon(d.cards[i].slotImage.get(0));
			  int sum = Integer.parseInt(textTotal.getText());
			  sum += calculateTotal(d.cards[i].getRank());
			  dealer.rankCard3 = d.cards[i].getRank();
			  dealer.suitCard3 = d.cards[i].getSuit();
			  textTotal.setText(sum+"");
			 
		}
	  
	  class Refresh implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			  //to start from here
			  //readFile(players);
			  
			  setDealerPanel();
			  setPlayer1Panel();
			 
			  setDealerCard();
			  setPlayer1Card();
			  
			  
				  
		  }
	  }
	  
	  public int randInt() {
	  	int min =0, max = 51;
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}



	public void result() {
		// TODO Auto-generated method stub

		lblDealerCard2.setVisible(true);
		lblDealerCard3.setVisible(true);
		textTotal.setVisible(true);
		int sumPlayer = Integer.parseInt(textPlayer1Total.getText()); 
		int sumDealer = Integer.parseInt(textTotal.getText());
		lblResultPlayer1.setVisible(true);
		if(sumPlayer==21){			
			lblResultPlayer1.setText("You Win");
			btnHitPlayer1.setEnabled(false);
			btnStayPlayer1.setEnabled(false);
		}
		else if(sumPlayer > 21){
			lblResultPlayer1.setText("You Lose");
			btnHitPlayer1.setEnabled(false);
			btnStayPlayer1.setEnabled(false);
		}
		else if(sumPlayer <= 21 && sumDealer < 21 && sumDealer > sumPlayer ){
			lblResultPlayer1.setText("You Lose");
			btnHitPlayer1.setEnabled(false);
			btnStayPlayer1.setEnabled(false);
		}
		else if(sumPlayer <= 21 && sumDealer < 21 && sumDealer < sumPlayer ){
			lblResultPlayer1.setText("You Win");
			btnHitPlayer1.setEnabled(false);
			btnStayPlayer1.setEnabled(false);
		}
		else if(sumPlayer <= 21 && sumDealer > 21 ){
			lblResultPlayer1.setText("You Win");
			btnHitPlayer1.setEnabled(false);
			btnStayPlayer1.setEnabled(false);
		}
		else if(sumPlayer <= 21 && sumDealer == sumPlayer ){
			lblResultPlayer1.setText("You Win");
			btnHitPlayer1.setEnabled(false);
			btnStayPlayer1.setEnabled(false);
		}
		else if(sumPlayer <= 21 && sumDealer == 21){
			lblResultPlayer1.setText("You Lose");
			btnHitPlayer1.setEnabled(false);
			btnStayPlayer1.setEnabled(false);
		}
		
		
		credit();
		
	}
	
	

	private void credit() {
		// TODO Auto-generated method stub
		
		int amt = Integer.parseInt(lblAmountPlayer1.getText());
		if(lblResultPlayer1.getText().equals("You Win")){
			amt = amt + 20;
			lblAmountPlayer1.setText(amt+"");
		}else{
			amt = amt - 10;
			lblAmountPlayer1.setText(amt+"");
			
		}
		player1.amount = amt;
		
	}
	
	
}
