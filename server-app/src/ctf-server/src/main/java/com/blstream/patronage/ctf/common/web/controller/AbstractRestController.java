package com.blstream.patronage.ctf.common.web.controller;

import com.blstream.patronage.ctf.common.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Date: 1/31/13
 */
public abstract class AbstractRestController {

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleBadRequestException(Exception e, HttpServletRequest requst, HttpServletResponse response) {
        MappingJacksonJsonView view = new MappingJacksonJsonView();
        view.getAttributesMap().put("error", e.getMessage());
        return new ModelAndView(view);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(Exception e, HttpServletRequest requst, HttpServletResponse response) {
        MappingJacksonJsonView view = new MappingJacksonJsonView();
        view.getAttributesMap().put("error", e.getMessage());
        return new ModelAndView(view);
    }
}
