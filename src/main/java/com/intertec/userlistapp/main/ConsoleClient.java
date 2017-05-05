package com.intertec.userlistapp.main;

import com.intertec.userlistapp.username.IUserNameAnalizer;
import com.intertec.userlistapp.username.Result;
import com.intertec.userlistapp.username.ValidUserNameAnalizer;
import com.intertec.userlistapp.data.FileAccessManager;
import com.intertec.userlistapp.util.PropertiesUtil;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Santiago Lazo on 5/4/17.
 */
public class ConsoleClient {

    private Scanner reader = new Scanner(System.in);
    private IUserNameAnalizer userNameAnalizer = new ValidUserNameAnalizer();
    private FileAccessManager fileAccessManager = new FileAccessManager();
    private List<String> existentUsers;
    private List<String> blackList;
    private PropertiesUtil props = new PropertiesUtil();

    public ConsoleClient(){
        existentUsers = fileAccessManager.retrieveDataListFromAFile(props.getProperties().getProperty("users.file.path"));
        blackList = fileAccessManager.retrieveDataListFromAFile(props.getProperties().getProperty("blacklist.file.path"));
 }

    public void start() throws Exception {
       while(true) {
           String username = readUserInput(reader);
           Result result = userNameAnalizer.checkUserName(username, existentUsers, blackList);
           printResultInScreen(result);
       }
    }

    public String readUserInput(Scanner reader){
        System.out.println("\n=========================");
        System.out.println("Please enter a username: ");
        String username = reader.nextLine();
        System.out.println("----------");
        System.out.println(username);

        return username;
    }

    public void printResultInScreen(Result result){
        if (result.getValidUser()) {
            System.out.println("Valid Username !");

        } else {
            System.out.println("Invalid Username !");
            System.out.println("Here some suggestions: ");
            System.out.println(result.getSuggestedUserNameList());
        }
    }

}
