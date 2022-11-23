<?php
// Projet TraceGPS - services web
// fichier :  api/services/GetLesUtilisateursQueJautorise.php
// Dernière mise à jour : 23/11/2022 par Erwan

// Rôle : ce service permet à un utilisateur de changer son mot de passe
// Le service web doit recevoir 5 paramètres :
//     pseudo : le pseudo de l'utilisateur
//     mdp : l'ancien mot de passe hashé en sha1
//     nouveauMdp : le nouveau mot de passe
//     confirmationMdp : la confirmation du nouveau mot de passe
//     lang : le langage du flux de données retourné ("xml" ou "json") ; "xml" par défaut si le paramètre est absent ou incorrect
// Le service retourne un flux de données XML ou JSON contenant un compte-rendu d'exécution

// Les paramètres doivent être passés par la méthode GET :
//     http://<hébergeur>/tracegps/api/ChangerDeMdppseudo=europa&mdp=13e3668bbee30b004380052b086457b014504b3e&nouveauMdp=123&confirmationMdp=123&lang=xml

// connexion du serveur web à la base MySQL
$dao = new DAO();

// Récupération des données transmises
$pseudo = ( empty($this->request['pseudo'])) ? "" : $this->request['pseudo'];
$mdpSha1 = ( empty($this->request['mdp'])) ? "" : $this->request['mdp'];
$lang = ( empty($this->request['lang'])) ? "" : $this->request['lang'];

// "xml" par défaut si le paramètre lang est absent ou incorrect
if ($lang != "json") $lang = "xml";

// La méthode HTTP utilisée doit être GET
if ($this->getMethodeRequete() != "GET")
    {	$msg = "Erreur : méthode HTTP incorrecte.";
        $code_reponse = 406;
    }
else 
{
    // Les paramètres doivent être présents
    if ($pseudo=="" || $mdpSha1 =="")
    {
        $msg = "Erreur : données incomplètes";
        $code_reponse = 400;
    }
    else
    {
        // Si authentification incorrecte ex : mot de passe incorrect, on en informe l'utilisateur
        $niveauConnexion = $dao->getNiveauConnexion($pseudo, $mdpSha1);
        if ($niveauConnexion == 0) {
            $msg = "Erreur : authentification incorrecte";
            $code_reponse = 401; 
        }
        else 
        {
            //On va d'aord chercher l'id de l'utilisateur correspondant au pseudo
            $utilisateur = $dao->getUnUtilisateur($pseudo);
            $id = $utilisateur->getId();
            echo "Ceci est un test de l'id : ".$id;
            // on va chercher la liste des autorisations
            $listeAutorisations = $dao->getLesUtilisateursAutorises($id);
            //On compte le nombre d'autorisations
            $nombreAutorisations = count($listeAutorisations);
            //On peut enfin construire le message, bien sûr selon le nombre d'autorisations que l'on trouve ci dessus !
            if($nombreAutorisations == 0)
            {
                $msg = "Aucune autorisation accordée par ".$pseudo. ".";
            }
        }
    }
}  
        
        
       