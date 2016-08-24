package com.taxisurfr.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Contractor implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "idGeneratorSeq", sequenceName = "idSequence")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGeneratorSeq")
    private long id;



    private String name;
    private String email;


    private Long mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column private String address1;
    @Column private String address2;
    @Column private String address3;
    @Column private String address4;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    private Long agentId;

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long userId) {
        this.agentId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getMobile() {
        return mobile;
    }

//    @Override
//    public BasicDBObject toDBObject() {
//        return null;
//    }
}