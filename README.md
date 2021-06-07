# Warehouse
Spring boot based warehouse project

Domain:Supply chain management

One HTML Form=One Model Class=One Database Table
means, from data(Submit)=model class object= table row

Annotations:
============
* @Entity : represents a class must be linked with one table.

* @Id : indicates primary key.

* @Table(name="__") : indicate table name.

* @Column(name="__") : indicates column name.

* @Data : generates getter()/setter() methods,toString(),equals() and hashCode(), constructors. It is also called as lombok annotation.

* @ElementCollection : storing list of elements in a single variable and generating child table linking with parent table by parent class ID.

* @CollectionTable : providing collection table name.

Layers Programming:
===================

-> for developing an application 4 layers are used.

Presentation layer, Service layer, Data Access layer and Integration layer

How to exchange data between UI to Controller and vice-versa?
=============================================================
-> using ModelAttribute and Model Interface.

* @ModelAttribute ClassName objectName : it is used to send data from UI to controller.

-> means a form data converted to one object and stores in spring container and reading the data from container to controller using ModelAttribte.

* @Model model : it is used to send data from controller (model.addAttribute(key,value)) to spring container data store in model memory in the form of key-value pair.
 
and reading the data from model map container to UI using some Expression language on UI page

EX: for JSP -> EL:${key}
    
    for thymeleaf -> <span th:text="${key}"/>
    
    
* @RequestParam DataType key : sending data from Browser to Controller use RequestParam url " URl?key=value ".

-> from browser this data is store in spring container as ParamMap in key-value pair and then read that data using @RequestParam DataType key syntax in Controller.












  
