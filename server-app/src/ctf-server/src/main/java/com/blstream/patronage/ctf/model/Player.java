package com.blstream.patronage.ctf.model;

import com.blstream.hooks.springframework.mongodb.mapping.DBRefCascade;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

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
 * User: lahim
 * Date: 1/22/13
 */
@Document
public class Player implements Serializable {

    private static final long serialVersionUID = 4434354945709061742L;

    private @Id String id;
    private @Field PlayerType type;

    @DBRef
//    @DBRefCascade
    private PortalUser portalUser;

    public Player() {
    }

    public Player(PlayerType type, PortalUser portalUser) {
        this.type = type;
        this.portalUser = portalUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }
}
