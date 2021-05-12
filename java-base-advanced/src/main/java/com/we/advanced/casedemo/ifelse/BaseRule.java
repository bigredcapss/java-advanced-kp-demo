package com.we.advanced.casedemo.ifelse;

/**
 * 规则抽象
 * @author we
 * @date 2021-05-12 09:07
 **/
public interface BaseRule {
    boolean execute(RuleDto dto);
}
