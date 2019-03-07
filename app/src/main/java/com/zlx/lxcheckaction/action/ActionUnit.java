package com.zlx.lxcheckaction.action;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @date: 2019\3\7 0007
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class ActionUnit {
    //目标行为
    private Action action;
    //先进先出验证模型
    private Queue<Valid> validQueue = new ArrayDeque<>();
    //上一个执行的valid
    private Valid lastValid;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Queue<Valid> getValidQueue() {
        return validQueue;
    }

    public void setValidQueue(Queue<Valid> validQueue) {
        this.validQueue = validQueue;
    }

    public Valid getLastValid() {
        return lastValid;
    }

    public void setLastValid(Valid lastValid) {
        this.lastValid = lastValid;
    }

    public ActionUnit addValid(Valid valid) {
        validQueue.add(valid);
        return this;
    }

    public void check() {
        for (Valid valid : validQueue) {
            if (valid.check()) {
                validQueue.remove(valid);
            }
        }

    }
}
