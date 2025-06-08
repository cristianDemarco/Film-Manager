package com.example.filmmanager.Controllers;

import com.example.filmmanager.Controller;
import com.example.filmmanager.ScenesData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.Objects;
import java.util.StringTokenizer;

public class VisualizeController extends Controller {
    @FXML
    TextField tf_title, tf_director, tf_genre, tf_date, tf_ticketsSold, tf_income;

    @FXML
    CheckBox chk_is3D;
    
    static public String record;

    public void goBack(ActionEvent actionEvent) throws IOException {
        changeScene(ScenesData.HomePage.getScene(), actionEvent);
    }

    public void initialize(){
        // Assegnazione valori textfield
        StringTokenizer st = new StringTokenizer(record, ";");
        st.nextToken();
        tf_title.setText(st.nextToken());
        tf_director.setText(st.nextToken());
        tf_genre.setText(st.nextToken());
        tf_date.setText(st.nextToken());
        tf_ticketsSold.setText(st.nextToken());
        tf_income.setText(st.nextToken() + "€");

        // Per rendere il checkbox non modificabile

        chk_is3D.setDisable(true);
        chk_is3D.setOpacity(1);

        // Assegnazione valore checkbox

        if(Objects.equals(st.nextToken(), "Y")){
            chk_is3D.setSelected(true);
        }
    }
}
