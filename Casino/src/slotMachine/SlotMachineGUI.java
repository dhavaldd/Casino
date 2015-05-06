
package slotMachine;

import java.awt.*;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.border.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlotMachineGUI {
    
    private JButton btnCredit, btnSpinSlot;
    private JFrame frame;
    private JLabel lblCredits, lblLost, lblResultThree, lblResultTwo, lblMoney, lblslot1, lblslot2, lblslot3, lblStatus, lblWon;
    private JPanel pannelSlots, pannelslot1, pannelslot2, pannelslot3;
    private JSeparator sepStats, sepStatsVertical, sepStatus;
    private int credits = 100, boughtCredits = 100, bet = 10, ResultThree, ResultTwo, lost;
    private double prizeMoney = 50.0, creditBuyout = 5.0, funds;
    private int slot1 = 7, slot2 = 7, slot3 = 7;
    private ArrayList<ImageIcon> slotImages = new ArrayList<ImageIcon>();
    private DecimalFormat df = new DecimalFormat("0.00");
    

    public SlotMachineGUI() {
      init();
    }
    
    private void init() {
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Slot Machine");
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setSize(220, 400);
        frame.setLocationRelativeTo(null);  
        
        pannelSlots = new JPanel();
        pannelSlots.setBorder(BorderFactory.createEtchedBorder());
        pannelSlots.setBackground(new Color(0, 122, 0));
        
        pannelslot1 = new JPanel();
        pannelslot1.setBackground(new Color(0, 255, 0));

        pannelslot2 = new JPanel();
        pannelslot2.setBackground(new Color(0, 255, 100));
        
        pannelslot3 = new JPanel();
        pannelslot3.setBackground(new java.awt.Color(0, 255, 0));
   
        //Loading Images
        slotImages.add(createImageIcon("images/card_35.png", "Jack of Hearts"));
        slotImages.add(createImageIcon("images/card_36.png", "Queen of Hearts"));
        slotImages.add(createImageIcon("images/card_37.png", "King of Hearts"));
        slotImages.add(createImageIcon("images/card_38.png", "Ace of Hearts"));
        slotImages.add(createImageIcon("images/card_back.png", "Card Back"));
        slotImages.add(createImageIcon("images/card_joker_black.png", "Joker"));
        slotImages.add(createImageIcon("images/card_placeholder.png", "Place Holder"));
        slotImages.add(createImageIcon("images/Seven.png", "Seven"));
        
        
        //Adding Fields
        lblslot1 = new JLabel();
        lblslot2 = new JLabel();
        lblslot3 = new JLabel();
        
        sepStats = new JSeparator();
        lblResultTwo = new JLabel();
        lblResultTwo.setText("Resulted Two: ");
        lblResultThree = new JLabel();
        lblResultThree.setText("Resulted Three: ");
        lblWon = new JLabel();
        lblWon.setText("Won: ");
        
        sepStatsVertical = new JSeparator();
        sepStatsVertical.setOrientation(SwingConstants.VERTICAL);
        lblCredits = new JLabel();
        lblCredits.setText("Credits: "+credits);
        lblMoney = new JLabel();
        lblMoney.setText("Money: $"+df.format(funds));
        lblLost = new JLabel();
        lblLost.setText("Lost: ");
        
        sepStatus = new JSeparator();
        lblStatus = new JLabel();
        lblStatus.setBackground(new Color(255, 255, 255));
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setText("!!Click SPIN to Start!!");
         
        lblslot1.setIcon(slotImages.get(slot1));
        lblslot2.setIcon(slotImages.get(slot2));
        lblslot3.setIcon(slotImages.get(slot3));
        
        //Adding Buttons
        btnSpinSlot = new JButton();
        btnSpinSlot.setBackground(new Color(0, 122, 0));
        btnSpinSlot.setText("Spin");
        btnSpinSlot.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        btnSpinSlot.setInheritsPopupMenu(true);
        btnSpinSlot.setMaximumSize(new Dimension(285, 50));
        btnSpinSlot.setMinimumSize(new Dimension(285, 50));
        btnSpinSlot.addActionListener(new SpinHandler());
        
        btnCredit = new JButton();
        btnCredit.setBackground(new Color(255, 0, 0));
        btnCredit.setText("Buy Credits");
        btnCredit.setInheritsPopupMenu(true);
        btnCredit.setMaximumSize(new Dimension(285, 50));
        btnCredit.setMinimumSize(new Dimension(285, 50));
        btnCredit.addActionListener(new BuyCreditsHandler());
        
        
        //Slots
        GroupLayout pannelSlotsLayout = new GroupLayout(pannelSlots);
        pannelSlots.setLayout(pannelSlotsLayout);
        pannelSlotsLayout.setHorizontalGroup(
        		pannelSlotsLayout.createParallelGroup()
        		.addGroup(pannelSlotsLayout.createSequentialGroup()
        				.addContainerGap()
        				.addComponent(pannelslot1.add(lblslot1))
        				.addGap(18, 18, 18)
        				.addComponent(pannelslot2.add(lblslot2))
        				.addGap(18, 18, 18)
        				.addComponent(pannelslot3.add(lblslot3))
        				.addGap(18, 18, 18)
        				.addContainerGap())
        		);
        
        pannelSlotsLayout.setVerticalGroup(
        pannelSlotsLayout.createParallelGroup()
        	.addGroup(pannelSlotsLayout.createSequentialGroup()
        		.addContainerGap()
        		.addGroup(pannelSlotsLayout.createParallelGroup()
        				.addComponent(pannelslot1.add(lblslot1))
        				.addComponent(pannelslot2.add(lblslot2))
        				.addComponent(pannelslot3.add(lblslot3)))
        				.addContainerGap())
        		);
        
        addComponet();
    }
   
    
  
    private void addComponet() {
        
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
        layout.createParallelGroup()
        	.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        				.addGroup(layout.createParallelGroup()
							.addGroup(layout.createParallelGroup()
									.addComponent(pannelSlots)
									.addComponent(btnSpinSlot)
									.addComponent(lblStatus)
									.addComponent(btnCredit)
									.addComponent(sepStats)
									.addGroup(layout.createSequentialGroup()
											.addGroup(layout.createParallelGroup()
													.addComponent(lblResultTwo)
													.addComponent(lblWon)
													.addComponent(lblResultThree))
													.addComponent(sepStatsVertical)
													.addGroup(layout.createParallelGroup()
															.addComponent(lblLost)
															.addComponent(lblCredits)
															.addComponent(lblMoney))))
															.addGroup(layout.createParallelGroup()
																	.addComponent(sepStatus)
																	))
					)
);
        
        layout.setVerticalGroup(
        layout.createParallelGroup()
        	.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(pannelSlots)
        			.addComponent(sepStats)
        			.addComponent(lblStatus)
        			.addComponent(sepStatus)
        			.addComponent(sepStats)
        			.addComponent(btnSpinSlot) 
        			.addComponent(sepStatus)
        			.addGroup(layout.createParallelGroup()
        					.addGroup(layout.createSequentialGroup()
        							.addComponent(lblWon)
        							.addComponent(lblResultTwo)
        							.addComponent(lblResultThree))
        							.addComponent(sepStatsVertical)
        							.addGroup(layout.createSequentialGroup()
        									.addComponent(lblLost)
        									.addComponent(lblCredits)
        									.addComponent(lblMoney))
        					)
        					.addComponent(btnCredit)
       
        			)
        		);
       
        frame.pack();
        
    }
    

    class SpinHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (funds < creditBuyout && credits < bet) {
                lblStatus.setText("GAME OVER");
                } else if ((credits - bet) >= 0) {
                genSlotNumbers(); 
                result();
                } else {
                lblStatus.setText("Bet is "+bet+" credits, purchase more with $10!");
            }
            if (funds < bet) {
                btnCredit.setBackground(new java.awt.Color(255, 0, 0));
                } else {
                btnCredit.setBackground(new java.awt.Color(50,255, 50));
            }
        }
    }
    class BuyCreditsHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	if (funds >= creditBuyout) {
                funds -= creditBuyout;
                lblMoney.setText("Money: $"+df.format(funds));
                credits += boughtCredits;
                lblCredits.setText("Credits: "+credits);
                lblStatus.setText("+"+boughtCredits+" credits purchased! -$"+df.format(creditBuyout));
                } else {
                lblStatus.setText("Insufficient $ to purchase credits!");
            }
        	if (funds < bet) {
                btnCredit.setBackground(new java.awt.Color(255, 0, 0));
                } else {
                btnCredit.setBackground(new java.awt.Color(50,255, 50));
            }
        }
    }
    
    
 
    

    public void genSlotNumbers() {
        Random rand = new Random();
        // If the Always win cheat mode is disabled play a normal game.
    
            slot1 = rand.nextInt(slotImages.size());
            slot2 = rand.nextInt(slotImages.size());
            slot3 = rand.nextInt(slotImages.size());
        
            setSlotIcon(slot1, slot2, slot3); // Set the Slot image
        }


    public void setSlotIcon(int img1, int img2, int img3) {
        lblslot1.setIcon(slotImages.get(img1)); // icon = the ArrayList index = random Slot number
        lblslot2.setIcon(slotImages.get(img2));
        lblslot3.setIcon(slotImages.get(img3));
        
    }
    

    public void result() {
        if (slot1 == slot2 && slot2 == slot3) {
            lblStatus.setText("You Resulted THREE symbols ("+slotImages.get(slot1).getDescription()+")! +$"+df.format(getPrize(prizeMoney))+"!");
            lblResultThree.setText("Resulted Three: "+ ++ResultThree);
           
            } else if (slot1 == slot2 || slot1 == slot3) {
            	lblStatus.setText("You Resulted TWO symbols ("+slotImages.get(slot1).getDescription()+")! +$"+df.format(getPrize(prizeMoney))+"!");
            	lblResultTwo.setText("Resulted Two: "+ ++ResultTwo);
            
            } else if (slot2 == slot3) {
            	lblStatus.setText("You Resulted TWO symbols ("+slotImages.get(slot2).getDescription()+")! +$"+df.format(getPrize(prizeMoney))+"!");
            	lblResultTwo.setText("Resulted Two: "+ ++ResultTwo);
            
            } else {
            	lblStatus.setText("Sorry, you didn't Result any symbols. -"+bet+" credits!");
            	lblLost.setText("Lost: "+ ++lost);
        }
        lblCredits.setText("Credits: "+(credits -= bet)); 
        lblMoney.setText("Money: $"+df.format((funds += getPrize(prizeMoney))));
        lblWon.setText("Wins: "+ (ResultThree + ResultTwo)); 
    }
    
  
    
    public double getPrize(double result) {
        if (slot1 == slot2 && slot2 == slot3) {
        	result = prizeMoney; 
            
            } else if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
            
            	result = prizeMoney / 5; 
            }
             else {
            	 result = 0;
        }
        return result;
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

    public static void main(String args[]) {
        
    	java.awt.EventQueue.invokeLater(new Runnable() {

    		public void run() {
    		new SlotMachineGUI();
    		}});
        
    }
    
}