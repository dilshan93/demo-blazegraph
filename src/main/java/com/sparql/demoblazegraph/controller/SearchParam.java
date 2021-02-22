package com.sparql.demoblazegraph.controller;

public class SearchParam {
    private String name;
    private String zipcode;
    private String neighborhood;
    private String policedistrict;

    public SearchParam(String name, String zipcode, String neighborhood, String policedistrict) {
        this.name = name;
        this.zipcode = zipcode;
        this.neighborhood = neighborhood;
        this.policedistrict = policedistrict;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPolicedistrict() {
        return policedistrict;
    }

    public void setPolicedistrict(String policedistrict) {
        this.policedistrict = policedistrict;
    }
}
