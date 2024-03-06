package iegcode.spring.config.profileenvironment;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = ProfileEnvironment.TestApplication.class)
@ActiveProfiles({"production", "local"})
public class ProfileEnvironment {

    @Autowired
    private TestApplication.sampleProfile sampleProfile;

    @Test
    void testActiveProfiles() {
        Assertions.assertArrayEquals(new String[]{"production", "local"}, sampleProfile.getProfiles());
    }

    @SpringBootApplication
    public static class TestApplication{

        @Component
        public static class sampleProfile implements EnvironmentAware{

            @Setter
            private Environment environment;

            public String[] getProfiles(){
                return environment.getActiveProfiles();
            }
        }
    }
}
