package com.multibanco.multiBanco.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteRest {

    @GetMapping()
    public String testarConexao() {
        return "Teste de Conexão REST";
    }
}
