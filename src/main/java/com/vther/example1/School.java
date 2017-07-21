package com.vther.example1;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class School {
    private String address;
    private String name;

    private Map<String, String> metaData = new HashMap<>();
    private List<String> selectors = new ArrayList<>();
}
