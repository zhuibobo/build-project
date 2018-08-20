package com.build4d.web.beanconfig.general;

import com.build4d.base.tools.common.PathUtility;
import com.build4d.web.general.NormalMenuUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("WebGeneralBeansConfig")
public class GeneralBeansConfig {
    @Bean
    public NormalMenuUtility builderMenuUtility(PathUtility pathUtility) {
        NormalMenuUtility builderMenuUtility=new NormalMenuUtility();
        builderMenuUtility.setPathUtility(pathUtility);
        return builderMenuUtility;
    }
}
