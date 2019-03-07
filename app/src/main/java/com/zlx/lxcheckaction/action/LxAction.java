package com.zlx.lxcheckaction.action;

/**
 * @date: 2019\3\7 0007
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public class LxAction {

    private ActionUnit actionUnit = new ActionUnit();

    /**
     * 添加action行为
     *
     * @param action
     * @return
     */
    public LxAction addAction(Action action) {
        clear();
        actionUnit.setAction(action);
        return this;
    }

    /**
     * 添加验证
     *
     * @param valid
     * @return
     */
    public LxAction addValid(Valid valid) {
        //只添加无效的，验证不通过的。
        if (!valid.check()) {
            actionUnit.addValid(valid);
        }
        return this;
    }

    //如果上一条valid没有通过，是不允许再发起call的
    public void doAction() {

        Valid lastValid = actionUnit.getLastValid();
        //如果上一条valid没有通过，是不允许再发起doAction的
        if (lastValid != null && !lastValid.check()) {
            return;
        }

        //执行action
        if (actionUnit.getValidQueue().size() == 0 && actionUnit.getAction() != null) {//验证队列为空 && 存在action
            //执行操作
            actionUnit.getAction().doAction();
            //清空
            clear();
        } else {
            //执行验证。
            Valid poll = actionUnit.getValidQueue().poll();
            actionUnit.setLastValid(poll);
            poll.doValid();
        }
    }


    public void clear() {
        actionUnit.getValidQueue().clear();
        actionUnit.setAction(null);
        actionUnit.setLastValid(null);
    }

    // 单一全局访问点
    public static LxAction getInstance() {
        return SingletonHolder.mInstance;
    }

    // 静态内部类，第一次加载LxAction类时不会初始化mInstance，
    // 当调用getInstance()时才会初始化
    private static class SingletonHolder {
        private static LxAction mInstance = new LxAction();
    }
}
