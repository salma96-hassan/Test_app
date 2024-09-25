package com.Test.app.Test_app.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public abstract class ConstantStrings {
    public static final long ACCESS_TOKEN_VALID_SEC=1000*60*60;
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";
}
