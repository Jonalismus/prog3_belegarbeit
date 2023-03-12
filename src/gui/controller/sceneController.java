package gui.controller;

import geschaeftslogik.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import vertrag.Allergen;
import vertrag.Verkaufsobjekt;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class sceneController implements Initializable {

    //Tablle für Hersteller
    @FXML
    private TableView<Hersteller> herstellerTable;

    @FXML
    private TableColumn<Hersteller, Integer> anzahlKuchen;

    @FXML
    private TableColumn<Hersteller, String> herstellerName;

    //Tabeller für Kuchen
    @FXML
    private TableView<Verkaufsobjekt> kuchenTable;

    @FXML
    private TableColumn<Verkaufsobjekt, String> kuchenTyp;

    @FXML
    private TableColumn<Verkaufsobjekt, Hersteller> kuchenHersteller;

    @FXML
    private TableColumn<Verkaufsobjekt, BigDecimal> kuchenPreis;

    @FXML
    private TableColumn<Verkaufsobjekt, Date> inspektionsdatum;

    @FXML
    private TableColumn<Verkaufsobjekt, Duration> haltbarkeit;

    @FXML
    private TableColumn<Verkaufsobjekt, Integer> fachnummer;

    //Tabellen für Allergene
    @FXML
    private TableView<Allergen> allergenTableEnthalten;

    @FXML
    private TableView<Allergen> allergenTableNichtEnthalten;

    @FXML
    private TableColumn<Verkaufsobjekt, Collection<Allergen>> allergeneVorhanden;

    @FXML
    private TableColumn<Verkaufsobjekt, Collection<Allergen>> allergeneNichtVorhanden;


    private final Model model;

    public sceneController(Model model) {
        this.model = model;
    }


    @FXML
    public void HerstellerEinfuegen() {
        Platform.runLater(() -> {
            Stage newStage = new Stage();
            newStage.initOwner(herstellerTable.getScene().getWindow());
            newStage.setTitle("Hersteller hinzufuegen");
            newStage.setResizable(false);

            Text text = new Text("Geben Sie den Namen des Herstellers ein, den Sie hinzufuegen moechten:");
            TextField textField = new TextField();
            Button okButton = new Button("OK");
            Button abbrechenButton = new Button("Abbrechen");

            okButton.setOnAction(e -> {
                String herstellerName = textField.getText();

                Hersteller hersteller = new Hersteller(herstellerName);
                if (model.herstellerEinfuegen(hersteller)) {
                    herstellerTable.getItems().clear();
                }

                tableUpdate();

                newStage.close();
            });

            buildStageOne(newStage, text, textField, okButton, abbrechenButton);
        });
    }


    @FXML
    public void HerstellerLoeschen() {
        Platform.runLater(() -> {
            Stage newStage = new Stage();
            newStage.initOwner(herstellerTable.getScene().getWindow());
            newStage.setTitle("Hersteller loeschen");
            newStage.setResizable(false);

            Text text = new Text("Geben Sie den Namen des Herstellers ein, den Sie loeschen moechten:");
            TextField textField = new TextField();
            Button okButton = new Button("OK");
            Button abbrechenButton = new Button("Abbrechen");

            okButton.setOnAction(e -> {
                String herstellerName = textField.getText();

                if (model.herstellerLoeschen(herstellerName)) {
                    herstellerTable.getItems().clear();
                }

                tableUpdate();

                newStage.close();
            });

            buildStageOne(newStage, text, textField, okButton, abbrechenButton);
        });
    }

    @FXML
    public void KuchenEinfuegen() {
        Platform.runLater(() -> {
            Stage newStage = new Stage();
            newStage.initOwner(kuchenTable.getScene().getWindow());
            newStage.setTitle("Kuchen hinzufuegen");
            newStage.setResizable(false);

            Text text = new Text("Legen Sie einen Kuchen mit allen noetigen Daten an:");
            TextField textField = new TextField();
            Button okButton = new Button("OK");
            Button abbrechenButton = new Button("Abbrechen");

            okButton.setOnAction(e -> {
                String kuchenString = textField.getText();

                String[] res = kuchenString.split(" ");

                try {
                    Hersteller hersteller = new Hersteller(res[1]);

                    BigDecimal preis;
                    try {
                        preis = new BigDecimal(res[2].replace(",", "."));
                    } catch (IllegalArgumentException | ArithmeticException e1) {
                        return;
                    }

                    int naehrwert;
                    try {
                        naehrwert = Integer.parseInt(res[3]);
                    } catch (NumberFormatException e2) {
                        return;
                    }

                    java.time.Duration haltbarkeit;
                    try {
                        haltbarkeit = java.time.Duration.ofDays(Integer.parseInt(res[4]));
                    } catch (NumberFormatException e3) {
                        return;
                    }

                    String[] allergenStrings = res[5].split(",");
                    Collection<Allergen> allergene = new ArrayList<>();
                    for (String allergenString : allergenStrings) {
                        allergene.add(Allergen.fromString(allergenString));
                    }
                    String sorte = res[6];


                    switch (res[0]) {
                        case "Kremkuchen" -> {
                            Kremkuchen kremkuchen = new Kremkuchen(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte);
                            if (model.verkaufsObjektEinfuegen(kremkuchen)) {
                                kuchenTable.getItems().clear();
                            }
                        }
                        case "Obstkuchen" -> {
                            Obstkuchen Obstkuchen = new Obstkuchen(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte);
                            if (model.verkaufsObjektEinfuegen(Obstkuchen)) {
                                kuchenTable.getItems().clear();
                            }
                        }
                        case "Obsttorte" -> {
                            Obsttorte Obsttorte = new Obsttorte(hersteller, preis, naehrwert, haltbarkeit, allergene, sorte, res[7]);
                            if (model.verkaufsObjektEinfuegen(Obsttorte)) {
                                kuchenTable.getItems().clear();
                            }
                        }
                    }
                } catch (Exception ex) {
                    return;
                }

                tableUpdate();
                newStage.close();
            });

            buildStageTwo(newStage, text, textField, okButton, abbrechenButton);
        });
    }


    @FXML
    public void KuchenLoeschen() {
        Platform.runLater(() -> {
            Stage newStage = new Stage();
            newStage.initOwner(kuchenTable.getScene().getWindow());
            newStage.setTitle("Kuchen loeschen");
            newStage.setResizable(false);

            Text text = new Text("Geben Sie die Fachnummer des Kuchen ein, welchen Sie loeschen moechten:");
            TextField textField = new TextField();
            Button okButton = new Button("OK");
            Button abbrechenButton = new Button("Abbrechen");

            okButton.setOnAction(e -> {
                String fachnummer = textField.getText();

                try {
                    if (model.verkaufsObjektLoeschen(Integer.parseInt(fachnummer))) {
                        kuchenTable.getItems().clear();
                    }
                } catch (Exception exc) {
                    return;
                }

                tableUpdate();

                newStage.close();
            });

            buildStageTwo(newStage, text, textField, okButton, abbrechenButton);
        });

    }

    private void tableUpdate() {
        kuchenTable.getItems().clear();
        List<Verkaufsobjekt> kuchen = model.kuchenAbrufen("kuchen");
        ObservableList<Verkaufsobjekt> listeKuchen = kuchenTable.getItems();
        listeKuchen.addAll(kuchen);
        kuchenTable.setItems(listeKuchen);
        herstellerTable.getItems().clear();
        List<Hersteller> resHersteller = model.abrufenDerHersteller();
        ObservableList<Hersteller> listHersteller = herstellerTable.getItems();
        listHersteller.addAll(resHersteller);
        herstellerTable.setItems(listHersteller);


        allergenTableEnthalten.getItems().clear();
        List<Allergen> allergeneVorhanden = model.allergeneAbrufen(true);
        ObservableList<Allergen> listeAllergenVorhanden = allergenTableEnthalten.getItems();
        listeAllergenVorhanden.addAll(allergeneVorhanden);
        allergenTableEnthalten.setItems(listeAllergenVorhanden);



        allergenTableNichtEnthalten.getItems().clear();
        List<Allergen> allergeneNichtVorhanden = model.allergeneAbrufen(false);
        ObservableList<Allergen> listeAllergenNichtVorhanden = allergenTableNichtEnthalten.getItems();
        listeAllergenNichtVorhanden.addAll(allergeneNichtVorhanden);
        allergenTableNichtEnthalten.setItems(listeAllergenNichtVorhanden);
    }

    @FXML
    public void inspektionSetzen() {
        Platform.runLater(() -> {
            Stage newStage = new Stage();
            newStage.initOwner(kuchenTable.getScene().getWindow());
            newStage.setTitle("Inspektionsdatum setzen");
            newStage.setResizable(false);

            Text text = new Text("Geben Sie die Fachnummer des Kuchen ein, bei welchen Sie ein das Inspektionsdatum setzen möchten:");
            TextField textField = new TextField();
            Button okButton = new Button("OK");
            Button abbrechenButton = new Button("Abbrechen");

            okButton.setOnAction(e -> {
                String fachnummer = textField.getText();

                try {
                    model.inspektionsDatumSetzen(Integer.parseInt(fachnummer));
                } catch (Exception exce) {
                    return;
                }


                tableUpdate();

                newStage.close();
            });

            abbrechenButton.setOnAction(e -> newStage.close());

            VBox vbox = new VBox(text, textField, new HBox(okButton, abbrechenButton));
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);
            StackPane root = new StackPane(vbox);

            Scene scene = new Scene(root, 700, 100);
            newStage.setScene(scene);
            newStage.show();
        });
    }


    private void buildStageOne(Stage newStage, Text text, TextField textField, Button okButton, Button abbrechenButton) {
        abbrechenButton.setOnAction(e -> newStage.close());

        VBox vbox = new VBox(text, textField, new HBox(okButton, abbrechenButton));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        StackPane root = new StackPane(vbox);

        Scene scene = new Scene(root, 400, 100);
        newStage.setScene(scene);
        newStage.show();
    }

    private void buildStageTwo(Stage newStage, Text text, TextField textField, Button okButton, Button abbrechenButton) {
        abbrechenButton.setOnAction(e -> newStage.close());

        VBox vbox = new VBox(text, textField, new HBox(okButton, abbrechenButton));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        StackPane root = new StackPane(vbox);

        Scene scene = new Scene(root, 600, 100);
        newStage.setScene(scene);
        newStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        herstellerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        anzahlKuchen.setCellValueFactory(new PropertyValueFactory<>("anzahlKuchen"));
        kuchenTyp.setCellValueFactory(new PropertyValueFactory<>("kuchentyp"));
        kuchenHersteller.setCellValueFactory(new PropertyValueFactory<>("hersteller"));
        kuchenPreis.setCellValueFactory(new PropertyValueFactory<>("preis"));
        inspektionsdatum.setCellValueFactory(new PropertyValueFactory<>("inspektionsdatum"));
        haltbarkeit.setCellValueFactory(new PropertyValueFactory<>("haltbarkeit"));
        fachnummer.setCellValueFactory(new PropertyValueFactory<>("fachnummer"));
        allergeneVorhanden.setCellValueFactory(new PropertyValueFactory<>("text"));
        allergeneNichtVorhanden.setCellValueFactory(new PropertyValueFactory<>("text"));
    }
}
