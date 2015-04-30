package com.sen.toy.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sen on 30/04/2015.
 */
public class RobotStorage {
    private static Set<Robot> ROBOTS = Collections.newSetFromMap(new ConcurrentHashMap<Robot, Boolean>());
    public static Robot findRobotBy(String name){
        for(Robot robot : ROBOTS){
            if (robot.getName().equalsIgnoreCase(name)) {
                return robot;
            }
        }
        return null;
    }

    public static boolean addNewRobot(Robot robot){
        return ROBOTS.add(robot);
    }

    public static List<String> getAllRobotsName(){
        List<String> list = new ArrayList<String>();
        for(Robot robot : ROBOTS){
            list.add(robot.getName());
        }
        return list;
    }

}
