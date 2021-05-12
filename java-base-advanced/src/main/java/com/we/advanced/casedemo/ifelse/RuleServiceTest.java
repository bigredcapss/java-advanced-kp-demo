package com.we.advanced.casedemo.ifelse;

import org.junit.Test;

import java.util.Arrays;

/**
 * 执行器的调用
 * @author we
 * @date 2021-05-12 09:58
 **/
public class RuleServiceTest {

    @Test
    public void execute() {
        //规则执行器
        //优点1：比较简单，每个规则可以独立，将规则，数据，执行器拆分出来，调用方比较规整
        //优点2：我在 Rule 模板类中定义 convert 方法做参数的转换这样可以能够，为特定 rule 需要的场景数据提供拓展。
        //缺点：上下 rule 有数据依赖性，如果直接修改公共传输对象 dto 这样设计不是很合理，建议提前构建数据。

        //1. 定义规则  init rule
        NationalityRule nationalityRule = new NationalityRule();
        AddressRule addressRule = new AddressRule();

        //2. 构造需要的数据 create dto
        RuleDto dto = new RuleDto();
        dto.setAge(5);
        dto.setAddress("北京");

        //3. 通过以链式调用构建和执行 rule execute
        boolean ruleResult = RuleService
                .create()
                .and(Arrays.asList(nationalityRule, addressRule))
                .execute(dto);
        System.out.println("this student rule execute result :" + ruleResult);
    }
}
