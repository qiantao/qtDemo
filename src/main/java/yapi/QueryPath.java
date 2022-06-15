package yapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryPath implements Serializable {
    private String path;//需传入
    private List<String> params = new ArrayList<>();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
    //     "query_path": {
//        "path": "/cust/memberCard/member_detail3",
//                "params": []
}
