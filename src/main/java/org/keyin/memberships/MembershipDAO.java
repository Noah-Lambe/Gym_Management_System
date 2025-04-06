package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
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

        } catch (SQLException e) {
            e.printStackTrace();
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
                        rs.getInt("membershipid"),
                        rs.getString("membershiptype"),
                        rs.getString("membershipdescription"),
                        rs.getDouble("membershipcost"),
                        rs.getInt("memberid"));
                memberships.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberships;
    }

    public double getTotalRevenue() {
        double total = 0;
        String sql = "SELECT SUM(membershipcost) FROM memberships";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    public List<Membership> getMembershipsByMemberId(int memberId) {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE memberid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                memberships.add(new Membership(
                        rs.getInt("membershipid"),
                        rs.getString("membershiptype"),
                        rs.getString("membershipdescription"),
                        rs.getDouble("membershipcost"),
                        rs.getInt("memberid")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberships;
    }
}
