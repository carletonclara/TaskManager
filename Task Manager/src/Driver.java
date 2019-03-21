	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Scanner;
	import java.util.Hashtable;

	public class Driver {

		public static void main(String[] args) throws FileNotFoundException {
			ArrayList<ArrayList<String>>allTasks = readTaskFile("tasks.csv");
			
			//test to make sure it prints
			for(ArrayList<String>al:allTasks){ //for each ArrayList<String> "al" in allTasks
				for(String s: al){ //for each String "s" in "al"
					System.out.println(s); //print s
				}
				System.out.println("\n\n***********************************\n\n"); //print a divider between tasks
			}
			//generate new array that takes in task object
			ArrayList<Task> taskList = new ArrayList<Task>();
			taskList = makeList(allTasks); //fill new array with list
			System.out.println("\n**********UNSORTED TASK LIST**********\n"+taskList); //TEST list
			
			//sort tasks in order of highest priority
			Collections.sort(taskList);
			System.out.println("\n**********SORTED TASK LIST**********\n"+taskList);
			
			//create a Hashtable
			Hashtable<String,ArrayList<Task>> requestList = new Hashtable<String,ArrayList<Task>>();
			requestList = requestList(taskList); //sort the tasks into the hashtable
			System.out.println(requestList);
			
			System.out.println("\n************Priority Queue Tests****************\n");
			
			//create new priorityQueue; data type = tasks
			PriorityQueue<Task> pQ = new PriorityQueue<Task>();
			//try to remove from an empty priority queue
			pQ.remove(0); //does not cause an error
			//add tasks to the queue
			pQ.add(taskList.get(2));
			pQ.add(taskList.get(1));
			pQ.add(taskList.get(5));
			pQ.add(taskList.get(3));
			System.out.println("PRIORITY QUEUE: adding 4 tasks\n"+ pQ); //print check
			//check if the last item added is correct (check lastAdded() works)
			System.out.println("LAST ADDED:\n" + pQ.lastAdded());

			//remove a few tasks
			pQ.remove(1);
			pQ.remove(6); //try to remove something that isn't there
			
			//check if task contains something or not
			if(pQ.contains(taskList.get(1))==true) {
				System.out.println("Test passed: queue contains " + taskList.get(2) + "\n");}
			else {System.out.println("Test failed\n");}
			
			if(pQ.contains(taskList.get(7))==false) {
				System.out.println("Test passed: queue does not contain " + taskList.get(7) + "\n");}
			else {System.out.println("Test failed\n");}
			
			System.out.println("PRIORITY QUEUE: removing 2 tasks 1 that is there and 1 that is not\n"+ pQ); //print check
			
			//check top priority of list
			//testing check() method
			System.out.println("TOP PRIORITY: \n" + pQ.check(0));
			
			pQ.add(taskList.get(8));
			pQ.add(taskList.get(4));
			pQ.add(taskList.get(6));
			System.out.println("PRIORITY QUEUE: adding 3 more\n"+ pQ);
			
			pQ.remove(0);
			System.out.println("PRIORITY QUEUE: removing top priority\n"+ pQ);
			
			
			System.out.println("\n************Task Manager Tests****************\n");
			
			//Create new taskManager 
			TaskManager tM = new TaskManager();
			//try to remove from an empty TaskManager
			tM.remove(taskList.get(3)); //does not cause any errors
			//add a few tasks
			tM.add(taskList.get(4));
			tM.add(taskList.get(6));
			tM.add(taskList.get(7));
			tM.add(taskList.get(2));
			System.out.println("TASK MANAGER: adding 4 tasks");
			System.out.println(tM); //print test
			//ask for requester's tasks (in list)
			System.out.println("\nNCC1701-D crew tasks: \n" + 
									tM.getRequests("NCC1701-D crew"));
			//ask for requester's tasks (not in list, should return null)
			System.out.println("\nClara's tasks: \n" +
									tM.getRequests("Clara"));	
			
			System.out.println("\nTASK MANAGER: removing last task of requester ");
			tM.remove(taskList.get(2));
			System.out.println(tM);
			
			tM.add(taskList.get(5));
			tM.add(taskList.get(3));
			tM.add(taskList.get(0));
			
			System.out.println("\nTASK MANAGER: add more tasks ");
			System.out.println(tM);
			System.out.println("\nTASK MANAGER: printed in order");
			System.out.println(tM.printInOrder());
		}
		
		
	public static ArrayList<ArrayList<String>> readTaskFile(String filename) throws FileNotFoundException{
		//tasks will hold an ArrayLists of each task
		ArrayList<ArrayList<String>> tasks = new ArrayList<ArrayList<String>> ();
		Scanner sc = new Scanner(new File(filename));
		//we can discard the first line, since it just describes the contents of each cell
		sc.nextLine();
		while (sc.hasNextLine()){
			//taskBits will hold the pieces of each individual task
			ArrayList<String>taskBits = new ArrayList<String>();
	         //each line is a task
			//split each line on commas
			String[] allSplitTask = sc.nextLine().split(",");
			//add all the pieces of the task to a single ArrayList
			for (String a:allSplitTask){
				taskBits.add(a);
			}
			//add taskBits to tasks
			tasks.add(taskBits);
	      }
		sc.close();
		return tasks;
	}
	
	public static ArrayList<Task> makeList(ArrayList<ArrayList<String>> aT){
		ArrayList<Task> tL = new ArrayList<Task>();	//create a new arraylist to hold Tasks	
		//for every array in allTasks, make it a Task instead of arraylist
		for(ArrayList<String>al:aT){ 
			Task t = new Task(); //create a new Task object
			//fill new task object with arraylists' elements
			t.setDateReq(al.get(0));
			t.setDateDue(al.get(1));
			t.setPriority(Integer.parseInt(al.get(2)));	
			t.setRequester(al.get(3));
			t.setRequest(al.get(4));
			tL.add(t);	
		}
		return tL;
	}
	
	public static Hashtable<String,ArrayList<Task>> requestList(ArrayList<Task>tL) {
		//Create a new hashtable
		Hashtable<String,ArrayList<Task>> rL = new Hashtable<String,ArrayList<Task>>();
		//go through every task in the list
		for(int i=0; i<=tL.size()-1;i++) {
			Task task = tL.get(i); //way to identify task
			//create list of tasks of a certain requester
			ArrayList<Task> requests = new ArrayList<Task>();
			//check if the requester key already exists
			if(rL.containsKey(task.getRequester())) {//if it exists
				requests = rL.get(task.getRequester()); //get the value; the arraylist
				requests.add(task); //add new task
			}
			else { //if it doesn't exist
				requests.add(task); //use empty arraylist and add task
			}
		Collections.sort(requests);	//sort requests in priority		
		rL.put(task.getRequester(), requests);	//add key and value to hashtable
		}	
		return rL;
	}
	

}

	
	

