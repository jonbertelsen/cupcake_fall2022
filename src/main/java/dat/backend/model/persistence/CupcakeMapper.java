package dat.backend.model.persistence;

import dat.backend.model.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper
{

    public static Top getTopById(int topId, ConnectionPool connectionPool)
    {
        String sql = "select * from top where top_id = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, topId);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    Top newTop = new Top(topId, name, price);
                    return newTop;
                }
                else
                {
                    return null;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Bottom getBottomById(int buttomId, ConnectionPool connectionPool)
    {
        String sql = "select * from bottom where bottom_id = ?";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, buttomId);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    Bottom newBottom = new Bottom(buttomId, name, price);
                    return newBottom;
                }
                else
                {
                    return null;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Top> getAllToppings(ConnectionPool connectionPool)
    {
        List<Top> topList = new ArrayList<>();
        String sql = "select * from top";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int topId = rs.getInt("top_id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    Top newTop = new Top(topId, name, price);
                    topList.add(newTop);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return topList;
    }

    public static List<Bottom> getAllBottoms(ConnectionPool connectionPool)
    {
        List<Bottom> bottomList = new ArrayList<>();
        String sql = "select * from bottom";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int bottomId = rs.getInt("bottom_id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    Bottom newBottom = new Bottom(bottomId, name, price);
                    bottomList.add(newBottom);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return bottomList;
    }


    public static int createOrder(User user, ConnectionPool connectionPool)
    {
        String sql = "insert into orders (customer_name) values (?)";

        try (Connection connection = connectionPool.getConnection())
        {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, user.getUsername());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public static void insertOrderLines(int orderId, ShoppingCart cart, ConnectionPool connectionPool)
    {
        String sql = "insert into order_lines (" +
                "order_id, top_id, bottom_id, " +
                "top_price, bottom_price, amount ) values (?,?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection())
        {
            for (Cupcake cupcake: cart.getCupcakeList())
            {
                try (PreparedStatement ps = connection.prepareStatement(sql))
                {
                    ps.setInt(1, orderId);
                    ps.setInt(2, cupcake.getTop().getId());
                    ps.setInt(3, cupcake.getBottom().getId());
                    ps.setDouble(4, cupcake.getTop().getPrice());
                    ps.setDouble(5, cupcake.getBottom().getPrice());
                    ps.setInt(6, cupcake.getQuantity());
                    ps.executeUpdate();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
