package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    Map<Integer, Koala> koalas;

    @PostConstruct
    public void init(){
        koalas=new HashMap<>();
    }


    @GetMapping
    public List<Koala> getAllKoalas(){
        return koalas.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Koala getKoalaById(@PathVariable int id){
        return koalas.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Koala createKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return koalas.get(koala.getId());
    }

    @PutMapping("/{id}")
    public Koala updateKoala(@PathVariable int id,@RequestBody Koala koala){
        if(koalas.containsKey(id)){
            koalas.put(id,koala);
        }
        return koalas.get(id);
    }
    @DeleteMapping("/{id}")
    public void removeKoala(@PathVariable int id){
        koalas.remove(id);
    }
}
