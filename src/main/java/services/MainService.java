package services;

import services.TelephoneEntry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainService {

    private final DBService dbService;

    public MainService() {
        this.dbService = new DBService();
    }

    public void addName(String name) {
        String sql = "INSERT INTO public.telephone (name) VALUES ('" + name + "')";
        dbService.insert(sql);
    }

    public List<TelephoneEntry> getNames() throws SQLException {
        List<TelephoneEntry> result = new ArrayList<>();
        String sql = "SELECT * FROM public.telephone";
        ResultSet rs = dbService.select(sql);
        while (rs.next()) {
            result.add(new TelephoneEntry(rs.getString("name")));
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
