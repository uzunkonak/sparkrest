# Java Spark Restful Web Service sample app

RestaurantController.java
```java
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
```

Spring Data JPA, Hibernate, Spring Boot, Spark used.
Database is Mysql.

To open in IDEA:<br />
mvn idea:idea<br />


create database sparkjava<br />
Simply run RestApplication and let hibernate creates necessary tables.<br />
Add samples records.<br />

Test via browser:<br />

http://localhost:4567/restaurants/<br />
http://localhost:4567/restaurants/2<br />

You can find more about spark: http://sparkjava.com/documentation.html
