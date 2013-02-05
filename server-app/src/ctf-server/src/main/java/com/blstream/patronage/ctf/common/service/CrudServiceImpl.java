package com.blstream.patronage.ctf.common.service;

import com.blstream.patronage.ctf.common.exception.NotFoundException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
public class CrudServiceImpl<T, ID extends Serializable, R extends PagingAndSortingRepository<T, ID>> implements CrudService<T, ID> {

    protected R repository;

    public void setRepository(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T create(T resource) {
        Assert.notNull(resource, "Resource couldn't be null");
        return repository.save(resource);
    }

    @Override
    @Transactional
    public T update(ID id, T resource) {
        Assert.notNull(id, "Resource ID couldn't be null");
        Assert.notNull(resource, "Resource couldn't be null");

        if (!repository.exists(id))
            throw new NotFoundException();

        return repository.save(resource);
    }

    @Override
    @Transactional
    public void delete(ID id) {
        Assert.notNull(id, "Resource ID couldn't be null");
        repository.delete(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAllWithCascade() {
        Iterable<T> list = repository.findAll();
        for (T element : list) {
            repository.delete(element);
        }
    }

    @Override
    public T findById(ID id) {
        Assert.notNull(id, "Resource ID couldn't be null");
        return repository.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public Long count() {
        return repository.count();
    }
}
