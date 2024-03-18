package org.example;

import java.sql.*;


public class Students {
//    public static void ok(ResultSet r) throws SQLException {
//        System.out.println(r.getString("first_name"));
//    }


    String url = "jdbc:postgresql://localhost:5432/A3Q1";
    String user = "postgres";
    String password = "postgres";

    //connect to the database and print all the record from students table
    public void getAllStudents() {
        try{
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, user, password);

            Statement s = c.createStatement();
            s.execute("SELECT * FROM students ORDER BY student_id ASC");
            ResultSet r = s.getResultSet();
            System.out.printf("%10s  %15s  %15s  %15s   %25s  %n", "student_id", "first_name", "last_name", "email", "enrollment_date");
            while(r.next()){
                System.out.printf("%8s  %15s  %15s  %30s  %-10s  %n", r.getString("student_id"), r.getString("first_name"), r.getString("last_name"), r.getString("email"), r.getString("enrollment_date"));
            }
            c.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    //connect to the database and add new student with first name, last name, email, and enrollment date
    public void addStudent(String first_name, String last_name, String email, String enrollment_date){
        String SQL = "INSERT INTO students(first_name, last_name, email, enrollment_date) VALUES(?,?,?,?)";
        try {
            Date date=Date.valueOf(enrollment_date);
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, user, password);

            PreparedStatement s = c.prepareStatement(SQL);

            s.setString(1, first_name);
            s.setString(2, last_name);
            s.setString(3, email);
            s.setDate(4, date);
            s.executeUpdate();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    //connect to the database and update the student's email with specified student_id
    public void updateStudentEmail(int student_id, String new_email) {
        String SQL = "UPDATE students SET email=? WHERE student_id=?";
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, user, password);

            PreparedStatement s = c.prepareStatement(SQL);

            s.setString(1, new_email);
            s.setInt(2, student_id);
            s.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //connect to the database run the sql query to delete student with student_id from arg
    public void deleteStudent(int student_id){
        String SQL = "DELETE FROM students WHERE student_id=?";
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager.getConnection(url, user, password);

            PreparedStatement s = c.prepareStatement(SQL);

            s.setInt(1, student_id);
            s.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Students students = new Students();

        //students.getAllStudents();

//        System.out.println("Before");
//        students.getAllStudents();
//        System.out.println();
//        System.out.println("After: ");
//        students.addStudent("Tony", "Stark", "tonystark@starkindustries.com", "2024-03-18");
//        students.getAllStudents();

//        System.out.println("Before");
//        students.getAllStudents();
//        System.out.println();
//        System.out.println("After: ");
//        students.updateStudentEmail(1, "newemail@gmail.com");
//        students.getAllStudents();


        System.out.println("Before:");
        students.getAllStudents();
        System.out.println();
        System.out.println("After: ");
        students.deleteStudent(2);
        students.getAllStudents();
    }
}