/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.frame.mainScherm.center;

import Domein.Hut;
import Domein.Stapel;
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import ui.CustomCursor;
import ui.frame.mainScherm.MainScherm;
import ui.frame.mainScherm.right.DeelPaneel;
import ui.frame.mainScherm.right.RightPaneBlueprint;


/**
 *
 * @author NotAvailable
 */
public class StapelsPane extends Pane
{

    private MainScherm mainScherm;
    private ImageView background;
    private ImageView stapel1, stapel2, stapel3, stapel4;
    private DeelPaneel stapel1DeelPaneel, stapel2DeelPaneel, stapel3DeelPaneel, stapel4DeelPaneel;
    private HBox hbox;
    private Button button1 = new Button();
    private Button button2 = new Button();
    private Button button4 = new Button();
    private Button button3 = new Button();

    public StapelsPane(MainScherm mainScherm)
    {
        this.mainScherm = mainScherm;

        // setActions();
    }

    public void init()
    {
        updateImages();
        background = mainScherm.getImageView("img/StapelsPaneBg.png");
        background.fitWidthProperty().bind(widthProperty());
        background.fitHeightProperty().bind(heightProperty());
        hbox = new HBox();
        hbox.prefWidthProperty().bind(widthProperty());
        hbox.prefHeightProperty().bind(heightProperty());

        hbox = RightPaneBlueprint.getHBox(this, hbox, false, mainScherm, "", 0.5, 7, 1,
                new Node[]
                    {
                    stapel1, stapel2, stapel3, stapel4
                    },
                new String[]
                    {
                    "image", "image", "image", "image"
                    }, false, 0);

        stapel1DeelPaneel = (DeelPaneel) hbox.getChildren().get(0);
        stapel2DeelPaneel = (DeelPaneel) hbox.getChildren().get(1);
        stapel3DeelPaneel = (DeelPaneel) hbox.getChildren().get(2);
        stapel4DeelPaneel = (DeelPaneel) hbox.getChildren().get(3);
        stapel1DeelPaneel.addBackgroundImage(stapel1);
        stapel2DeelPaneel.addBackgroundImage(stapel2);
        stapel3DeelPaneel.addBackgroundImage(stapel3);
        stapel4DeelPaneel.addBackgroundImage(stapel4);

        getChildren().add(background);

        getChildren()
                .add(hbox);

        double w = 0.05;
        double h = 0;

        hbox.widthProperty().addListener(e ->
            {
            HBox.setMargin(stapel1DeelPaneel, new Insets(heightProperty().multiply(h).doubleValue(), widthProperty().multiply(w).doubleValue(), heightProperty().multiply(h * 4).doubleValue(), widthProperty().multiply(w).doubleValue()));
            });
        hbox.widthProperty().addListener(e ->
            {
            HBox.setMargin(stapel2DeelPaneel, new Insets(heightProperty().multiply(h).doubleValue(), widthProperty().multiply(w).doubleValue(), heightProperty().multiply(h).doubleValue(), widthProperty().multiply(w).doubleValue()));
            });
        hbox.widthProperty().addListener(e ->
            {
            HBox.setMargin(stapel3DeelPaneel, new Insets(heightProperty().multiply(h).doubleValue(), widthProperty().multiply(w).doubleValue(), heightProperty().multiply(h).doubleValue(), widthProperty().multiply(w).doubleValue()));
            });
        hbox.widthProperty().addListener(e ->
            {
            HBox.setMargin(stapel4DeelPaneel, new Insets(heightProperty().multiply(h).doubleValue(), widthProperty().multiply(w).doubleValue(), heightProperty().multiply(h).doubleValue(), widthProperty().multiply(w).doubleValue()));
            });
        button1 = new Button();
        button2 = new Button();
        button4 = new Button();
        button3 = new Button();
        button1.setStyle("-fx-background-color: transparent;");

        button2.setStyle("-fx-background-color: transparent;");

        button3.setStyle("-fx-background-color: transparent;");

        button4.setStyle("-fx-background-color:  transparent;");
        stapel1DeelPaneel.addControl(button1, 1, 1);
        stapel2DeelPaneel.addControl(button2, 1, 1);
        stapel3DeelPaneel.addControl(button3, 1, 1);
        stapel4DeelPaneel.addControl(button4, 1, 1);
        setActions();
    }

