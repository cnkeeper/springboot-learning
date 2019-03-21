package com.github.cnkeep.exception;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/3/13
 */
public class TransactionalExecption extends org.springframework.transaction.TransactionException {
    public TransactionalExecption(String msg) {
        super(msg);
    }
}
