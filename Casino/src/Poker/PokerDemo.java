package Poker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class PokerDemo extends JFrame {

	private static Deck d;
	private boolean bet = false;
	private int playerBalance = 100, botBalance = 100;
	private HashSet<Integer>cardRepeat = new HashSet<Integer>();
	private HashSet<Card>playerCards = new HashSet<Card>();
	private HashSet<Card>tableCards = new HashSet<Card>();
	private HashSet<Card>botCards = new HashSet<Card>();
	private JPanel contentPane, panel;
	public JLabel lblPlayerCard1, lblPlayerCard2, lblPlayerBalance, lblPlayerDealer;
	private JPanel panel_1;
	private JLabel lblTablecard2;
	private JLabel lblTablecard3;
	private JLabel lblTablecard1;
	private JLabel lblTablecard4;
	private JLabel lblTablecard5;
	private JPanel panel_2;
	private JLabel lblBotcard1;
	private JLabel lblBotcard2;
	private JLabel lblBotbalance;
	private JLabel lblBotDealer;
	private JLabel lblNewLabel;
	private JLabel lblPlayer;
	private JLabel lblPlayerblinds;
	private JLabel lblBotblinds;
	private JLabel lblPlayerblindamount;
	private JLabel lblBotblindamount;
	private String dealer = "Player";
	private JLabel lblPot;
	private JLabel lblNewLabel_1;
	private int checkNo = 0;
	private JLabel lblStatus;
	private JTextField textBet;
	private JButton btnOk, btnCheck, btnBet, btnFold;
	private JButton btnCancel, btnContinue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					d = new Deck();
					d.shuffle();
					PokerDemo frame = new PokerDemo();
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
	public PokerDemo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setBackground(new Color(0,128,0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setPlayerPanel();
		setPlayerInititalise();
		setTablePanel();
		setTableInitialise1();
		setBotPanel();
		setBotCard();
		setBlinds();
	}

	private void setBlinds() {
		// TODO Auto-generated method stub
		if(dealer.equals("Player")){
			lblPlayerDealer.setEnabled(true);
		    lblBotDealer.setEnabled(false);
			dealer = "Bot";
		}
		else{
			lblPlayerDealer.setEnabled(false);
		    lblBotDealer.setEnabled(true);
			dealer = "Player";
		}
		
		lblPlayerblindamount.setText("10");
		
		lblBotblindamount.setText("10");
		int playerBlind = Integer.parseInt(lblPlayerblindamount.getText());
		int botBlind = Integer.parseInt(lblBotblindamount.getText());
		int total = playerBlind+botBlind;
		
		int playerBalance = Integer.parseInt(lblPlayerBalance.getText());
		int botBalance = Integer.parseInt(lblBotbalance.getText());
		botBalance -= botBlind;
		playerBalance -= playerBlind;
		
		lblPlayerBalance.setText(playerBalance+"");
		lblBotbalance.setText(botBalance+""); 
		lblPot.setText(total+"");
	}

	private void setBotCard() {
		// TODO Auto-generated method stub
		int i = randInt();
		lblBotcard1.setIcon(d.cards[i].slotImage.get(0));
		lblBotcard1.setText("");
		botCards.add(d.cards[i]);
		
		i = randInt();
		lblBotcard2.setIcon(d.cards[i].slotImage.get(0));
		lblBotcard2.setText("");
		botCards.add(d.cards[i]);
		
		lblBotbalance.setText(botBalance+"");
		
		lblBotcard1.setVisible(false);
		lblBotcard2.setVisible(false);
	}

	private void setBotPanel() {
		// TODO Auto-generated method stub
		lblBotcard1 = new JLabel("BotCard1");
		lblBotcard1.setBounds(232, 5, 114, 148);
		panel_2.add(lblBotcard1);
		
		lblBotcard2 = new JLabel("BotCard2");
		lblBotcard2.setBounds(356, 5, 105, 148);
		panel_2.add(lblBotcard2);
		
		lblBotbalance = new JLabel("BotBalance");
		lblBotbalance.setBounds(489, 72, 46, 14);
		panel_2.add(lblBotbalance);
		
		lblBotDealer = new JLabel("Dealer");
		lblBotDealer.setBounds(151, 72, 46, 14);
		panel_2.add(lblBotDealer);
		
		lblNewLabel = new JLabel("Bot");
		lblNewLabel.setBounds(29, 11, 46, 14);
		panel_2.add(lblNewLabel);
		
		lblBotblinds = new JLabel("Bet");
		lblBotblinds.setBounds(489, 97, 72, 14);
		panel_2.add(lblBotblinds);
		
		lblBotblindamount = new JLabel("BotBlindAmount");
		lblBotblindamount.setBounds(571, 97, 46, 14);
		panel_2.add(lblBotblindamount);
	}

	private void setTableInitialise3() {
		// TODO Auto-generated method stub
		int i = randInt();
		lblTablecard5.setIcon(d.cards[i].slotImage.get(0));
		lblTablecard5.setText("");
		tableCards.add(d.cards[i]);
		
		textBet.setEnabled(false);
		btnCancel.setEnabled(false);
		btnOk.setEnabled(false);
		btnCheck.setEnabled(false);
		btnFold.setEnabled(false);
		btnBet.setEnabled(false);
		//result()
	}

	private void setTableInitialise2() {
		// TODO Auto-generated method stub
		int i = randInt();
		lblTablecard4.setIcon(d.cards[i].slotImage.get(0));
		lblTablecard4.setText("");
		tableCards.add(d.cards[i]);
		if(!bet)
			lblStatus.setText("Bot checked. Your Turn");
		else{
			
			String prevStatus = lblStatus.getText();
			lblStatus.setText(prevStatus+" Bot checked. Your Turn");
		}
	}

	private void setTableInitialise1() {
		// TODO Auto-generated method stub
		int i = randInt();
		lblTablecard1.setIcon(d.cards[i].slotImage.get(0));
		lblTablecard1.setText("");
		tableCards.add(d.cards[i]);
		
		i = randInt();
		lblTablecard2.setIcon(d.cards[i].slotImage.get(0));
		lblTablecard2.setText("");
		tableCards.add(d.cards[i]);
		
		i = randInt();
		lblTablecard3.setIcon(d.cards[i].slotImage.get(0));
		lblTablecard3.setText("");
		tableCards.add(d.cards[i]);
		
		checkNo = 0;
		if(!bet)
			lblStatus.setText("Bot checked. Your Turn");
		else{
			
			String prevStatus = lblStatus.getText();
			lblStatus.setText(prevStatus+" Bot checked. Your Turn");
		}
			
		bet = false;
	}

	private void setPlayerInititalise() {
		// TODO Auto-generated method stub
		int i = randInt();
		lblPlayerCard1.setIcon(d.cards[i].slotImage.get(0));
		lblPlayerCard1.setText("");
		playerCards.add(d.cards[i]);
		
		i = randInt();
		lblPlayerCard2.setIcon(d.cards[i].slotImage.get(0));
		lblPlayerCard2.setText("");
		playerCards.add(d.cards[i]);
		
		lblPlayerBalance.setText(playerBalance+"");
		
	}

	private void setPlayerPanel() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setBounds(0, 0, 753, 150);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(0,128,0));
		
		lblPlayerCard1 = new JLabel("PlayerCard1");
		lblPlayerCard1.setBounds(232, 5, 114, 148);
		panel.add(lblPlayerCard1);
		
		lblPlayerCard2 = new JLabel("PlayerCard2");
		lblPlayerCard2.setBounds(356, 5, 105, 148);
		panel.add(lblPlayerCard2);
		
		lblPlayerBalance = new JLabel("PlayerBalance");
		lblPlayerBalance.setBounds(489, 72, 46, 14);
		panel.add(lblPlayerBalance);
		
	    lblPlayerDealer = new JLabel("Dealer");
		lblPlayerDealer.setBounds(151, 72, 46, 14);
		panel.add(lblPlayerDealer);
		
		lblPlayer = new JLabel("Player");
		lblPlayer.setBounds(28, 31, 46, 14);
		panel.add(lblPlayer);
		
		lblPlayerblinds = new JLabel("Bet");
		lblPlayerblinds.setBounds(489, 99, 77, 14);
		panel.add(lblPlayerblinds);
		
		lblPlayerblindamount = new JLabel("PlayerBlindAmount");
		lblPlayerblindamount.setBounds(576, 99, 46, 14);
		panel.add(lblPlayerblindamount);
	}

	private void setTablePanel() {
		// TODO Auto-generated method stub
		panel_1 = new JPanel();
		panel_1.setBounds(0, 161, 753, 205);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 128, 0));
		
		lblTablecard2 = new JLabel("TableCard2");
		lblTablecard2.setBounds(232, 5, 114, 148);
		panel_1.add(lblTablecard2);
		
		lblTablecard3 = new JLabel("TableCard3");
		lblTablecard3.setBounds(356, 5, 105, 148);
		panel_1.add(lblTablecard3);
		
		lblTablecard1 = new JLabel("TableCard1");
		lblTablecard1.setBounds(108, 5, 114, 148);
		panel_1.add(lblTablecard1);
		
		lblTablecard4 = new JLabel("TableCard4");
		lblTablecard4.setBounds(464, 5, 105, 148);
		panel_1.add(lblTablecard4);
		
		lblTablecard5 = new JLabel("TableCard5");
		lblTablecard5.setBounds(573, 5, 105, 148);
		panel_1.add(lblTablecard5);
		
		btnCheck = new JButton("Check/Call");
		btnCheck.setBounds(282, 164, 97, 23);
		panel_1.add(btnCheck);
		btnCheck.addActionListener(new Check());
		
		btnBet = new JButton("Bet");
		btnBet.setBounds(666, 39, 61, 23);
		panel_1.add(btnBet);
		btnBet.addActionListener(new Bet());
		
		btnFold = new JButton("Fold");
		btnFold.setBounds(432, 164, 57, 23);
		panel_1.add(btnFold);
		
		btnContinue = new JButton("Continue");
		btnContinue.setBounds(84, 164, 89, 23);
		panel_1.add(btnContinue);
		btnContinue.addActionListener(new Continue());
		
		lblPot = new JLabel("Pot");
		lblPot.setBounds(606, 168, 46, 14);
		panel_1.add(lblPot);
		
		lblNewLabel_1 = new JLabel("Pot");
		lblNewLabel_1.setBounds(573, 168, 22, 14);
		panel_1.add(lblNewLabel_1);
		
		lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(84, 140, 625, 14);
		panel_1.add(lblStatus);
		
		textBet = new JTextField();
		textBet.setText("0");
		textBet.setBounds(666, 69, 61, 20);
		panel_1.add(textBet);
		textBet.setColumns(10);
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(666, 87, 61, 23);
		panel_1.add(btnOk);
		btnOk.addActionListener(new Ok());
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(666, 121, 77, 23);
		panel_1.add(btnCancel);
		btnCancel.addActionListener(new Cancel());
		
		textBet.setVisible(false);
		btnOk.setVisible(false);
		btnCancel.setVisible(false);
		
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 377, 753, 150);
		contentPane.add(panel_2);
		panel_2.setBackground(new Color(0, 128, 0));
		
	}
	
	  public int randInt() {
		  	int min =0, max = 51;
		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    if(!cardRepeat.add(randomNum))
		    	return randInt();
		    return randomNum;
		}
	  
	  class Check implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			  check();
		  }
	  }

	  private void check() {
			// TODO Auto-generated method stub
			if(checkNo == 0)
			  {
				setTableInitialise2();
				checkNo++;
					
			  }
			  else if(checkNo == 1)
			  {
				setTableInitialise3();
				checkNo++;
				lblBotcard1.setVisible(true);
				lblBotcard2.setVisible(true);
				result();
			  }
		}
	  
	  private void result() {
		// TODO Auto-generated method stub
		  Card botArray[] = new Card[7];
		  Card playerArray[] = new Card[7];
		  int b = 0, p = 0;
		  for (Card c : botCards) {
			   // System.out.println(c.toString()+ " " +c.hashCode());
			    botArray[b++] = c;
			}
		  System.out.println();
		  for (Card c : playerCards) {
			    //System.out.println(c.toString()+ " " +c.hashCode());
			    playerArray[p++] = c;
			}
		  System.out.println();
		  for (Card c : tableCards) {
			   // System.out.println(c.toString()+ " " +c.hashCode());
			    botArray[b++] = c;
			    playerArray[p++] = c;
			}
		  
		  Hand botHand = new Hand(botArray);
		  Hand playerHand = new Hand(playerArray);
		  
		 // System.out.println(botHand.toString());
		 // System.out.println(playerHand.toString());
		  
		  HandValue botHandValue = new HandValue(botHand);
		  HandValue playerHandValue = new HandValue(playerHand);
		  
		  /*System.out.println(bot.toString());
		  System.out.println(player.toString());*/
		  String status;
		  int pot, botBet, playerBet, playerBalance, botBalance;
		  
		  playerBalance = Integer.parseInt(lblPlayerBalance.getText());
		  botBalance = Integer.parseInt(lblBotbalance.getText());
		  pot = Integer.parseInt(lblPot.getText());
		  botBet = Integer.parseInt(lblBotblindamount.getText());
		  playerBet = Integer.parseInt(lblPlayerblindamount.getText());
		  pot = botBet +playerBet;
		  if(botHandValue.value > playerHandValue.value){
			  status = "Bot wins with "+botHandValue.type.getDescription() +" over Players " + playerHandValue.type.getDescription();			  
			  botBalance += pot;
		  }
		  else{
			  status = "Player wins with "+playerHandValue.type.getDescription() +" over Bots " + botHandValue.type.getDescription();
			  playerBalance +=  pot;
			 
		  }
		  
		  lblStatus.setText(status);
		  lblBotbalance.setText(botBalance+"");
		  lblPlayerBalance.setText(playerBalance+"");
		  lblPot.setText("0");
		  lblBotblindamount.setText("0");
		  lblPlayerblindamount.setText("0");
	}

	class Bet implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			textBet.setVisible(true);
			btnCancel.setVisible(true);
			btnOk.setVisible(true);
			btnCheck.setEnabled(false);
			btnFold.setEnabled(false);
			bet = true;
		  }
	  }
	  
	  class Cancel implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			textBet.setVisible(false);
			btnCancel.setVisible(false);
			btnOk.setVisible(false);
			btnCheck.setEnabled(true);
			btnFold.setEnabled(true);
		  }
	  }
	  
	  class Ok implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			int botBalance = Integer.parseInt(lblBotbalance.getText());
			int playerBalance = Integer.parseInt(lblPlayerBalance.getText());
			int bet = Integer.parseInt(textBet.getText());
			int pot = Integer.parseInt(lblPot.getText());
			int playerBet = Integer.parseInt(lblPlayerblindamount.getText());
			int botBet = Integer.parseInt(lblBotblindamount.getText());
			
			pot = pot + 2*bet;
			botBalance -= bet;
			playerBalance-= bet;
			playerBet += bet;
			botBet += bet;
			
			lblPlayerBalance.setText(playerBalance+"");
			lblBotbalance.setText(botBalance+"");
			lblPot.setText(pot+"");
			lblPlayerblindamount.setText(playerBet+"");
			lblBotblindamount.setText(botBet+"");
			
			textBet.setVisible(false);
			btnCancel.setVisible(false);
			btnOk.setVisible(false);
			btnCheck.setEnabled(true);
			btnFold.setEnabled(true);
			
			String status = "Player raised by "+ bet + " and Bot called "+bet +"." ;
			lblStatus.setText(status);
			check();
			
		  }
	  }
	  
	  class Continue implements ActionListener {
		  public void actionPerformed(ActionEvent event) {
			botCards.clear();
			tableCards.clear();
			playerCards.clear();
			cardRepeat.clear();
			//lblPlayerCard1.setIcon(I);
			setPlayerInititalise();
			setBlinds();
		  }
	  }
}
