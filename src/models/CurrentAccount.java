package models;

import controllers.FullAccess;
import controllers.LimitedAccess;

public class CurrentAccount extends Account {

	public CurrentAccount(User u){
		accType = "Current";
		if(u.getAge() >= 18){
			AccBehaviour = new FullAccess();
		}
		else {
			AccBehaviour = new LimitedAccess();
		}
		setValues();
	}
	private void setValues() {AccBehaviour.setValues(this);}

}
