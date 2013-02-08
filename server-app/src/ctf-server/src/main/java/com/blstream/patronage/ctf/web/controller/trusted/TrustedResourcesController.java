package com.blstream.patronage.ctf.web.controller.trusted;

import com.blstream.patronage.ctf.common.web.controller.AbstractRestController;
import com.blstream.patronage.ctf.model.Player;
import com.blstream.patronage.ctf.model.PortalRole;
import com.blstream.patronage.ctf.model.PortalUser;
import com.blstream.patronage.ctf.service.PlayerService;
import com.blstream.patronage.ctf.service.PortalRoleService;
import com.blstream.patronage.ctf.service.PortalUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
@RequestMapping("/api/trusted")
public class TrustedResourcesController extends AbstractRestController {

    private static final Logger logger = LoggerFactory.getLogger(TrustedResourcesController.class);

    private PlayerService playerService;

    private PortalUserService portalUserService;

    private PortalRoleService portalRoleService;


    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody PortalUser createUser(@RequestBody PortalUser portalUser) {
        if (logger.isDebugEnabled()) {
            logger.debug("---- createUser method invoked ----");
        }

        Assert.notNull(portalUser, "Portal user cannot be null");
        Assert.notNull(portalUser.getUsername(), "Username cannot be null");
        Assert.notNull(portalUser.getPassword(), "Password cannot be null");

        portalUser = portalUserService.create(portalUser);

        return portalUser;
    }

    @RequestMapping(value = "/createPlayer", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Player createPlayer(@RequestBody Player player) {
        if (logger.isDebugEnabled()) {
            logger.debug("---- createPlayer method invoked ----");
        }

        PortalUser portalUser;

        Assert.notNull(player, "Player cannot be null");

        portalUser = player.getPortalUser();

        Assert.notNull(portalUser, "Portal user cannot be null");
        Assert.notNull(portalUser.getUsername(), "Username cannot be null");
        Assert.notNull(portalUser.getPassword(), "Password cannot be null");

        portalUser = portalUserService.create(portalUser);

        player.setPortalUser(portalUser);
        player = playerService.create(player);

        return player;
    }

    @RequestMapping(value = "/getRoles", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<PortalRole> getRoles() {
        if (logger.isDebugEnabled()) {
            logger.debug("---- getRoles method invoked ----");
        }
        return portalRoleService.findAll();
    }

    @Inject
    @Named("portalUserService")
    public void setPortalUserService(PortalUserService portalUserService) {
        this.portalUserService = portalUserService;
    }

    @Inject
    @Named("portalRoleService")
    public void setPortalRoleService(PortalRoleService portalRoleService) {
        this.portalRoleService = portalRoleService;
    }
}
