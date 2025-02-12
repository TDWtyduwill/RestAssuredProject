package api.endpoints.store;

public class routesPets {

    public static String base_url = "https://petstore.swagger.io/v2";

    public static String post_url_image = base_url + "/pet/{petId}/uploadImage";
    public static String post_url = base_url + "/pet";
    public static String put_url = base_url + "/pet";
    public static String get_url_status = base_url + "/pet/findByStatus";
    public static String get_url = base_url + "/pet/{petId}";
    public static String post_url_form = base_url + "/pet/{petId}";
    public static String delete_url = base_url + "/pet/{petId}";
}
