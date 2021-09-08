package cn.tcualhp.tech_search.common;

/**
 * @author lihepeng
 * @description 通过接口返回类
 * @date 2019-10-31 21:36
 **/
public class Response {

    private Meta meta;
    private Object data;

    
    public Response success() {
        this.meta = new Meta(1, "ok");
        return this;
    }

    public Response success(Object data) {
        this.meta = new Meta(1, "ok");
        this.data = data;
        return this;
    }

    public Response success(int result, Object data) {
        this.meta = new Meta(result, "ok");
        this.data = data;
        return this;
    }

    public Response success(int result, Object data, String message) {
        this.meta = new Meta(result, message);
        this.data = data;
        return this;
    }

    public Response failure() {
        this.meta = new Meta(0, "error");
        return this;
    }

    public Response failure(String message) {
        this.meta = new Meta(0, message);
        return this;
    }

    public Response failure(int result, String message) {
        this.meta = new Meta(result, message);
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    class Meta {
        private int result;
        private String message;

        public Meta(int result) {
            this.result = result;

        }

        public Meta(int result, String message) {
            this.result = result;
            this.message = message;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public int getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "Meta{" +
                    "result=" + result +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Response{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
