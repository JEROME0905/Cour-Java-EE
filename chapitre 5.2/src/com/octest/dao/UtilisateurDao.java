package com.octest.dao;

import java.util.List;

import com.octest.beans.Utilisateur;

public interface UtilisateurDao {
	// l'interface DAO regroupe tous les méthodes à definir dans la classe UtilisateurDaoImpl.
	
	// Methode pour ajouter quelqu'un à la liste des personnes.
    void ajouter(Utilisateur utilisateur);
    
    // Methode pour récupérer la liste des methodes
    List<Utilisateur> lister();
}