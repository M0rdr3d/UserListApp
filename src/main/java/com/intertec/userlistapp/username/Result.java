package com.intertec.userlistapp.username;

import java.util.List;

/**
 * Created by Santiago Lazo on 5/3/17.
 */
public class Result {

    public Result(Boolean isValidUser, List<String> suggestedUserNameList){
        this.isValidUser = isValidUser;
        this.suggestedUserNameList = suggestedUserNameList;
    }

    private Boolean isValidUser;
    private List<String> suggestedUserNameList;


    public void setValidUser(Boolean validUser) {
        isValidUser = validUser;
    }

    public Boolean getValidUser() {
        return isValidUser;
    }

    public List<String> getSuggestedUserNameList() {
        return suggestedUserNameList;
    }

    public void setSuggestedUserNameList(List<String> suggestedUserNameList) {
        this.suggestedUserNameList = suggestedUserNameList;
    }
}
