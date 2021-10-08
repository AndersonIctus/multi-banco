package com.multibanco.multiBanco.resource;

import com.multibanco.multiBanco.models.Perfil;
import com.multibanco.multiBanco.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilResource {
    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping()
    public List<Perfil> listar(Sort sort) {
        return perfilRepository.findAll(sort);
    }
}
