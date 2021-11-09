Rucher Thibault
Lescorbie Valentin
Lenglet Jolan
Orveau Rémi
Hubert Nathan 

fichier de code dans le dossier Sources
Classes : 
- ManageDist:
	applique les demande du client via plusieurs fonction
	- fonction connect prend un int correspondant à l'identifiant du client qui fait la requête de connexion, son entré crée sa ligne dans la Hashmap todo_lists
	- fonction get_task prend un int correspondant à l'id de la tache et un autre correspondant à l'id du client, elle renvoie la task correspondante.
	- fonction add prend un String, une Date(ou null) et l'id du client. Elle ajoute la task à la fin de la liste et renvoie son identifiant après création.
	- fonction change_priority, prend l'id de la task et l'id du client. Prend la task à l'id indiqué et la met en première position en décalant toute les autres.
	- fonction get_nb_task, prend l'id du client et renvoie le nombre de task qui lui sont affilié.
	- delete task, prend l'id de la task et celui du client, supprime la task correspondant à l'id de la task et du client de la liste du client.
	- close, prend l'id du client et ferme la connection en supprimant les données de la liste du client.
- ManageDistImpl:
	implémente les fonctions de manageDist 
Serveur:
	- crée l'objet manageDistImpl et l'enregistre dans l'annuaire pour que le client puisse l'utiliser.
Client:
	cherche à se connecter. si connection réussi il peut envoyé des requêtes au serveur.
	ajouter des tâches à faire avec ou sans date de fin. 
	demander à voir une tâche particulière.
	changer la priorité d'une tâche.
	avoir le nombre de tâche inscrite dans sa todolist.
	supprimé une tâche.
	fermer sa connection avec le serveur.
	 
Task:
	à deux attributs : le nom de la task TASK, un string Finale
			   la date de rendu DEAD_LINE, un type Date
	- constructeur avec un string(le nom de la task) et une date(la date de fin de la task)
	- un autre constructeur avec juste un string(le nom de la task) la date est mise à null.
	- toString qui renvoie la task sous forme d'une chaine de caractère.
	- getName renvoie le nom de la task en chaine de caractère.
	- getDate renvoie la date de la task en forme Date.
TodoList:
	à un attibut : un arrayList correspondant à une liste de tâche.
	- un constructeur
	- addTask prend une task et l'ajoute en fin de la liste
	- isContent prend un string et vérifie si une autre tache existe déjà avec ce nom.
	- getTask prend un int et renvoie la tâche correspondante.
Date:
	à 3 attributs : day correpond au jour
			month correspond au mois 
			year correspond à l'année
	- un constructeur avec 3 int qui crée la date mois, jour, année
RemoteException :
noClientException : 
	lorsqu'on essaye de ce déconnecté alors que le nombre de client est à 0.
	se déclenche lorsque qu'un client est toujours connecté alors que le nombre de client est censé être à 0.


script
si on tape un chiffre entre 0 et 5 une action est possible 
le 0 ets pour la deconnexion
le 1 est pour l'ajout d'une tache 
	suite au 1 on doit entrée la ligne correspondant au nom de la tache et on a la possibilité de dire qu'on veux une date ou non en entrant 1 ou 0 puis les numeros de la date si on en veux une.
le 2 est pour rendre prioritaire une tache 
	on doit entré le numéro de la tache à prioriser.
le 3 est pour connaitre le nombre de tache restante -> un affichage viens donné l'information
le 4 est pour voir une tache 
	on doit entré le numéro de la tache qu'on souhaite voir
le 5 est pour supprimer une tache
	on doit entré le numéro de la tache à supprimé
ça boucle pour retourner au choix de l'action 