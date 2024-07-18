package api.endpoints.User;

//NUR URLS WERDEN HIER HINTERLEGT
// Create user (Post): https://petstore.swagger.io/v2/user/ <-- baseURL
//Get user (Get): https://petstore.swagger.io/v2/user/{username}
// Update User (Put): https://petstore.swagger.io/v2/user/{username}
// Delete user (Delete): https://petstore.swagger.io/v2/user/{username}
public class Routes {
    // public damit ich von überall auf die baseURL zugreifen kann
    public static String base_url = "https://petstore.swagger.io/v2/";

    //User module
    public static String post_url = base_url + "user";
    public static String get_url = base_url + "user/{username}";
    public static String update_url = base_url + "user/{username}";
    public static String delete_url = base_url + "user/{username}";

    //Store module

    //Pet module


}
