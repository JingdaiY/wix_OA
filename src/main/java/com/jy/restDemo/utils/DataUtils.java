package com.jy.restDemo.utils;

import com.jy.restDemo.dto.DataItem;
import com.jy.restDemo.dto.enums.DataItemNumericFields;
import com.jy.restDemo.dto.enums.DataItemStringFields;

public class DataUtils {

    public String[] parseKeyValuePair(String param) {

        param = param.replace(')', ' ').trim();

        String[] pair = param.split(",", 2);

        return pair;
    }

    public String[] parseKeyValuePairForEqual(String param) {

        param = param.split("\\(", 2)[1];

        String[] pair = parseKeyValuePair(param);

        return pair;
    }

    public String[] parseAndOrNot(String param) {

        param.substring(0, param.length() - 1);

        param = param.trim();

        return param.split("\\),", 2);

    }

    public <E extends Enum<E>> boolean isValidEnum(final Class<E> enumClass, final String enumName) {
        if (enumName == null) {
            return false;
        }
        try {
            Enum.valueOf(enumClass, enumName);
            return true;
        } catch (final IllegalArgumentException ex) {
            return false;
        }
    }

    public String getTextFieldValue(String key, DataItem dataItem) {

        if (DataItemStringFields.ID.name().equals(key.toUpperCase())) {

            return dataItem.getId();

        }else if (DataItemStringFields.CONTENT.name().equals(key.toUpperCase())) {

            return dataItem.getTitle();

        }else {

            return dataItem.getContent();
        }

    }

    public long getNumericFieldValue(String key, DataItem dataItem) {

        if (DataItemNumericFields.VIEWS.name().equals(key.toUpperCase())) {

            return dataItem.getViews();

        }else {

            return dataItem.getTimestamp();
        }

    }
}
