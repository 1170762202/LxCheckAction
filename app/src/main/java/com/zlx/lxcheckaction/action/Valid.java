package com.zlx.lxcheckaction.action;

/**
 * @date: 2019\3\7 0007
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description:
 */
public interface Valid {
    //校验valid 是否通过（例：判断储存在本地的用户token是否为空）
    boolean check();

    //执行验证
    void doValid();
}
