package com.we.advanced.casedemo.ifelse;

import static com.we.advanced.casedemo.ifelse.RuleConstant.MATCH_ADDRESS_START;

/**
 * 具体规则--demo1
 * @author we
 * @date 2021-05-12 09:12
 **/
public class AddressRule extends AbstractRule {

    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("AddressRule invoke!");
        if(dto.getAddress().startsWith(MATCH_ADDRESS_START)){
            return true;
        }
        return false;
    }
}
