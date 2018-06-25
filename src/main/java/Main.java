import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

//Получить все базы
/*        List<String> dbs = mongo.getDatabaseNames();
        for(String db : dbs){
            System.out.println(db);
        }*/

//Получить все коллекции из базы
/*        DB db = mongo.getDB("test");
        Set<String> tables = db.getCollectionNames();
        for(String coll : tables){
            System.out.println(coll);
        }*/

//Получить коллекцию
/*        DB db = mongo.getDB("test");
        DBCollection table = db.getCollection("users");*/

//Добавить данные в коллекцию
/*        DB db = mongo.getDB("test");
        DBCollection table = db.getCollection("users");
        BasicDBObject document = new BasicDBObject();
        document.put("name", "Tom");
        document.put("age", 32);
        table.insert(document);*/

//Получить все из коллекции
/*        DB db = mongo.getDB("test");
        DBCollection table = db.getCollection("users");
        DBCursor result = table.find();
        while (result.hasNext()){
            DBObject current = result.next();
            System.out.println(current.get("_id") + " " + current.get("name") + " " + current.get("profession"));
        }*/

//Получить конкретного из коллекции
/*        DB db = mongo.getDB("test");
        DBCollection table = db.getCollection("users");
        BasicDBObject query = new BasicDBObject();
        query.put("name", "Tom");
        DBObject result = table.findOne(query);
        System.out.println(result.get("_id") + " " + result.get("name"));*/

//Изменить конкретного криво
/*        DB db = mongo.getDB("test");
        DBCollection table = db.getCollection("users");
        BasicDBObject newData = new BasicDBObject();
        newData.put("profession", "Gardener");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", "Tom");
        table.update(searchQuery, newData);*/

//Удаление
        DB db = mongo.getDB("test");
        DBCollection table = db.getCollection("users");
        BasicDBObject query = new BasicDBObject();
        query.put("name", "Tommy");
        table.remove(query);
    }
}
