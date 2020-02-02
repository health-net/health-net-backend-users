package it.healthnet.staff.data.mappers;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import it.healthnet.staff.data.Database;
import it.healthnet.staff.domain.staff.Email;
import it.healthnet.staff.domain.staff.FullName;
import it.healthnet.staff.domain.staff.Staff;
import it.healthnet.staff.domain.staff.StaffId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public final class StaffMapper {

    @Autowired
    private final Database database;

    public StaffMapper(Database database) {
        this.database = database;
    }

    public void insert(Staff staff) {
        RequestBodyEntity tmp = Unirest.post("https://healthnet.eu.auth0.com/api/v2/users").header("Authorization", "Bearer "+database.getJWT()).header("Content-Type", "application/json").body(
                "{\"email\":\""+staff.getEmail().getValue()+"\", \"password\":\""+staff.getPassword()
                +"\", \"name\":\""+staff.getFullName().getValue()+"\", \"connection\":\"Username-Password-Authentication\"}"
        );

        try {
            System.out.println(tmp.asJson().getBody().toString());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public void delete(Staff staff) {
        var request = Unirest.delete("https://healthnet.eu.auth0.com/api/v2/users/"+staff.getId().getValue()).header("Authorization", "Bearer "+database.getJWT());
    }

    public Staff selectById(StaffId id) {
        try {
            HttpResponse<JsonNode> tmp = Unirest.get("https://healthnet.eu.auth0.com/api/v2/users/"+convertUUIDv4ToAuth0ID(id.getValue())).header("Authorization", "Bearer "+database.getJWT()).asJson();
            if(tmp.getStatus()==404) throw new NoSuchElementException();
            else
            return new Staff(new StaffId(convertAuth0IDToUUIDv4(tmp.getBody().getObject().getString("user_id"))), new FullName(tmp.getBody().getObject().getString("name")), new Email(tmp.getBody().getObject().getString("email")));
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Staff> selectByEmail(Email email) {
        try {
            HttpResponse<JsonNode> tmp = Unirest.get("https://healthnet.eu.auth0.com/api/v2/users-by-email").queryString("email", email.getValue()).queryString("search_engine", "v3").header("Authorization", "Bearer "+database.getJWT()).asJson();
            return getStaffListFromResultJSON(tmp.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Staff> selectAll() {
        try {
            var tmp = Unirest.get("https://healthnet.eu.auth0.com/api/v2/users").header("Authorization", "Bearer "+database.getJWT()).asJson().getBody();
            return getStaffListFromResultJSON(tmp);

        } catch (UnirestException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private List<Staff> getStaffListFromResultJSON(JsonNode result) {
        var tmpArray = result.getArray();
        System.out.println("\n\n\n\n"+tmpArray.length());
        var res = new LinkedList<Staff>();
        for(int i=0; i<tmpArray.length(); i++) {
            var tmpJSONobj = tmpArray.getJSONObject(i);
            System.out.println("\n\n\n\n"+tmpJSONobj.toString());

            var id = new StaffId(convertAuth0IDToUUIDv4(tmpJSONobj.getString("user_id")));
            var fullName = new FullName(tmpJSONobj.getString("name"));
            var email = new Email(tmpJSONobj.getString("email"));
            res.add(new Staff(id, fullName, email));
        }
        return res;

    }

    public String convertAuth0IDToUUIDv4 (String auth0ID){

        char[] res = new char[36];
        char[] auth0CharArray = auth0ID.substring(6).toCharArray();

        res[0] = auth0CharArray[0];
        res[1] = auth0CharArray[1];
        res[2] = auth0CharArray[2];
        res[3] = auth0CharArray[3];
        res[4] = auth0CharArray[4];
        res[5] = auth0CharArray[5];
        res[6] = auth0CharArray[6];
        res[7] = auth0CharArray[7];
        res[8] = '-';
        res[9] = auth0CharArray[8];
        res[10] = auth0CharArray[9];
        res[11] = auth0CharArray[10];
        res[12] = auth0CharArray[11];
        res[13] = '-';
        res[14] = '4';
        res[15] = auth0CharArray[12];
        res[16] = auth0CharArray[13];
        res[17] = auth0CharArray[14];
        res[18] = '-';
        res[19] = 'A';
        res[20] = auth0CharArray[15];
        res[21] = auth0CharArray[16];
        res[22] = auth0CharArray[17];
        res[23] = '-';
        res[24] = auth0CharArray[18];
        res[25] = auth0CharArray[19];
        res[26] = 'c';
        res[27] = 'c';
        res[28] = '1';
        res[29] = '0';
        res[30] = '6';
        res[31] = 'b';
        res[32] = 'e';
        res[33] = '1';
        res[34] = '1';
        res[35] = '0';
        String resString = new String(res);
        return resString;

    }

    public String convertUUIDv4ToAuth0ID(String uuid){
        char[] res = new char[20];
        char[] uuidCharArray = uuid.toCharArray();

        res[0] = uuidCharArray[0];
        res[1] = uuidCharArray[1];
        res[2] = uuidCharArray[2];
        res[3] = uuidCharArray[3];
        res[4] = uuidCharArray[4];
        res[5] = uuidCharArray[5];
        res[6] = uuidCharArray[6];
        res[7] = uuidCharArray[7];
        res[8] = uuidCharArray[9];
        res[9] = uuidCharArray[10];
        res[10] = uuidCharArray[11];
        res[11] = uuidCharArray[12];
        res[12] = uuidCharArray[15];
        res[13] = uuidCharArray[16];
        res[14] = uuidCharArray[17];
        res[15] = uuidCharArray[20];
        res[16] = uuidCharArray[21];
        res[17] = uuidCharArray[22];
        res[18] = uuidCharArray[24];
        res[19] = uuidCharArray[25];

        var tmpString = res.toString();
        return "auth0|"+tmpString;
    }
}
