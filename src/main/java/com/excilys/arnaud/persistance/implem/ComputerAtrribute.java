package com.excilys.arnaud.persistance.implem;



public enum ComputerAtrribute {
  ID("computer.id"),
  NAME("computer.name"),
  INTRODUCED("introduced"),
  DISCONTINUED("discontinued"),
  COMPANY("company.name"),
  COMPANY_ID("company_id");
  
  private String name;
  
  private ComputerAtrribute(String name) {
    this.name = name;
  }
  

  public String getName() {
    return name;
  }
}
