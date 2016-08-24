package com.taxisurfr.manager;

import com.taxisurfr.domain.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class AgentManager extends AbstractDao<Agent> {
    @Inject
    Logger logger;

    public AgentManager() {
        super(Agent.class);;

    }

    public com.taxisurfr.domain.Agent getAgent(com.taxisurfr.domain.Contractor contractor)
    {
        return null;
//        return  ofy().load().type(Agent.class).id(contractor.getAgentId()).now();
    }

//    public AgentInfo createAgent(String agentEmail)
//    {
//        // check local user
//        Objectify ofy = ObjectifyService.ofy();
//
//        AgentInfo agentInfo = null;
//            // To create new user for testing
//
//        Agent agent = createAgent(agentEmail, false);
//
//            return  agent.getInfo();
//
//    }

    private Agent createAgent(String email,boolean admin)
    {
        return null;

//        Agent agent = ObjectifyService.ofy().load().type(Agent.class).filter("email", email).first().now();
//        if (agent == null)
//        {
//            agent = new Agent();
//            agent.setEmail(email);
//            agent.setAdmin(admin);
//            ObjectifyService.ofy().save().entity(agent).now();
//        }
//        return agent;
    }

    public Agent getAgent(String email)
    {
        return null;
//        Agent agent = ObjectifyService.ofy().load().type(Agent.class).filter("email =", email).first().now();
//        if (agent != null)
//        {
//            agentInfo = agent.getInfo();
//            logger.info("getUser for email " + email + " returned " + agentInfo.getEmail() + "  " + agentInfo.getId() + " " + agentInfo.isAdmin());
//        } else
//        {
//            if (SystemProperty.environment.value() !=
//                    SystemProperty.Environment.Value.Production && "test@example.com".equals(email)) {
//                    agentInfo = new AgentInfo();
//                    agentInfo.setEmail(email);
//            }
//        }
//        return agent;
    }

    public List<Agent> getAgents()
    {
        return null;
//        List<Agent> agents ; //= ObjectifyService.ofy().load().type(Agent.class).list();
//
//        for (Agent agent : agents)
//        {
//            AgentInfo agentInfo = agent.getInfo();
//            logger.info("getAgents:agent "+agentInfo.getEmail());
//            list.add(agent.getInfo());
//        }
//        return list;

    }

}
