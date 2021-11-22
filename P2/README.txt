Hubert Nathan
Lenglet Jolan
Lescorbie Valentin
Orveau Rémi
Rucher Thibault

Classes :
- Client : 
	le client se connecte auprès du serveur et demande à recevoir un certain nombre de chiffres à intervalle régulier.
	- constructeur du client se créer avec une HashMap ayant comme clé l'id de sa requête et une arraylist correspondant à tout les chiffres aléatoires envoyé par le serveur
	- addNew
	- connectNew
	- printNumber
	- getNumber(int) : renvoie la première valeur de la ArrayList correspondant à la clé de l'id de la requête mis en paramètre.
	- main
- ClockDist :
	- connect(int, int, Client) permet de se connecté au serveur en lui donnant, via les deux int, le x et le n correspondant à n le nombre de nombre que l'on souhaite et x le nombre de seconde entre chaque envoie de nombre aléatoire.
		renvoie un int correspondant au numero de la requête
	- close() renvoie un Status correspondant à la deconnexion
- ClockDistImp : implémente les fonction de ClockDist et a un compteur pour les demandes.
- Server : 
	- créer l'objet ClockDistImp et l'enregistre dans l'annuaire pour que le client puisse l'utiliser.
- Status :
	- classe enum permettant de renvoyer un status de connexion entre le client et le serveur.