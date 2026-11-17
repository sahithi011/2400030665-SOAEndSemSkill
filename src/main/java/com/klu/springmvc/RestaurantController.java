package com.klu.springmvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @GetMapping
    public List<Map<String, Object>> getRestaurants() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> r1 = new HashMap<>();
        r1.put("id", 1);
        r1.put("NAME", "Pizza Hut");
        r1.put("LOCATION", "Vijaywada");

        Map<String, Object> r2 = new HashMap<>();
        r2.put("id", 2);
        r2.put("NAME", "Dominos");
        r2.put("LOCATION", "Guntur");

        list.add(r1);
        list.add(r2);

        return list;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRestaurant(@PathVariable Integer id) {

        List<Map<String, Object>> restaurants = getRestaurants();

        for (Map<String, Object> restaurant : restaurants) {
            Integer restaurantid = (Integer) restaurant.get("id");

            if (restaurantid.equals(id)) {
                return restaurant;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Restaurant ID not present");
        return response;
    }
}