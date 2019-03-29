package com.octest.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Test")
public class Test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des cookies dans un tableau
        Cookie[] cookies = request.getCookies();

        // Si le cookie n'est pas nul
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // On cherche dans le tableau un cookie qui est égal à prenom
                if (cookie.getName().equals("prenom")) {
                    request.setAttribute("prenom", cookie.getValue());
                }
            }
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperation du nom et du prenom grace au formulaire.
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");

        // Création d'un nouveau cookie prenom
        Cookie cookie = new Cookie("prenom",prenom);

        // Définition de la date d'expiration du cookie en seconde ici 1 mois
        cookie.setMaxAge(60 * 60 * 24 * 30);

        // Stockage du prenom du visiteur
        response.addCookie(cookie);

        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);

    }
}
