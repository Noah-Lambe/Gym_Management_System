package org.keyin.memberships;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MembershipService {

    private MembershipDAO dao = new MembershipDAO();

    public void viewAllMembershipsAndRevenue() {
        List<Membership> allMemberships = dao.getAllMemberships();
        double totalRevenue = dao.getTotalRevenue();

        System.out.println("\n--- All Memberships ---");
        for (Membership m : allMemberships) {
            System.out.println(m);
        }

        System.out.println("\n Total Revenue: $" + totalRevenue);
    }

    public void purchaseMembership(Scanner scanner, int userId) {
        System.out.println("\n--- Purchase Membership ---");
        System.out.print("Membership Type: ");
        String type = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        Membership membership = new Membership();
        membership.setMembershipType(type);
        membership.setDescription(desc);
        membership.setCost(cost);
        membership.setMemberId(userId);
        membership.setDatePurchased(LocalDate.now());

        dao.addMembership(membership);
        System.out.println("Membership purchased successfully!");
    }

    public void viewMembershipExpenses(int userId) {
        List<Membership> userMemberships = dao.getMembershipsByMemberId(userId);
        double total = 0;

        System.out.println("\n--- Your Memberships ---");
        for (Membership m : userMemberships) {
            System.out.println(m);
            total += m.getCost();
        }

        System.out.println(" Total Spent: $" + total);
    }
}
