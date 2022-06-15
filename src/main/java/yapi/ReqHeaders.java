package yapi;

import java.io.Serializable;

public class ReqHeaders implements Serializable {

    private String required;
    private String _id;
    private String name;
    private String value;

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    //        "req_headers": [
//        {
//            "required": "1",
//                "_id": "5cfdf67c58d6f710ada5e8f",
//                "name": "Content-Type",
//                "value": "application/json"
//        }
//        ],
}
