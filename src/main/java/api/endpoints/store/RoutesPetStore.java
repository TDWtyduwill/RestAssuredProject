package api.endpoints.store;

public class RoutesPetStore {

    public static String base_url = "https://petstore.swagger.io/v2";


    //store modules
    public static String post_url = base_url + "/store/order";
    public static String get_url = base_url + "/store/inventory";
    public static String get_url_pattern = base_url + "/store/order/{orderId}";
    public static String delete_url = base_url + "store/order/{orderId}";
}