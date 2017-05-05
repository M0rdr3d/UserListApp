package com.intertec.userlistapp.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Santiago Lazo on 5/3/17.
 */
public class FileAccessManager {


    public List<String> retrieveDataListFromAFile(String filePath) {
        List<String> users = new ArrayList<String>();
        try{
            File file  = new File(filePath);
            users = retrieveDataList(file);
        }catch (Exception e){
            System.out.println("[ERROR] -> " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    public List<String> retrieveDataList(File file) throws FileNotFoundException {
        List<String> users = new ArrayList<String>();
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String userName = scanner.nextLine();
            String usernameToRegister = userName.trim();
            if(!usernameToRegister.equals("")){
                users.add(usernameToRegister);
            }
        }
        return users;
    }

}
