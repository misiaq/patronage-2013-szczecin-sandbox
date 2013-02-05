package com.blstream.patronage.ctf.web.controller.secured;

import com.blstream.patronage.ctf.common.web.controller.BaseRestController;
import com.blstream.patronage.ctf.model.PortalRole;
import com.blstream.patronage.ctf.service.PortalRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Named;

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
@Controller
@RequestMapping("/api/secured/role")
public class PortalRoleController extends BaseRestController<PortalRole, String, PortalRoleService> {

    private static final Logger logger = LoggerFactory.getLogger(PortalRoleController.class);

    @Inject
    @Named("portalRoleService")
    @Override
    public void setService(PortalRoleService service) {
        super.setService(service);
    }

    @Override
    public String getIdFromResource(PortalRole resource) {
        return resource.getAuthority();
    }

//    @Override
//    public PortalRole create(@RequestBody PortalRole resource) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("---- create method invoked ----");
//        }
//        return service.create(resource);
//    }

    @RequestMapping(value = "findByName/{authority}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PortalRole findByAuthority(@PathVariable String authority) {
        if (logger.isDebugEnabled()) {
            logger.debug("---- findByAuthority method invoked ----");
        }

        return service.findByAuthority(authority);
    }
}
