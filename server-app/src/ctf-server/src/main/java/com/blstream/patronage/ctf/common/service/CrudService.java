package com.blstream.patronage.ctf.common.service;

import java.io.Serializable;
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
 * Date: 1/16/13
 */
public interface CrudService<T, ID extends Serializable> {

    T create(T resource);

    T update(ID id, T resource);

    void delete(ID id);

    void deleteAll();

    void deleteAllWithCascade();

    T findById(ID id);

    List<T> findAll();

    Long count();
}
