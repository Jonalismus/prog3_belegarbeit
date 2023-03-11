package gui.controller;

import geschaeftslogik.Hersteller;
import geschaeftslogik.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import vertrag.Verkaufsobjekt;


import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


public class startController implements Initializable {

    int kapazitaet;
    @FXML
    private TextField startTextField;
    @FXML
    Text startText;

    @FXML
    public void startFensterKapazitaet(ActionEvent actionEvent) {
        try {
            kapazitaet = Integer.parseInt(startTextField.getText());
            LinkedList<Hersteller> herstellerLinkedList = new LinkedList<>();
            LinkedList<Verkaufsobjekt> verkaufsobjektLinkedList = new LinkedList<>();
            Model model = new Model(kapazitaet, verkaufsobjektLinkedList, herstellerLinkedList);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/hauptfenster.fxml"));
            loader.setControllerFactory(e -> new sceneController(model));
            Parent root = loader.load();
            Scene hauptfenster_scene = new Scene(root);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(hauptfenster_scene);
            app_stage.show();
        } catch (Exception e) {
            startText.setText("Bitte geben sie eine Zahl an");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
