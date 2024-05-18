package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainService {

    private final DBService dbService;

    public MainService() {
        this.dbService = new DBService();
    }

    public void addName(String name) {
        String sql = "INSERT INTO public.telephone (name) VALUES ('" + name + "')";
        dbService.insert(sql);
    }

    public List<Map<String, Object>> getNames() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT * FROM public.telephone";
        ResultSet rs = dbService.select(sql);
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("name", rs.getString("name"));
            result.add(row);
        }
        return result;
    }

    public void deleteName(String name) {
        String sql = "DELETE FROM public.telephone WHERE name = '" + name + "'";
        dbService.insert(sql);
    }

    public void updateName(String oldName, String newName) {
        String sql = "UPDATE public.telephone SET name = '" + newName + "' WHERE name = '" + oldName + "'";
        dbService.insert(sql);
    }
}