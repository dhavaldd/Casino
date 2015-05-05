package ClientServer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import BlackJack.BlackJackDemo;
import Poker.PokerDemo;
import slotMachineGUI.SlotMachineGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Client {

	
    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Casino");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(20, 40);
    
    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Return in the
     * listener sends the textfield contents to the server.  Note
     * however that the textfield is initially NOT editable, and
     * only becomes editable AFTER the client receives the NAMEACCEPTED
     * message from the server.
     */
    public Client() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setSize(220, 400);
        frame.setLocationRelativeTo(null);  
         
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            /**
             * Responds to pressing the enter key in the textfield by sending
             * the contents of the text field to the server.    Then clear
             * the text area in preparation for the next message.
             */
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    /**
     * Prompt for and return the desired screen name.
     */
    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private String getGame() {
    	String[] choices = { "Slot Machine" , "Blackjack", "Poker" };
        return (String) JOptionPane.showInputDialog(frame, "Choose now...",
                "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null, 
choices, 
choices[0]); 
    }
    
    
    
    /**
     * Connects to the server then enters the processing loop.
     */
    private void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = "";
        Socket socket = new Socket(serverAddress, 9002);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
       String name = "";
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
            	name = getName();
                out.println(name);
            } else if (line.startsWith("NAMEACCEPTED")) {
            	String game = getGame(); 
            if(game.equals("Blackjack")){
            	
            		BlackJackDemo.main(name);
            	
            	}
            if(game.equals("Slot Machine")){
            	frame.setVisible(false);
            	SlotMachineGUI.main(null);
            	
            }
            if(game.equals("Poker")){
            	frame.setVisible(false);
            	PokerDemo.main(null);
            	
            }
            		out.println("GAME"+game);
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }
        }
    }

  

	
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}


 