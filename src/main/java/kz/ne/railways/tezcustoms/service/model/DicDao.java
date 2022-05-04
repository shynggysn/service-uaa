package kz.ne.railways.tezcustoms.service.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DicDao implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private long id;
    private String sid;
    private String name;
    private String exp;
    private HashMap<String, DicDao> data = new HashMap<String, DicDao>();
    private Map dinamycData = new HashMap();

    public DicDao() {

    }

    public DicDao(long _id, String _sid, String _name, String _exp) {
        this.id = _id;
        this.sid = _sid;
        this.name = _name;
        this.exp = _exp;
    }

    public DicDao(long _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

    public DicDao(long _id, String _sid, String _name) {
        this.id = _id;
        this.sid = _sid;
        this.name = _name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public void setData(HashMap<String, DicDao> data) {
        this.data = data;
    }

    public HashMap<String, DicDao> getData() {
        return data;
    }

    public void setDinamycData(Map dinamycData) {
        this.dinamycData = dinamycData;
    }

    public Map getDinamycData() {
        return dinamycData;
    }
}
