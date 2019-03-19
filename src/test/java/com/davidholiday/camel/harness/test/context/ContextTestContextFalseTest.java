package com.davidholiday.camel.harness.test.context;


import com.davidholiday.camel.harness.test.context.lifecycle.AppContextLifecycle;
import com.davidholiday.camel.harness.test.context.lifecycle.HarnessedAppContextLifecycle;
import com.davidholiday.camel.harness.testing.ProcessorTestHarness;
import com.davidholiday.camel.harness.context.AppContextLifecycleHarness;
import com.davidholiday.camel.harness.test.context.mocks.Bean;

import com.davidholiday.camel.harness.test.processor.mocks.ToHeaderProcessor;

import org.apache.camel.Processor;
import org.junit.Assert;
import org.junit.Test;


/**
 * this test ensures that when we tell the harnessed context lifecycle object we are NOT in 'alternate context mode'
 * that the registry contents are what we expect them to be.
 */
public class ContextTestContextFalseTest extends ProcessorTestHarness {

    private static final Processor TO_HEADER_PROCESSOR = new ToHeaderProcessor();

    private static final AppContextLifecycleHarness CAMEL_CONTEXT_LIFECYCLE_HARNESS_FALSE_TEST_CONTEXT =
            new HarnessedAppContextLifecycle(false);

    public ContextTestContextFalseTest() {
        super(TO_HEADER_PROCESSOR, CAMEL_CONTEXT_LIFECYCLE_HARNESS_FALSE_TEST_CONTEXT);
    }

    @Test
    public void ContextTestWithoutTestContect() {


        Bean bean = (Bean)context().getRegistry()
                                   .lookupByName(AppContextLifecycle.TEST_BEAN_NAME);

        String expectedBeanValue = "foo";
        String actualBeanValue = bean.get();
        Assert.assertEquals(
                "when not in test context the FOO bean should be in the registry...",
                expectedBeanValue,
                actualBeanValue
        );

    }

}
