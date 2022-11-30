<?php
// Projet TraceGPS - services web
// fichier :  api/services/DemanderUneAutorisation.php
// Dernière mise à jour : 30/11/2022 par Erwan

// Rôle : ce service web permet à un utilisateur de demander un nouveau mot de passe s'il l'a oublié.
// Le service web doit recevoir 6 paramètres :
//     pseudo : le pseudo de l'utilisateur
//mdp : le mot de passe hashé en sha1 de l'utilisateur qui demande l'autorisation
//pseudoDestinataire : le pseudo de l'utilisateur à qui on demande l'autorisation
//texteMessage : le texte d'un message accompagnant la demande
 //nomPrenom : le nom et le prénom du demandeur
//lang : le langage du flux de données retourné ("xml" ou "json") ; "xml" par défaut si le paramètre est absent ou incorrect
// Le service retourne un flux de données XML ou JSON contenant un compte-rendu d'exécution
// Les paramètres doivent être passés par la méthode GET :
//     http://<hébergeur>/tracegps/api/CreerUnUtilisateur?pseudo=turlututu&adrMail=delasalle.sio.eleves@gmail.com&numTel=1122334455&lang=xml
