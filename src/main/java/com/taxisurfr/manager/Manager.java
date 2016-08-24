package com.taxisurfr.manager;

import javax.inject.Inject;
import java.util.logging.Logger;

//public class Manager<T extends Info, K extends ArugamEntity<?>>
public class Manager
{
//    DBCollection dbCollection;

    @Inject
    Logger logger;

//    protected DBCollection getCollection(String collectionNeme){
//        if (dbCollection == null){
//            try {
//                // Get an instance of Mongo
//                Mongo m = new Mongo("localhost", 27017);
//                DB db = m.getDB("taxisurfr");
//                dbCollection = db.getCollection(collectionNeme);
//                if (dbCollection == null) {
//                    dbCollection = db.createCollection(collectionNeme, null);
//                }
//            } catch (UnknownHostException ex) {
//                logger.severe(ex.getMessage());
//            }
//        }
//        return dbCollection;
//
//    }
//
//    @PersistenceContext(unitName = "taxisurfr")
//    protected EntityManager em;
//    public void deleteAll(Class<?> entityType)
//    {
//        List<K> resultList = getAll(entityType);
//        for (K entity : resultList)
//        {
//            ObjectifyService.ofy().delete().entity(entity).now();
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<T> getAllInfo(Class<?> entityType)
//    {
//        List<K> resultList = getAll(entityType);
//        List<T> list = Lists.newArrayList();
//        for (K entity : resultList)
//        {
//            list.add((T) entity.getInfo());
//        }
//        return list;
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<K> getAll(Class<?> entityType)
//    {
//        new ImageManager();
//        new BookingManager();
//        List<K> agents = (List<K>) ObjectifyService.ofy().load().type(entityType).list();
//        return agents;
//    }
//
//    public void importDataset(String dataset, Class<?> type) {
//        deleteAll(type);
//
//        String[] datasets = dataset.split("<list>");
//        for (String ds : datasets) {
//            if (ds.contains(type.getSimpleName() + "Info")) {
//                dataset = "<list>" + ds;
//                break;
//            }
//        }
//
//        @SuppressWarnings("unchecked")
//        List<T> list = (List<T>) new XStream().fromXML(dataset);
//        for (T info : list)
//        {
//            if (type.equals(Route.class))
//            {
//                Route entity = Route.getRoute((RouteInfo) info);
//                save(entity, type, info);
//            }
//            if (type.equals(ArugamImage.class))
//            {
//                ArugamImage entity = ArugamImage.getArugamImage((ArugamImageInfo) info);
//                save(entity, type, info);
//            }
//            if (type.equals(Booking.class))
//            {
//                Booking entity = Booking.getBooking((BookingInfo) info, null);
//                save(entity, type, info);
//            }
//            if (type.equals(Contractor.class))
//            {
//                Contractor entity = Contractor.getContractor((ContractorInfo) info);
//                save(entity, type, info);
//            }
//            if (type.equals(Agent.class))
//            {
//                Agent entity = Agent.getAgent((AgentInfo) info);
//                save(entity, type, info);
//            }
//            if (type.equals(Rating.class))
//            {
//                Rating entity = Rating.getRating((RatingInfo) info);
//                save(entity, type, info);
//            }
//        }
//    }
//
//    public void save(ArugamEntity<?> entity, Class type, Info info)
//    {
//        ObjectifyService.ofy().save().entity(entity).now();
//
//    }
//
//    public String dump(Class<?> entityType)
//    {
//        return new XStream().toXML(getAllInfo(entityType));
//    }

}
