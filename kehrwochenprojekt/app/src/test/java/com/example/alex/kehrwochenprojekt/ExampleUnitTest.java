package com.example.alex.kehrwochenprojekt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import Kehrwochenprojekt.Datamodels.Flat;
import Kehrwochenprojekt.Datamodels.Task;
import Kehrwochenprojekt.Datamodels.User;
import Kehrwochenprojekt.Utility.ExclusionStrategies.ExclusionStrategyFactory;
import Kehrwochenprojekt.Utility.ExclusionStrategies.FlatDelete;
import Kehrwochenprojekt.Utility.ExclusionStrategies.FlatGet;
import Kehrwochenprojekt.Utility.ExclusionStrategies.FlatPatch;
import Kehrwochenprojekt.Utility.ExclusionStrategies.FlatPost;
import Kehrwochenprojekt.Utility.ExclusionStrategies.FlatPut;
import Kehrwochenprojekt.Utility.ExclusionStrategies.FlatTaskGet;
import Kehrwochenprojekt.Utility.ExclusionStrategies.FlatUserDelete;
import Kehrwochenprojekt.Utility.ExclusionStrategies.FlatUserGet;
import Kehrwochenprojekt.Utility.ExclusionStrategies.TaskCommentPost;
import Kehrwochenprojekt.Utility.ExclusionStrategies.TaskDelete;
import Kehrwochenprojekt.Utility.ExclusionStrategies.TaskGet;
import Kehrwochenprojekt.Utility.ExclusionStrategies.TaskPatch;
import Kehrwochenprojekt.Utility.ExclusionStrategies.TaskPost;
import Kehrwochenprojekt.Utility.ExclusionStrategies.TaskPut;
import Kehrwochenprojekt.Utility.ExclusionStrategies.TaskUserGet;
import Kehrwochenprojekt.Utility.ExclusionStrategies.TaskUserPost;
import Kehrwochenprojekt.Utility.ExclusionStrategies.UserDelete;
import Kehrwochenprojekt.Utility.ExclusionStrategies.UserPost;
import Kehrwochenprojekt.Utility.ExclusionStrategies.UserPut;
import Kehrwochenprojekt.Utility.RESTClient;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
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

    @Test
    public void testNodeJSConnection(){
        //localhost:8080
        String url = "http://localhost:8080/";
        String endpoint = "app/user/";

        try {
            //Create local objects
            User u = new User();
            u.setUserName("AlexMiamorsch");
            u.setPassword("securepassword");
            u.setForeName("Alexander");
            u.setSurName("Brueckner");

            Flat f = new Flat();
            f.setName("Alex' WG");
            f.setCreator(u.getUserName());
            f.setPenalty("Zehn Peitschenhiebe");

            Task t = new Task();
            t.setName("Task 1");
            t.setGuideline("putzen putzen putzen");
            t.setCreationDate(new Date(System.currentTimeMillis()));
            Date dl = new Date();
            dl.setTime(System.currentTimeMillis()+2500000);
            t.setDeadline(dl);

            //Create RESTAPI Object
            String request = UserPost.getRequest(u);
            RESTClient restGuy = new RESTClient("http://localhost:8080/");


            //Create user
            System.out.println(restGuy.sendRequest("app/user/",request,"POST"));
            System.out.println("Created user");

            //Create Flat
            request = FlatPost.getRequest(f);
            String flatpostresponse = restGuy.sendRequest("app/group/",request,"POST");
            System.out.println("Created Flat");
            JSONObject jsonobj = new JSONObject(flatpostresponse);
            f.setID(jsonobj.getString("flatId"));

            //Put user in flat
            request = FlatPut.getRequest(u,f);
            System.out.println(restGuy.sendRequest("app/group/",request,"PUT"));
            System.out.println("Inserted USer");
            //Wait for the slow-as-shit Database
            Thread.sleep(5000);
            //See if he is in the flat
            request = FlatGet.getRequest(u);
            System.out.println(restGuy.sendRequest("app/group",request,"GET"));
            //See if the flat contains the user
            request = FlatUserGet.getRequest(f);
            System.out.println(restGuy.sendRequest("app/group/user",request,"GET"));

            //Give him the task
            request = TaskPost.getRequest(u,t);
            String taskResponse = restGuy.sendRequest("app/task/",request,"POST");
            System.out.println(taskResponse);
            t.setTaskId(new JSONObject(taskResponse).getString("taskId"));
            //Check for his task
            request = TaskGet.getRequest(t);
            System.out.println(restGuy.sendRequest("app/task",request,"GET"));

            //Purge Database
            System.out.println(restGuy.sendRequest("app/user/",UserDelete.getRequest(u),"DELETE"));
            System.out.println(restGuy.sendRequest("app/group/", FlatDelete.getRequest(f),"DELETE"));
            System.out.println(restGuy.sendRequest("app/task/", TaskDelete.getRequest(t),"DELETE"));

        }
        catch(IOException|JSONException|InterruptedException ioe){
            ioe.printStackTrace();
            fail(ioe.getMessage());
        }

    }

    @Test
    public void printRequests(){

        boolean illegalArgThrownFlatPatch = false, illegalArgThrownTaskPatch = false;

        User u = new User();
        u.setUserName("AlexMiamorsch");
        u.setPassword("securepassword");
        u.setForeName("Alexander");
        u.setSurName("Brueckner");

        Flat f = new Flat();
        f.setName("Alex' WG");
        f.setCreator(u.getUserName());
        f.setPenalty("Zehn Peitschenhiebe");

        Task t = new Task();
        t.setName("Task 1");
        t.setGuideline("putzen putzen putzen");
        t.setCreationDate(new Date(System.currentTimeMillis()));
        Date dl = new Date();
        dl.setTime(System.currentTimeMillis()+2500000);
        t.setDeadline(dl);

        f.setID("5954ed163e8ba626946095be");
        t.setTaskId("5954ed163e8ba626affe2e");

        System.out.println("Delete Flat");
        System.out.println(FlatDelete.getRequest(f));
        System.out.println("Get Flat");
        System.out.println(FlatGet.getRequest(u));
        System.out.println("Patch Flat Requests, all varieties");
        FlatPatch flatPatcher1 = new FlatPatch(f.getID(), "newname", "newpenalty");
        FlatPatch flatPatcher2 = new FlatPatch(f.getID(), null, "newpenalty");
        FlatPatch flatPatcher3 = new FlatPatch(f.getID(), "newname", null);
        try {
          FlatPatch flatPatcher4 = new FlatPatch(f.getID(), null, null);
        }
        catch(IllegalArgumentException iae){
            illegalArgThrownFlatPatch = true;
        }

        System.out.println(flatPatcher1.getRequest(f));
        System.out.println(flatPatcher2.getRequest(f));
        System.out.println(flatPatcher3.getRequest(f));
        System.out.println("Get FlatTask");
        System.out.println(FlatTaskGet.getRequest(f));
        System.out.println("Delete FlatUser");
        System.out.println(FlatUserDelete.getRequest(f,u));
        System.out.println("Get FlatUser");
        System.out.println(FlatUserGet.getRequest(f));
        System.out.println("Post TaskComment");
        System.out.println(TaskCommentPost.getRequest(t,"Well done"));
        System.out.println("Delete Task");
        System.out.println(TaskDelete.getRequest(t));
        System.out.println("Get Task");
        System.out.println(TaskGet.getRequest(t));

        System.out.println("Patch Tasks, all variations");
        TaskPatch TaskPatcher1 = new TaskPatch(t.getTaskId(), "newname",
                null,null);
        TaskPatch TaskPatcher2 = new TaskPatch(t.getTaskId(), null,
                new Date(System.currentTimeMillis()), null);
        TaskPatch TaskPatcher3 = new TaskPatch(t.getTaskId(), null,
                null, "new guideline");
        TaskPatch TaskPatcher4 = new TaskPatch(t.getTaskId(), "newname",
                null, "new guideline");
        TaskPatch TaskPatcher5 = new TaskPatch(t.getTaskId(), "newname",
                new Date(System.currentTimeMillis()), null);

        try {
            TaskPatch TaskPatcher6 = new TaskPatch(t.getTaskId(), null,null,null);
        }
        catch(IllegalArgumentException iae){
            illegalArgThrownTaskPatch = true;
        }
        System.out.println("Post Task");
        System.out.println(TaskPost.getRequest(u,t));
        System.out.println("Put Task");
        System.out.println(TaskPut.getRequest(t));
        System.out.println("Get TaskUser");
        System.out.println(TaskUserGet.getRequest(u));

        User u2 = new User();
        u2.setUserName("newguy");
        u2.setForeName("new");
        u2.setSurName("guy");
        u2.setPassword("notsecure");
        System.out.println("Post TaskUser");
        System.out.println(TaskUserPost.getRequest(t,u2,u));
        System.out.println("Delete User");
        System.out.println(UserDelete.getRequest(u));
        System.out.println("Post User");
        System.out.println(UserPost.getRequest(u));
        System.out.println("Put User");
        System.out.println(UserPut.getRequest(u));

        assertTrue(illegalArgThrownFlatPatch && illegalArgThrownTaskPatch);


    }
}