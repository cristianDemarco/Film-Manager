package com.example.filmmanager.Controllers;

import com.example.filmmanager.Controller;
import com.example.filmmanager.ScenesData;
import com.example.filmmanager.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class InsertController extends Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        cb_genre.getItems().addAll(genres);
        cb_genre.getSelectionModel().selectFirst();

        textFields = new TextField[]{tf_title, tf_director, tf_ticketsSold, tf_income};

        for(int i = 0; i < textFields.length; i++){
            tfLabel.put(textFields[i], textFieldsLabels[i]);
        }
    }

    static public String filmCode;
    @FXML
    TextField tf_title, tf_director, tf_ticketsSold, tf_income;

    TextField[] textFields;

    String[] textFieldsLabels = {"Titolo", "Regista", "Numero di biglietti venduti", "Incasso"};
    @FXML
    DatePicker dp_date;
    @FXML
    ComboBox<String> cb_genre;
    String[] genres = {"Thriller", "Commedia", "Drammatico", "Fantastico"};
    @FXML
    CheckBox chk_is3D;
    @FXML
    private Label lbl_error;

    Map<TextField, String> tfLabel = new HashMap<>();

    public void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(ScenesData.HomePage.getScene(), actionEvent);
    }

    public void confirmRegistration(ActionEvent actionEvent){
        try{
            // Verifica valorizzazione dei textfield
            for (int i = 0; i < textFields.length; i++){
                if(textFields[i].getText().isEmpty()){
                    lbl_error.setText(textFieldsLabels[i] + " non è valorizzato");
                    return;
                }
            }

            lbl_error.setText("");

            if(Validator.containsDigits(tf_director.getText())){
                lbl_error.setText("Regista non deve contenere numeri");
                return;
            }

            // Verifica valorizzazione della data
            String formattedDate;
            if(dp_date.getValue() == null){
                lbl_error.setText("La data deve essere selezionata");
                return;
            } else {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale.ITALY);
                formattedDate = dp_date.getValue().format(dateTimeFormatter);
            }

            // Verifica numericità numero di biglietti venduti e incasso

            if(!Validator.isIntegerAndPositive(tf_ticketsSold.getText())){
                lbl_error.setText(tfLabel.get(tf_ticketsSold) + " deve essere numerico");
                return;
            }

            lbl_error.setText("");

            if(!Validator.isIncomeValid(tf_income.getText())){
                lbl_error.setText(tfLabel.get(tf_income) + " deve essere numerico");
                return;
            }

            lbl_error.setText("");

            // Creazione e scrittura record

            String record = String.join(";",
                            String.valueOf(filmCode),
                            tf_title.getText(),
                            tf_director.getText(),
                            cb_genre.getValue(),
                            formattedDate,
                            tf_ticketsSold.getText(),
                            String.valueOf(Double.parseDouble(tf_income.getText())),
                            chk_is3D.isSelected() ? "Y" : "N",
                            "\n"
            );

            writeRecord(record);
            goBack(actionEvent);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void writeRecord(String record) throws IOException {
        FileWriter fileWriter = new FileWriter("data.txt", true);
        fileWriter.write(record);
        fileWriter.close();
    }
}
