/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.frame.mainScherm.center;

import Domein.Speler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.frame.mainScherm.MainScherm;
import ui.frame.mainScherm.right.DeelPaneel;
import ui.frame.mainScherm.right.RightPaneBlueprint;


/**
 *
 * @author NotAvailable
 */
public class EindeRondePane extends RightPaneBlueprint
{

    MainScherm mainScherm;

    HBox hbox = new HBox();
    double x = 0.2;
    double y = 0.08;

    public EindeRondePane(MainScherm mainScherm, Pane container)
    {
        super(mainScherm, mainScherm.getController().getSpelers().get(0), container, 1, 1);
        this.mainScherm = mainScherm;

        if (mainScherm.getController().getSpelers().size() == 4)
            {
            x = 0.2;
            }
        if (mainScherm.getController().getSpelers().size() == 3)
            {
            x = 0.238;
            }
        if (mainScherm.getController().getSpelers().size() == 2)
            {
            x = 0.33;
            }
        init();
    }

    private void init()
    {
        setBackgroundImage("img/ConsoleScherm.png");
        hbox = getHBox(vbox, hbox, true, mainScherm, "", 0.9, 1, 1, new Node[]
            {
            }, new String[]
            {
            }, true, 0);

        addChild(hbox);
        toonNamen();
        for (int i = 0; i < mainScherm.getController().getSpelers().size(); i++)
            {
            toonSpeler(i);
            }

        DeelPaneel buttonPanel = new DeelPaneel(mainScherm, 1, 0.3, vbox, true);
        Button button = new Button("Volgende Ronde");

        button.getStylesheets().add(this.getClass().getClassLoader().getResource("ui/Stylesheets/Buttons.css").toExternalForm());

        buttonPanel.addControl(button, 0.5, 0.25);
        buttonPanel.setFontTracking(button, 50);
        addChild(buttonPanel);
        button.setOnAction((event) ->
            {
            if (mainScherm.shouldPlayMenuSFX())
                {
                mainScherm.queueSFX("menu", -1);
                }
            mainScherm.finishRound();
            //System.err.println("FinishRound()");
            });
        vbox.widthProperty().addListener(e ->
            {
            VBox.setMargin(buttonPanel, new Insets(0, 0, 0, widthProperty().multiply(0.25).doubleValue()));
            });
    }

    private void toonNamen()
    {

        VBox namenBox = new VBox();

        Label spelerLabel = new Label("Spelers");
        Label houtLabel = new Label("Hout");
        Label leemLabel = new Label("Leem");
        Label steenLabel = new Label("Steen");
        Label goudLabel = new Label("Goud");
        Label voedselLabel = new Label("Voedsel");
        Label pionnenLabel = new Label("Pionnen");
        Label puntenLabel = new Label("Punten");
        Label voedselPerBeurtLabel = new Label("Voedsel Per Beurt");
        Label bruikbaarGereedschapLabel = new Label("Waarde v. gereedschap");
        Label huttenLabel = new Label("Hut(ten)");

        DeelPaneel spelerPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel houtPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel leemPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel steenPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel goudPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel voedselPane = new DeelPaneel(mainScherm, x + 0.06, y * 3.25, this, true);
        DeelPaneel pionnenPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel puntenPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel voedselPerBeurtPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel bruikbaarGereedschapPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);
        DeelPaneel huttenPane = new DeelPaneel(mainScherm, x + 0.06, y / 1.25, this, true);

        spelerPane.addControl(spelerLabel, 1, 56, Pos.CENTER, style);
        houtPane.addControl(houtLabel, 1, 56, Pos.CENTER, style);
        leemPane.addControl(leemLabel, 1, 56, Pos.CENTER, style);
        steenPane.addControl(steenLabel, 1, 56, Pos.CENTER, style);
        goudPane.addControl(goudLabel, 1, 56, Pos.CENTER, style);
        voedselPane.addControl(voedselLabel, 1, 20, Pos.CENTER, style);
        pionnenPane.addControl(pionnenLabel, 1, 56, Pos.CENTER, style);
        puntenPane.addControl(puntenLabel, 1, 56, Pos.CENTER, style);
        voedselPerBeurtPane.addControl(voedselPerBeurtLabel, 1, 56, Pos.CENTER, style);
        bruikbaarGereedschapPane.addControl(bruikbaarGereedschapLabel, 1, 56, Pos.CENTER, style);
        huttenPane.addControl(huttenLabel, 1, 56, Pos.CENTER, style);

