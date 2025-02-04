/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.frame.mainScherm.bottom;

import Domein.DomeinController;
import Domein.Locatie;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ui.frame.StartGui;
import ui.frame.mainScherm.MainScherm;


/**
 *
 * @author NotAvailable
 */
public class ButtonsPane extends Pane
{

    private boolean clear;
    private ComboBox cboPlaatsPionnen;
    private Slider sliderPionnen;
    private HBox firstPanel, secondPanel, mainPanel;
    private int gekozenIndex = 99;
    private Button pionnenOkButton, PauzeButton;
    private TextField txfHuidigeSpeler;
    private MainScherm mainScherm;
    private boolean enabled = true;
    private Locatie gekozenLocatie;

    public ButtonsPane(DomeinController dc, Console console, MainScherm mainScherm)
    {
        this.mainScherm = mainScherm;

        init();
        setActions();
        clear = false;
    }

    private void init()
    {
        cboPlaatsPionnen = new ComboBox();
        sliderPionnen = new Slider();
        secondPanel = new HBox();
        firstPanel = new HBox();
        mainPanel = new HBox();
        pionnenOkButton = new Button("Ok");
        PauzeButton = new Button("Pauze Game");
        txfHuidigeSpeler = new TextField();
        ImageView buttonPannelImage = mainScherm.getImageView(mainScherm.getUrl("wood"));
        clear = false;

        txfHuidigeSpeler.setEditable(false);
        txfHuidigeSpeler.focusedProperty().addListener(e ->
            {
            requestFocus();
            });
        firstPanel.setSpacing(10);
        firstPanel.getChildren().add(cboPlaatsPionnen);
        firstPanel.getChildren().add(sliderPionnen);
        firstPanel.getChildren().add(pionnenOkButton);
        secondPanel.getChildren().add(txfHuidigeSpeler);
        secondPanel.getChildren().add(PauzeButton);
        mainPanel.getChildren().add(firstPanel);
        mainPanel.getChildren().add(secondPanel);
        getChildren().add(buttonPannelImage);
        getChildren().add(mainPanel);
        PauzeButton.setMaxHeight(Double.MAX_VALUE);
        txfHuidigeSpeler.setMaxHeight(Double.MAX_VALUE);
        pionnenOkButton.setMaxHeight(Double.MAX_VALUE);
        sliderPionnen.setMaxHeight(Double.MAX_VALUE);
        cboPlaatsPionnen.setMaxHeight(Double.MAX_VALUE);
        pionnenOkButton.setMinWidth(42);
        cboPlaatsPionnen.setMinWidth(212);
        txfHuidigeSpeler.setMinWidth(153);
        firstPanel.setMargin(sliderPionnen, new Insets(3, 0, 0, 0));
        buttonPannelImage.fitWidthProperty().bind(widthProperty());
        buttonPannelImage.fitHeightProperty().bind(heightProperty());

        txfHuidigeSpeler.prefWidthProperty().bind(Bindings.divide(secondPanel.widthProperty(), 2));
        firstPanel.prefWidthProperty().bind(Bindings.divide(widthProperty(), 1.5));
        secondPanel.prefWidthProperty().bind(Bindings.divide(widthProperty(), 3));
        cboPlaatsPionnen.prefWidthProperty().bind(Bindings.divide(firstPanel.widthProperty(), 2.4));
        sliderPionnen.prefWidthProperty().bind(Bindings.divide(firstPanel.widthProperty(), 2.4));
        pionnenOkButton.prefWidthProperty().bind(Bindings.divide(firstPanel.widthProperty(), 8));
        sliderPionnen.prefHeightProperty().bind(heightProperty().multiply(0.9));
        sliderPionnen.minHeightProperty().bind(heightProperty().multiply(0.9));
        sliderPionnen.maxHeightProperty().bind(heightProperty().multiply(0.9));
        mainPanel.getStylesheets().add(this.getClass().getClassLoader().getResource("ui/Stylesheets/ButtonPanel.css").toExternalForm());
        firstPanel.getStylesheets().add(this.getClass().getClassLoader().getResource("ui/Stylesheets/ButtonPanel.css").toExternalForm());
        secondPanel.getStylesheets().add(this.getClass().getClassLoader().getResource("ui/Stylesheets/ButtonPanel.css").toExternalForm());
        txfHuidigeSpeler.setStyle("-fx-background-color: transparent;-fx-text-inner-color: white;-fx-font-size: 14pt; -fx-font-weight: 900;-fx-font-family: \"Calibri\" ;");
        setStyle("-fx-background-color: transparent;");
        initSlider();
        resetCBOPlaatsPionnen();
    }

