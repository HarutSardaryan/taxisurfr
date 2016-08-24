package com.taxisurfr.manager;

import com.taxisurfr.domain.Route;
import com.taxisurfr.rest.js.Query;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

@Stateless
public class RouteManager extends AbstractDao<Route>{

    @Inject
    Logger logger;
//    @PersistenceContext(unitName = "taxisurfr")
//    protected EntityManager em;

    private static List<Route> routeInfoCache;

    public RouteManager() {
        super(Route.class);;

    }

//    public List<RouteInfo> deleteRoute(AgentInfo userInfo, RouteInfo routeInfo) throws IllegalArgumentException {
//        Route route = ofy().load().type(Route.class).id(routeInfo.getId()).now();
//        route.setInactive(true);
//        ofy().save().entity(route);
//        final List<Route> all = getAll(Route.class);
//        return getRoutes(userInfo);
//    }

//    public List<RouteInfo> saveRoute(AgentInfo userInfo, RouteInfo routeInfo, RouteInfo.SaveMode mode) throws IllegalArgumentException {
//        addRoute(userInfo, routeInfo, mode);
//        return getRoutes(userInfo);
//    }

//    public RouteInfo addRoute(AgentInfo userInfo, RouteInfo routeInfo, RouteInfo.SaveMode mode) throws IllegalArgumentException {
//        Route route = null;
//        switch (mode) {
//            case ADD:
//            case ADD_WITH_RETURN:
//                route = new Route();
//                persist(route, routeInfo);
//                if (mode.equals(RouteInfo.SaveMode.ADD_WITH_RETURN)) {
//                    route = new Route();
//                    String start = routeInfo.getEnd();
//                    String end = routeInfo.getStart();
//                    routeInfo.setStart(start);
//                    routeInfo.setEnd(end);
//                    routeInfo.setPickupType(RouteInfo.PickupType.HOTEL);
//                    persist(route, routeInfo);
//                }
//                break;
//
//            case UPDATE:
//                route = ofy().load().type(Route.class).first().now();
//                persist(route, routeInfo);
//                break;
//        }
//
//        return route.getInfo();
//    }

//    private void persist(Route route, RouteInfo routeInfo) {
//        route.setStart(routeInfo.getStart());
//        route.setEnd(routeInfo.getEnd());
//        route.setCents(routeInfo.getCents());
//        route.setAgentCents(routeInfo.getAgentCents());
//
//        route.setPickupType(routeInfo.getPickupType());
//        route.setImage(routeInfo.getImage());
//        route.setDescription(routeInfo.getDescription());
//        route.setContractorId(routeInfo.getContractorId());
//        route.setAssociatedRoute(routeInfo.getAssociatedRoute());
//
//        ofy().save().entity(route).now();
//    }

    @SuppressWarnings("unchecked")
//    public List<RouteInfo> getRoutes(AgentInfo agentInfo) throws IllegalArgumentException {
//        List<RouteInfo> routes = new ArrayList<>();
//        logger.info("getting routes for agent email " + agentInfo.getEmail() + " id " + agentInfo.getId());
//
//        // find a list of providers being managed by this user
//        List<Contractor> contractorList = ofy().load().type(Contractor.class).filter("agentId =", agentInfo.getId()).list();
//
//        List<Long> contractorIdList = Lists.newArrayList();
//        for (Contractor contractor : contractorList) {
//            contractorIdList.add(contractor.getInfo().getId());
//            logger.info("contractorId:" + contractor.getInfo().getId());
//        }
//
//        List<Route> resultList = ofy().load().type(Route.class).list();
//        logger.info("get route count " + resultList.size());
//        for (Route route : resultList) {
//            RouteInfo routeInfo = route.getInfo();
//            logger.info("routeInfo.getContractorId() " + routeInfo.getContractorId());
//
//            if (agentInfo == null || contractorIdList.contains(routeInfo.getContractorId())) {
//                if (!routeInfo.isInactive()) {
//                    routes.add(routeInfo);
//                }
//            }
//        }
//        logger.info("returning routeinfo count " + routes.size());
//
//        return routes;
//
//    }

//    public List<RouteInfo> getRoutes(String query) throws IllegalArgumentException {
//        logger.info("query routes" + query);
//        query = query.toUpperCase();
//        List<RouteInfo> routes = new ArrayList<>();
//        routeInfoCache = ofy().load().type(Route.class).filter("inactive =", false).list();
//        logger.info("get all routes returned no. of routes" + routeInfoCache.size());
//        List<Route> resultList = routeInfoCache;
//
//        for (Route route : resultList) {
//            RouteInfo routeInfo = route.getInfo();
//            if (routeInfo.getStart().toUpperCase().startsWith(query)) {
//                routes.add(routeInfo);
//            }
//        }
//        logger.info("get queried routes returned no. of routes" + routes.size());
//        return routes;
//
//    }

    @Deprecated
//    public RouteInfo getRouteAsInfo(Long routeId) {
//        return ofy().load().type(Route.class).id(routeId).now().getInfo();
//    }
//
//    public Route getRoute(Long routeId) {
//        return ofy().load().type(Route.class).id(routeId).now();
//    }
//
//    public static void resetCache() {
//        routeInfoCache = null;
//    }
//
//    public void loadall() {
//        for (int i = 0; i < 10; i++) {
//            List<Route> list = ofy().load().type(Route.class).list();
//            logger.info("loadall " + i + "  " + list.size());
//
//        }
//    }

    public List<Route> getRoutesAsEntities(String query) {

        return null;
//        final String queryForPredicate = query.trim().replace(" to", "").toUpperCase();
//        return FluentIterable.from(ofy().load().type(Route.class).list()).filter(new Predicate<Route>() {
//            @Override
//            public boolean apply(@Nullable Route route) {
//                return route.getStart().toUpperCase().startsWith(queryForPredicate);
//            }
//        }).toList();
    }

    public Route getRouteFromLink(String link) {
        return null;
//        List<Route> list = ofy().load().type(Route.class).filter("link =", link).list();
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
    }

    public List<Route> getRoutesFromQuery(final String start, final String end) {
        List<Route> result = new ArrayList<>();
        if (start != null && end != null) {

            final String startUpper = start.toUpperCase();
            final String endUpper = end.toUpperCase();
//            result = FluentIterable.from(ofy().load().type(Route.class).list()).filter(new Predicate<Route>() {
//                @Override
//                public boolean apply(@Nullable Route route) {
//                    return route.getStart().toUpperCase().equals(startUpper) && route.getEnd().toUpperCase().startsWith(endUpper);
//                }
//            }).toList();
        }
        return result;
    }

    public List<Route> getRoutesStart(String query) {
        query += '%';
        logger.info("query:"+query);
        List<Route> routes = getEntityManager().createNamedQuery("Route.getByQuery")
                .setParameter("query", query)
                .getResultList();
        logger.info("count:"+routes);
        return routes;

    }


    public Route getRoute(Query query) {
        List<Route> routes = getEntityManager().createNamedQuery("Route.getByStartEnd")
                .setParameter("startroute", query.start)
                .setParameter("endroute", query.end)
                .getResultList();
        return routes.size() > 0 ? routes.get(0) : null;
    }

    public List<Route> getRoutes() {
        return findAll();
    }
}
