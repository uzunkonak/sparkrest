package com.uzunkonak.sparkapp.controller;

/**
 * Created by Caner Uzunkonak on 09.01.2017.
 */

import com.uzunkonak.sparkapp.model.Restaurant;
import com.uzunkonak.sparkapp.model.RestaurantRepository;
import com.uzunkonak.sparkapp.spark.ResponseError;
import com.uzunkonak.sparkapp.spark.SparkController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

import static com.uzunkonak.sparkapp.util.JsonUtil.json;
import static com.uzunkonak.sparkapp.util.JsonUtil.toJson;
import static spark.Spark.*;

@Component
@Order(value = 1)
public class RestaurantController implements SparkController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void register() {
        get("/restaurants", (req, res) -> restaurantRepository.findAll(), json());

        get("/restaurants/:id", (req, res) -> {
            String id = req.params(":id");
            Restaurant user = restaurantRepository.findOne(Long.parseLong(id));
            if (user != null) {
                return user;
            }
            res.status(400);
            return new ResponseError("No such restaurant with id '%s' found", id);
        }, json());

        post("/restaurants", (req, res) ->
                restaurantRepository.save(new Restaurant(req.queryParams("name"), req.queryParams("city"))), json());

        put("/restaurants/:id", (req, res) ->
                restaurantRepository.save(new Restaurant(Long.parseLong(req.params(":id")),
                        req.queryParams("name"), req.queryParams("email"))), json());

        after((req, res) -> {
            res.type("application/json");
        });

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
    }
}
