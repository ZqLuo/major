package com.example.API.express;

import java.util.List;
import java.util.stream.Stream;

/**
 * 快递公司查询
 * Created by zqLuo
 */
public class ExpressList {

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
        public String ret_code; //0代表成功
        public boolean flag;
        public List<ExpressListVO> expressList;

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public List<ExpressListVO> getExpressList() {
            return expressList;
        }

        public void setExpressList(List<ExpressListVO> expressList) {
            this.expressList = expressList;
        }

        public class ExpressListVO{
            public String imgUrl; //快递公司的图片/logo
            public String simpleName; //拼音或英文简称
            public String phone; //官方电话
            public String expName; //快递名称
            public String url; //官方网址
            public String note; //备注信息

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getSimpleName() {
                return simpleName;
            }

            public void setSimpleName(String simpleName) {
                this.simpleName = simpleName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getExpName() {
                return expName;
            }

            public void setExpName(String expName) {
                this.expName = expName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }
        }
    }
}
