package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
    }

    @GetMapping
    public List<Kangaroo> getAllKangaroos() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable int id) {
        if(id<=0){
            throw new ZooException("Id must be greater then zero",HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaroo with given id doesn't exist:",HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
   // @ResponseStatus(HttpStatus.CREATED)
    public Kangaroo createKangaroo(@RequestBody Kangaroo kangaroo) {
if (kangaroo.getId()<=0 || kangaroo.getName() == null || kangaroo.getHeight()<=0 || kangaroo.getWeight()<=0 || kangaroo.getSleepHour()<=0 || kangaroo.getGender()==null){
    throw new ZooException("Wrong construction",HttpStatus.BAD_REQUEST);
}

        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo) {
        if(id<=0){
            throw new ZooException("Id must be greater then zero",HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaroo with given id doesn't exist:",HttpStatus.NOT_FOUND);
        }

            kangaroo.setId(id);
            kangaroos.put(id, kangaroo);

        return kangaroos.get(id);
    }

    @DeleteMapping("/{id}")
    public Kangaroo removeKangaroo(@PathVariable int id) {
        Kangaroo kangaroo=kangaroos.get(id);
        if(id<=0){
            throw new ZooException("Id must be greater then zero",HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaroo with given id doesn't exist:",HttpStatus.NOT_FOUND);
        }
        kangaroos.remove(id);
        return  kangaroo;
    }
}
