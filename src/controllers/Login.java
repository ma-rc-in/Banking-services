package controllers;

import models.User;

import java.util.ArrayList;
import java.util.Scanner;



public class Login {

	Scanner myScanner = new Scanner(System.in);

	private ArrayList<User> users;

	public static void main(String[] args) {
		Login login = new Login();
		login.users = InitialiseBank.users();
		login.getDetails();
	}

	private void getDetails(){

		System.out.println("Please enter your username");
		String username = myScanner.nextLine();
		System.out.println("Please enter your password");
		String password = myScanner.nextLine();
		User user = checkDetails(username, password);

		if(user != null){
			MainMenu mm = new MainMenu(user);
		}
		else {
			System.out.println("Incorrect username or password, please try again");
			getDetails();
		}
	}

	private User checkDetails(String username, String password) {
		
		for(User u : users){
			if(u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password)){
				return u;
			}
		}
		return null;
	}
}
