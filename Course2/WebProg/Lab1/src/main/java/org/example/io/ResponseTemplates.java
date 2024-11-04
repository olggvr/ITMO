package org.example.io;

public enum ResponseTemplates {

    HTTP_RESPONSE("""
        Status: 200 OK
        Content-Type: application/json;charset=utf-8
        Content-Length: %d
        
        %s
        """),
    HTTP_ERROR("""
        Status: %d
        Content-Type: application/json;charset=utf-8
        Content-Length: %d
    
        %s
        """),
    RESULT_JSON ("""
        {
            "x": "%d",
            "y": "%f",
            "z": "%f",
            "time": "%s ns",
            "now": "%s",
            "result": %b
        }
        """),
    ERROR_JSON("""
        {
            "now": "%s",
            "reason": "%s"
        }
        """);

    ResponseTemplates(String template) {
    }
}
