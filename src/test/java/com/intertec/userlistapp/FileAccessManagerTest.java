package com.intertec.userlistapp;

import com.intertec.userlistapp.data.FileAccessManager;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Santiago Lazo on 5/3/17.
 */
public class FileAccessManagerTest {
    @Test
    public void shouldRetrieveDataListFromFile() throws Exception {
        FileAccessManager fileAccessManager = new FileAccessManager();
        List<String> existentUsers =  fileAccessManager.retrieveDataListFromAFile("src/test/resources/test.txt");

        List<String> expected = Arrays.asList("axian", "dalai", "muad", "jhon");
        assertThat(existentUsers, is(expected));

    }
}