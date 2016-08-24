package com.taxisurfr.manager;

import com.taxisurfr.domain.Route;
import com.taxisurfr.domain.SessionStat;
import com.taxisurfr.rest.js.NewSessionJS;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Stateless
public class StatManager extends AbstractDao<SessionStat>
{
    private static final Logger logger = Logger.getLogger(StatManager.class.getName());
    public static String newline = System.getProperty("line.separator");

    public StatManager()
    {
        super(SessionStat.class);;
    }

    public SessionStat addSession(NewSessionJS newSessionJS) {
        SessionStat sessionStat = new SessionStat();
        sessionStat.setTime(LocalDateTime.now());
        sessionStat.setSrc(extractSrc(newSessionJS.url));
        persist(sessionStat);
        return sessionStat;
    }

    private String extractSrc(String url){
        String src = url != null ? url.contains("?src=") ? url.substring(url.indexOf("?src=")+5) : null : null;
        return src;
    }
//    public void updateSessionStat(StatInfo statInfo)
//    {
//        SessionStat sessionStat = ofy().load().type(SessionStat.class).filter("ip", statInfo.getIp()).first().now();
//        if (sessionStat != null)
//        {
//            switch (statInfo.getUpdate())
//            {
//                case TYPE:
//                    sessionStat.setType(statInfo.getDetail());
//                    break;
//                case ROUTE:
//                    sessionStat.setRoute(statInfo.getDetail());
//                    break;
//            }
//            ofy().save().entity(sessionStat).now();
//        }
//        else
//        {
//            logger.log(Level.SEVERE, "not session found for ip " + statInfo.getIp());
//        }
//     }
//
//    public void report()
//    {
//        List<SessionStat> list = getAll(SessionStat.class);
//        String report = "sessions:" + list.size();
//        for (SessionStat stat : list)
//        {
//            report += "<br> src=" + stat.getSrc() + "  country=" + stat.getCountry() + "  type=" + stat.getType();
//
//        }
//
//        Mailer.sendReport(report);
//        deleteAll(SessionStat.class);
//
//    }
//

}