        namenBox = getVBox(new DeelPaneel[]
            {
            spelerPane, houtPane, leemPane, steenPane, goudPane, pionnenPane, puntenPane, bruikbaarGereedschapPane, huttenPane, voedselPerBeurtPane, voedselPane
            }, x, y);
        hbox.getChildren().add(namenBox);
        widthProperty().addListener(e ->
            {
            String temp = "-fx-border-color: black; -fx-border-insets:0 " + bruikbaarGereedschapPane.widthProperty().multiply(0.3) + " 0 0; -fx-border-width: 0 2 0 0; -fx-border-style: solid;";
            spelerPane.setStyle(temp);
            houtPane.setStyle(temp);
            leemPane.setStyle(temp);
            steenPane.setStyle(temp);
            goudPane.setStyle(temp);
            voedselPane.setStyle(temp);
            pionnenPane.setStyle(temp);
            puntenPane.setStyle(temp);
            voedselPerBeurtPane.setStyle(temp);
            bruikbaarGereedschapPane.setStyle(temp);
            huttenPane.setStyle("-fx-border-color: black; -fx-border-insets:0 " + bruikbaarGereedschapPane.widthProperty().multiply(0.1) + " 0 0; -fx-border-width: 0 2 0 0; -fx-border-style: solid;");

            });
    }

    private void toonSpeler(int index)
    {
        String style = "";
        switch (index)
            {
            case 0:
                style = "-fx-text-fill:red;";
                break;
            case 1:
                style = "-fx-text-fill:blue;";
                break;
            case 2:
                style = "-fx-text-fill: #8e800c;";
                break;
            case 3:
                style = "-fx-text-fill:green;";
                break;

            }
        Speler s = mainScherm.getController().getSpelers().get(index);
        //String style = "-fx-background-color:blue;";

        Label spelerLabel = new Label(s.getKleur() + "");
        Label houtLabel = new Label(s.getPreviousHout() + " -> " + s.getAantalHout());
        Label leemLabel = new Label(s.getPreviousLeem() + " -> " + s.getAantalLeem());
        Label steenLabel = new Label(s.getPreviousSteen() + " -> " + s.getAantalSteen());
        Label goudLabel = new Label(s.getPreviousGoud() + " -> " + s.getAantalGoud());
        String temp = "              Vorig: " + s.getVoedselEindeVorigeBeurt();
        temp += "\n               +" + s.getAddedVoedsel() + " van rol  ";
        temp += "\n               +" + s.getPreviousVoedselPerBeurt() + " van v.p.beurt";
        temp += "\n               -" + s.getUpkeep() + " pionnen voeden";
        temp += "\n               Huidig: " + s.getAantalVoedsel();

        Label voedselLabel = new Label(temp);

        Label pionnenLabel = new Label(s.getPreviousPionnenSize() + " -> " + s.getPionnen().size());
        Label puntenLabel = new Label(s.getPreviousPunten() + " -> " + s.getPunten());
        Label voedselPerBeurtLabel = new Label(s.getPreviousVoedselPerBeurt() + " -> " + s.getVoedselPerBeurt());
        Label bruikbaarGereedschapLabel = new Label(s.getPreviousWaardeGereedschap() + " -> " + s.getHoeveelheidGereedschap());
        Label huttenBijgekochtLabel = new Label(s.getPreviousHuttenSize() + " -> " + s.getHutten().size());

        DeelPaneel spelerPaneel = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel houtPaneel = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel leemPaneel = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel steenPaneel = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel goudPaneel = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel voedselPaneel = new DeelPaneel(mainScherm, x, y * 3.25, this, true);
        DeelPaneel pionnenPaneel = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel puntenPaneel = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel voedselPerBeurtPane = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel bruikbaarGereedschapPane = new DeelPaneel(mainScherm, x, y / 1.25, this, true);
        DeelPaneel huttenBijgekochtPane = new DeelPaneel(mainScherm, x, y / 1.25, this, true);

        // DeelPaneel SHuttenPane = new DeelPaneel(mainScherm,x, y, mainScherm, this, true);
        spelerPaneel.addControl(spelerLabel,
                1, 56, Pos.CENTER, style);
        houtPaneel.addControl(houtLabel,
                1, 56, Pos.CENTER, style);
        leemPaneel.addControl(leemLabel,
                1, 56, Pos.CENTER, style);
        steenPaneel.addControl(steenLabel,
                1, 56, Pos.CENTER, style);
        goudPaneel.addControl(goudLabel,
                1, 56, Pos.CENTER, style);
        voedselPaneel.addControl(voedselLabel,
                1, 12.5, Pos.CENTER, style);
        pionnenPaneel.addControl(pionnenLabel,
                1, 56, Pos.CENTER, style);
        puntenPaneel.addControl(puntenLabel,
                1, 56, Pos.CENTER, style);
        voedselPerBeurtPane.addControl(voedselPerBeurtLabel,
                1, 56, Pos.CENTER, style);
        bruikbaarGereedschapPane.addControl(bruikbaarGereedschapLabel,
                1, 56, Pos.CENTER, style);
        huttenBijgekochtPane.addControl(huttenBijgekochtLabel,
                1, 56, Pos.CENTER, style);
        spelerLabel.setStyle(style);

        houtLabel.setStyle(style);

        leemLabel.setStyle(style);

        steenLabel.setStyle(style);

        goudLabel.setStyle(style);

        voedselLabel.setStyle(style);

        pionnenLabel.setStyle(style);

        puntenLabel.setStyle(style);

        voedselPerBeurtLabel.setStyle(style);

        VBox spelerBox = getVBox(new DeelPaneel[]
            {
            spelerPaneel, houtPaneel, leemPaneel, steenPaneel, goudPaneel, pionnenPaneel, puntenPaneel, bruikbaarGereedschapPane, huttenBijgekochtPane, voedselPerBeurtPane, voedselPaneel
            }, x, y);

        hbox.getChildren()
                .add(spelerBox);

    }

}
