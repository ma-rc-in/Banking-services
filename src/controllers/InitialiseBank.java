package controllers;

import models.User;

import java.util.ArrayList;

public class InitialiseBank {

	public static ArrayList<User> users() {

		ArrayList<User> users = new ArrayList<User>();
		String[] forenames = {"Marcin", "Harry", "Brad", "Donald"};
		String[] surnames = {"9898", "Potter", "Pitt", "Duck"};
		int[] years = {20, 21, 22, 15};

		for (int i = 0; i < 4; i++) {
			User u = new User();
			u.setId(i + 1);
			u.setForename(forenames[i]);
			u.setSurname(surnames[i]);
			u.setUsername("user" + (i + 1));
			u.setPassword("pass" + (i + 1));
			u.setAge(years[i]);
			users.add(u);
		}
		return users;
	}
}
