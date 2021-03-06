package com.chainsys.flightbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.flightbooking.exceptions.InsertionFailed;
import com.chainsys.flightbooking.model.Airlines;
import com.chainsys.flightbooking.model.AirlinesFlight;
import com.chainsys.flightbooking.model.BookingAirlines;
import com.chainsys.flightbooking.util.ConnectionUtil;

public class AirlinesFlightDAO {

	/**
	 * Method to add airlines flight
	 * 
	 * @param airlines
	 * @throws Exception 
	 */
	public void addAirlinesFlight(AirlinesFlight airlinesflight) throws Exception {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			String url = "INSERT INTO airlines_flight(id,flight_name,flight_no,adult_seats,child_seats,adult_price,child_price,status,flight_class,created_by,created_time,updated_by,updated_time) VALUES(seq_airlines_id.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedstatement = connection.prepareStatement(url);
			preparedstatement.setInt(1, airlinesflight.getFlightName().getId());
			preparedstatement.setString(2, airlinesflight.getFlightNo());
			preparedstatement.setLong(3, airlinesflight.getAdultSeats());
			preparedstatement.setLong(4, airlinesflight.getChildSeats());
			preparedstatement.setDouble(5, airlinesflight.getAdultPrice());
			preparedstatement.setDouble(6, airlinesflight.getChildPrice());
			preparedstatement.setString(7, airlinesflight.getStatus());
			preparedstatement.setString(8, airlinesflight.getFlightClass());
			preparedstatement.setInt(9, airlinesflight.getCreatedBy());
			preparedstatement.setTimestamp(10, airlinesflight.getCreatedTime());
			preparedstatement.setInt(11, airlinesflight.getUpdatedBy());
			preparedstatement.setTimestamp(12, airlinesflight.getUpdatedTime());
			preparedstatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new InsertionFailed("Unable to Add Airlines Details");
		} finally {
			ConnectionUtil.close(connection, preparedstatement, null);
		}
	}

	/**
	 * Method to get all Airlines Flights where status 'Y'
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<AirlinesFlight> findAll() throws SQLException {
		List<AirlinesFlight> airlinesLists = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,flight_name,flight_no,adult_seats,child_seats,adult_price,child_price,status,flight_class FROM airlines_flight where status='Y' ORDER BY id";
			preparedStatement = connection.prepareStatement(sql);
			resultset = preparedStatement.executeQuery();
			AirlinesDAO airlineDAO = new AirlinesDAO();
			while (resultset.next()) {
				AirlinesFlight airline = new AirlinesFlight();
				airline.setId(resultset.getInt("id"));
				Airlines airlinename = airlineDAO.findById(Integer
						.parseInt(resultset.getString("flight_name")));
				airline.setFlightName(airlinename);
				airline.setFlightNo(resultset.getString("flight_no"));
				airline.setAdultSeats(Integer.parseInt(resultset
						.getString("adult_seats")));
				airline.setChildSeats(Integer.parseInt(resultset
						.getString("child_seats")));
				airline.setAdultPrice(Integer.parseInt(resultset
						.getString("adult_price")));
				airline.setChildPrice(Integer.parseInt(resultset
						.getString("child_price")));
				airline.setStatus(resultset.getString("status"));
				airline.setFlightClass(resultset.getString("flight_class"));
				airlinesLists.add(airline);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultset);
		}
		return airlinesLists;
	}

	/**
	 * Method to get all Airlines Flights
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<AirlinesFlight> findAllAirlines() throws SQLException {
		List<AirlinesFlight> airlinesLists = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,flight_name,flight_no,adult_seats,child_seats,adult_price,child_price,status,flight_class FROM airlines_flight ORDER BY id";
			preparedStatement = connection.prepareStatement(sql);
			resultset = preparedStatement.executeQuery();
			airlinesLists = new ArrayList<>();
			AirlinesDAO airlineDAO = new AirlinesDAO();
			while (resultset.next()) {
				AirlinesFlight airline = new AirlinesFlight();
				airline.setId(resultset.getInt("id"));
				Airlines airlinename = airlineDAO.findById(Integer
						.parseInt(resultset.getString("flight_name")));
				airline.setFlightName(airlinename);
				airline.setFlightNo(resultset.getString("flight_no"));
				airline.setAdultSeats(Integer.parseInt(resultset
						.getString("adult_seats")));
				airline.setChildSeats(Integer.parseInt(resultset
						.getString("child_seats")));
				airline.setAdultPrice(Integer.parseInt(resultset
						.getString("adult_price")));
				airline.setChildPrice(Integer.parseInt(resultset
						.getString("child_price")));
				airline.setStatus(resultset.getString("status"));
				airline.setFlightClass(resultset.getString("flight_class"));
				airlinesLists.add(airline);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultset);
		}
		return airlinesLists;
	}

	/**
	 * Method to find airlines flight by airlinesid
	 * 
	 * @param airlinesid
	 * @return
	 * @throws SQLException
	 */
	public AirlinesFlight findById(int airlinesid) throws SQLException {
		AirlinesFlight airline = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,flight_name,flight_no,adult_seats,child_seats,adult_price,child_price,status,flight_class FROM airlines_flight where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, airlinesid);
			ResultSet resultset = preparedStatement.executeQuery();
			AirlinesDAO airlineDAO = new AirlinesDAO();
			if (resultset.next()) {
				airline = new AirlinesFlight();
				airline.setId(resultset.getInt("id"));
				Airlines airlinename = airlineDAO.findById(Integer
						.parseInt(resultset.getString("flight_name")));
				airline.setFlightName(airlinename);
				airline.setFlightNo(resultset.getString("flight_no"));
				airline.setAdultSeats(Integer.parseInt(resultset
						.getString("adult_seats")));
				airline.setChildSeats(Integer.parseInt(resultset
						.getString("child_seats")));
				airline.setAdultPrice(Integer.parseInt(resultset
						.getString("adult_price")));
				airline.setChildPrice(Integer.parseInt(resultset
						.getString("child_price")));
				airline.setStatus(resultset.getString("status"));
				airline.setFlightClass(resultset.getString("flight_class"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return airline;
	}

	/**
	 * Method to find airlines flight by id and class
	 * 
	 * @param airlinesid
	 * @param flightclass
	 * @return
	 * @throws SQLException
	 */
	public AirlinesFlight findByIdClass(int airlinesid, String flightclass)
			throws SQLException {
		AirlinesFlight airline = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,flight_name,flight_no,adult_seats,child_seats,adult_price,child_price,status,flight_class FROM airlines_flight where flight_name=? and flight_class=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, airlinesid);
			preparedStatement.setString(2, flightclass);
			resultset = preparedStatement.executeQuery();
			AirlinesDAO airlineDAO = new AirlinesDAO();
			if (resultset.next()) {
				airline = new AirlinesFlight();
				airline.setId(resultset.getInt("id"));
				Airlines airlinename = airlineDAO.findById(Integer
						.parseInt(resultset.getString("flight_name")));
				airline.setFlightName(airlinename);
				airline.setFlightNo(resultset.getString("flight_no"));
				airline.setAdultSeats(Integer.parseInt(resultset
						.getString("adult_seats")));
				airline.setChildSeats(Integer.parseInt(resultset
						.getString("child_seats")));
				airline.setAdultPrice(Integer.parseInt(resultset
						.getString("adult_price")));
				airline.setChildPrice(Integer.parseInt(resultset
						.getString("child_price")));
				airline.setStatus(resultset.getString("status"));
				airline.setFlightClass(resultset.getString("flight_class"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return airline;
	}

	/**
	 * Update Airlines Booked Seats in Airlines Flight table
	 * 
	 * @param bookairlines
	 * @throws SQLException
	 */
	public void updateAirlinesSeats(BookingAirlines bookairlines)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			String url = "UPDATE airlines_flight SET adult_seats=?,child_seats=?,status=? where id=?";
			preparedStatement = connection.prepareStatement(url);
			AirlinesFlight airline = findById(bookairlines.getAirlinesId()
					.getId());
			if (airline != null) {
				int adultseats = airline.getAdultSeats()
						- bookairlines.getAdultSeats();
				int childseats = airline.getChildSeats()
						- bookairlines.getChildSeats();
				airline.setAdultSeats(adultseats);
				airline.setChildSeats(childseats);
			}
			preparedStatement.setInt(1, airline.getAdultSeats());
			preparedStatement.setInt(2, airline.getChildSeats());
			if (airline.getAdultSeats() != 0 && airline.getChildSeats() != 0) {
				airline.setStatus("Y");
			} else {
				airline.setStatus("N");
			}
			preparedStatement.setString(3, airline.getStatus());
			preparedStatement.setInt(4, airline.getId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update cancelled tickets in airlines flight
	 * 
	 * @param airlinesflight
	 * @throws SQLException
	 */
	public void updateCancelledSeats(AirlinesFlight airlinesflight)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			String url = "UPDATE airlines_flight SET adult_seats=?,child_seats=?,status=? where id=?";
			preparedStatement = connection.prepareStatement(url);
			AirlinesFlight airline = findById(airlinesflight.getId());
			if (airline != null) {
				int adultseats = airline.getAdultSeats()
						+ airlinesflight.getAdultSeats();
				int childseats = airline.getChildSeats()
						+ airlinesflight.getChildSeats();
				airline.setAdultSeats(adultseats);
				airline.setChildSeats(childseats);
				if (airline.getAdultSeats() > 0) {
					airline.setStatus("Y");
				}
			}
			preparedStatement.setInt(1, airline.getAdultSeats());
			preparedStatement.setInt(2, airline.getChildSeats());
			preparedStatement.setString(3, airline.getStatus());
			preparedStatement.setInt(4, airline.getId());
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
	}

}
