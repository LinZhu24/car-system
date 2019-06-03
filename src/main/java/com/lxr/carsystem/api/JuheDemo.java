package com.lxr.carsystem.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 *二手车价值评估调用示例代码 － 聚合数据
 *在线接口文档：http://www.juhe.cn/docs/71
 **/

public class JuheDemo {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="79f7594f4222bab1d41eb80f9a3e1f4e";

    //1.城市列表
    public static void getRequest1(){
        String result =null;
        String url ="http://v.juhe.cn/usedcar/city";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("method","");//固定值：getAllCity

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.品牌列表
    public static void getRequest2(){
        String result =null;
        String url ="http://v.juhe.cn/usedcar/brand";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("method","");//固定值：getCarBrandList

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.车系列表
    public static void getRequest3(){
        String result =null;
        String url ="http://v.juhe.cn/usedcar/series";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("method","");//固定值：getCarSeriesList
        params.put("brandId","");//品牌标识，可以通过车三百品牌数据接口拿回所有的品牌信息，从而提取品牌标识。

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4.车型列表
    public static void getRequest4(){
        String result =null;
        String url ="http://v.juhe.cn/usedcar/car";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("method","");//固定值：getCarModelList
        params.put("seriesId","");//车系标识，可以通过车三百车系数据接口拿回车系信息，从而提前车系标识。

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5.车源列表
    public static void getRequest5(){
        String result =null;
        String url ="http://op.juhe.cn/che300/query";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");// 返回数据的格式,xml或json，默认json
        params.put("method","");// 固定值：getCarList
        params.put("zone","");//城市标识。
        params.put("page","");//车源列表页码，也就是需要拿第几页车源，每页最多会有20条车源，如果不足20条会全部返回。如果不给的话，默认为1。
        params.put("keyword","");//搜索关键字，根据改关键字进行搜索提供车源，如：宝马X1。
        params.put("carBrand","");//品牌ID，可以通过车三百的品牌数据接口拿回所有的品牌相关信息。
        params.put("carSeries","");//车系ID，可以通过车三百的车系数据接口拿回指定品牌下的所有车系相关信息。
        params.put("carModel","");//车型ID，可以通过车三百的车型数据接口拿回指定车系下的所有车型相关信息。
        params.put("carYear","");//车龄，既可以指定一个区间（如：3-5），也可以指定一个具体的上牌年份（如：2009）。
        params.put("carMile","");//车辆里程，既可以指定一个区间（如：3-5），也可以指定一个具体的公里数（如：10），该参数表达的意思是多少万公里。
        params.put("carPrice","");//车辆价格，既可以指定一个区间（如：3-5），也可以指定一个具体的数目（如：10），该参数表达的意思是多少万元。
        params.put("sellerType","");//卖家类型，1表示个人，2表示商家，不指定的话返回结果就是个人和商家混合车源。
        params.put("vprSort","");//性价比排序，指定返回的车源按照性价比排序。可以取值asc和desc，其中asc表示升序，desc表示降序。该排序和其它排序方式一样，只会有一种生效，在调用接口的时候只需要指定一种排序即可。如果不指定的话，默认会是下面的发布时间排序。
        params.put("priceSort","");//价格排序，指定返回的车源按照价格排序。可以取值asc和desc，其中asc表示升序，desc表示降序。该排序和其它排序方式一样，只会有一种生效，在调用接口的时候只需要指定一种排序即可。如果不指定的话，默认会是下面的发布时间排序。
        params.put("registerDateSort","");//上牌时间排序，指定返回的车源按照上牌时间排序。可以取值asc和desc，其中asc表示升序，desc表示降序。该排序和其它排序方式一样，只会有一种生效，在调用接口的时候只需要指定一种排序即可。如果不指定的话，默认会是下面的发布时间排序。
        params.put("mileAgeSort","");//车辆里程排序，指定返回的车源按照车辆里程排序。可以取值asc和desc，其中asc表示升序，desc表示降序。该排序和其它排序方式一样，只会有一种生效，在调用接口的时候只需要指定一种排序即可。如果不指定的话，默认会是下面的发布时间排序。
        params.put("postDateSort","");//发布时间排序，指定返回的车源按照发布时间排序。可以取值asc和desc，其中asc表示升序，desc表示降序。该排序和其它排序方式一样，只会有一种生效，在调用接口的时候只需要指定一种排序即可。如果不指定的话，默认会是下面的发布时间排序。

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6.车源详细信息
    public static void getRequest6(){
        String result =null;
        String url ="http://op.juhe.cn/che300/query";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("method","");//固定值：getCarDetail
        params.put("keyId","");//车源的编号，如：8423

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //7.精确估值
    public static void getRequest7(){
        String result =null;
        String url ="http://v.juhe.cn/usedcar/assess";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("method","");//固定值：getUsedCarPrice
        params.put("modelId","");//车型标识，可以通过车三百车型列表数据接口拿回车三百所支持的所有车型相关信息，也可以申请合作成功之后提供网站自己的车型信息进行两者之间的映射。
        params.put("regDate","");//待估车辆的上牌时间（格式：yyyy-MM）。
        params.put("mile","");//待估车辆的公里数，单位万公里。
        params.put("zone","");//城市标识，可以通过车三百城市列表数据接口拿回车三百所支持的所有城市相关信息，也可以申请合作成功之后提供网站自己的城市信息进行两者之间的映射。
        params.put("title","");//待估车辆的标题信息，可选参数。
        params.put("price","");//待估车辆在贵网站上面的卖价（不是指导价，是用户标的价格），可选参数。

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //8.估值记录
    public static void getRequest8(){
        String result =null;
        String url ="http://op.juhe.cn/che300/query";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        params.put("method","");//固定值：getEvalRecord
        params.put("evalTimeStart","");//估值记录筛选的起始时间，可选参数。
        params.put("evalTimeEnd","");//估值记录筛选的结束时间，可选参数。

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getRequest2();
    }

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */

    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
