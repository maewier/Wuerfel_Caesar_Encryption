import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class SwingGUI {

    String faq = "Hier erscheint der verschlüsselte Text." +
                 "Zum ver- und entschlüsseln, werden zwei Losungen benötigt." +
                 "Zur Eingabe von Texten sind alle Zeichen der Tastatur erlaubt." +
                 "Für Losungen nur Klein/Großbuchstaben.";
    static Codec eins;
    static Codec zwei;

    JFrame codecFrame;
    JLabel lblResponse = new JLabel();

    JTextArea textfeld = new JTextArea(10,20);
    JTextArea textfeld2 = new JTextArea(10,20);
    JLabel los1 = new JLabel("Losung 1:");
    JLabel los2 = new JLabel("Losung 2:");
    JPasswordField losung1 = new JPasswordField(8);
    JPasswordField losung2 = new JPasswordField(8);

    JButton wuerfel = new JButton("Wuerfel");
    JButton caesar = new JButton("Caesar");
    JButton encode = new JButton("Encode");
    JButton decode = new JButton("Decode");
    JButton clear = new JButton("Clear");
    JButton howitworks = new JButton("?");

    public SwingGUI(Codec eins, Codec zwei){
        this.eins = eins;
        this.zwei = zwei;
    }

    public static void main(String[]args){
        SwingGUI test = new SwingGUI(eins, zwei);
        test.init();
    }

    public void init(){

        codecFrame = new JFrame("CodecGUI");
        codecFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textfeld.setLineWrap(true);
        textfeld2.setLineWrap(true);

        wuerfel.setMnemonic(MouseEvent.BUTTON1);
        wuerfel.addActionListener(new WuerfelBtn());

        caesar.setMnemonic(MouseEvent.BUTTON1);
        caesar.addActionListener(new CaesarBtn());

        encode.addActionListener(new Encode());

        decode.addActionListener(new Decode());

        clear.addActionListener(new Clear());

        howitworks.addActionListener(new Howitworks());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(wuerfel);
        topPanel.add(caesar);
        topPanel.add(encode);
        topPanel.add(decode);
        topPanel.add(clear);
        topPanel.add(howitworks);

        JSplitPane centerPanel = new JSplitPane();
        centerPanel.setLeftComponent(new JScrollPane(textfeld));
        centerPanel.setRightComponent(new JScrollPane(textfeld2));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(los1);
        bottomPanel.add(losung1);
        bottomPanel.add(los2);
        bottomPanel.add(losung2);
        bottomPanel.add(lblResponse);


        codecFrame.getContentPane().add(BorderLayout.NORTH, topPanel);
        codecFrame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        codecFrame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        codecFrame.setSize(500, 400);
        codecFrame.setVisible(true);
    }

    class WuerfelBtn implements ActionListener{
        public void actionPerformed(ActionEvent e){
            eins = new Wuerfel();
            zwei = new Wuerfel();
            wuerfel.setEnabled(false);
            caesar.setEnabled(true);
            lblResponse.setText("Doppel-Würfel");
        }
    }

    class CaesarBtn implements ActionListener{
        public void actionPerformed(ActionEvent e){
            eins = new Caesar();
            zwei = new Caesar();
            caesar.setEnabled(false);
            wuerfel.setEnabled(true);
            lblResponse.setText("Cäsar-Chiffre");
        }
    }

    class Encode implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String text;
            String key1;
            String key2;
            String temp;
            String erg;
            try {
                if(textfeld.getText()!=null && (!losung1.getText().isEmpty() && !losung2.getText().isEmpty())) {
                    text = textfeld.getText();
                    key1 = losung1.getText();
                    key2 = losung2.getText();
                    eins.setzeLosung(key1);
                    zwei.setzeLosung(key2);
                    temp = eins.kodiere(text);
                    erg = zwei.kodiere(temp);
                    lblResponse.setText("Text verschlüsselt");
                    textfeld2.setText(erg);
                }
                else
                {
                    lblResponse.setText("Eingabe unvollständig");
                }
            }catch (NullPointerException i){
                lblResponse.setText("Verschlüsselungsmethode auswählen");
            }
        }
    }

    class Decode implements ActionListener {
        public void actionPerformed(ActionEvent e){
            String text;
            String key1;
            String key2;
            String temp;
            String erg;
            try {
                if(textfeld.getText()!=null && (!losung1.getText().isEmpty() && !losung2.getText().isEmpty())) {
                    text = textfeld.getText();
                    key1 = losung1.getText();
                    key2 = losung2.getText();
                    eins.setzeLosung(key1);
                    zwei.setzeLosung(key2);
                    temp = zwei.dekodiere(text);
                    erg = eins.dekodiere(temp);
                    lblResponse.setText("Text entschlüsselt");
                    textfeld2.setText(erg);
                }
                else
                {
                    lblResponse.setText("Eingabe unvollständig");
                }
            }catch (NullPointerException i){
                i.printStackTrace();
            }
        }
    }

    class Clear implements ActionListener {

        public void actionPerformed(ActionEvent e){
            textfeld.setText("");
            losung1.setText("");
            losung2.setText("");
            lblResponse.setText("");
        }
    }

    class Howitworks implements ActionListener {

        public void actionPerformed(ActionEvent e){
            textfeld.setText("Hier den zu verschlüsselnden Text eingeben");
            lblResponse.setText("Statusmeldung");
            textfeld2.setText(faq);
        }
    }

}
