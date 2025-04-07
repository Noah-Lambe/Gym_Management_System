package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {

    public void addMembership(Membership membership) {
        String sql = "INSERT INTO memberships (membership_type, description, cost, member_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, membership.getMembershipType());
            pstmt.setString(2, membership.getDescription());
            pstmt.setDouble(3, membership.getCost());
            pstmt.setInt(4, membership.getMemberId());

            pstmt.executeUpdate();
            System.out.println("Membership added successfully for member ID: " + membership.getMemberId());

        } catch (SQLException e) {
            System.out.println("Error adding membership: " + e.getMessage());
        }
    }

    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Membership m = new Membership(
                        rs.getInt("membership_id"),
                        rs.getString("membership_type"),
                        rs.getString("description"),
                        rs.getDouble("cost"),
                        rs.getInt("member_id"));
                memberships.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving memberships: " + e.getMessage());
        }

        return memberships;
    }

    public double getTotalRevenue() {
        double total = 0;
        String sql = "SELECT SUM(cost) FROM memberships";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (SQLException e) {
            System.out.println("Error calculating total revenue: " + e.getMessage());
        }

        return total;
    }

    public List<Membership> getMembershipsByMemberId(int memberId) {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                memberships.add(new Membership(
                        rs.getInt("membership_id"),
                        rs.getString("membership_type"),
                        rs.getString("description"),
                        rs.getDouble("cost"),
                        rs.getInt("member_id")));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving memberships for member ID " + memberId + ": " + e.getMessage());
        }

        return memberships;
    }
}
