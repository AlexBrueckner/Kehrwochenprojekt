package com.example.alex.kehrwochenprojekt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import Kehrwochenprojekt.Datamodels.Task;
import Kehrwochenprojekt.Datamodels.User;
import Kehrwochenprojekt.Utility.ExclusionStrategies.ExclusionStrategyFactory;
import Kehrwochenprojekt.Utility.ExclusionStrategies.UserPost;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testJSON() throws Exception{


        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        User u = new User();
        u.setForeName("Penis");
        u.setSurName("Kopf");
        u.setPassword("123secure");
        u.setUserName("securetest");


        Task t1 = new Task();
        Task t2 = new Task();

        t1.setGuideline("Just do it!");
        t2.setGuideline("Seriously, JUST DO IT!");
        t1.setName("Task1");
        t2.setName("Task2");

        t1.addComment("That sucked!");
        t2.addComment("Well done!");


        u.addTask(t1);
        u.addTask(t2);
        System.out.println("-- BEG TEST 1 --");
        System.out.println(gson.toJson(u));
        System.out.println("-- END TEST 1 --");
        assertTrue(true);

    }


    @Test
    public void testStrategies(){
        User u = new User();
        u.setUserName("User1");
        u.setPassword("secure");
        u.setForeName("Fore");
        u.setSurName("Sur");
        Task t1 = new Task();
        Task t2 = new Task();
        t1.setName("Task1");
        t1.setGuideline("Clean this up");
        t1.addComment("Well done");
        t2.setName("Task2");
        t2.setGuideline("Clean this up DAMNIT");
        t2.addComment("Well done NOT!");
        u.addTask(t1);
        u.addTask(t2);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println("-- BEG TEST 2 --");
        System.out.println("-- Vanilla GSON:  --");
        System.out.println(gson.toJson(u));
        System.out.println("-- Excluded GSON:  --");
        System.out.println(UserPost.getRequest(u));
        System.out.println("-- END TEST 2 --");
        assertNotEquals(gson.toJson(u), UserPost.getRequest(u));
    }



    @Test
    public void testTaskJSON(){
        Task t = new Task();
        t.setName("muffinsmachen");
        t.setGuideline("RTFM");
        t.addComment("Ekelhaft");
        Gson gson = ExclusionStrategyFactory.build(null,null);
        System.out.println(gson.toJson(t));

        List<String> excludeUs = new ArrayList<String>();
        excludeUs.add("guideline");
        excludeUs.add("comments");
        gson = ExclusionStrategyFactory.build(excludeUs,null);
        System.out.println(gson.toJson(t));
        //Such logic - much wow
        assertTrue(true != false && false != true || true && false);


    }
}