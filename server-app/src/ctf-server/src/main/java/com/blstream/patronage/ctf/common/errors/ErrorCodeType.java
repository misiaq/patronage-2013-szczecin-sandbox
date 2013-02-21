package com.blstream.patronage.ctf.common.errors;

/**
 * Copyright 2013 BLStream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * User: mkr
 * Date: 2/19/13
 *
 * This class is a representation of enum type for error codes.
 * All error codes are stored in error-codes.properties file.
 */
public enum ErrorCodeType {
    SUCCESS("0.0.0"),

    FAILED("5.0.0"),
    BAD_REQUEST("4.0.4"),

    CANNOT_CREATE_NEW_PLAYER("1.0.1"),
    PLAYER_ALREADY_EXISTS("1.0.2"),
    ;

    private String code;

    private ErrorCodeType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return code;
    }
}
