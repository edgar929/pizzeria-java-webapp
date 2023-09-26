package enums;

public enum Requests {
    UPLOAD_PROPERTY("upload property"),
    DELETE_PIZZERIA("delete pizzeria"),
    ADD_OPTION("add option"),
    AVAILABLE_PIZZERIAS("available pizzerias"),
    AVAILABLE_OPTIONSETS("available optionsets"),
    PRINT_PIZZERIA("print pizzeria"),
    ORDER_PIZZERIA("order pizzeria"),
    UPDATE_BASEPRICE("update base price");
    
    private String _request;
    
    private Requests(String exc)
    {
        _request = exc;
    }
    
    public String get_request() {
        return _request;
    }
    
    public void set_request(String _request) {
        this._request = _request;
    }
}
