package net.liuxuan.supportsystem.service.security;

import net.liuxuan.spring.security.dynamical.CustomInvocationSecurityMetadataSource;
import net.liuxuan.supportsystem.entity.security.UrlAuth;
import net.liuxuan.supportsystem.repository.security.UrlAuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (c) 2010-2016.  by Liuxuan   All rights reserved. <br/>
 * ***************************************************************************
 * 源文件名:  net.liuxuan.SprKi.service.security.UrlAuthServiceImpl
 * 功能:
 * 版本:	@version 1.0
 * 编制日期: 2017/03/22 14:41
 * 修改历史: (主要历史变动原因及说明)
 * YYYY-MM-DD |    Author      |	 Change Description
 * 2017-03-22  |    Moses        |     Created
 */
@Service
@Transactional
public class UrlAuthServiceImpl implements UrlAuthService {

    private static Logger log = LoggerFactory.getLogger(UrlAuthServiceImpl.class);

    /**
     * The Url auth repository.
     */
    @Autowired
    private UrlAuthRepository urlAuthRepository;

    @Autowired
    private CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;

    @Override
    @CacheEvict(cacheNames = "urlAuth",allEntries = true)
    public void saveUrlAuth(UrlAuth urlAuth) {
        urlAuthRepository.save(urlAuth);
        customInvocationSecurityMetadataSource.reload();
    }

    @Override
    @Cacheable(cacheNames = "urlAuth", key = "#id")
    public UrlAuth findUrlAuthById(Long id) {
        UrlAuth urlAuth = urlAuthRepository.findOne(id);
        return urlAuth;
    }

    @Override
    @CacheEvict(cacheNames = "urlAuth", allEntries = true)
    public boolean deleteUrlAuthById(Long id) {
        UrlAuth urlAuth = urlAuthRepository.getOne(id);

        if (urlAuth != null) {
            urlAuth.getRoles().forEach(role -> {
                role.getUrlAuths().remove(urlAuth);
            });

            urlAuthRepository.delete(urlAuth);
//            urlAuth.setDisabled(true);
            customInvocationSecurityMetadataSource.reload();
            return true;
        }

        return false;
    }

    @Override
//    @Cacheable(cacheNames = "urlAuth", key = "#{'exist_'+urlAuthname}")
    public boolean checkUrlAuthExists(String urlAuthname) {
        List<UrlAuth> list = findByUrlAuthName(urlAuthname);
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Cacheable(cacheNames = "urlAuth", key = "#{'list_'+urlAuthname}")
    public List<UrlAuth> findByUrlAuthName(String urlAuthname) {
        return urlAuthRepository.findByUrlAuthName(urlAuthname);
    }

    @Override
    @Cacheable(cacheNames = "urlAuth", key = "'list_disablefalse'")
    public List<UrlAuth> getAllUrlAuth() {
        return urlAuthRepository.findByDisabledFalse();
    }

    @Override
    @CacheEvict(cacheNames = "urlAuth",allEntries = true)
    public void resetCache(){

    }

}