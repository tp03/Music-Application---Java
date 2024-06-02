import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class userTest {


    @Test
    public void testUserCreation() {

        boolean test_result = false;

        UserCreator user_creator_test = new UserCreator();

        try{

            user_creator_test.created_user("test1", "testowy", "ksywka1", "test@gmail.com", "12345678");

            if (user_creator_test.getSuccessful() == 1)
            {
                test_result = true;
            }

            else
            {
                test_result = false;
            }

            Connection connection = null;
            try {
                DatabaseConnection DC = new DatabaseConnection();
                connection = DC.MakeConnection();
                String insert_query = "DELETE FROM app_user WHERE user_id = " + user_creator_test.getId();
                PreparedStatement prepstat = connection.prepareStatement(insert_query);
                prepstat.executeQuery();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }  
        }
        catch (Exception e)
        {}
        assertTrue(test_result); 
    } 

    @Test
    public void testId() {

        boolean test_result = false;

        try{

            UserCreator user_creator_test = new UserCreator();

            user_creator_test.created_user("test2", "testowy", "ksywka2", "test@gmail.com", "12345678");
            if (user_creator_test.getId() != 0)
            {
                test_result = true;
            }

            else
            {
                test_result = false;
            }
            Connection connection = null;
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "DELETE FROM app_user WHERE user_id = " + user_creator_test.getId();
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
        }
        catch (Exception e)
        {}
        
        assertTrue(test_result);        
    }

    @Test
    void testID2() {

        UserCreator user_creator_test = new UserCreator();

        try {
            
            user_creator_test.created_user("test3", "testowy", "ksywka3", "test@gmail.com", "12345678");

            UserReader user_reader_test = new UserReader("ksywka3", "12345678");


            Spotify_user tested_user = user_reader_test.searchDB();
            
            assertTrue(tested_user.getId() == user_creator_test.getId());
        }
        catch (Exception e)
        {}

        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "DELETE FROM app_user WHERE user_id = " + user_creator_test.getId();
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

    }

    @Test
    void testName() {

        UserCreator user_creator_test = new UserCreator();

        try {

            user_creator_test.created_user("test4", "testowy", "ksywka4", "test@gmail.com", "12345678");

            UserReader user_reader_test = new UserReader("ksywka4", "12345678");

            Spotify_user tested_user = user_reader_test.searchDB();

            System.out.println(tested_user.getName());            
            assertTrue(tested_user.getName().equals("test4"));
        }
        catch (Exception e)
        {}

        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "DELETE FROM app_user WHERE user_id = " + user_creator_test.getId();
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

    }

    @Test
    void testSurname() {

        UserCreator user_creator_test = new UserCreator();

        try {

            user_creator_test.created_user("test5", "testowy", "ksywka5", "test@gmail.com", "12345678");

            UserReader user_reader_test = new UserReader("ksywka5", "12345678");

            Spotify_user tested_user = user_reader_test.searchDB();
            
            assertTrue(tested_user.getLastName().equals("testowy"));
        }
        catch (Exception e)
        {}

        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "DELETE FROM app_user WHERE user_id = " + user_creator_test.getId();
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

    }

    @Test
    void testLOGIN() {

        UserCreator user_creator_test = new UserCreator();

        try {
            user_creator_test.created_user("test6", "testowy", "ksywka6", "test@gmail.com", "12345678");

            UserReader user_reader_test = new UserReader("ksywka6", "12345678");

            Spotify_user tested_user = user_reader_test.searchDB();
            
            assertTrue(tested_user.getLogin().equals("ksywka6"));
        }
        catch (Exception e)
        {}

        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "DELETE FROM app_user WHERE user_id = " + user_creator_test.getId();
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

    }

    @Test
    void testEMAIL() {

        UserCreator user_creator_test = new UserCreator();

        
        try {
            user_creator_test.created_user("test7", "testowy", "ksywka7", "test@gmail.com", "12345678");

            UserReader user_reader_test = new UserReader("ksywka7", "12345678");

            Spotify_user tested_user = user_reader_test.searchDB();
            
            assertTrue(tested_user.getEmail().equals("test@gmail.com"));
        }
        catch (Exception e)
        {}

        Connection connection = null;
        try {
            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "DELETE FROM app_user WHERE user_id = " + user_creator_test.getId();
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

    }

    @Test
    void testPassword() {

        UserCreator user_creator_test = new UserCreator();
        Connection connection = null;

        try {
            user_creator_test.created_user("test8", "testowy", "ksywka8", "test@gmail.com", "12345678");

            UserReader user_reader_test = new UserReader("ksywka8", "12345678");

            Spotify_user tested_user = user_reader_test.searchDB();

            String pass = tested_user.getPassword();

            DatabaseConnection DC = new DatabaseConnection();
            connection = DC.MakeConnection();
            String insert_query = "DELETE FROM app_user WHERE user_id = " + user_creator_test.getId();
            PreparedStatement prepstat = connection.prepareStatement(insert_query);
            prepstat.executeQuery();
            connection.close();
            
            assertTrue(pass.equals("12345678"));
        }
        catch (Exception e)
        {}
    }

    @Test
    void nonExistantUser() {

        
        UserReader user_reader_test = new UserReader("nie ma mnie", "12345678");

        try {
            
            Exception exception = assertThrows(Exception.class, () ->
            user_reader_test.searchDB());

            assertEquals("user doesn't exists", exception.getMessage());
        }
        catch (Exception e)
        {}
    }

    @Test
    void wrongUserName() {

        UserCreator user_creator_test = new UserCreator();

        try {
            
            Exception exception = assertThrows(Exception.class, () ->
            user_creator_test.created_user("a", "b", "tp03", "c", "d"));

            assertEquals("username already used", exception.getMessage());
        }
        catch (Exception e)
        {}
    }

}
