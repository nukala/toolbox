package org.ravi.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Slf4jReporter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.apache.log4j.Level.DEBUG;
import static org.apache.log4j.Level.WARN;
import static org.apache.log4j.Logger.getLogger;
import static org.apache.log4j.Logger.getRootLogger;
import static org.ravi.metrics.AbstractSample.WorkContext.WorkContextBuilder.ofInt;

public abstract class AbstractSample {
    protected static final MetricRegistry registry = new MetricRegistry();

    // begin-setup log4j and info for me!
    private static volatile boolean logInited = false;
    static {
        if ( !logInited) {
    		getRootLogger().setLevel(WARN);
    		getRootLogger().removeAllAppenders();
    		getRootLogger().addAppender(new ConsoleAppender(new PatternLayout("%d [%t] %p %c{4}::%M@%L %x - %m%n")));

    		getLogger(AbstractSample.class.getPackage().getName()).setLevel(DEBUG);

    		logInited = true;
        }
    }
    // end-setup log4j and info for me!

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final Slf4jReporter reporter;
    private int loops;

    protected AbstractSample(int loops) {
        this.reporter = Slf4jReporter.forRegistry(registry)
                .convertDurationsTo(MILLISECONDS)
                .convertRatesTo(TimeUnit.SECONDS)
                .prefixedWith(String.format(" final report %n"))
                .outputTo(logger)
                .build();
        this.loops = loops;
    }

    protected int getLoops() {return loops;}

    protected void simulateWork() {
        for (int i = 0; i < getLoops(); i++) {
            WorkContext workContext = ofInt(i).build();
            try {
                bootUp(workContext);
                long duration = getNapTime();
                logger.debug("{}: nap = {} ms", i + 1, duration);
                sleepUninterruptibly(duration, MILLISECONDS);
            } finally {
                shutDown(workContext);
            }
        }
    }

    public abstract void bootUp(WorkContext workContext);

    public abstract void shutDown(WorkContext workContext);

    protected long getNapTime() {
        return nextLong(23, 2_927);
    }

    protected void showReport() { reporter.report(); }

    protected Slf4jReporter getReporter() { return reporter; }

    /**
     * to help with multi-threaded cases, this can be a key in some-sort-of-map
     */
    static final class WorkContext {
        private String key;

        private WorkContext(int num) {
            this.key = Integer.toString(num);
        }

        public String getKey() {
            return key;
        }

        // over-ride hashCode for keys
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else if (obj == null) {
                return false;
            } else if (getClass() != obj.getClass()) {
                return false;
            }

            if (!(getClass().isAssignableFrom(obj.getClass()))) {
                return false;
            }

            WorkContext rhs = (WorkContext) obj;
            EqualsBuilder eb = new EqualsBuilder();
            eb.append(rhs.getKey(), getKey());
            return eb.isEquals();
        }


        @Override
        public int hashCode() {
            HashCodeBuilder hb = new HashCodeBuilder();

            hb.append(getKey());
            return hb.toHashCode();
        }

        @Override
        public String toString() {
            org.apache.commons.lang3.builder.ToStringBuilder tos = new org.apache.commons.lang3.builder.ToStringBuilder(this, org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE);

            tos.append("key", getKey());

            return tos.toString();
        }

        public static class WorkContextBuilder {
            private WorkContext buildable;

            public static WorkContextBuilder ofInt(int number) {
                WorkContextBuilder ret = new WorkContextBuilder();

                ret.buildable = new WorkContext(number);
                return ret;
            }


            public WorkContext build() {
                try {
                    return buildable;
                } finally {
                    buildable = null;
                }
            }
        }
    }
}
