package com.sen.toy.command;

import com.sen.toy.models.Robot;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by sen on 30/04/2015.
 */
public class CommandInvoker {
    public final static String MOVE = "MOVE";
    public final static String TURN_LEFT = "LEFT";
    public final static String TURN_RIGHT ="RIGHT";
    static Command moveCommand = new Command() {
        @Override
        public void execute(Robot robot) {
            robot.moveForward();
        }
    };

    static Command turnLeftCommand = new Command() {
        @Override
        public void execute(Robot robot) {
            robot.turnLeft();
        }
    };

    static Command turnRightCommand = new Command() {
        @Override
        public void execute(Robot robot) {
            robot.turnRight();
        }
    };

    private static Map<String, Command> commandMap =
        new TreeMap<String, Command>(String.CASE_INSENSITIVE_ORDER);
    static {
        commandMap.put(MOVE, moveCommand);
        commandMap.put(TURN_LEFT, turnLeftCommand);
        commandMap.put(TURN_RIGHT, turnRightCommand);
    }

    public static void invoke(String command, Robot theRobot){
        if(canInvokeCommand(command, theRobot)){
            commandMap.get(command).execute(theRobot);
        }
    }

    private static  boolean canInvokeCommand(String command, Robot theRobot){
        return theRobot!=null
            && theRobot.isOnBoard()
            && commandMap.containsKey(command);
    }
}
