package com.intertec.userlistapp.username;

import java.util.List;

/**
 * Created by Santiago Lazo on 5/4/17.
 */
public interface IUserNameAnalizer {

    public Result checkUserName(String userName) throws Exception;
    public Result checkUserName(String userName, List<String> registeredUsers, List<String> blackList) throws Exception;

}
