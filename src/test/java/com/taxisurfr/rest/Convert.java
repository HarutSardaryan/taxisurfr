package com.taxisurfr.rest;

import com.taxisurfr.domain.PickupType;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.PrintWriter;

/**
 * Created by nbb on 19.07.2016.
 */
public class Convert {

    public static final String QUERY_STMT = "insert into Route (id,routeStart,cents,link,inactive,routeEnd,description,contractorId,associatedRoute,pickupType) VALUES(";
    public static void main(String[] args) {
        new Convert().doit();
    }

    private void doit() {
        Serializer serializer = new Persister();
        File source = new File("src/test/resources/routes.txt");
        try {
            PrintWriter out = new PrintWriter("src/test/resources/insert_routes.txt");

            RouteList example = serializer.read(RouteList.class, source);
            for (RouteData rd : example.list){
                PickupType pickupType = rd.pickupType.equals("AIRPORT") ? PickupType.AIRPORT : PickupType.HOTEL;
                String insertStmt =QUERY_STMT+rd.id+",'"+rd.start+"',"+rd.cents+",'"+rd.link+"',"+rd.inactive+",'"+rd.end+"','"+rd.description+"',"+rd.contractorId+","+rd.associatedRoute+",'"+pickupType.ordinal()+"');";
//                System.out.println(insertStmt);
                out.println(insertStmt);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

