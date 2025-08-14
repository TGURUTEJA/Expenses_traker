package com.Expenses_traker.Auth_service.pojo;



public class testData {
    private String id;
    private String name;
    private String description;
    private String category;

    public testData() {
        super();
    }
    public testData(String id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description; 
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "testData [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category + "]";
    }
}
