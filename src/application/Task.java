// AUTHOR: LINYUN LIU
// DATE: MARCH 15th, 2021

package application;

public class Task {
	
	private String Name;
	private String Details;
	private String Priority;
	private String DueDate;
	private String TimeLong;
	private boolean CompStatus;
	
	
	public Task(String Name, String DueDate, String TimeLong, String Details, String Priority, boolean CompStatus) {
		this.Name = Name;
		this.DueDate = DueDate;
		this.TimeLong = TimeLong;
		this.Priority = Priority;
		this.Details = Details;
		this.CompStatus = CompStatus;

	} 
	
	
	// setters
	public void setName(String Name) {
		this.Name = Name;
	}
	public void setDetails(String Details) {
		this.Details = Details;
	}
	public void setPriority(String Priority) {
		this.Priority = Priority;
	}
	public void setDueDate(String DueDate) {
		this.DueDate = DueDate;
	}
	public void setTimeLong(String TimeLong) {
		this.TimeLong = TimeLong;
	}
	public  void setCompStatus(boolean CompStatus) {
		this.CompStatus = CompStatus;	
	}
	
	
	//getters
	public String getName() {
		return Name;
	}
	public String getDetails() {
		return Details;
	}
	public String getPriority() {
		return Priority;
	}
	public String getDueDate() {
		return DueDate;
	}
	public String getTimeLong() {
		return TimeLong;
	}
	public boolean getCompStatus() {
		return CompStatus;
	}
	
	
	
	// toString methods
	public String toStringName() {
		return Name;
	}
	public String toStringDetails() {
		return Details;
	}
	public String toStringPriority() {
		return Priority;
	}
	public String toStringDueDate() {
		return DueDate;
	} 
	public String toStringTimeLong() {
		return TimeLong;
	}
	public String toStringCompStatus() {
		return ""+CompStatus;
	}
	
	
	public String toString() {
		return Name+","+DueDate+","+TimeLong+","+Details.replace("\n", "NEXTLINE").replace(",", "ACOMMA")+","+Priority+","+CompStatus;
	}

}


