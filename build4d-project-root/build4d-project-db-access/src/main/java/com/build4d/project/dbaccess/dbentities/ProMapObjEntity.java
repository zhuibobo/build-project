package com.build4d.project.dbaccess.dbentities;

public class ProMapObjEntity {
    private String id;

    private String lat;

    private String lng;

    private String bindto;

    private String bindtoid;

    private String type;

    private String path;

    private String distext;

    public ProMapObjEntity(String id, String lat, String lng, String bindto, String bindtoid, String type, String path, String distext) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.bindto = bindto;
        this.bindtoid = bindtoid;
        this.type = type;
        this.path = path;
        this.distext = distext;
    }

    public ProMapObjEntity() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getBindto() {
        return bindto;
    }

    public void setBindto(String bindto) {
        this.bindto = bindto == null ? null : bindto.trim();
    }

    public String getBindtoid() {
        return bindtoid;
    }

    public void setBindtoid(String bindtoid) {
        this.bindtoid = bindtoid == null ? null : bindtoid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getDistext() {
        return distext;
    }

    public void setDistext(String distext) {
        this.distext = distext == null ? null : distext.trim();
    }
}