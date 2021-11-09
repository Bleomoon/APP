package src;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Client {

    public int ID;

    public static int askInt(Scanner scanner){
        String sIn = scanner.nextLine();
        int in = -1;
        try {
            in = Integer.parseInt(sIn);
        } catch (Exception e) {
            System.out.println("Error ! Not an integer");
        }
        return in ;
    }
	
	public static Date askDate(Scanner scanner){
		int day, month, year = -1;
		do{
			System.out.println("Day : ");
			day = askInt(scanner);
		}while(day == -1);
		do{
			System.out.println("Month : ");
            month = askInt(scanner);
		}while(month == -1);
		do{
			System.out.println("Year : ");
            year = askInt(scanner);
		}while(year == -1);
        return new Date(day, month, year);
    }

    public static void main(String[] args) throws RemoteException {
        ManageDist objdist = null;
        int id = 0;
		
        try {
            System.out.println("Searching for object.");
            String url = "rmi://" + args[0] + "/echoservice";
            objdist = (ManageDist) Naming.lookup(url);
            id = objdist.connect();
            if (id != -1) {
				Scanner scanner = null;
				boolean end = false;
				Date date;
				int choice, idTask, adding, isDate= -1;
				Status myStatus;
				
				if(args.length > 1)
				{
					try {
						File file = new File(args[1]);
						scanner = new Scanner(file);
					}
					catch(FileNotFoundException e)
					{
						System.out.println("Could not read the file");
						end = true;
					}
				}
				else
					scanner = new Scanner(System.in);
               
                while (!end) {
					//initialise data, ask the choice and start the switch
					myStatus = null;
					date = null;
					do {
						System.out.println("What do you want to do ? :\n" +
								"1 : add a task\n" +
								"2 : changing priority of a task\n" +
								"3 : knowing how much task still need to be done\n" +
								"4 : see a task\n" +
								"5 : delete a task\n" +
								"0 : deconnexion\n"
						);
						
						choice = askInt(scanner);
					}while(choice == -1);
					
                    switch (choice){
                        case 0 : //client disconnect
                            myStatus = objdist.close(id);
							if(myStatus == Status.success)
							{
								System.out.println("BYE!");
								end = true;
							}
							else
								System.out.println(myStatus);
                            break;
                        case 1 : //client add a task
                            System.out.println("Give it a name!");
                            String name = scanner.nextLine();
							do {
								System.out.println("Do you want an ending date ? (1=yes, 0=no)");
								isDate = askInt(scanner);
                            }while(isDate != 0 && isDate != 1);
							
                            if (isDate == 1)
								date = askDate(scanner);
                            adding = objdist.add(name, date, id);
							if(adding == -1)
								System.out.println("Error in adding the new task");
							else
								System.out.println("Task N°" + adding + " add succefully!");
                            break;
                        case 2 : //client change priority
                            do {
								System.out.println("On which task do you want to change the priority ?");
								idTask = askInt(scanner);
							}while(idTask == -1);
							myStatus = objdist.changePriority(idTask, id);
							if(myStatus == Status.success)
								System.out.println("Priority of task : " + idTask + " changed successfully !");
							else
								System.out.println(myStatus);
                            break;
                        case 3 : //client ask number of tasks
                            System.out.println("There is " + objdist.getNbTask(id)+ " task to do in your todo List");
                            break;
                        case 4 : //client ask which task to see
                            do {
								System.out.println("Which task do you want to see ?");
								idTask = askInt(scanner);
                            }while(idTask == -1);
							Task t = objdist.getTask(idTask, id);
							if(t != null)
								System.out.println(t);
							else
								System.out.println("The task that you selected does not exist");
                            break;
                        case 5 : //client ask which one to delete
                            do {
								System.out.println("Which task do you want to delete ?");
								idTask = askInt(scanner);
							}while(idTask == -1);
                            myStatus = objdist.deleteTask(idTask,id);
							if(myStatus == Status.success)
								System.out.println("Task : " + idTask + " deleted successfully !");
							else
								System.out.println(myStatus);
                            break;
                        default:
                            System.out.println("Unidentified action");
                            break;
                    }
					System.out.print("\n");
                }
            } else {
                System.out.println("Server full. Try again later!");
            }
        } catch(Exception e) {
            objdist.close(id);
        }
    }
}
