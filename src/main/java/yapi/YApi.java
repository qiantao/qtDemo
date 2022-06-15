package yapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class YApi implements Serializable {

    private int index =1;
    private String name="公共分类";//需传入
    private String desc;
    private long add_time=1560135702;
    private long up_time=1560135702;
    private List<YApiInterface> list =new ArrayList<>();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public long getUp_time() {
        return up_time;
    }

    public void setUp_time(long up_time) {
        this.up_time = up_time;
    }

    public List<YApiInterface> getList() {
        return list;
    }

    public void setList(List<YApiInterface> list) {
        this.list = list;
    }
    //    {
//        "index": 1,
//            "name": "公共分类",
//            "desc": null,
//            "add_time": 1560135702,
//            "up_time": 1560135702,
//            "list": [
//        {
//            "query_path": {
//            "path": "/cust/memberCard/member_detail3",
//                    "params": []
//        },
//            "edit_uid": 0,
//                "status": "undone",
//                "type": "static",
//                "req_body_is_json_schema": true,
//                "res_body_is_json_schema": true,
//                "api_opened": false,
//                "index": 0,
//                "tag": [],
//            "_id": 325,
//                "method": "POST",
//                "catid": 259,
//                "title": "会员详情3",
//                "path": "/cust/memberCard/member_detail3",
//                "project_id": 52,
//                "req_params": [],
//            "res_body_type": "json",
//                "uid": 73,
//                "add_time": 1560147323,
//                "up_time": 1560147580,
//                "req_query": [],
//            "req_headers": [
//            {
//                "required": "1",
//                    "_id": "5cfdf67c58d6f710ada5e8f",
//                    "name": "Content-Type",
//                    "value": "application/json"
//            }
//        ],
//            "req_body_form": [],
//            "__v": 0,
//                "desc": "<p>{备注}</p>\n",
//                "markdown": "{备注}",
//                "req_body_other": "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"type\":\"object\",\"properties\":{\"cgCode\":{\"type\":\"number\",\"mock\":{\"mock\":\"@integer\"},\"description\":\"集团编码\"},\"memberCardNumber\":{\"type\":\"string\",\"description\":\"会员卡号\",\"mock\":{\"mock\":\"@string\"}}}}",
//                "req_body_type": "json",
//                "res_body": "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"string\"},\"data\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"number\"},\"custMemberAssetsStatistics\":{\"type\":\"object\",\"properties\":{\"assetsId\":{\"type\":\"number\"}}}}}}}"
//        }
//    ]
//    }
}
