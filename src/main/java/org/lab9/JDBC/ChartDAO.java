package org.lab9.JDBC;

import org.lab9.entity.Chart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChartDAO {
    public static void create(String albumName, int albumId, int position){
        Database database = Database.getInstance();
        String queryString = "INSERT INTO chart(album_name,album_id,position) VALUES('"+albumName+"',"+albumId+","+position+");";
        database.makeUpdate(queryString);
    }
    public static List<Chart> getChart(int limitNumber){
        List<Chart> chartList= new ArrayList<>();
        Database database = Database.getInstance();
        String queryString = "SELECT * FROM chart order by position limit " +limitNumber;
        ResultSet rs =database.makeQuery(queryString);
        try {
            while (rs.next()){
                Chart chartRow = new Chart();
                chartRow.setId(rs.getInt(1));
                chartRow.setAlbumName(rs.getString(2));
                chartRow.setAlbumId(rs.getInt(3));
                chartRow.setPosition(rs.getInt(4));
                chartList.add(chartRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chartList;
    }
}
