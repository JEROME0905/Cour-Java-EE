package com.octest.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.octest.beans.Utilisateur;

public class Noms {
    private Connection connexion;

    public List<Utilisateur> recupererUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Statement statement = null; // objet qui contient la requête
        ResultSet resultat = null; // objet qui contient le resultat de la requête

        loadDatabase(); // Permet la connexion à la base de données

        try {
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");

            // Récupération des données
            while (resultat.next()) {
                String nom = resultat.getString("nom"); // récupération d'une donnée de type String
                String prenom = resultat.getString("prenom");

                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);

                utilisateurs.add(utilisateur); // Ajout du nouvel utilisateur dans la liste.
            }
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            // Il est important de fermer chaque connexion pour la mémoire.
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }

        return utilisateurs;
    }

    private void loadDatabase() {
        // Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            // connexion a la base de données
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "Python2351;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        loadDatabase();

        try {
            // Mise en place d'une requête préparée
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            // definition des valeurs à tranmetttre.
            // Le 1 indique que nous allons renseigner la premiere valeur
            // Le 2 indique que nous allons renseigner la deuxième valeur
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());

            // Execution de la requête
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}