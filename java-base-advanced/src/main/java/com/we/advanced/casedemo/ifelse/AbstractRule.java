package com.we.advanced.casedemo.ifelse;

/**
 * 规则模板
 * @author we
 * @date 2021-05-12 09:08
 **/
public abstract class AbstractRule implements BaseRule {

    @Override
    public boolean execute(RuleDto dto) {
        return executeRule(convert(dto));
    }

    protected <T> T convert(RuleDto dto){
        return (T) dto;
    }

    protected <T> boolean executeRule(T t){
        return true;
    }


}
