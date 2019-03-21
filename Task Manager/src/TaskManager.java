import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class TaskManager extends Task {

	PriorityQueue<Task> priorityQ;
	Hashtable<String,ArrayList<Task>> requesters;
	
	TaskManager(){
		priorityQ = new PriorityQueue<Task>();
		requesters = new Hashtable<String,ArrayList<Task>>();
	}
	
	//add method
	public void add(Task t) {
		priorityQ.add(t);
		ArrayList<Task> req = new ArrayList<Task>();
		if(requesters.containsKey(t.getRequester())) {
			req = requesters.get(t.getRequester()); //get the value; the arraylist
			req.add(t); 
		}
		else {
			req.add(t);
		}
		Collections.sort(req);
		requesters.put(t.getRequester(), req);
	}
	
	//remove method
	public Task remove(Task t) {
		if((priorityQ.isEmpty()==false) 
				&& (requesters.isEmpty()==false)) {
			if((priorityQ.contains(t))==true 
					&& (requesters.containsKey(t.getRequester()))==true) {
				priorityQ.remove(priorityQ.getLocation(t));
				requesters.remove(t.getRequester());
				return t;
			}
		}
		return null;
	}
	
	public ArrayList<Task> printInOrder() {
		//create an array list of tasks
		ArrayList<Task> order = new ArrayList<Task>();
		//fill it with all the tasks in the priority queue
		order = priorityQ.getList();
		Collections.sort(order); //sort it
		return order;
	}
	
	//get requester's requests
	public ArrayList<Task> getRequests(String rqstr){
		if (requesters.containsKey(rqstr)==true) {
			return requesters.get(rqstr);
		}
		return null;
	}
	
	public String toString() {
		return "\nPRIORITY QUEUE: " + priorityQ.toString() 
				+"\nREQUESTERS : \n" + requesters.toString();
	}
	
}
