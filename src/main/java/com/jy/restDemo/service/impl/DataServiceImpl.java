package com.jy.restDemo.service.impl;

import com.jy.restDemo.dto.DataItem;
import com.jy.restDemo.dto.enums.DataItemNumericFields;
import com.jy.restDemo.dto.enums.DataItemStringFields;
import com.jy.restDemo.dto.request.DataRequest;
import com.jy.restDemo.service.DataService;
import com.jy.restDemo.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DataServiceImpl implements DataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataServiceImpl.class);

    private HashMap<String, DataItem> map = new HashMap<>();
    private final DataUtils dataUtils;

    public DataServiceImpl(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
    }

    @Override
    public ResponseEntity<List<DataItem>> retrieveData(String queryParam) {

        String[] operators = queryParam.split("\\(", 2);

        List<DataItem> items;

        String[] keyValue;

        List<DataItem> list = new ArrayList<>(map.values());

        try {
            switch (operators[0]) {

                case "EQUAL":

                    keyValue = dataUtils.parseKeyValuePair(operators[1]);

                    items = equals(keyValue[0], keyValue[1], list);

                    break;

                case "AND":

                    String[] equalsForAnd = dataUtils.parseAndOrNot(operators[1]);

                    keyValue = dataUtils.parseKeyValuePairForEqual(equalsForAnd[0]);

                    items = equals(keyValue[0], keyValue[1], list);

                    keyValue = dataUtils.parseKeyValuePairForEqual(equalsForAnd[1]);

                    items = equals(keyValue[0], keyValue[1], items);

                    break;

                case "OR":

                    String[] equalsForOr = dataUtils.parseAndOrNot(operators[1]);

                    keyValue = dataUtils.parseKeyValuePairForEqual(equalsForOr[0]);

                    items = equals(keyValue[0], keyValue[1], list);

                    keyValue = dataUtils.parseKeyValuePairForEqual(equalsForOr[1]);

                    items.addAll(equals(keyValue[0], keyValue[1], list));

                    break;

                case "NOT":

                    operators[1] = operators[1].replace(')', ' ').trim();

                    operators = operators[1].split("\\(", 2);

                    keyValue = dataUtils.parseKeyValuePair(operators[1]);

                    items = notEquals(keyValue[0], keyValue[1], list);

                    break;

                case "GREATER_THAN":

                    keyValue = dataUtils.parseKeyValuePair(operators[1]);

                    items = greaterThan(keyValue[0], keyValue[1], list);

                    break;

                case "LESS_THAN":

                    keyValue = dataUtils.parseKeyValuePair(operators[1]);

                    items = lessThan(keyValue[0], keyValue[1], list);

                    break;

                default:

                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

            }

        }catch(Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

        return ResponseEntity.ok().body(items);

    }

    @Override
    public ResponseEntity persistData(DataRequest request) {

        DataItem item = new DataItem();

        item.setId(request.getId());

        item.setTitle(request.getTitle());

        item.setContent(request.getContent());

        item.setViews(request.getViews());

        item.setTimestamp(request.getTimestamp());

        map.put(request.getId(), item);

        return ResponseEntity.ok().body("SUCCESS");

    }

    public int checkMapSize() {

        return map.size();

    }


    private List<DataItem> equals(String key, String value, List<DataItem> list) {

        return list.stream().filter(x -> matchEqual(key, value, x)).collect(Collectors.toList());

    }

    private List<DataItem> notEquals(String key, String value, List<DataItem> list) {

        return list.stream().filter(x -> !matchEqual(key, value, x)).collect(Collectors.toList());

    }

    private List<DataItem> greaterThan(String key, String value, List<DataItem> list) {

        return list.stream().filter(x -> matchGreaterThan(key, value, x)).collect(Collectors.toList());

    }

    private List<DataItem> lessThan(String key, String value, List<DataItem> list) {

        return list.stream().filter(x -> matchLessThan(key, value, x)).collect(Collectors.toList());

    }

    private boolean matchGreaterThan(String key, String value, DataItem dataItem) {

        value = value.replace("\"", " ").trim();

        if (dataUtils.isValidEnum(DataItemNumericFields.class, key.toUpperCase())) {

            return Long.parseLong(value) < dataUtils.getNumericFieldValue(key, dataItem);

        }

        return false;
    }

    private boolean matchLessThan(String key, String value, DataItem dataItem) {

        value = value.replace("\"", " ").trim();

        if (dataUtils.isValidEnum(DataItemNumericFields.class, key.toUpperCase())) {

            return Long.parseLong(value) > dataUtils.getNumericFieldValue(key, dataItem);

        }

        return false;
    }

    private boolean matchEqual(String key, String value, DataItem dataItem) {

        value = value.replace("\"", " ").trim();

        if (dataUtils.isValidEnum(DataItemStringFields.class, key.toUpperCase())) {

            return value.equals(dataUtils.getTextFieldValue(key, dataItem));

        }else if (dataUtils.isValidEnum(DataItemNumericFields.class, key.toUpperCase())) {

            return Long.valueOf(value) == dataUtils.getNumericFieldValue(key, dataItem);
        }

        return false;
    }

}
