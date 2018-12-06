import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class CodecGUI extends Application {

    Codec eins;
    Codec zwei;

    TextArea textfeld = new TextArea();
    TextArea textfeld2 = new TextArea();
    PasswordField losung1 = new PasswordField();
    PasswordField losung2 = new PasswordField();
    Button caesarBtn = new Button("Cäsar");
    Button wuerfeBtn = new Button("Würfel");
    Button encode = new Button("Encode");
    Button decode = new Button("Decode");
    Button clear = new Button("Clear");
    Label lblResponse = new Label();

    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15, 15, 15, 15));

        Text title = new Text("Doppelwürfel");
        title.setFill(Paint.valueOf("#2A5058"));
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        textfeld.setPrefColumnCount(40);
        textfeld.setWrapText(true);

        textfeld2.setPrefColumnCount(40);
        textfeld2.setWrapText(true);

        DropShadow shadow = new DropShadow();
        caesarBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            caesarBtn.setEffect(shadow);
        });
        wuerfeBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            wuerfeBtn.setEffect(shadow);
        });
        encode.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            encode.setEffect(shadow);
        });
        decode.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            decode.setEffect(shadow);
        });
        clear.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            clear.setEffect(shadow);
        });

        caesarBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)-> {
            caesarBtn.setEffect(null);});
        wuerfeBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)-> {
            wuerfeBtn.setEffect(null);});
        encode.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)-> {
            encode.setEffect(null);});
        decode.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)-> {
            decode.setEffect(null);});
        clear.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e)-> {
            clear.setEffect(null);});

        caesarBtn.setOnAction( e -> caesarBtn_Click() );

        wuerfeBtn.setOnAction( e -> wuerfeBtn_Click() );

        encode.setOnAction( e -> encode_Click() );

        decode.setOnAction( e -> decode_Click() );
        clear.setOnAction( e -> clear_Click() );

        grid.add(caesarBtn, 0, 0);
        grid.add(wuerfeBtn, 1, 0);
        grid.add(textfeld, 0,1, 6, 4);
        grid.add(losung1,0, 6);
        grid.add(losung2,1, 6);
        grid.add(encode,2,6);
        grid.add(decode,3,6);
        grid.add(clear, 4,6);
        grid.add(lblResponse,0,7);
        grid.add(textfeld2, 0,8,6,4);

        Scene scene = new Scene(grid); //540,520

        primaryStage.setTitle("Verschlüsselung");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void caesarBtn_Click() {
        eins = new Caesar();
        zwei = new Caesar();
        lblResponse.setText("Caesar Verschlüsselung");
        System.out.println("Caesar-Objekte " + eins.toString() + " / " + zwei.toString() + " erstellt");
    }

    private void wuerfeBtn_Click() {
        eins = new Wuerfel();
        zwei = new Wuerfel();
        lblResponse.setText("Doppel-Würfel");
        System.out.println("Würfel-Objekte " + eins.toString() + " / " + zwei.toString() + " erstellt");
    }

    private void encode_Click() {
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
        }catch (NullPointerException e){
            lblResponse.setText("Verschlüsselungsmethode auswählen");
        }

    }

    private void decode_Click() {
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
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    private void clear_Click() {
        textfeld.clear();
        losung1.clear();
        losung2.clear();
        lblResponse.setText(null);
    }
}
