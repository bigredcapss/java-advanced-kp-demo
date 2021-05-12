package com.we.advanced.casedemo.ifelse;

import static com.we.advanced.casedemo.ifelse.RuleConstant.MATCH_ADDRESS_START;
import static com.we.advanced.casedemo.ifelse.RuleConstant.MATCH_NATIONALITY_START;

/**
 * 具体规则--demo2
 * @author we
 * @date 2021-05-12 09:19
 **/
public class NationalityRule extends AbstractRule{
    @Override
    protected <T> T convert(RuleDto dto) {
        NationalityRuleDto nationalityRuleDto = new NationalityRuleDto();
        if(dto.getAddress().startsWith(MATCH_ADDRESS_START)){
            nationalityRuleDto.setNationality(MATCH_NATIONALITY_START);
        }
        return (T)nationalityRuleDto;
    }

    @Override
    protected <T> boolean executeRule(T t) {
        System.out.println("NationalityRule invoke");
        NationalityRuleDto nationalityRuleDto = (NationalityRuleDto) t;
        if(nationalityRuleDto.getNationality().startsWith(MATCH_NATIONALITY_START)){
            return true;
        }
        return false;
    }
}
