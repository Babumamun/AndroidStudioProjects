package Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import Model.ShopBean;

public class JsonParse {
    //Singleton mode
    //Singleton mode
//③define sole instance of the class.
//    private static JsonParse instance = new JsonParse();
//
//    //①Hide the constructor of the class.
//    private JsonParse() {
//
//    }
//
//    //②Define a public static operation (getInstance())
//    public synchronized static JsonParse getInstance() {
//        return instance;
//    }

//    public List<ShopBean> getShopList(String json) {
//        Type type = new TypeToken<List<ShopBean>>() {
//        }.getType();
//
//        List<ShopBean> sbl = new Gson().fromJson(json, type);
//        return sbl;
//    }
//}
    private JsonParse(){

    }
    public static JsonParse instance= new JsonParse();

    public synchronized static JsonParse getInstance(){
    return instance;
    }

    public static List<ShopBean>getShopList(String json){
        Type type= new TypeToken<List<ShopBean>>(){}.getType();
        List<ShopBean>sbl= new Gson().fromJson(json,type);
        return sbl;

    }
        }