    private void setActions()
    {
        button1.setOnMouseEntered((event) ->
            {
            CustomCursor tempCursor = new CustomCursor(mainScherm, false);
            mainScherm.getStage().getScene().setCursor(new ImageCursor(tempCursor.getImage()));
            });
        button4.setOnMouseEntered((event) ->
            {
            CustomCursor tempCursor = new CustomCursor(mainScherm, false);
            mainScherm.getStage().getScene().setCursor(new ImageCursor(tempCursor.getImage()));
            });
        button2.setOnMouseEntered((event) ->
            {
            CustomCursor tempCursor = new CustomCursor(mainScherm, false);
            mainScherm.getStage().getScene().setCursor(new ImageCursor(tempCursor.getImage()));
            });
        button3.setOnMouseEntered((event) ->
            {
            CustomCursor tempCursor = new CustomCursor(mainScherm, false);
            mainScherm.getStage().getScene().setCursor(new ImageCursor(tempCursor.getImage()));
            });
        button1.setOnMouseExited((event) ->
            {
            CustomCursor tempCursor = new CustomCursor(mainScherm, true);
            mainScherm.getStage().getScene().setCursor(new ImageCursor(tempCursor.getImage()));
            });

        button2.setOnMouseExited((event) ->
            {
            CustomCursor tempCursor = new CustomCursor(mainScherm, true);
            mainScherm.getStage().getScene().setCursor(new ImageCursor(tempCursor.getImage()));
            });
        button3.setOnMouseExited((event) ->
            {
            CustomCursor tempCursor = new CustomCursor(mainScherm, true);
            mainScherm.getStage().getScene().setCursor(new ImageCursor(tempCursor.getImage()));
            });

        button4.setOnMouseExited((event) ->
            {
            CustomCursor tempCursor = new CustomCursor(mainScherm, true);
            mainScherm.getStage().getScene().setCursor(new ImageCursor(tempCursor.getImage()));
            });

        button1.setOnAction((event) ->
            {

            if (mainScherm.magPionnenPlaatsen())
                {

                if (mainScherm.getController().getStapel(0).getAantalPlaatsenVrij() > 0)
                    {

                    mainScherm.getController().plaatsPionnenOpVeld(mainScherm.getController().getStapel(0), 1);
                    if (mainScherm.shouldPlayLocatieSFX())
                        {
                        mainScherm.queueSFX("stapel", 0.4);
                        }
                    updateStapels();
                    mainScherm.volgendeSpeler();
                    }
                else
                    {
                    if (mainScherm.shouldPlayLocatieSFX())
                        {
                        mainScherm.queueSFX("negative", 50);
                        }
                    }
                }
            });
        button2.setOnAction((event) ->
            {
            if (mainScherm.magPionnenPlaatsen())
                {

                if (mainScherm.getController().getStapel(1).getAantalPlaatsenVrij() > 0)
                    {
                    mainScherm.getController().plaatsPionnenOpVeld(mainScherm.getController().getStapel(1), 1);
                    if (mainScherm.shouldPlayLocatieSFX())
                        {
                        mainScherm.queueSFX("stapel", 0.4);
                        }
                    updateStapels();
                    mainScherm.volgendeSpeler();
                    }
                else
                    {
                    if (mainScherm.shouldPlayLocatieSFX())
                        {
                        mainScherm.queueSFX("negative", 50);
                        }
                    }
                }
            });
        button3.setOnAction((event) ->
            {

            if (mainScherm.magPionnenPlaatsen())
                {

                if (mainScherm.getController().getStapel(2).getAantalPlaatsenVrij() > 0)
                    {
                    mainScherm.getController().plaatsPionnenOpVeld(mainScherm.getController().getStapel(2), 1);
                    if (mainScherm.shouldPlayLocatieSFX())
                        {
                        mainScherm.queueSFX("stapel", 0.4);
                        }
                    updateStapels();
                    mainScherm.volgendeSpeler();
                    }
                else
                    {
                    if (mainScherm.shouldPlayLocatieSFX())
                        {
                        mainScherm.queueSFX("negative", 50);
                        }
                    }
                }
            });
        button4.setOnAction((event) ->
            {

            if (mainScherm.magPionnenPlaatsen())
                {

                if (mainScherm.getController().getStapel(3).getAantalPlaatsenVrij() > 0)
                    {
                    mainScherm.getController().plaatsPionnenOpVeld(mainScherm.getController().getStapel(3), 1);
                    if (mainScherm.shouldPlayLocatieSFX())
                        {
                        mainScherm.queueSFX("stapel", 0.4);
                        }
                    updateStapels();
                    mainScherm.volgendeSpeler();
                    }
                else
                    {
                    if (mainScherm.shouldPlayLocatieSFX())
                        {
                        mainScherm.queueSFX("negative", 50);
                        }
                    }
                }
            });
    }

