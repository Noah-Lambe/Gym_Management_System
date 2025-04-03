package org.keyin.memberships;

import java.util.List;
import org.keyin.memberships.Membership;

// Service class for membership handle all the business logic
// and only uses the DAO to interact with the database it does not have methods to do so
// you can inject in your dao to use in your service. An example will be in the code
public class MembershipService {
    private MembershipDAO membershipDAO = new MembershipDAO();

    public void purchaseMembership(Membership membership) {
        if (membership.getCost() <= 0) {
            throw new IllegalArgumentException("Cost must be greater than 0");
        }

        membershipDAO.addMembership(membership);
        System.out.println("Membership purchased successfully");
    }

    public List<Membership> getAllMemberships() {
        return membershipDAO.getAllMemberships();
    }

    public double getTotalRevenue() {
        return membershipDAO.getTotalRevenue();
    }

    public List<Membership> getMembershipsByMemberId(int memberId) {
        return membershipDAO.getMembershipsByMemberId(memberId);
    }

    public double viewMembershipExpenses(int memberId) {
        List<Membership> memberships = membershipDAO.getMembershipsByMemberId(memberId);

        double totalCost = 0;
        for (Membership membership : memberships) {
            totalCost += membership.getCost();
        }

        return totalCost;
    }

    // When you inject in the DAO you have access to all methods in it
    // MembershipDAO dao = new MembershipDAO();

}
