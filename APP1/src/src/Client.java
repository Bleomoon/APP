package src;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Client {

    public int ID;

    public static int askInt(Scanner scanner){
        String sIn = scanner.nextLine();
        int in = 0;
        try {
            in = Integer.parseInt(sIn);
        } catch (Exception e) {
            System.out.println("Erreur ce n'est pas un entier");
        }
        return in ;
    }

    public static void main(String[] args) throws RemoteException {
        ManageDist objdist = null;
        int id = 0;
        try {
            System.out.println("Recherche de l'objet.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ManageDist) Naming.lookup(url);
            id = objdist.connect();
            if (id != -1) {
                Scanner scanner = new Scanner(System.in);
                boolean end = false;
                while (!end) {
                    System.out.println("Quelles actions souhaitez-vous faire :\n" +
                            "1 : ajout d'une tache\n" +
                            "2 : rendre prioritaire une tache\n" +
                            "3 : savoir le nombre qu'il reste a faire\n" +
                            "4 : voir une tache\n" +
                            "5 : supprimer une tache\n" +
                            "0 : deconnexion\n"
                    );
                    int choice = askInt(scanner);
                    int idTask;
                    switch (choice){
                        case 0 :
                            objdist.close(id);
                            end = true;
                            break;
                        case 1 :
                            System.out.println("Donnez le nom de la tache");
                            String name = scanner.nextLine();
                            System.out.println("Voulez vous une date de fin. (1=oui, 0=non)");
                            int isDate = askInt(scanner);
                            Date date = null;
                            if (isDate == 1) {
                                System.out.println("Jour : ");
                                int day = askInt(scanner);
                                System.out.println("Mois : ");
                                int month = askInt(scanner);
                                System.out.println("Annee : ");
                                int year = askInt(scanner);
                                if ( day == 0 || month == 0 || year == 0) {
                                    date = null;
                                } else {
                                    date = new Date(day, month, year);
                                }
                            }
                            objdist.add(name, date, id);
                            break;
                        case 2 :
                            System.out.println("Quel est la tache que vous voulez priorisez ?");
                            idTask = askInt(scanner);
                            objdist.changePriority(idTask, id);
                            break;
                        case 3 :
                            System.out.println("Il reste " +objdist.getNbTask(id)+ " tache dans votre todo List");
                            break;
                        case 4 :
                            System.out.println("Quel tache voulez vous voir ?");
                            idTask = askInt(scanner);
                            System.out.println(objdist.getTask(idTask, id).toString());
                            break;
                        case 5 :
                            System.out.println("Quel est la tache que vous voulez supprimez ?");
                            idTask = askInt(scanner);
                            objdist.deleteTask(idTask,id);
                            break;
                        default:
                            System.out.println("Action inexistante");
                            break;
                    }
                }
            } else {
                System.out.println("Serveur plein. Reessayer ulterieurement");
            }
        } catch(Exception e) {
            objdist.close(id);
        }
    }
}
