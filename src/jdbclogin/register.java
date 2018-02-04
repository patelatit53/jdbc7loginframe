import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class register  {
     JFrame f;
   JLabel l1,l2;
   JTextField f1,f2;
   JButton b1;
    Connection con;
    Statement st;
    ResultSet rs;

    /**
     * Creates new form Register
     */
    public static void main(String args[]) {

       register r=new register();
        r.frame();

    }

    public void connect(String user_name,String pass) {
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/chat","root","root");
            System.out.println("Connection creted");
            st = (Statement) con.createStatement();
            String str = ("select * from U1 WHERE NAME = '" +user_name+"' and PASSWORD = '" + pass+"'");
            System.out.println("query is" + str);
            ResultSet rs = st.executeQuery(str);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            for(int count = 1 ; count<=rsmd.getColumnCount();count++)
            {
                System.out.print(rsmd.getColumnName(count)+"\t");
            }
            System.out.println("");
            while(rs.next())
            {
                System.out.print(rs.getInt("ID")+"\t");
                System.out.print(rs.getString("NAME")+"\t");
                System.out.print(rs.getString("PASSWORD")+"\t");
                System.out.println("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public void frame() 
    {
     f=new JFrame ("Login");
     f1=new JTextField(30);
     f2=new JTextField(30);
     l1=new JLabel("username");
     l2=new JLabel("password");
     b1=new JButton("submit");
     f.setLayout(new GridLayout(3,2));
     f.add(l1);
     f.add(f1);
     f.add(l2);
     f.add(f2);
     f.add(b1);
     f.setVisible(true);
     f.pack();
     b1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
         String userName=f1.getText();
         String passWord=f2.getText();
            connect(userName,passWord);
        }
    });
    }
    }


