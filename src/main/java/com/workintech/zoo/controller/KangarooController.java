package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech/kangaroos")
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
        return kangaroos.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kangaroo createKangaroo(@RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroos.get(kangaroo.getId());
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo) {
        if (kangaroos.containsKey(id)) {
            kangaroo.setId(id); // Ensure the ID remains the same
            kangaroos.put(id, kangaroo);
        }
        return kangaroos.get(id);
    }

    @DeleteMapping("/{id}")
    public void removeKangaroo(@PathVariable int id) {
        kangaroos.remove(id);
    }
}
