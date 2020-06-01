package it.gualtierotesta.blog;

import com.google.code.beanmatchers.BeanMatchers;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class LombokBeanTest {

    @Test
    public void testTheClassIsGoodJavaBean() {
        MatcherAssert.assertThat(LombokBean.class,
            CoreMatchers.allOf(
                // This is Java Bean so we want an empty constructor
                BeanMatchers.hasValidBeanConstructor(),
                // All fields should have getter and setter
                BeanMatchers.hasValidGettersAndSetters(),
                // Only the 'id' field in hashcode and equals
                BeanMatchers.hasValidBeanHashCodeFor("id"),
                BeanMatchers.hasValidBeanEqualsFor("id"),
                // Password and veryLongString fields should not be included in the toString method
                BeanMatchers.hasValidBeanToStringExcluding("password", "veryLongString")
            ));
    }

}
