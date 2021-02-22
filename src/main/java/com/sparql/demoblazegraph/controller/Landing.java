package com.sparql.demoblazegraph.controller;

import com.sparql.demoblazegraph.config.JenaConfig;
//import com.sparql.demoblazegraph.service.LandingService;
import net.minidev.json.JSONObject;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.repository.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class Landing {

//    @Autowired
//    LandingService landingService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String greeting(){
        return "Welcome to SpringBoot!!";
    }

//    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
//    public ResponseEntity<?> greeting1() throws MalformedQueryException, RepositoryException {
//        return landingService.getResult();
//    }


    @RequestMapping("/getItems")
    public List<JSONObject> describePolitician() {

        String queryString =
                "PREFIX ds: <https://data.baltimorecity.gov/resource/k5ry-ef3g/>" +
                        "SELECT ?name WHERE  { \n" +
                        "  ?s ds:policedistrict \"SOUTHEASTERN\" .\n" +
                        "  ?s ds:name ?name .\n" +
                        "}";
        List<JSONObject> resultSet = JenaConfig.getItems(queryString);
        return resultSet;
    }

    @RequestMapping(value = "/findPlace",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> findPolitician(@RequestBody SearchParam searchParam) {
        boolean allEmpty = true;
       System.out.println(searchParam);
        String queryString =
                "PREFIX ds: <https://data.baltimorecity.gov/resource/k5ry-ef3g/>" +
                        "SELECT ?name ?zipcode ?neighborhood ?policedistrict WHERE { ";

        if (searchParam.getName() != null && !searchParam.getName().equals("")) {
            allEmpty = false;
            queryString += "?s ds:name \""+searchParam.getName()+"\" .\n";
        }

        if (searchParam.getNeighborhood() != null && !searchParam.getNeighborhood().equals("")) {
            allEmpty = false;
            queryString += "?s ds:neighborhood \""+searchParam.getNeighborhood()+"\" .\n";
        }

        if (searchParam.getPolicedistrict() != null && !searchParam.getPolicedistrict().equals("")) {
            allEmpty = false;
            queryString += "?s ds:policedistrict \""+searchParam.getPolicedistrict()+"\" .\n";
        }

        if (searchParam.getZipcode() != null && !searchParam.getZipcode().equals("")) {
            allEmpty = false;
            queryString += "?s ds:zipcode \""+searchParam.getZipcode()+"\" .\n";
        }

        if (allEmpty) {
            List<JSONObject> emptyArray = new ArrayList<>();
            return emptyArray;
        }
        queryString += " ?s ds:name ?name .\n" +
                "  ?s ds:zipcode ?zipcode .\n" +
                "  ?s ds:neighborhood ?neighborhood .\n" +
                "  ?s ds:policedistrict ?policedistrict .}";
//        System.out.println(queryString);

        List<JSONObject> resultSet = JenaConfig.findPlaces(queryString);
        return resultSet;
    }
}