    public void initAfter()
    {

        txfHuidigeSpeler.setText("Huidige Speler: " + (mainScherm.getController().getHuidigeSpelerIndex() + 1));

        ArrayList<Locatie> all = mainScherm.getController().getAllLocaties();
        for (int i = 0; i < all.size(); i++)
            {
            cboPlaatsPionnen.getItems().add(all.get(i));
            }

    }

    private void initSlider()
    {
        sliderPionnen.setMinorTickCount(1);
        sliderPionnen.setBlockIncrement(1);
        sliderPionnen.setMajorTickUnit(1);
        sliderPionnen.setValue(0);
        sliderPionnen.setMax(0);
        sliderPionnen.setShowTickMarks(true);
        sliderPionnen.setShowTickLabels(true);
        sliderPionnen.setSnapToTicks(true);
        sliderPionnen.getStylesheets().add(this.getClass().getClassLoader().getResource("ui/Stylesheets/BlackSlider.css").toExternalForm());
        sliderPionnen.valueProperty().addListener(e ->
            {
            sliderPionnen.setValue(Math.round(sliderPionnen.valueProperty().doubleValue()));
            });

    }

    public void bevestigPlaatsPionnen()
    {
        boolean hasPlaced = false;
        if (gekozenLocatie != null)
            {
            if (!gekozenLocatie.magPionnenSelecteren())
                {
                if (gekozenLocatie.kanLocatieVullen(mainScherm.getController().getHuidigeSpeler()))
                    {
                    if (gekozenLocatie.getHuidigAantalPionnen() == 0)
                        {
                        if (gekozenLocatie.getNaam().contains("Stapel"))
                            {
                            hasPlaced = true;

                            mainScherm.getController().plaatsPionnenOpVeld(gekozenLocatie, gekozenLocatie.getMaxPionnen());
                            mainScherm.getStapelsPane().updateStapels();
                            mainScherm.volgendeSpeler();

                            }
                        else
                            {
                            hasPlaced = true;
                            mainScherm.getController().plaatsPionnenOpVeld(gekozenLocatie, gekozenLocatie.getMaxPionnen());
                            mainScherm.setPionnenImages(gekozenLocatie, gekozenLocatie.getMaxPionnen(), mainScherm.getController().getHuidigeSpeler());
                            mainScherm.volgendeSpeler();
                            }
                        resetSlider();
                        resetCBOPlaatsPionnen();
                        clear = true;
                        }
                    else
                        {
                        MainScherm.console.printLine("Deze locatie is reeds in gebruik door een andere speler!");
                        }
                    }
                else
                    {
                    MainScherm.console.printLine("Je hebt niet genoeg pionnen hiervoor!");
                    }
                }
            else
                {
                if (sliderPionnen.getValue() != 0)
                    {
                    if (gekozenLocatie.heeftPlaatsGenoeg((int) sliderPionnen.getValue()))
                        {
                        hasPlaced = true;
                        mainScherm.getController().plaatsPionnenOpVeld(gekozenLocatie, (int) sliderPionnen.getValue());
                        mainScherm.setPionnenImages(gekozenLocatie, (int) sliderPionnen.getValue(), mainScherm.getController().getHuidigeSpeler());
                        resetSlider();
                        resetCBOPlaatsPionnen();
                        clear = true;
                        mainScherm.volgendeSpeler();
                        }
                    else
                        {
                        MainScherm.console.printLine("Er zijn niet genoeg plaatsen beschikbaar op deze locatie! Huidig aantal plaatsen vrij op deze locatie is: " + gekozenLocatie.getAantalPlaatsenVrij());
                        }
                    }
                else
                    {
                    MainScherm.console.printLine("Gelieve eerst een aantal pionnen te selecteren!");
                    }
                }
            }
        else
            {

            MainScherm.console.printLine("Gelieve eerst een locatie te selecteren!");
            }
        if (hasPlaced)
            {
            if (mainScherm.shouldPlayLocatieSFX())
                {
                mainScherm.queueSFX(gekozenLocatie.getNaam(), -1);
                }
            }
    }

