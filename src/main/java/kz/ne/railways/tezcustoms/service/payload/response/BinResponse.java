package kz.ne.railways.tezcustoms.service.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class BinResponse {

    private String firstName;
    private String surName;
    private String middleName;

    //TODO parse into separate fields;
    private String fio;

    private String organizationName;
    private String kato;
    private String address;

    public BinResponse(){}

    public BinResponse(String firstName, String surName, String middleName, String organizationName, String kato, String address) {
        this.firstName = firstName;
        this.surName = surName;
        this.middleName = middleName;
        this.organizationName = organizationName;
        this.kato = kato;
        this.address = address;
    }

    public BinResponse(Map map){
        this.fio = map.get("fio").toString();
        this.organizationName = map.get("name").toString();
        this.kato = map.get("katoId").toString();
        this.address = map.get("katoAddress").toString();
    }


}