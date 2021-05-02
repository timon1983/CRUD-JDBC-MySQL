package Repository.jdbc;

import Model.Region;
import Repository.RegionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl extends Util implements RegionRepository {

    @Override
    public Region getById(Long id) {
        Region region = new Region(0, null);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
               String sqlSelectRegion = "SELECT * FROM regions WHERE region_id = " + id;
               ResultSet resultSetRegion = statement.executeQuery(sqlSelectRegion);
               resultSetRegion.next();
               region.setId(resultSetRegion.getLong("region_id"));
               region.setName(resultSetRegion.getString("region_name"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return region;
    }

    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
               String sqlSelectRegion = "SELECT * FROM regions";
               ResultSet resultSetRegion = statement.executeQuery(sqlSelectRegion);
               if(resultSetRegion == null){
                   return null;
               }else {
                while (resultSetRegion.next()) {
                    Region region = new Region(0, null);
                    region.setId(resultSetRegion.getLong("region_id"));
                    region.setName(resultSetRegion.getString("region_name"));
                    regions.add(region);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return regions;
    }

    @Override
    public Region save(Region region) {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){
              String sqlInsertRegion = "INSERT INTO regions(region_name) VALUES('" + region.getName() + "')";
              statement.executeUpdate(sqlInsertRegion);
              ResultSet resultSelectRegion = statement.executeQuery("SELECT * FROM regions WHERE region_id = " +
                      "(SELECT MAX(region_id) FROM regions)");
              resultSelectRegion.next();
              long newRegionID = resultSelectRegion.getLong("region_id");
              region.setId(newRegionID);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return region;
    }

    @Override
    public Region update(Region region) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
                String sqlUpdateRegion = "UPDATE regions SET region_name = '" + region.getName()  + "'" +
                        " WHERE region_id = " + region.getId();
                statement.executeUpdate(sqlUpdateRegion);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
        return region;
    }

    @Override
    public void deleteById(Long id) {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){
               String sqlDeleteRegion = "DELETE FROM regions WHERE region_id = " + id;
               statement.executeUpdate(sqlDeleteRegion);
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement ERROR");
        }
    }
}
