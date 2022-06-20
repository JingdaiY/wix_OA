package com.jy.restDemo.restdemo.service;

import com.jy.restDemo.dto.DataItem;
import com.jy.restDemo.dto.request.DataRequest;
import com.jy.restDemo.service.DataService;
import com.jy.restDemo.service.impl.DataServiceImpl;
import com.jy.restDemo.utils.DataUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/*******
 * More Unit tests could be added
 */
public class DataServiceImplTest {

    private DataService dataService;

    @Before
    public void setUp() {

        dataService = new DataServiceImpl(new DataUtils());

    }

    @Test
    public void retrieveData_positive() {

        String query_param = "EQUAL(id, \"testId\")";

        DataRequest request = new DataRequest();

        request.setId("testId");

        dataService.persistData(request);

        ResponseEntity<List<DataItem>> responseEntity = dataService.retrieveData(query_param);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Assert.assertEquals(1, responseEntity.getBody().size());

    }

    @Test
    public void retrieveData_negative() {

        String query_param = "InvalidOperator(id, \"testId\")";

        DataRequest request = new DataRequest();

        request.setId("testId");

        dataService.persistData(request);

        ResponseEntity<List<DataItem>> responseEntity = dataService.retrieveData(query_param);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void persistData_positive() {

        DataRequest request = new DataRequest();

        request.setId("testId");

        ResponseEntity responseEntity = dataService.persistData(request);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
