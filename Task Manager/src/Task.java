

public class Task implements Comparable<Task> {
	
	String dateReq;
	String dateDue;
	int priority;
	String requester;
	String request;
	
	//empty constructor
	Task(){}
	
	//constructor
	Task(String drq, String dd, int p, String rqstr, String rq){
		dateReq = drq;
		dateDue = dd;
		priority = p;
		requester = rqstr;
		request = rq;
	}
	//getters
	public String getDateReq() {return dateReq;}
	public String getDateDue() {return dateDue;}
	public int getPriority() {return priority;}
	public String getRequester() {return requester;}
	public String getRequest() {return request;}
	
	//setters
	public void setDateReq(String drq) {dateReq = drq;}
	public void setDateDue(String dd) {dateDue = dd;}
	public void setPriority(int p) {priority = p;}
	public void setRequester(String rqstr) {requester = rqstr;}
	public void setRequest(String rq) {request = rq;}
	
	public int compareTo(Task t) {
		int comp = 0;
		if(this.getPriority()==t.getPriority()) {
			if(this.getDateDue()==t.getDateDue()) {
				if(this.getRequester()==t.getRequester()) {return comp;	}
				else if(this.getRequester().compareTo(t.getRequester())>0) {return 1;}
				else {return -1;}
			}
			else if(this.getDateDue().compareTo(t.getDateDue())>0) {return 1;}
			else {return -1;}
		}
		else if(this.getPriority()>t.getPriority()) {return 1;}
		return -1;
	}
	
	
	public String toString() {
		return("\nDate Requested: "+ dateReq + "\n"
				+"Date Due: " + dateDue + "\n"
				+"Priority: " + priority + "\n"
				+"Requester: " + requester + "\n"
				+"Request: " + request + "\n");
	}

	
	
	
}
