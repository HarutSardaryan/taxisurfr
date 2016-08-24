package com.taxisurfr.manager;

import com.taxisurfr.domain.Contractor;
import com.taxisurfr.domain.Route;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Logger;

@Stateless
public class ContractorManager extends AbstractDao<Contractor> {
    @Inject
    Logger logger;

    public ContractorManager() {
        super(Contractor.class);;

    }


//    public ContractorInfo createContractor(ContractorInfo contractorInfo)
//    {
//
//        Contractor contractor = Contractor.getContractor(contractorInfo);
//        ObjectifyService.ofy().save().entity(contractor).now();
//        return contractor.getInfo();
//    }
//
//    @SuppressWarnings("unchecked")
//    public List<ContractorInfo> getContractors(AgentInfo agentInfo)
//    {
//        List<ContractorInfo> list = Lists.newArrayList();
//        List<Contractor> contractors;
//        if (agentInfo == null)
//        {
//            contractors = ObjectifyService.ofy().load().type(Contractor.class).list();
//        }
//        else
//        {
//            contractors = ObjectifyService.ofy().load().type(Contractor.class).filter("agentId =", agentInfo.getId()).list();
//        }
//
//        for (Contractor contractor : contractors)
//        {
//            list.add(contractor.getInfo());
//        }
//        return list;
//
//    }
//
//    public void delete(ContractorInfo contractorInfo)
//    {
//        throw new RuntimeException();
//
//    }

//    public List<ContractorInfo> deleteContractor(AgentInfo agentInfo, ContractorInfo contractorInfo)
//    {
//        Contractor contractor = ofy().load().type(Contractor.class).id(contractorInfo.getId()).now();
//
//        ofy().delete().entity(contractor).now();
//        return getContractors(agentInfo);
//
//    }
//
//    public List<ContractorInfo> saveContractor(AgentInfo agentInfo, ContractorInfo contractorInfo, ContractorInfo.SaveMode mode) throws IllegalArgumentException
//    {
//        List<ContractorInfo> routes = null;
//        contractorInfo.setAgentId(agentInfo.getId());
//        Contractor contractor;
//        switch (mode)
//        {
//            case ADD:
//                contractor = new Contractor();
//                persist(contractor, contractorInfo);
//                break;
//
//            case UPDATE:
//                contractor = ofy().load().type(Contractor.class).id(contractorInfo.getId()).now();
//                persist(contractor, contractorInfo);
//                break;
//        }
//
//        return getContractors(agentInfo);
//
//    }
//
//    private void persist(Contractor contractor, ContractorInfo contractorInfo)
//    {
//        contractor.setName(contractorInfo.getName());
//        contractor.setEmail(contractorInfo.getEmail());
//        contractor.setAgentId(contractorInfo.getAgentId());
//        contractor.setAddress(contractorInfo.getAddress());
//
//        ofy().save().entity(contractor).now();
//    }
}
