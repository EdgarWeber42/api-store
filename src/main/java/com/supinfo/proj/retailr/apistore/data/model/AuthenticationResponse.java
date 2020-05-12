package com.supinfo.proj.retailr.apistore.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AuthenticationResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String jwt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String response;

    public AuthenticationResponse(String jwt, String response) {
        this.jwt = jwt;
        this.response = response;
    }

    public String getJwt() {
        return jwt;
    }

    public String getResponse() {
        return response;
    }
}
