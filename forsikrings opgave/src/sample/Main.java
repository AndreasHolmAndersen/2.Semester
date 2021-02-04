package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    Stage window;
    Scene scene1, scene2;
    BeregnPris beregnPris = new BeregnPris();
    Label errorMessage = new Label();
    Label endComment = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Ulykkesforsikring - Pris beregner");

        //Dropdown menu der spørger hvilken proffesion du er
        ChoiceBox<String> dropDownMenu = new ChoiceBox<>();
        dropDownMenu.getItems().addAll("Advokat", "Tømrer", "Læge", "Skolelærer");
        dropDownMenu.setValue("Vælg en proffesion");

        //CHeckbox for at finde ud af om du er selvstændig eller ej
        Label label1 = new Label("Kryds af hvis du er selvstændig, hvis ikke lad boxen stå tom");
        CheckBox box1 = new CheckBox("");

        //TextField for at indtaste cpr nr
        TextField cprInput = new TextField("Indtast Cpr Nr");

        //Pris beregner knap der også sender en videre til næste scene
        Button button1 = new Button("Beregn pris");
        button1.setOnAction(e -> {
            beregnPris.setProffesion(dropDownMenu.getValue());
            if (box1.isSelected()) {
                beregnPris.setSelvstændig(true);
            }
            else {
                beregnPris.setSelvstændig(false);
            }
            validCpr(cprInput);
            if (beregnPris.isValidCpr()== true) {
                window.setScene(scene2);
            }
            endComment.setText(beregnPris.pris(dropDownMenu));


        });

        //Knap der sender dig tilbage til første scene
        Button button2 = new Button("Gå tilbage");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout til første scene
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(dropDownMenu, label1, box1, cprInput, errorMessage, button1);
        scene1 = new Scene(layout1, 400, 250);

        //Layout til anden scene
        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(button2, endComment);
        scene2 = new Scene(layout2, 400, 250);

        window.setScene(scene1);
        window.show();
    }

    //Method til at tjekke om input af cpr nr er de rigtige tal
    public void validCpr(TextField input) {
        try {
            if(input.getText().length()==10) {
                int day = Integer.parseInt(input.getText(0, 2));
                if (day > 0 && day < 32) {
                    int month = Integer.parseInt(input.getText(2, 4));
                    if (month > 0 && month < 13) {
                        int year = Integer.parseInt(input.getText(4, 6));
                        if (year > 0 && year < 100) {
                            beregnPris.setCprNr(true);
                        } else {
                            beregnPris.setCprNr(false);
                            errorMessage.setText("Ikke et gyldigt cpr nr, prøv igen");
                        }
                    } else {
                        beregnPris.setCprNr(false);
                        errorMessage.setText("Ikke et gyldigt cpr nr, prøv igen");
                    }
                } else {
                    beregnPris.setCprNr(false);
                    errorMessage.setText("Ikke et gyldigt cpr nr, prøv igen");
                }
            }
            else {
                beregnPris.setCprNr(false);
                errorMessage.setText("Ikke et gyldigt cpr nr, prøv igen");
            }
        } catch (NumberFormatException e) {
            beregnPris.setCprNr(false);
            errorMessage.setText("Ikke et gyldigt cpr nr, prøv igen");
        }
    }
}


