package com.octest.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


@MultipartConfig
public class Tests extends HttpServlet {

    // permet de faire la copie du fichier temporaire vers le dossier finale
    public static final int TAILLE_TAMPON = 10240;

    // Indique l'adresse finale de stockage de tous les fichiers.
    public static final String CHEMIN_FICHIERS = "d:/Projet git/Cour-Java-EE/chapitre 4.2"; // A changer

    /*/Users/lebai/fichiers/*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // On récupère le champ description comme d'habitude
        String description = request.getParameter("description");

        request.setAttribute("description", description );
        System.out.println(description);

        // On récupère le champ du fichier
        // Utilistion de la classe Part pour récupérer le fichier
        Part part = request.getPart("fichier");

        System.out.println(part);

        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
            nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

            request.setAttribute(nomChamp, nomFichier);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
    }


    // Traitement du fichier
    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }

    // decoupe l'entete http du fichier et recupere le nom du fichier que l'on à upload
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }
}
