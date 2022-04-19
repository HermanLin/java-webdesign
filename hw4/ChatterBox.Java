// Herman Lin
/******************************************************************* 
ChatterBox.java
    This is the User Interface of a simple Client/Server chat 
    room service.

In order to run the full program suite: 
1) First, compile the necessary files:
    
    $ javac Server.java
    $ javac Client.java
    $ javac ChatterBox.java

2) Then, run the Server:
    
    $ java Server

3) Finally, open any number of clients using ChatterBox:

    $ java ChatterBox

*******************************************************************/


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;

public class ChatterBox {

    static final int DEFAULT_PORT = 5190; // change to preferred port
    static ChatterBox cb;
    static final String IP_ADDRESS =  // used for regex checking
        "[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,4}";

    JTextArea textChat, textInput;
    JButton sendButton;
    String serverAddr, username;
    Client connection;

    ChatterBox() {
        // default settings for the window
        JFrame jf = new JFrame("ChatterBox");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        serverPrompt(jf);
    }

    /*
        Prompt user for which server to connect to and a username
    */
    private void serverPrompt (JFrame jf) {
        // create components for the prompt
        JTextField addrInput = new JTextField(20);
        JTextField nameInput = new JTextField(20);

        JButton submit = new JButton("Login");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                serverAddr = addrInput.getText().strip();
                username = nameInput.getText().strip();

                if (Pattern.matches(IP_ADDRESS, serverAddr)) {
                    Boolean connected = verifyServer(serverAddr);
                    if (connected) { startChatterBox(jf); }
                    else {
                        JOptionPane.showMessageDialog(
                            null,
                            "Unable to connect to server: " + serverAddr,
                            "ChatterBox Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Please use a valid server address format",
                        "ChatterBox Error",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // create dynamic grid of cells which allows for
        // components to occupy one or more cells
        JPanel prompt = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        // add components to the panel
        //c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        prompt.add(new Label("Server Address"), c);

        //c.weightx = 0.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        prompt.add(addrInput, c);

        c.gridwidth = GridBagConstraints.RELATIVE;
        prompt.add(new Label("Username"), c);
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        prompt.add(nameInput, c);

        c.gridwidth = GridBagConstraints.HORIZONTAL;
        prompt.add(submit, c);

        // add panel to the window frame
        jf.add(prompt);
        jf.pack();
    }

    /*
        Verify that the supplied server address is currently
        online. If the server is available, create a new
        Client and return true. Otherwise, return false.
    */
    private Boolean verifyServer(String serverAddr) {
        try {
            Socket sock = new Socket(serverAddr, DEFAULT_PORT);
            connection = new Client(sock, username);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /*
        Create the ChatterBox interface for user IO
    */
    private void startChatterBox(JFrame jf) {
        // create the components for the ChatterBox
        textChat = new JTextArea(20, 30);
        textChat.setEditable(false);
        textChat.setLineWrap(true);
        JScrollPane chatSP = new JScrollPane(textChat);

        textInput = new JTextArea(3, 30);
        textInput.setLineWrap(true);
        JScrollPane inputSP = new JScrollPane(textInput);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textInput.getText();
                 
                // check if the message is a "LOGOUT" message
                connection.write(text + "\r\n");
                if (text.strip().compareToIgnoreCase("LOGOUT") == 0) {
                    jf.dispose();
                    System.exit(0);
                }

                textInput.setText("");
            }
        });

        // create dynamic grid of cells which allows for
        // components to occupy one or more cells
        JPanel jp = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        // add components to the panel
        c.gridwidth = GridBagConstraints.REMAINDER;
        jp.add(chatSP, c);

        c.gridwidth = GridBagConstraints.RELATIVE;
        jp.add(inputSP, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        jp.add(sendButton, c);

        jf.setTitle("ChatterBox: " + serverAddr);
        // replace the old panel in the frame
        jf.setContentPane(jp); 
        jf.pack();
        jf.getContentPane().invalidate();
        jf.getContentPane().validate();

        // create and start a thread to continually update the chat
        Thread update = new Thread(new Runnable() {
            public void run() {
                String message = "";
                while (true) {
                    // read from anywhere (Client or Server)
                    try { message = connection.read(); }
                    catch (Exception e) { break; }
                    textChat.append(message + "\n");
                }
            }
        });
        update.start();
    }

    public static void main(String[] args) {
        cb = new ChatterBox();
    }
}
