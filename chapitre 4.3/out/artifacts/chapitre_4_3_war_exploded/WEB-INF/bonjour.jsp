<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Test</title>
</head>
<body>
<!-- Utilisation de sessionScope pour afficher les informations de la session en cour -->
<c:if test="${!empty sessionScope.prenom && !empty sessionScope.nom }">

    <p>Vous êtes ${ sessionScope.prenom } ${sessionScope.nom } ! </p>

</c:if>

<form method="post" action="bonjour">
    <p>
        <label for="nom">Nom : </label>
        <input type="text" name="nom" id="nom" />
    </p>
    <p>
        <label for="prenom">Prénom :</label>
        <input type="text" name="prenom" id="prenom" />

    </p>

    <input type="submit" value="Envoyer"/>
</form>
</body>
</html>