    public void updateStapels()
    {
        if (!mainScherm.getController().isHetSpelGedaan())
            {
            updateImages();
            updateStapelImages();
            updatePionnen();
            }

    }

    public void clearPionnen()
    {
        stapel1DeelPaneel.removeContent();
        stapel2DeelPaneel.removeContent();
        stapel3DeelPaneel.removeContent();
        stapel4DeelPaneel.removeContent();
    }

    private void updatePionnen()
    {
        for (int i = 0; i < 4; i++)
            {
            Stapel stapel = (Stapel) mainScherm.getController().getStapel(i);
            switch (i)
                {
                case 0:
                    if (stapel.getHuidigAantalPionnen() == 1)
                        {
                        stapel1DeelPaneel.setPion(mainScherm.getController().getSpelers().get(stapel.getSpelerIndex()));
                        }
                    break;
                case 1:
                    if (stapel.getHuidigAantalPionnen() == 1)
                        {
                        stapel2DeelPaneel.setPion(mainScherm.getController().getSpelers().get(stapel.getSpelerIndex()));
                        }
                    break;
                case 2:
                    if (stapel.getHuidigAantalPionnen() == 1)
                        {
                        stapel3DeelPaneel.setPion(mainScherm.getController().getSpelers().get(stapel.getSpelerIndex()));
                        }
                    break;
                case 3:
                    if (stapel.getHuidigAantalPionnen() == 1)
                        {
                        stapel4DeelPaneel.setPion(mainScherm.getController().getSpelers().get(stapel.getSpelerIndex()));
                        }
                    break;
                }
            }
    }

    private void updateStapelImages()
    {
        if (stapel1DeelPaneel.getChildren().size() == 0)
            {
            stapel1DeelPaneel.addBackgroundImage(stapel1);
            stapel1DeelPaneel.addControl(button1, 1, 1);
            }
        if (stapel2DeelPaneel.getChildren().size() == 0)
            {
            stapel2DeelPaneel.addBackgroundImage(stapel2);
            stapel2DeelPaneel.addControl(button2, 1, 1);
            }
        if (stapel3DeelPaneel.getChildren().size() == 0)
            {
            stapel3DeelPaneel.addBackgroundImage(stapel3);
            stapel3DeelPaneel.addControl(button3, 1, 1);
            }
        if (stapel4DeelPaneel.getChildren().size() == 0)
            {
            stapel4DeelPaneel.addBackgroundImage(stapel4);
            stapel4DeelPaneel.addControl(button4, 1, 1);
            }
    }

