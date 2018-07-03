import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

public class UserDaoMongoImpl implements UserDao{

    private MongoClient mongo = null;
    DB db=null;
    DBCollection table=null;

    public UserDaoMongoImpl() {
        try {
            mongo = new MongoClient("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        db = mongo.getDB("test");
        table = db.getCollection("users");
    }

    @Override
    public Set<User> getAllUsers() {
        Set<User> users = new HashSet<>();
        DBCursor result = table.find();
        while (result.hasNext()){
            DBObject current = result.next();
            users.add(mapBDBOToUser((BasicDBObject) current));
        }
        return users;
    }

    @Override
    public User getByName(String name) {
        BasicDBObject query = new BasicDBObject();
        query.put("name", name);
        BasicDBObject result = (BasicDBObject) table.findOne(query);
        return mapBDBOToUser(result);
    }

    @Override
    public void updateByName(User user) {
        BasicDBObject query = new BasicDBObject();
        query.put("name", user.getName());
        BasicDBObject result = (BasicDBObject) table.findOne(query);
        table.update(result,mapUserToBDBO(user));
    }

    @Override
    public void deleteByName(User user) {
        BasicDBObject query = new BasicDBObject();
        query.put("name", user.getName());
        table.remove(query);
    }

    @Override
    public void add(User user) {
        table.save(mapUserToBDBO(user));
    }

    private BasicDBObject mapUserToBDBO(User user){
        BasicDBObject document = new BasicDBObject();
        BasicDBObject document2 = new BasicDBObject();
        document.put("name", user.getName());
        document.put("age", user.getAge());
        document.put("date", user.getDate());
        document2.put("city",user.getAddress().city);
        document2.put("street",user.getAddress().street);
        document2.put("house",user.getAddress().house);
        document.put("address", document2);
        return document;
    }

    private User mapBDBOToUser(BasicDBObject basicDBObject){
        BasicDBObject addressDB=(BasicDBObject)basicDBObject.get("address");
        Address address=new Address(addressDB.getString("city"),addressDB.getString("street"),addressDB.getInt("house"));
        return new User(basicDBObject.getString("name"),basicDBObject.getInt("age"),basicDBObject.getDate("date"),address);
    }
}
