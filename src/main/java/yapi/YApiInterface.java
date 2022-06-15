package yapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class YApiInterface implements Serializable {

    private QueryPath query_path;
    private int edit_uid;
    private String status ="done";
    private String type = "static";
    private boolean req_body_is_json_schema = true;
    private boolean res_body_is_json_schema = true;
    private boolean api_opened=false;
    private int index= 0 ;
    private List<String> tag = new ArrayList<>();
    private int _id=327;
    private String method="POST";
    private int catid = 261;
    private String title;//需传入
    private String path;//需传入
    private int project_id=54;
    private List<String> req_params = new ArrayList<>();
    private String res_body_type="json";
    private int uid=75;
    private long add_time=1560147323;
    private long up_time=1560147580;
    private List<String> req_query = new ArrayList<>();
    //    private List<ReqHeaders> req_headers = new ArrayList<>();
//    private String req_headers = "[{\"required\": \"1\",\"_id\": \"5cfdf67c58d6f710ada5e8f9\",\"name\": \"Content-Type\",\"value\": \"application/json\"}]";
    private List<Object> req_headers = new ArrayList<>();
    private List<String> req_body_form = new ArrayList<>();
    private int __v = 0;
    private String desc;//需传入
    private String markdown;//需传入
    private String req_body_other;//需传入
    private String req_body_type;//需传入
    private String res_body;//需传入

    public QueryPath getQuery_path() {
        return query_path;
    }

    public void setQuery_path(QueryPath query_path) {
        this.query_path = query_path;
    }

    public int getEdit_uid() {
        return edit_uid;
    }

    public void setEdit_uid(int edit_uid) {
        this.edit_uid = edit_uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReq_body_is_json_schema() {
        return req_body_is_json_schema;
    }

    public void setReq_body_is_json_schema(boolean req_body_is_json_schema) {
        this.req_body_is_json_schema = req_body_is_json_schema;
    }

    public boolean isRes_body_is_json_schema() {
        return res_body_is_json_schema;
    }

    public void setRes_body_is_json_schema(boolean res_body_is_json_schema) {
        this.res_body_is_json_schema = res_body_is_json_schema;
    }

    public boolean isApi_opened() {
        return api_opened;
    }

    public void setApi_opened(boolean api_opened) {
        this.api_opened = api_opened;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public List<String> getReq_params() {
        return req_params;
    }

    public void setReq_params(List<String> req_params) {
        this.req_params = req_params;
    }

    public String getRes_body_type() {
        return res_body_type;
    }

    public void setRes_body_type(String res_body_type) {
        this.res_body_type = res_body_type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public List<String> getReq_query() {
        return req_query;
    }

    public void setReq_query(List<String> req_query) {
        this.req_query = req_query;
    }

//    public List<ReqHeaders> getReq_headers() {
//        return req_headers;
//    }
//
//    public void setReq_headers(List<ReqHeaders> req_headers) {
//        this.req_headers = req_headers;
//    }


//    public String getReq_headers() {
//        return req_headers;
//    }
//
//    public void setReq_headers(String req_headers) {
//        this.req_headers = req_headers;
//    }


    public List<Object> getReq_headers() {
        return req_headers;
    }

    public void setReq_headers(List<Object> req_headers) {
        this.req_headers = req_headers;
    }

    public List<String> getReq_body_form() {
        return req_body_form;
    }

    public void setReq_body_form(List<String> req_body_form) {
        this.req_body_form = req_body_form;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getReq_body_other() {
        return req_body_other;
    }

    public void setReq_body_other(String req_body_other) {
        this.req_body_other = req_body_other;
    }

    public String getReq_body_type() {
        return req_body_type;
    }

    public void setReq_body_type(String req_body_type) {
        this.req_body_type = req_body_type;
    }

    public String getRes_body() {
        return res_body;
    }

    public void setRes_body(String res_body) {
        this.res_body = res_body;
    }
    //            "desc": "<p>{备注}</p>\n",
//            "markdown": "{备注}",
//            "req_body_other": "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"type\":\"object\",\"properties\":{\"cgCode\":{\"type\":\"number\",\"mock\":{\"mock\":\"@integer\"},\"description\":\"集团编码\"},\"memberCardNumber\":{\"type\":\"string\",\"description\":\"会员卡号\",\"mock\":{\"mock\":\"@string\"}}}}",
//            "req_body_type": "json",
//            "res_body": "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"type\":\"object\",\"properties\":{\"code\":{\"type\":\"string\"},\"data\":{\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"number\"},\"custMemberAssetsStatistics\":{\"type\":\"object\",\"properties\":{\"assetsId\":{\"type\":\"number\"}}}}}}}"
//    }
}
