package com.sen.toy.command;

import com.sen.toy.models.Robot;

/**
 * Created by sen on 30/04/2015.
 */
public interface Command {
    void execute(Robot robot);
}