    private void updateImages()
    {
        for (int i = 0; i < 4; i++)
            {
            ImageView image = mainScherm.getImageView(mainScherm.getUrl("pionRood"));

            Stapel stapel = (Stapel) mainScherm.getController().getStapel(i);
            if (stapel == null)
                {
                System.out.println("Stapel null");
                }
            if (stapel.getBovensteHut() == null)
                {
                System.out.println("hut null");
                }
            Hut hut = stapel.getBovensteHut();
            int h = hut.getHoutKost();
            int l = hut.getLeemKost();
            int s = hut.getSteenKost();
            int g = hut.getGoudKost();
            String path = "";
            if ((h == 0) && (l == 0) && (s == 0) && (g == 3))
                {
                path = "img/Hutten/Hut0003.png";
                }
            if ((h == 0) && (l == 0) && (s == 1) && (g == 2))
                {
                path = "img/Hutten/Hut0012.png";
                }
            if ((h == 0) && (l == 0) && (s == 2) && (g == 1))
                {
                path = "img/Hutten/Hut0021.png";
                }
            if ((h == 0) && (l == 0) && (s == 3) && (g == 0))
                {
                path = "img/Hutten/Hut0030.png";
                }

            if ((h == 0) && (l == 1) && (s == 0) && (g == 2))
                {
                path = "img/Hutten/Hut0102.png";
                }
            if ((h == 0) && (l == 1) && (s == 1) && (g == 1))
                {
                path = "img/Hutten/Hut0111.png";
                }

            if ((h == 0) && (l == 1) && (s == 2) && (g == 0))
                {
                path = "img/Hutten/Hut0120.png";
                }

            if ((h == 0) && (l == 2) && (s == 0) && (g == 1))
                {
                path = "img/Hutten/Hut0201.png";
                }
            if ((h == 0) && (l == 2) && (s == 1) && (g == 0))
                {
                path = "img/Hutten/Hut0210.png";
                }

            if ((h == 0) && (l == 3) && (s == 0) && (g == 0))
                {
                path = "img/Hutten/Hut0300.png";
                }
            if ((h == 1) && (l == 0) && (s == 0) && (g == 2))
                {
                path = "img/Hutten/Hut1002.png";
                }
            if ((h == 1) && (l == 0) && (s == 1) && (g == 1))
                {
                path = "img/Hutten/Hut2001.png";
                }
            if ((h == 1) && (l == 0) && (s == 2) && (g == 0))
                {
                path = "img/Hutten/Hut1020.png";
                }
            if ((h == 1) && (l == 1) && (s == 0) && (g == 1))
                {
                path = "img/Hutten/Hut1101.png";
                }

            if ((h == 1) && (l == 1) && (s == 1) && (g == 0))
                {
                path = "img/Hutten/Hut1110.png";
                }
            if ((h == 1) && (l == 2) && (s == 0) && (g == 0))
                {
                path = "img/Hutten/Hut1200.png";
                }
            if ((h == 2) && (l == 0) && (s == 0) && (g == 1))
                {
                path = "img/Hutten/Hut2001.png";
                }

            if ((h == 2) && (l == 0) && (s == 1) && (g == 0))
                {
                path = "img/Hutten/Hut2010.png";
                }
            if ((h == 2) && (l == 1) && (s == 0) && (g == 0))
                {
                path = "img/Hutten/Hut2100.png";
                }
            if ((h == 3) && (l == 0) && (s == 0) && (g == 0))
                {
                path = "img/Hutten/Hut3000.png";
                }
            image = mainScherm.getImageView(path);
            switch (i)
                {
                case 0:
                    stapel1 = image;
                    break;
                case 1:
                    stapel2 = image;
                    break;
                case 2:
                    stapel3 = image;
                    break;
                case 3:
                    stapel4 = image;
                    break;
                }

            }

    }

}
