package com.intertec.userlistapp.username;

import com.intertec.userlistapp.util.PropertiesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Santiago Lazo on 5/3/17.
 */
public class ValidUserNameAnalizer implements IUserNameAnalizer {

    private PropertiesUtil props = new PropertiesUtil();
    private int minLengthInput = Integer.parseInt(props.getProperties().getProperty("min.input.length"));
    private int maxLengthInput =  Integer.parseInt(props.getProperties().getProperty("max.input.length"));
    private int maxSuggestedWords =  Integer.parseInt(props.getProperties().getProperty("max.suggested.words"));
    private int maxRetrySuggestedWords =  Integer.parseInt(props.getProperties().getProperty("max.retry.suggested.words"));
    private char replaceCharacter = props.getProperties().getProperty("replace_character").toCharArray()[0];

    public Result checkUserName(String userName) throws Exception {
        return null;
    }

    public Result checkUserName(String userName, List<String> registeredUsers, List<String> blackList) throws Exception {

        validateInputLength(userName, minLengthInput, maxLengthInput);
        List<String> suggestedUserNameList = new ArrayList<String>();
        Boolean isValidUserName = true;

        if(registeredUsers.contains(userName)){
            System.out.println("[WARN] -> Unfortunately the username was already taken");
            suggestedUserNameList =  generateSuggestedUserNameList(userName, registeredUsers, blackList);
            isValidUserName = false;
        }

        if(checkUserNameWithBlackList(userName, blackList)){
         System.out.println("[WARN] -> The username contains forbidden words");
         suggestedUserNameList = generateSuggestedUserNameListForBlackListValidation(userName, registeredUsers, blackList);
         isValidUserName = false;
        }
        return new Result(isValidUserName, suggestedUserNameList);
    }


    public void validateInputLength(String username, int minLength, int maxLength) throws Exception{
        if(username.length() < minLength)
            throw new Exception("Username should be at least " + minLength + " characters long");
        if(username.length() > maxLength)
            throw new Exception("Username should be at max " + maxLength + " characters long");

    }

    public List<String> generateSuggestedUserNameList(String userName, List<String> registeredUsers, List<String> blackList){

        List<String> suggestedUserNameList = new ArrayList<String>();
        int userNamecounter = 0;

        for(int i=0; i<maxSuggestedWords; i++) {
            int retriesCounter = 0;
            String suggestedUserName = "";
            while (true) {
                suggestedUserName = userName + userNamecounter;
                //suggestion validated in user list && Suggestion validated in black list
                if (!registeredUsers.contains(suggestedUserName) && !checkUserNameWithBlackList(suggestedUserName, blackList)) {
                    userNamecounter++;
                    break;
                }
                if(retriesCounter >= maxRetrySuggestedWords){
                    userNamecounter++;
                    break;
                }
                retriesCounter ++ ;
                userNamecounter ++ ;
            }
            if(retriesCounter < maxRetrySuggestedWords){
                suggestedUserNameList.add(suggestedUserName);
            }

        }
        return suggestedUserNameList;
    }


    public Boolean checkUserNameWithBlackList(String username, List<String> blackList){
      for(String word : blackList){
            if(username.contains(word)){
             return true;
            }
        }
        return false;
    }

    public List<String> generateSuggestedUserNameListForBlackListValidation(String username, List<String> registeredUsers ,List<String> blackList){
        //replace a random character of the each forbidden word in the username - replace for '_'
        String newWord = "";
        Random random = new Random();
        for(String word : blackList){
            if(username.contains(word)){
                if(newWord.equals("")){
                    newWord = username;
                }
                char[] badWord = word.toCharArray();
                int index = random.nextInt(word.length());
                badWord[index] = replaceCharacter;

                newWord = newWord.replaceAll(word, new String(badWord) );
            }
        }
        return generateSuggestedUserNameList(newWord, registeredUsers, blackList);

    }

}
