package com.fc.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ComplexType {
    private Object[] object;
    private List<String> list;
    private Set<String> set;
    private Map<String,Object> map;

    public Object[] getObject() {
        return object;
    }

    public void setObject(Object[] object) {
        this.object = object;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "ComplexType{" +
                "object=" + Arrays.toString(object) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                '}';
    }
}
