<?php
// Projet TraceGPS - services web
// fichier :  api/services/SupprimerUnParcours.php
// Dernière mise à jour : 07/12/2022 par Erwan
// Rôle : ce service permet à un utilisateur de supprimer un de ses parcours (ou traces).
// Le service web doit recevoir 4 paramètres :
//pseudo : le pseudo de l'utilisateur qui demande à supprimer
//mdp : le mot de passe hashé en sha1 de l'utilisateur qui demande à supprimer
//idTrace : l'id de la trace à supprimer
//lang : le langage utilisé pour le flux de données ("xml" ou "json")
// Le service retourne un flux de données XML ou JSON contenant un compte-rendu d'exécution
// Les paramètres doivent être passés par la méthode GET :
//     http://<hébergeur>/tracegps/api/SupprimerUnParcours

// connexion du serveur web à la base MySQL
$dao = new DAO();

// Récupération des données transmises
$pseudo = ( empty($this->request['pseudo'])) ? "" : $this->request['pseudo'];
$mdp = ( empty($this->request['mdp'])) ? "" : $this->request['mdp'];
$idTrace = ( empty($this->request['idTrace'])) ? "" : $this->request['idTrace'];
$lang = ( empty($this->request['lang'])) ? "" : $this->request['lang'];

// "xml" par défaut si le paramètre lang est absent ou incorrect
if ($lang != "json") $lang = "xml";

// les paramètres doivent être présents 
if ($pseudo == "" || $mdp == "" || $idTrace == "")
{
    $msg = "Erreur : données incomplètes";
    $code_reponse = 400;
}
else
{
    // Si authentification incorrecte ex : mot de passe incorrect, on en informe l'utilisateur
    $niveauConnexion = $dao->getNiveauConnexion($pseudo, $mdp);
    if ($niveauConnexion == 0) {
        $msg = "Erreur : authentification incorrecte";
        $code_reponse = 401;
    }
    else {
        //Vérification que la trace à supprimmer existe bien 
        $existe = $dao->getUneTrace($idTrace); 
        if (! $existe)
        {
            $msg = "Erreur : parcours inexistant."; 
            $code_reponse = 400; 
        }
        else {
            // récupération de l'id de l'utilisateur connecté et celui du propriétaire de la trace
            $idUtilisateur = $dao->getUnUtilisateur($pseudo)->getId(); 
            $idPropriétaire = $dao->getUneTrace($idTrace)->getIdUtilisateur(); 
            //On peut maintenant vérifier que l'utilisateur connecté est bien le propriétaire de la trace 
            if ($idUtilisateur != $idPropriétaire)
            {
                $msg = "Erreur : vous n'êtes pas le propriétaire de ce parcours."; 
                $code_reponse = 400; 
            }
        }
    }
}


















?> 