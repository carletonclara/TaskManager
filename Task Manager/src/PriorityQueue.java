
import java.util.ArrayList;

public class PriorityQueue<Task extends Comparable>  {

	ArrayList<Task> queue;
	
	//constructor
	PriorityQueue(){
		queue = new ArrayList<Task>();
	}
	
	PriorityQueue(ArrayList<Task> q){
		queue = q;
	}
	
	//relations to main element
	//code used from Prof example code
	int getLeftChild(int i){ return(2*i+1);  }
	int getRightChild(int i) { return(2*i+2); }
	int getParent(int i){return((i-1)/2); }
	
	//add method
	public void add(Task t) {
		queue.add(t);
		bubbleUp(); //sort the queue
	}
	
	//remove method
	public Task remove(int i) {
		//check if the queue is empty
		if(queue.isEmpty()==false) {
			//if queue is not empty
			//if requested location exists
			if(i<queue.size()-1) {
				Task temp = queue.get(i);
				queue.remove(queue.get(i));
				bubbleDown(); //maintain the order of the list
				return temp;
			}
		} 
		return null;
	}
	
	//check method
	//check a task at a particular location
	public Task check(int i) {
		return queue.get(i);
	}
	
	//get the location of a specified task
	public int getLocation(Task t) {
		return queue.indexOf(t);
	}
	
	//lastAdded
	public Task lastAdded() {
		return queue.get(queue.size()-1); //returns the last element in the queue
	}
	
	//checks if the queue contains a specific task
	public boolean contains(Task t) {
		return queue.contains(t);
	}
	
	
	//bubbleUp method
	//used to make sure that the queue remains in order
	//method modified from Prof's example code
	public void bubbleUp() {
		//get the element at the end of list
		int checkLoc = queue.size()-1;
	      //keep swapping as long as child has higher priority than parent
	      //and the child isn't the root
	      while(checkLoc!=0 && 
	          (queue.get(checkLoc).compareTo(queue.get(getParent(checkLoc)))< 0)){
	         int parentLoc = getParent(checkLoc);
	         Task temp = queue.get(checkLoc);
	         queue.set(checkLoc,queue.get(parentLoc));
	         queue.set(parentLoc,temp);
	         checkLoc = parentLoc;
	      }
	}
	
	public void bubbleDown() {
		Task temp;
		int node = 0;
		int next;
		if((getLeftChild(node)>queue.size()-1) 
				&&  (getRightChild(node)>queue.size()-1)) {
			next = queue.size();}
		else if(getRightChild(node)>queue.size()-1) {
			next = getLeftChild(node);}
		else if(queue.get(getLeftChild(node)).compareTo(queue.get(getRightChild(node)))<0) {
			next = getLeftChild(node);}
		else {next = getRightChild(node);	}
		temp = queue.get(node);
		while((next<queue.size()) 
				&& (queue.get(next).compareTo(temp)<0)) {
			queue.set(node, queue.get(next));
			node = next;
			if((getLeftChild(node)>queue.size()-1) 
					&&  (getRightChild(node)>queue.size()-1)) {
				next = queue.size();}
			else if(getRightChild(node)>queue.size()-1) {
				next = getLeftChild(node);}
			else if(queue.get(getLeftChild(node)).compareTo(queue.get(getRightChild(node)))<0) {
				next = getLeftChild(node);}
			else {next = getRightChild(node);	}
		}
		queue.set(node,temp);
	}
	
	public String preorder(int i) {
		String s = "";
		//continue until there are no more nodes in tree
		if(i>queue.size()-1) {
			return s;
		}
		//print parent before its children
		s += queue.get(i).toString();
		//traverse left subtree, repeating process
		s += preorder(getLeftChild(i));
		//continue to the right subtree
		s +=preorder(getRightChild(i));	
		return s;
	}
	
	//checks if the priority queue is empty or not
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	//return the size of the queue
	public int size() {
		return queue.size();
	}
	
	public ArrayList<Task> getList(){
		return queue;
	}
	
	//toString
	public String toString() {
		return preorder(0);
	}
	
}
