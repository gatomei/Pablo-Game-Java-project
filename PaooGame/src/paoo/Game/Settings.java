package paoo.Game;
import java.sql.*;

public class Settings {

    public Settings(int level)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:baza.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Joc where LEVEL= "+level+";");
            while(rs.next())
            {
                String s=rs.getString("FilePath");
                Map.setPath(s);
            }
            rs.close();
           stmt.close();
            c.close();
        }
        catch (Exception exc)
        {
            System.out.println(exc.getStackTrace());
        }
    }
    public static void SetHighScore(int level,int score)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:baza.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE Joc set HighScore = "+score+" where LEVEL="+level+";");
            c.commit();
            stmt.close();
            c.close();
        }
        catch (Exception exc)
        {
            System.out.println(exc.getStackTrace());
        }
    }
    public static int GetHighScore(int level)
    {
        int x=0;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:baza.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Joc where LEVEL="+level+";");
            while(rs.next())
            {
                x=rs.getInt("HighScore");
            }
            rs.close();
            stmt.close();
            c.close();
        }
        catch (Exception exc)
        {
            System.out.println(exc.getStackTrace());
        }
        return x;
    }
}
