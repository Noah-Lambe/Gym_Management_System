package org.keyin.memberships;

import java.time.LocalDate;

//*\
// This is class file that represents a membership
//
public class Membership {
    private int membershipId;
    private String membershipType;
    private String description;
    private double cost;
    private int memberId;
    private LocalDate datePurchased;

    public Membership() {
    }

    public Membership(int membershipId, String membershipType, String description, double cost, int memberId,
            LocalDate datePurchased) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.description = description;
        this.cost = cost;
        this.memberId = memberId;
        this.datePurchased = LocalDate.now();
    }

    // Getters and setters
    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(LocalDate datePurchased) {
        this.datePurchased = datePurchased;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Membership{" +
                "membershipId=" + membershipId +
                ", membershipType='" + membershipType + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", memberId=" + memberId +
                '}';
    }
}