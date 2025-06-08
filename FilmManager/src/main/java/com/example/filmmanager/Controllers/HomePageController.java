package com.example.filmmanager.Controllers;

import com.example.filmmanager.Controller;
import com.example.filmmanager.ScenesData;
import com.example.filmmanager.Validator;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.*;

public class HomePageController extends Controller {
    @FXML
    private TextField tf_filmCode;

    @FXML
    private Label lbl_info;

    @FXML
    public void insert(ActionEvent actionEvent) throws IOException {
        if(isCodeValid()){
            System.out.println(findFilm(tf_filmCode.getText()));
            if(findFilm(tf_filmCode.getText()) == null){
                InsertController.filmCode = tf_filmCode.getText();
                changeScene(ScenesData.InsertPage.getScene(), actionEvent);
            } else {
                setInfoLabelText("Film già registrato", 0);
            }
        }
    }

    @FXML
    public void visualize(ActionEvent actionEvent) throws IOException {
        if(isCodeValid()){
            String filmLine = findFilm(tf_filmCode.getText());
            // Visualizzazione solo se il film è già registrato
            if(filmLine != null){
                lbl_info.setText("");
                VisualizeController.record=filmLine;
                changeScene(ScenesData.VisualizePage.getScene(), actionEvent);
            } else {
                setInfoLabelText("Film non presente in archivio", 0);
            }
        }
    }

    @FXML
    public void delete() throws IOException {
        if(isCodeValid()){
            String filmLine = findFilm(tf_filmCode.getText());
            if(filmLine != null){
                File data = new File("data.txt");
                File updatedData = new File("updatedData");
                BufferedReader bufferedReader = new BufferedReader(new FileReader(data));
                String line;

                try{
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(updatedData, true));
                    while ((line = bufferedReader.readLine()) != null){
                        if(line.equals(filmLine)){
                            continue;
                        }

                        bufferedWriter.write(line);
                    }
                    bufferedWriter.close();
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }

                bufferedReader.close();
                data.delete();
                updatedData.renameTo(data);
                setInfoLabelText("Film cancellato con successo", 1);
            } else {
                setInfoLabelText("Film non presente in archivio", 0);
            }
        }
    }

    public boolean isCodeValid(){
        String filmCode = tf_filmCode.getText();
        if(filmCode.isEmpty()){
            setInfoLabelText("Codice non valorizzato", 0);

            return false;
        }
        if(filmCode.length() != 8){
            setInfoLabelText("Il codice deve essere di 8 cifre", 0);

            return false;
        }

        if(!Validator.isIntegerAndPositive(filmCode)){
            setInfoLabelText("Il codice deve essere numerico", 0);
            return false;
        }
        return true;
    }

    public void setInfoLabelText(String text, int errorOrSuccess){
        if(errorOrSuccess == 1){
            lbl_info.setTextFill(Color.GREEN);
        } else {
            lbl_info.setTextFill(Color.RED);
        }
        lbl_info.setText(text);
    }
}