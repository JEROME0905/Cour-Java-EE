<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Test</title>
</head>
<body>
<c:out value="${ prenom }" />
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