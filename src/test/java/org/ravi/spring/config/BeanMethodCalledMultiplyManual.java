package org.ravi.spring.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanMethodCalledMultiplyManual.BeanMethodCalledMultiplyManualCfg.class})
/** What happens if we call a bean-creating-method-multiply, causes unpredictable results. Some times it uses FIRST, then again SECOND */
public class BeanMethodCalledMultiplyManual {
    private static final Logger logger = LoggerFactory.getLogger(BeanMethodCalledMultiplyManual.class);
    private static final AtomicInteger counter = new AtomicInteger(0);

    static {
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.INFO);
        org.apache.log4j.Logger.getRootLogger().removeAllAppenders();
        org.apache.log4j.Logger.getRootLogger().addAppender(new org.apache.log4j.ConsoleAppender(new org.apache.log4j.PatternLayout("%d %p %M \t\t   - %m%n")));

        org.apache.log4j.Logger.getLogger("org.springframework").setLevel(org.apache.log4j.Level.WARN);

        logger.info("To indicate test started and gather timestmap");
    }

    @Autowired
    @Qualifier("thingToTest")
    private SomeBean thingToTest;
    @Autowired
    private ApplicationContext context;

    @Test
    public void randomTest() {
        logger.info("\tthingToTest = [{}], counter=[{}]", thingToTest, counter);

        Class<SomeBean> clz = SomeBean.class;
        Map<String, SomeBean> beans = context.getBeansOfType(clz);
        logger.info("Number of ({}) beans = [{}]", clz.getSimpleName(), beans.size());

        assertThat(beans.size()).as("number of beans=1").isEqualTo(1);
        assertThat(thingToTest.getNumber()).isEqualTo(1);
    }

    private static class SomeBean {
        private final int number;

        public SomeBean() {
            number = counter.incrementAndGet();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    // .append("code", hashCode())
                    .append("num", number)
                    .toString();
        }

        public int getNumber() {
            return number;
        }
    }

    static class BeanMethodCalledMultiplyManualCfg {
        @Bean(name = "thingToTest")
        public SomeBean someBean() {
            return new BeanMethodCalledMultiplyManual.SomeBean();
        }

        @Bean
        public String constructorUser(SomeBean bean) {
            logger.info("constructor injection got=[{}]", bean);
            return "reuse";
        }

        @Bean(name = "recreate")
        public String recreate() { // wierd has no dependency, hence it is run first !
            logger.info("Re-invocation created=[{}]", someBean());
            return "recreate";
        }

        @Bean
        public String withWierd(@Qualifier("recreate") String wrd) {
            return "withWierd";
        }
    }
}
