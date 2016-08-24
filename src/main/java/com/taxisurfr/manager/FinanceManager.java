package com.taxisurfr.manager;

import java.util.logging.Logger;

public class FinanceManager extends Manager
{
    private static final Logger logger = Logger.getLogger(FinanceManager.class.getName());

//    public class FinanceInfoComparator implements Comparator<FinanceInfo>
//    {
//
//        @Override
//        public int compare(FinanceInfo bi1, FinanceInfo bi2)
//        {
//            return (new DateTime(bi1.getDate()).isAfter(new DateTime(bi2.getDate()))) ? -1 : 1;
//        }
//    }
//
//    public FinanceManager()
//    {
//        ObjectifyService.register(Finance.class);
//
//    }
//
//    public List<FinanceInfo> getFinance(AgentInfo agentInfo)
//    {
//
//        List<FinanceInfo> list = Lists.newArrayList();
//        List<Finance> listFinance = ObjectifyService.ofy().load().type(Finance.class).filter("agentId =", agentInfo.getId()).list();
//
//        for (Finance transfer : listFinance)
//        {
//            FinanceInfo info = transfer.getInfo();
//            list.add(transfer.getInfo());
//        }
//
//        Collections.sort(list, new FinanceInfoComparator());
//        return list;
//    }
//
//    public List<FinanceInfo> addTransfer(FinanceInfo financeInfo)
//    {
//
//        Finance finance = new Finance();
//        finance.setAgentId(financeInfo.getAgentId());
//        finance.setType(FinanceInfo.Type.TRANSFER);
//        finance.setDate(financeInfo.getDate());
//        finance.setName(financeInfo.getName());
//        finance.setAmount(financeInfo.getAmount());
//        finance.setBookingId(null);
//        finance.setOrderRef(null);
//        ObjectifyService.ofy().save().entity(finance).now();
//
//        AgentInfo agentInfo = new AgentInfo();
//        agentInfo.setId(financeInfo.getAgentId());
//        return getFinance(agentInfo);
//    }
//
//    @Deprecated
//    public void addPayment(BookingInfo bookingInfo, Date date)
//    {
//        Route route = ObjectifyService.ofy().load().type(Route.class).id(bookingInfo.getRouteId()).now();
//        Contractor contractor = ObjectifyService.ofy().load().type(Contractor.class).id(route.getContractorId()).now();
//        Agent agent = ObjectifyService.ofy().load().type(Agent.class).id(contractor.getAgentId()).now();
//
//        AgentInfo agentInfo = agent.getInfo();
//        Long amount = route.getAgentCents() != null ? route.getAgentCents() : (long) (route.getCents() * 0.90);
//        logger.info("addpayment:" + amount);
//
//        Finance finance = new Finance();
//        finance.setAgentId(agentInfo.getId());
//        finance.setType(FinanceInfo.Type.PAYMENT);
//        finance.setDate(date);
//        finance.setName(bookingInfo.getName());
//        finance.setAmount(amount);
//        finance.setBookingId(bookingInfo.getId());
//        finance.setOrderRef(bookingInfo.getOrderRef());
//        finance.setAgentId(agentInfo.getId());
//        finance.setDeliveryDate(bookingInfo.getDate());
//        ObjectifyService.ofy().save().entity(finance).now();
//
//    }
//
//    public void addPayment(Booking booking, Date date)
//    {
//        Route route = ObjectifyService.ofy().load().type(Route.class).id(booking.getRoute()).now();
//        Contractor contractor = ObjectifyService.ofy().load().type(Contractor.class).id(route.getContractorId()).now();
//        Agent agent = ObjectifyService.ofy().load().type(Agent.class).id(contractor.getAgentId()).now();
//
//        AgentInfo agentInfo = agent.getInfo();
//        Long amount = route.getAgentCents() != null ? route.getAgentCents() : (long) (route.getCents() * 0.90);
//        logger.info("addpayment:" + amount);
//
//        Finance finance = new Finance();
//        finance.setAgentId(agentInfo.getId());
//        finance.setType(FinanceInfo.Type.PAYMENT);
//        finance.setDate(date);
//        finance.setName(booking.getName());
//        finance.setAmount(amount);
//        finance.setBookingId(booking.getId());
//        finance.setOrderRef(booking.getRef());
//        finance.setAgentId(agentInfo.getId());
//        finance.setDeliveryDate(booking.getDate());
//        ObjectifyService.ofy().save().entity(finance).now();
//
//    }
//
//    public void cancel(Long bookingId)
//    {
//        Finance finance = ObjectifyService.ofy().load().type(Finance.class).filter("bookingId =", bookingId).first().now();
//
//        finance.setAmount(0L);
//        ObjectifyService.ofy().save().entity(finance).now();
//    }

}
