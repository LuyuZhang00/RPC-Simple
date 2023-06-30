package com.barry.rpc.common.utils;

import junit.framework.TestCase;

import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
//        System.out.println(testClass.getClass().getName());

    }

    public void testGetPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);
        String methodName = methods[0].getName();
        assertEquals("b", methodName);
    }

    public void testInvoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method b = methods[0];

        TestClass testClass = new TestClass();
        Object invoke = ReflectionUtils.invoke(testClass, b);
        assertEquals("b", invoke);
    }
}