package com.octest.servlets;

import com.octest.beans.Auteur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Test")
public class Test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Objet request indique les paramètres que l'utilisateur à pu envoyer.
        // Objet que l'on construit pour renvoyer une page.

        //response.setContentType("text/html"); // indique que nous renvoyons du html
        //response.setCharacterEncoding("UTF-8"); // Evite les problèmes avec les accents

        //PrintWriter out = response.getWriter();
        //:out.println("Bonjour !");

        // Exemple pour l'expression language
        String[] noms = {"Mathieu", "Lucile", "Jerome"};
        request.setAttribute("noms", noms);

        // récupérer un parametre de l'URL
        String name = request.getParameter("name");
        System.out.println(name);
        request.setAttribute("name", name);

        // Comment envoyer une variable à la JSP.
        String message = "Au revoir !"; // Définition de la variable à envoyer.
        request.setAttribute("variable", message); // Définition de l'attribut et attribution de la variable message.

        // Envoie d'un objet java bean

        Auteur auteur = new Auteur();
        auteur.setPrenom("Mathieu");
        auteur.setNom("Nebra");
        auteur.setActif(true);

        request.setAttribute("auteur", auteur);

        // JSTL ET LES BOUCLES
        String titres[] = {"Salut les copains ", "Au revoir les copains", " Salut la foule"};

        request.setAttribute("titres", titres);

        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // LES FORMULAIRES AVEC JAVA EE
        // Récupéaration des données du formulaire

        String nom = request.getParameter("nom");
        request.setAttribute("nom", nom);

        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);

    }
}
