package com.jy.restDemo.restdemo.utils;

import com.jy.restDemo.dto.enums.DataItemNumericFields;
import com.jy.restDemo.dto.enums.DataItemStringFields;
import com.jy.restDemo.utils.DataUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * More unit tests on the way
 */
public class DataUtilsTest {

    private DataUtils dataUtils;

    @Before
    public void setUp() {

        dataUtils = new DataUtils();

    }

    @Test
    public void test_parseKeyValuePair_happyPath() {

        String str = "1,2";
        String[] pair = dataUtils.parseKeyValuePair(str);
        assertNotNull(pair);
        assertEquals("1", pair[0]);
        assertEquals("2", pair[1]);

        String str2 = "3,4  )";
        String[] pair2 = dataUtils.parseKeyValuePair(str2);
        assertNotNull(pair);
        assertEquals("3", pair2[0]);
        assertEquals("4", pair2[1]);

    }

    @Test
    public void test_parseKeyValuePair_neg() {

        String str = "1:2";
        String[] pair = dataUtils.parseKeyValuePair(str);
        assertNotNull(pair);
        assertEquals(1, pair.length);
        assertNotEquals("1", pair[0]);

    }

    @Test
    public void test_isValidEnum_pos() {

        assertTrue(dataUtils.isValidEnum(DataItemNumericFields.class, "TIMESTAMP"));
        assertTrue(dataUtils.isValidEnum(DataItemNumericFields.class, "VIEWS"));
        assertTrue(dataUtils.isValidEnum(DataItemStringFields.class, "ID"));
        assertTrue(dataUtils.isValidEnum(DataItemStringFields.class, "TITLE"));
        assertTrue(dataUtils.isValidEnum(DataItemStringFields.class, "CONTENT"));
    }

    @Test
    public void test_isValidEnum_neg() {

        assertFalse(dataUtils.isValidEnum(DataItemNumericFields.class, "CONTENT"));
        assertFalse(dataUtils.isValidEnum(DataItemNumericFields.class, "!!!"));
        assertFalse(dataUtils.isValidEnum(DataItemNumericFields.class, "~"));
        assertFalse(dataUtils.isValidEnum(DataItemStringFields.class, "asd"));
        assertFalse(dataUtils.isValidEnum(DataItemNumericFields.class, "TITLE"));
        assertFalse(dataUtils.isValidEnum(DataItemNumericFields.class, "CONTENT"));
    }
}
