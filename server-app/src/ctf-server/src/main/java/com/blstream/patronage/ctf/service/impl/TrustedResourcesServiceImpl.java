package com.blstream.patronage.ctf.service.impl;

import com.blstream.patronage.ctf.model.Player;
import com.blstream.patronage.ctf.model.PortalRole;
import com.blstream.patronage.ctf.model.PortalUser;
import com.blstream.patronage.ctf.service.PlayerService;
import com.blstream.patronage.ctf.service.PortalRoleService;
import com.blstream.patronage.ctf.service.PortalUserService;
import com.blstream.patronage.ctf.service.TrustedResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
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
 * User: mkr
 * Date: 2/8/13
 */
@Named("trustedResourcesService")
public class TrustedResourcesServiceImpl implements TrustedResourcesService {

    private Logger logger = LoggerFactory.getLogger(TrustedResourcesServiceImpl.class);

    private PlayerService playerService;

    private PortalUserService portalUserService;

    private PortalRoleService portalRoleService;


    @Transactional
    @Override
    public Player createPlayer(final Player player) {
        if (logger.isDebugEnabled()) {
            logger.debug("---- createPlayer");
        }
        PortalUser portalUser;
        Player newPlayer;

        Assert.notNull(player, "Player cannot be null");

        portalUser = player.getPortalUser();

        Assert.notNull(portalUser, "Portal user cannot be null");
        Assert.notNull(portalUser.getUsername(), "Username cannot be null");
        Assert.notNull(portalUser.getPassword(), "Password cannot be null");

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Portal user: %s is now creating...", portalUser.getUsername()));
        }

        portalUser.setRoles(loadDefaultRolesForPlayer());
        portalUser = portalUserService.create(portalUser);

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Portal user: %s was created [id: %s]", portalUser.getUsername(), portalUser.getId()));
        }

        player.setPortalUser(portalUser);

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Player %s is now creating...", player.getPortalUser().getUsername()));
        }

        newPlayer = playerService.create(player);

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Player %s was created [id: %s]", player.getPortalUser().getUsername(), player.getPortalUser().getId()));
        }

        return newPlayer;
    }

    @Inject
    @Named("playerService")
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
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

    private List<PortalRole> loadDefaultRolesForPlayer() {
        List<PortalRole> roles = new ArrayList<PortalRole>();

        PortalRole portalUser = portalRoleService.findByAuthority("PORTAL_USER");
        Assert.notNull(portalUser, "Portal user role doesn't exist.");
        roles.add(portalUser);

        PortalRole portalPlayer = portalRoleService.findByAuthority("PORTAL_PLAYER");
        Assert.notNull(portalPlayer, "Portal player role doesn't exist.");
        roles.add(portalPlayer);

        return roles;
    }
}
