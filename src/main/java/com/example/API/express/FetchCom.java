package com.example.API.express;

import java.util.List;

/**
 * 单号查快递公司名
 * Created by zqLuo
 */
public class FetchCom {

    public String showapi_res_code; //0为成功,其他为失败
    public String showapi_res_error; //showapi平台返回的错误信息
    public ShowapiResBody showapi_res_body;

    public String getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(String showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBody getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBody showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public class ShowapiResBody{
        public String ret_code; //0表示成功
        public String msg;
        public List<Data> data;

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        public class Data{
            public String simpleName; //拼音
            public String expName; //名称

            public String getSimpleName() {
                return simpleName;
            }

            public void setSimpleName(String simpleName) {
                this.simpleName = simpleName;
            }

            public String getExpName() {
                return expName;
            }

            public void setExpName(String expName) {
                this.expName = expName;
            }
        }
    }
}
