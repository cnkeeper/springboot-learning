package com.github.cnkeep;

/**
 * @description: websocket消息类型
 * @author: <a href="mailto:zhangleili@lizhi.fm">LeiLi.Zhang</a>
 * @date: 2019-09-04
 * @version: v1.1.8
 **/
public enum MessageType {
    /**
     * 默认消息类型
     */
    NULL(0, String.class);

    /**
     * 消息类型
     */
    private int type;
    /**
     * 消息体对应的实体类
     */
    private Class messageClass;

    MessageType(int type, Class messageClass) {
        this.type = type;
        this.messageClass = messageClass;
    }

    public int getType() {
        return type;
    }

    public Class getMessageClass() {
        return messageClass;
    }

    /**
     * @param type
     * @return
     */
    public static MessageType getType(int type) {
        for (MessageType t : MessageType.values()) {
            if (t.type == type) {
                return t;
            }
        }
        return MessageType.NULL;
    }
}
