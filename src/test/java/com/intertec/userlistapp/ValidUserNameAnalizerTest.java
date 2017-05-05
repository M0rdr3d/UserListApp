package com.intertec.userlistapp;

import com.intertec.userlistapp.data.FileAccessManager;
import com.intertec.userlistapp.username.Result;
import com.intertec.userlistapp.username.ValidUserNameAnalizer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Santiago Lazo on 5/4/17.
 */
public class ValidUserNameAnalizerTest {

    ValidUserNameAnalizer userNameAnalizer;
    FileAccessManager fileAccessManager;
    List<String> existentUsers;
    List<String> blackList;

    @Before
    public void setUp(){
        userNameAnalizer= new ValidUserNameAnalizer();
        fileAccessManager = new FileAccessManager();
        existentUsers =  fileAccessManager.retrieveDataListFromAFile("src/test/resources/users.txt");
        blackList = fileAccessManager.retrieveDataListFromAFile("src/test/resources/blacklist.txt");
    }

    @Test
    public void shouldReturnFalseWithANotValidUserName() throws Exception {

        String userToValidate = "dalaix";
        Result result = userNameAnalizer.checkUserName(userToValidate,existentUsers, blackList);

        assertFalse(result.getValidUser());
    }


    @Test
    public void shouldReturnTrueWithAValidUserName() throws Exception {

        String userToValidate = "slazodb";
        Result result = userNameAnalizer.checkUserName(userToValidate,existentUsers, blackList);

        assertTrue(result.getValidUser());
    }


    @Test
    public void shouldReturnAListWithSuggestedUserName() throws Exception {
        String userToValidate = "dalai lama";
        Result result = userNameAnalizer.checkUserName(userToValidate,existentUsers, blackList);

        System.out.println(result.getSuggestedUserNameList());
        assertFalse(result.getValidUser());
    }

    @Test
    public void shouldReturnFalseIfUserNameContainsABlackListWord() throws Exception {
        String userToValidate = "NikillYou";
        Result result = userNameAnalizer.checkUserName(userToValidate, existentUsers, blackList);
        assertFalse(result.getValidUser());

    }

    @Test
    public void shouldReturnAListWithSuggestedUserNameWhenFailBlackListValidation() throws Exception {
        String userToValidate = "solcannabis58";
        Result result = userNameAnalizer.checkUserName(userToValidate, existentUsers, blackList);
        System.out.println(result.getSuggestedUserNameList());
    }

    //6 characters long in config file
    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInputLengthisLessThanExpected() throws Exception {
        String userToValidate = "abcd";
        userNameAnalizer.checkUserName(userToValidate,existentUsers, blackList);
    }

    //50 characters long in config file
    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenInputLengthisGreaterThanExpected() throws Exception {
        String userToValidate = "akdjdhajdodidjdoeurpeowodowudlsodksodoapdiddfdfdpaodo";
        userNameAnalizer.checkUserName(userToValidate,existentUsers, blackList);
    }

}
