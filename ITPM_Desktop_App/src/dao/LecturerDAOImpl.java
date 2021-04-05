package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbConnection.DBConnection;
import models.Active_Days;
import models.Lecturer;

public class LecturerDAOImpl {
	DBConnection db = new DBConnection();

	public String insertLecturers(Lecturer lecturer) throws Exception{

		String result = "";
			Connection connection = db.connect();

			String insertLec = "insert into lecturer values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(insertLec);

			ps.setInt(1, 0);
			ps.setString(2, lecturer.getLecName());
			ps.setString(3, lecturer.getFaculty());
			ps.setString(4, lecturer.getDepart());
			ps.setInt(5, lecturer.getEmployeeId());
			ps.setInt(6, lecturer.getLevel());
			ps.setString(7, lecturer.getRank());
			ps.setString(8, lecturer.getCampus());
			ps.setString(9, lecturer.getBuilding());

			ps.execute();
			connection.close();

		return result;
	}

	public ArrayList<Lecturer> listLecturer() {
		ArrayList<Lecturer> lec_group = new ArrayList<>();

		try {
			Connection connection = db.connect();
			String listGroup = "select * from lecturer";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listGroup);

			while (rs.next()) {
				Lecturer lec = new Lecturer();

				lec.setEid(rs.getInt(1));
				lec.setLecName(rs.getString(2));
				lec.setFaculty(rs.getString(3));
				lec.setDepart(rs.getString(4));
				lec.setEmployeeId(rs.getInt(5));
				lec.setLevel(rs.getInt(6));
				lec.setRank(rs.getString(7));
				lec.setCampus(rs.getString(8));
				lec.setBuilding(rs.getString(9));

				lec_group.add(lec);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lec_group;
	}

	public Lecturer getLecturerById(int id) {
		Lecturer lecturer = new Lecturer();
		try {
			Connection connection = db.connect();
			String listGroup = "select * from lecturer where lec_id='" + id + "'";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listGroup);

			while (rs.next()) {
				lecturer.setEid(rs.getInt(1));
				lecturer.setLecName(rs.getString(2));
				lecturer.setFaculty(rs.getString(3));
				lecturer.setDepart(rs.getString(4));
				lecturer.setEmployeeId(rs.getInt(5));
				lecturer.setLevel(rs.getInt(6));
				lecturer.setRank(rs.getString(7));
				lecturer.setCampus(rs.getString(8));
				lecturer.setBuilding(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lecturer;
	}

	public void updateLecturers(Lecturer lecturer) {

		try {
			Connection con = db.connect();

			String updateQuery = "Update lecturer set name = '" + lecturer.getLecName() + "', faculty = '"
					+ lecturer.getFaculty() + "', department = '" + lecturer.getDepart() + "',emp_id = '"
					+ lecturer.getEmployeeId() + "', level ='" + lecturer.getLevel() + "'," + " rank = '"
					+ lecturer.getRank() + "', campus = '" + lecturer.getCampus() + "', building='"
					+ lecturer.getBuilding() + "' where lec_id = '" + lecturer.getEid() + "' ";

			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/********** empid eka wenas krnna in update and del ****/
	public void deleteLec(int id) {
		try {
			Connection connection = db.connect();
			String delQuery = "delete from lecturer where lec_id = '" + id + "'";
			PreparedStatement ps = connection.prepareStatement(delQuery);
			ps.execute();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String insertActiveDays(int empid, int mon, int tue, int wed, int thur, int fri, int sat, int sun) {

		String result = "";

		try {

			Connection connection = db.connect();

			String insertDays = "insert into lecturer_activedays values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(insertDays);

			ps.setInt(1, 0);
			ps.setInt(2, empid);
			ps.setInt(3, mon);
			ps.setInt(4, tue);
			ps.setInt(5, wed);
			ps.setInt(6, thur);
			ps.setInt(7, fri);
			ps.setInt(8, sat);
			ps.setInt(9, sun);

			ps.execute();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Active_Days> listDays() {
		ArrayList<Active_Days> activeDay_group = new ArrayList<>();

		try {
			Connection connection = db.connect();
			String listDayGroup = "select * from lecturer_activedays";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listDayGroup);

			while (rs.next()) {
				Active_Days ac_days = new Active_Days();

				ac_days.setId(rs.getInt(1));
				ac_days.setEmpID(rs.getInt(2));
				ac_days.setMon(rs.getInt(3));
				ac_days.setTue(rs.getInt(4));
				ac_days.setWed(rs.getInt(5));
				ac_days.setThur(rs.getInt(6));
				ac_days.setFri(rs.getInt(7));
				ac_days.setSat(rs.getInt(8));
				ac_days.setSun(rs.getInt(9));

				// System.out.println(ac_days.getSat());
				activeDay_group.add(ac_days);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return activeDay_group;
	}

	public Active_Days getActiveDaysByEmployeeID(int id) {
		Active_Days ac_days = new Active_Days();

		try {
			Connection connection = db.connect();
			String listDayGroup = "select * from lecturer_activedays where emp_id='" + id + "'";

			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(listDayGroup);

			while (rs.next()) {

				ac_days.setId(rs.getInt(1));
				ac_days.setEmpID(rs.getInt(2));
				ac_days.setMon(rs.getInt(3));
				ac_days.setTue(rs.getInt(4));
				ac_days.setWed(rs.getInt(5));
				ac_days.setThur(rs.getInt(6));
				ac_days.setFri(rs.getInt(7));
				ac_days.setSat(rs.getInt(8));
				ac_days.setSun(rs.getInt(9));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ac_days;
	}

	public void updateActiveDays(int id, int mon, int tue, int wed, int thur, int fri, int sat, int sun) {

		try {
			Connection con = db.connect();

			String updateQuery = "Update lecturer_activedays set monday = '" + mon + "', tuesday='" + tue
					+ "', wednesday = '" + wed + "', thursday='" + thur + "', friday = '" + fri + "', saturday='" + sat
					+ "', sunday = '" + sun + "'  where emp_id = '" + id + "' ";

			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.execute();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteActiveDays(int id) {
		try {
			Connection connection = db.connect();
			String delDayQuery = "delete from lecturer_activedays where emp_id = '" + id + "'";
			PreparedStatement ps = connection.prepareStatement(delDayQuery);
			ps.execute();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
