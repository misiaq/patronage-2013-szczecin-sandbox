package com.blstream.patronage.ctf.web.controller.trusted;

import com.blstream.patronage.ctf.common.web.controller.AbstractRestController;
import com.blstream.patronage.ctf.model.Player;
import com.blstream.patronage.ctf.service.TrustedResourcesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/trusted")
public class TrustedResourcesController extends AbstractRestController {

    private static final Logger logger = LoggerFactory.getLogger(TrustedResourcesController.class);

    private TrustedResourcesService trustedResourcesService;

    @RequestMapping(value = "/createPlayer", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Player createPlayer(@RequestBody Player player) {
        if (logger.isDebugEnabled()) {
            logger.debug("---- createPlayer method invoked ----");
        }

        Assert.notNull(player, "Player cannot be null");

        player = trustedResourcesService.createPlayer(player);

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Player %s was created [id: %s]", player.getPortalUser().getUsername(), player.getPortalUser().getId()));
        }

        return player;
    }

    @Inject
    @Named("trustedResourcesService")
    public void setTrustedResourcesService(TrustedResourcesService trustedResourcesService) {
        this.trustedResourcesService = trustedResourcesService;
    }
}
