package com.kenny.smart.type;

/**
 * ClassName: UserStatus
 * Function:  用户状态
 * Date:      2019/9/20 10:00
 * @author Kenny
 * version    V1.0
 */
public enum  UserStatus {
    enabled(0),
    disabled(1);

    private final int value;

    private UserStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
