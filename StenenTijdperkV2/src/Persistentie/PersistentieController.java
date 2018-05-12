/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import Domein.DomeinController;
import Domein.GereedschapsFiche;
import Domein.Hut;
import Domein.Kleur;
import Domein.Pion;
import Domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import javafx.util.Pair;

/**
 *
 * @author kenzo
 */
public class PersistentieController {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/stenentijdperkdb?user=stenentijdperk&password=root&serverTimezone=UTC";

    public PersistentieController() {

    }

    public void saveNew(String naam, DomeinController dc) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(JDBC_URL)) {

                // DC
                PreparedStatement qrySaveDC = con.prepareStatement("INSERT INTO domeincontroller(domeinControllerNaam, huidigeSpelerIndex, startSpelerIndex, rondeNummer) VALUES(?, ?, ?, ?)");
                qrySaveDC.setString(1, naam);
                qrySaveDC.setInt(2, dc.getHuidigeSpelerIndex());
                qrySaveDC.setInt(3, dc.getStartSpelerindex());
                qrySaveDC.setInt(4, dc.getRondeNummer());

                // Spelers
                PreparedStatement qrySaveSpelers[] = new PreparedStatement[dc.getSpelers().size()];

                for (int i = 0; i < dc.getSpelers().size(); ++i) {
                    qrySaveSpelers[i] = con.prepareStatement("INSERT INTO speler(spelerIndex, dcNaam, kleur, voedselPerBeurt, punten, aantalGoud, aantalHout, aantalLeem, aantalSteen, aantalVoedsel, previousHout, previousLeem, previousSteen, previousGoud, previousVoedsel, previousPunten, previousVoedselPerBeurt, previousPionnenSize, previousWaardeGereedschap, previousHuttenSize)"
                            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                    qrySaveSpelers[i].setInt(1, i);
                    qrySaveSpelers[i].setString(2, naam);
                    qrySaveSpelers[i].setString(3, dc.getSpelers().get(i).getKleur().toString());
                    qrySaveSpelers[i].setInt(4, dc.getSpelers().get(i).getVoedselPerBeurt());
                    qrySaveSpelers[i].setInt(5, dc.getSpelers().get(i).getPunten());
                    qrySaveSpelers[i].setInt(6, dc.getSpelers().get(i).getAantalGoud());
                    qrySaveSpelers[i].setInt(7, dc.getSpelers().get(i).getAantalHout());
                    qrySaveSpelers[i].setInt(8, dc.getSpelers().get(i).getAantalLeem());
                    qrySaveSpelers[i].setInt(9, dc.getSpelers().get(i).getAantalSteen());
                    qrySaveSpelers[i].setInt(10, dc.getSpelers().get(i).getAantalVoedsel());
                    qrySaveSpelers[i].setInt(11, dc.getSpelers().get(i).getPreviousHout());
                    qrySaveSpelers[i].setInt(12, dc.getSpelers().get(i).getPreviousLeem());
                    qrySaveSpelers[i].setInt(13, dc.getSpelers().get(i).getPreviousSteen());
                    qrySaveSpelers[i].setInt(14, dc.getSpelers().get(i).getPreviousGoud());
                    qrySaveSpelers[i].setInt(15, dc.getSpelers().get(i).getPreviousVoedsel());
                    qrySaveSpelers[i].setInt(16, dc.getSpelers().get(i).getPreviousPunten());
                    qrySaveSpelers[i].setInt(17, dc.getSpelers().get(i).getPreviousVoedselPerBeurt());
                    qrySaveSpelers[i].setInt(18, dc.getSpelers().get(i).getPreviousPionnenSize());
                    qrySaveSpelers[i].setInt(19, dc.getSpelers().get(i).getPreviousWaardeGereedschap());
                    qrySaveSpelers[i].setInt(20, dc.getSpelers().get(i).getPreviousHuttenSize());
                }

                // Gereedschapsfiches
                ArrayList<PreparedStatement> qrySaveGereedschapsFiches = new ArrayList<PreparedStatement>();

                for (int i = 0; i < dc.getSpelers().size(); ++i) {
                    for (GereedschapsFiche gf : dc.getSpelers().get(i).getGereedschapsFiches()) {
                        PreparedStatement temp = con.prepareStatement("INSERT INTO gereedschapsfiche(spelerIndex, dcNaam, waarde, isGebruikt) VALUES(?, ?, ?, ?)");

                        temp.setInt(1, i);
                        temp.setString(2, naam);
                        temp.setInt(3, gf.getWaarde());
                        temp.setBoolean(4, gf.isGebruikt());

                        qrySaveGereedschapsFiches.add(temp);
                    }
                }

                // Pionnen
                ArrayList<PreparedStatement> qrySavePionnen = new ArrayList<PreparedStatement>();

                for (int i = 0; i < dc.getSpelers().size(); ++i) {
                    for (Pion p : dc.getSpelers().get(i).getPionnen()) {
                        PreparedStatement temp = con.prepareStatement("INSERT INTO pion(spelerID, dcNaam) VALUES(?, ?)");
                        temp.setInt(1, i);
                        temp.setString(2, naam);

                        qrySavePionnen.add(temp);
                    }
                }

                // Stapels
                PreparedStatement qrySaveStapels[] = new PreparedStatement[dc.getSpelbord().getStapels().size()];

                for (int i = 0; i < dc.getSpelbord().getStapels().size(); ++i) {
                    qrySaveStapels[i] = con.prepareStatement("INSERT INTO stapel(stapelIndex, dcNaam) VALUES(?, ?)");

                    qrySaveStapels[i].setInt(1, i);
                    qrySaveStapels[i].setString(2, naam);
                }

                // Hut
                ArrayList<PreparedStatement> qrySaveHutten = new ArrayList<PreparedStatement>();

                for (int i = 0; i < dc.getSpelers().size(); ++i) {
                    for (Hut h : dc.getSpelers().get(i).getHutten()) {
                        PreparedStatement temp = con.prepareStatement("INSERT INTO hut(spelerIndex, dcNaam, houtKost, steenKost, leemKost, goudKost) VALUES(?, ?, ?, ?, ?, ?)");

                        temp.setInt(1, i);
                        temp.setString(2, naam);
                        temp.setInt(3, h.getHoutKost());
                        temp.setInt(4, h.getSteenKost());
                        temp.setInt(5, h.getLeemKost());
                        temp.setInt(6, h.getGoudKost());
                        qrySaveHutten.add(temp);
                    }
                }
                for (int i = 0; i < dc.getSpelbord().getStapels().size(); ++i) {
                    for (Hut h : dc.getSpelbord().getStapel(i).getHutten()) {
                        PreparedStatement temp = con.prepareStatement("INSERT INTO hut(stapelIndex, dcNaam, houtKost, steenKost, leemKost, goudKost) VALUES(?, ?, ?, ?, ?, ?)");

                        temp.setInt(1, i);
                        temp.setString(2, naam);
                        temp.setInt(3, h.getHoutKost());
                        temp.setInt(4, h.getSteenKost());
                        temp.setInt(5, h.getLeemKost());
                        temp.setInt(6, h.getGoudKost());
                        qrySaveHutten.add(temp);
                    }
                }

                qrySaveDC.executeUpdate();
                for (PreparedStatement p : qrySaveSpelers) {
                    p.executeUpdate();
                }
                for (PreparedStatement p : qrySaveGereedschapsFiches) {
                    p.executeUpdate();
                }
                for (PreparedStatement p : qrySavePionnen) {
                    p.executeUpdate();
                }
                for (PreparedStatement p : qrySaveStapels) {
                    p.executeUpdate();
                }
                for (PreparedStatement p : qrySaveHutten) {
                    p.executeUpdate();
                }

                System.out.println("SAVE [" + naam + "] SUCCES!");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Returnt all domeincontroller in een pair van <String, int> volgens <naam, rondeNummer>
     * @return 
     */
    public ArrayList<Pair> getSaveNamesWithRoundNr() {
        ArrayList<Pair> saves = new ArrayList<Pair>();
        
        try (Connection con = DriverManager.getConnection(JDBC_URL + "&useSSL=false")) 
            {
                PreparedStatement qryGetSave = con.prepareStatement("SELECT domeinControllerNaam, rondeNummer FROM domeincontroller");
                ResultSet rs = qryGetSave.executeQuery();
                
                while(rs.next()){
                    Pair temp = new Pair(rs.getString("domeinControllerNaam"), rs.getInt("rondeNummer"));
                    saves.add(temp);
                }
            }
        catch(SQLException ex){
                System.out.println(ex);
        }

        return saves;
    }
    
    public DomeinController loadGame(String naam){
        DomeinController newDC = new DomeinController(true);
        
        try(Connection con = DriverManager.getConnection(JDBC_URL + "&useSSL=false")){
            PreparedStatement qryGetDomeinController = con.prepareStatement("SELECT * FROM domeincontroller WHERE domeinControllerNaam = ?");
            qryGetDomeinController.setString(1, naam);
            
            ResultSet rs = qryGetDomeinController.executeQuery();
            
            rs.next();
            // Add dc
            newDC.setStartSpelerIndex(rs.getInt("startSpelerIndex"));
            newDC.setHuidigeSpelerIndex();
            newDC.setRondeNummer(rs.getInt("rondeNummer"));
            
            PreparedStatement qryGetSpelers = con.prepareStatement("SELECT * FROM speler WHERE dcNaam = ?");
            qryGetSpelers.setString(1, naam);
            
            rs = qryGetSpelers.executeQuery();
            
            //Add spelers
            while(rs.next())
            {
                Kleur kleur = Kleur.valueOf(rs.getString("kleur"));
                int index = rs.getInt("spelerIndex");
                Speler temp = new Speler(newDC, kleur, index);
                temp.setVoedselPerBeurt(rs.getInt("voedselPerBeurt"));
                temp.setPunten(rs.getInt("punten"));
                
                
                
            }
            
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
    
        return newDC;
    }
}
