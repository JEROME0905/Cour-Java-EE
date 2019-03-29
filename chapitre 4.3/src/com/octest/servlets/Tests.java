package com.octest.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Tests")
public class Tests extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // création d'un objet session pour récupérer les éléments de la session
        HttpSession session = request.getSession();

        // récupération du prenom de la session dans une vairable prenom String
        String prenom = (String) session.getAttribute("prenom");

        // Suppression de la session par cette ligne de code ou part le tmeout du serveur si l'utilisatur est inactif pendant un certain temps.
        session.invalidate();


        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// récupération du nom et du prenom envoyé par le formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        // Cread'un objet de type session
        HttpSession session = request.getSession();

        // Mise en memoire du prenom et du nom envoyé par le formulaire pendant toute la durrée de la session
        session.setAttribute("nom", nom);
        session.setAttribute("prenom", prenom);

        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);

    }
}
