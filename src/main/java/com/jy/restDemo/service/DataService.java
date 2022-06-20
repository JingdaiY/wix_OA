package com.jy.restDemo.service;

import com.jy.restDemo.dto.DataItem;
import com.jy.restDemo.dto.request.DataRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DataService {

    ResponseEntity<List<DataItem>> retrieveData(String id);

    ResponseEntity persistData(DataRequest request);

}
