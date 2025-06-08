package com.example.filmmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.StringTokenizer;

public class Controller {
    public void changeScene(SceneData sceneData, ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getSuperclass().getResource(sceneData.path));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle(sceneData.title);
        stage.setScene(scene);

        stage.show();

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public String findFilm(String film_code){
        // Ricerca film nel file di archivio
        try{
            FileReader fileReader = new FileReader("data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine())!=null){
                StringTokenizer st = new StringTokenizer(line, ";");
                String code = st.nextToken();
                if(Objects.equals(code, film_code)){
                    return line;
                }
            }
            fileReader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
