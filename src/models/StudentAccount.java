package models;

import controllers.FullAccess;
import controllers.LimitedAccess;

public class StudentAccount extends Account {

	public StudentAccount(User u){
		accType = "Student";
		if(u.getAge() < 18){
			AccBehaviour = new FullAccess();
		}
		else {
			AccBehaviour = new LimitedAccess();
		}
		setValues();
	}
	private void setValues() {AccBehaviour.setValues(this);}

}
