package com.chainsys.flightbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chainsys.flightbooking.model.Airlines;
import com.chainsys.flightbooking.util.ConnectionUtil;

public class AirlinesDAO {

	/**
	 * Method to add new airlines
	 * 
	 * @param airlines
	 * @throws Exception 
	 */
	public boolean addAirline(Airlines airlines) throws Exception {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		boolean isSucess=false;
		try {
			connection = ConnectionUtil.getConnection();
			String url = "INSERT INTO airlines(id,airlines_name,created_by,created_time,updated_by,updated_time) VALUES(seq_airlinesid.NEXTVAL,?,?,?,?,?)";
			preparedstatement = connection.prepareStatement(url);
			preparedstatement.setString(1, airlines.getAirlinesName());
			preparedstatement.setInt(2, airlines.getCreatedBy());
			preparedstatement.setTimestamp(3, airlines.getCreatedTime());
			preparedstatement.setInt(4, airlines.getUpdatedBy());
			preparedstatement.setTimestamp(5, airlines.getUpdatedTime());
			int row =preparedstatement.executeUpdate();
			if(row>0){
				isSucess=true;
			}else{
				isSucess=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Unable to insert");
		} finally {
			ConnectionUtil.close(connection, preparedstatement, null);
		}
		return isSucess;
	}

	/**
	 * Method to get all airlines
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Airlines> findAll() throws SQLException {
		List<Airlines> airlinesLists = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,airlines_name FROM airlines ORDER BY id";
			preparedStatement = connection.prepareStatement(sql);
			resultset = preparedStatement.executeQuery();
			while (resultset.next()) {
				Airlines airline = new Airlines();
				airline.setId(resultset.getInt("id"));
				airline.setAirlinesName(resultset.getString("airlines_name"));
				airlinesLists.add(airline);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			ConnectionUtil.close(connection, preparedStatement, null);
		}
		return airlinesLists;
	}

	/**
	 * Method to find airlines by airlinesid
	 * 
	 * @param airlinesid
	 * @return
	 * @throws SQLException
	 */
	public Airlines findById(int airlinesid) throws SQLException {
		Airlines airline = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "SELECT id,airlines_name FROM airlines where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, airlinesid);
			resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				airline = new Airlines();
				airline.setId(resultset.getInt("id"));
				airline.setAirlinesName(resultset.getString("airlines_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultset);
		}
		return airline;
	}
}
