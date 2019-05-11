
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<!-- Cette ligne permet d'inclure un fichier jsp principe du template -->
<%@ include file="menu.jsp" %>
<p>
    Bonjour ${ 6 * 3 }
    ${empty name ? '' :name } <!-- Condition avec un ternaire. Le : signifie else -->

    <% /* String name = (String) request.getAttribute("name"); out.println(name);*/%>

    ${noms[0]}
    ${ auteur.prenom} ${ auteur.nom }
    ${auteur.actif ? 'Vous êtes très actif !' : 'Vous êtes inactif !'}
</p>
<p>
    <!-- Récupération de la variable variable  -->
    <% String test = (String) request.getAttribute("variable");

        //System.out.println(test);
        out.println(test);%>
</p>

<p>     <!-- Exemple de code JSTL -->
    <c:out value="Bonjour !"/>

</p>

<!-- JSTL ET LES VARIABLES -->
<p><c:out value="Hello"/></p> <!-- Evite la faille XSS echappe le code  -->
<!-- On peut aussi utiliser expression language -->
<c:out value="${ variable}" default="Valeur par défaut si la variable est vide" escapeXml="false"/>
<!-- escapeXml permet de désactiver la faille XSS -->
<!--  Autre moyen d'écrire la ligne du dessus -->
<p><c:out value="${ variable}" escapeXml="false">Valeur par défaut </c:out></p>

<!-- Créer des variables à l'intérieur de nos JSP -->
<c:set var="pseudo" value="jerome21" scope="page"/>
<!-- Création de la variable pseudo qui à pour valeur Mateo21 et scope détermine la porté de la variable-->
<!-- Explication des différentes portées de scope -->

<!-- page :  variable valable uniquement dans le fichier jsp courant-->
<!-- request :  variable valable pendant tout la requete-->
<!-- session :  variable valable pendant toute la visite du visiteur-->
<!-- application : variable valable pour toute l'application -->

<!-- Affichage de la variable Créée-->
<c:out value="${pseudo }"/>

<!-- Autre façon de créer une variable -->

<c:set var="pseudo2" scope="page">Lucile21</c:set>
<c:out value="${pseudo2}"/>

<!-- Modifier la variable d'un bean -->
<c:set target="${auteur}" property="prenom" value="sebastien"/>
<c:out value="${auteur.prenom }"></c:out>

<!-- Supprimer une variable  -->
<c:remove var="pseudo" scope="page"/>
<!-- Après cette ligne ci-dessus la variable est supprimer le la mémoire -->

<!--  JSTL ET CONDITIONS  -->

<!-- comment déclarer un if avec la librairie JSTL -->
<c:if test="${50 > 10 }" var="test3" scope="page"> c'est vrai</c:if>
<!-- l'attribut var va garder en memoire le resultat de la condition ici le résultat est vrai donc la valeur de la variable test3 est true -->
<c:out value="${test3 }"/>

<!-- Ecrire des conditions multiples -->


<!-- JSTL ET LES BOUCLES -->

<!-- Boucle foreach -->

<!-- Une boule qui séxécute un certain nombre de fois -->
<c:forEach var="i" begin="0" end="10" step="1">
    <p><c:out value="${i }"/> !</p>
</c:forEach>

<!-- Une boucle sur un array (tableau) -->
<c:forEach items="${titres }" var="titre" varStatus="status">
    <p> N° <c:out value="${status.count}"/> : <c:out value="${titre}"/></p>
</c:forEach>

<!-- Une boucle qui travaille sur une chaîne de caractères découpée -->
<c:forTokens var="morceau" items="Un élément/Encore un autre élément/Un dernier pour la route" delims="/ ">
    <p>${ morceau }</p>
</c:forTokens>
<!-- LES FORMULAIRES AVEC JAVA EE -->
<!--  Création d'un formulaire -->
<!--  1er exemple -->

<c:if test="${ !empty nom }"><p><c:out value="Bonjour, vous vous appelez ${ nom }"/></p></c:if>

<form method="post" action="/test/bonjour">
    <label for="nom"> Nom : </label>
    <input type="text" name="nom" id="nom"/>
    <input type="submit" value="Envoyer"/>
</form>

</body>
</html>