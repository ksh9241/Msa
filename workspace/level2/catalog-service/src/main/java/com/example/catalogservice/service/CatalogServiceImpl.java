package com.example.catalogservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 파이널이 붙은 인스턴스 생성자를 만들어줌 (롬복)
public class CatalogServiceImpl implements CatalogService{
    private final CatalogRepository catalogRepository;

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
