package com.taxisurfr.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
        @NamedQuery(name = "Route.getByQuery", query = "SELECT s FROM Route s WHERE s.startroute like  :query "),
        @NamedQuery(name = "Route.getByStartEnd", query = "SELECT s FROM Route s WHERE s.startroute =  :startroute AND s.endroute = :endroute ")
})
@XmlRootElement
public class Route implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    public static final long NO_ASSOCIATED = 0L;



    @Column
    public String startroute;
    @Column
    public String endroute;


    @Column(length = 1337)
    private String description;

    public Long getContractorId() {
        return contractorId;
    }

    public void setContractorId(Long contractorId) {
        this.contractorId = contractorId;
    }

    @Column
    private Long contractorId;


    @Column
    private PickupType pickupType = PickupType.AIRPORT;
    @Column
    private Long cents;
    private Long agentCents;
    private Long image;


    private boolean inactive;
    private Long associatedRoute = NO_ASSOCIATED;
    private String link;

    @Id
    @Column
    private long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartroute() {
        return startroute;
    }

    public void setStartroute(String startroute) {
        this.startroute = startroute;
    }

    public String getEndroute() {
        return endroute;
    }

    public void setEndroute(String endroute) {
        this.endroute = endroute;
    }

    public Long getCents() {
        return cents;
    }

    public void setCents(Long cents) {
        this.cents = cents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PickupType getPickupType() {
        return pickupType;
    }

    public void setPickupType(PickupType pickupType) {
        this.pickupType = pickupType;
    }

//
//
//    public Long getImage()
//    {
//        return image;
//    }
//
//    public void setImage(Long image)
//    {
//        this.image = image;
//    }
//
//    public String getEnd()
//    {
//        return end;
//    }
//
//    public void setEnd(String end)
//    {
//        this.end = end;
//    }
//
//    public PickupType getPickupType()
//    {
//        return pickupType;
//    }
//
//    public void setPickupType(PickupType pickupType)
//    {
//        this.pickupType = pickupType;
//    }
//
//    public void setCents(Long cents)
//    {
//        this.cents = cents;
//    }
//
//    public Long getCents()
//    {
//        return cents;
//    }
//
//    public void setAssociatedRoute(Long associatedRoute)
//    {
//        this.associatedRoute = associatedRoute;
//    }
//
//    public Long getAgentCents()
//    {
//        return agentCents;
//    }
//
//    public void setAgentCents(Long agentCents)
//    {
//        this.agentCents = agentCents;
//    }
//
//    public void setInactive(boolean inactive)
//    {
//        this.inactive = inactive;
//    }
//
//    public boolean getInactive()
//    {
//        return inactive;
//    }
//
//    public Long getAssociatedRoute()
//    {
//        return associatedRoute;
//    }
//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }


}