    private boolean selectLocatie()
    {
        gekozenIndex = cboPlaatsPionnen.getSelectionModel().getSelectedIndex();
        int count = 0;
        boolean valid = true;
        Locatie gekozenLoc = (Locatie) cboPlaatsPionnen.getItems().get(gekozenIndex);

        if (mainScherm.getController().isLocatieNogNietGebruikt(gekozenLoc))
            {
            valid = true;
            gekozenLocatie = gekozenLoc;
            if (gekozenLocatie.magPionnenSelecteren())
                {
                sliderPionnen.setDisable(false);
                setAantalPionnenSlider();
                }
            else
                {
                resetSlider();
                }
            }
        else
            {
            clear = true;
            MainScherm.console.printLine("U hebt reeds pionnen op de locatie " + gekozenLoc.getNaam() + " gezet.");
            resetCBOPlaatsPionnen();
            }

        return valid;
    }

    public void update()
    {
        Platform.runLater(() ->
            {
            cboPlaatsPionnen.requestFocus();

            });
        if (!enabled)
            {
            toggleButtons();
            }
    }

    public void toggleButtons()
    {
        if (enabled)
            {
            cboPlaatsPionnen.setDisable(true);
            sliderPionnen.setDisable(true);
            pionnenOkButton.setDisable(true);
            cboPlaatsPionnen.setStyle("-fx-opacity:0.5;");
            sliderPionnen.setStyle("-fx-opacity:0.5;");
            pionnenOkButton.setStyle("-fx-opacity:0.5;");
            enabled = false;
            }
        else
            {
            cboPlaatsPionnen.setDisable(false);
            sliderPionnen.setDisable(false);
            pionnenOkButton.setDisable(false);
            cboPlaatsPionnen.setStyle("-fx-opacity:1;");
            sliderPionnen.setStyle("-fx-opacity: 1;");
            pionnenOkButton.setStyle("-fx-opacity:1;");
            enabled = true;
            }

    }

    private void resetCBOPlaatsPionnen()
    {
        cboPlaatsPionnen.setPromptText("      Plaats pionnen  ");
        sliderPionnen.setDisable(false);
        sliderPionnen.setValue(0);
        Platform.runLater(() -> cboPlaatsPionnen.getSelectionModel().clearSelection());
    }

    private void resetSlider()
    {
        sliderPionnen.setMax(0);
        sliderPionnen.setDisable(true);

    }

    private void setActions()
    {
        cboPlaatsPionnen.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {

                if (!clear)
                    {
                    selectLocatie();
                    }
                else
                    {
                    clear = false;//
                    }
            }

        });

        pionnenOkButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (!clear)
                    {
                    bevestigPlaatsPionnen();
                    }

                else
                    {
                    clear = false;
                    }
            }

        });
        PauzeButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (StartGui.isPaused)
                    {
                    mainScherm.closePauzeMenu();

                    }
                else
                    {
                    mainScherm.openPauzeMenu();

                    }

            }

        });

    }

    public void setSliderMax()
    {
        sliderPionnen.setValue(sliderPionnen.getMax());
    }

    public void setHuidigeSpeler()
    {
        txfHuidigeSpeler.setText("Huidige Speler: " + (mainScherm.getController().getHuidigeSpelerIndex() + 1) + "");
    }

    private void setAantalPionnenSlider()
    {

        sliderPionnen.setMax(gekozenLocatie.getBruikbarePionnen(mainScherm.getController().getHuidigeSpeler()));
    }

    public Locatie getGekozenLocatie()
    {
        return gekozenLocatie;
    }

}
