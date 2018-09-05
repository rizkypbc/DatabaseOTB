package bpp.arnet.project.databaseotb.Model;

import java.util.List;

public class Value {

    String value;
    String message;
    List<OTB> otbList;
    List<Port> port;

    public List<Port> getPort() {
        return port;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<OTB> getOtbList() {
        return otbList;
    }

    public void setOtbList(List<OTB> otbList) {
        this.otbList = otbList;
    }

}
