package models;

import controllers.FullAccess;
import controllers.LimitedAccess;

public class SavingsAccount extends Account {

    public SavingsAccount(User u){
        accType = "Savings";
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
