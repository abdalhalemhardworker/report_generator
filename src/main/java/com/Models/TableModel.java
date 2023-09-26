package com.Models;

import java.util.List;

public class TableModel {

    private List<String> headers;

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    private List<List<String>> data;

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    public TableModel(List<String> headers, List<List<String>> data) {
        super();
    }

    public boolean addRow(List<String> row) {
        return true;
    }
}