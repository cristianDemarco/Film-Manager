package com.example.filmmanager;

public enum ScenesData {
    HomePage(new SceneData("Pagina principale", "homePageView.fxml")),
    InsertPage(new SceneData("Registra film", "insertView.fxml")),
    VisualizePage(new SceneData("Visualizza informazioni film", "visualizeView.fxml"));

    public SceneData scene;

    ScenesData(SceneData scene) {
        this.scene = scene;
    }

    public SceneData getScene(){
        return scene;
    }
}
