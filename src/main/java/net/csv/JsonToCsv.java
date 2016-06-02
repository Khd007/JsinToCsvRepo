package net.csv;


/**
 * @author khalid Hamid
 * @since 25/05/2016
 * @version 1.0
 */
import java.io.*;
import java.util.Scanner;
import com.csvreader.*;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;


public class JsonToCsv {
    public static void main(String args[]) throws IOException {
        /**
         * This is a main metho dtakes no argument.
         * Asks user to input entries to a JSON file
         * and then converts that into a CSV File
         */
        Scanner obj1 = new Scanner(System.in);
        JSONArray jsonArray = new JSONArray();
        Scanner obj2 = new Scanner(System.in);
        try {
            System.out.println("enter no of entries");
            int num = obj1.nextInt();
            System.out.println("Enter Json file name");
            String jsonFile=obj2.nextLine();
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(jsonFile));

            for (int i = 0; i < num; i++) {
                JSONObject Jobj1 = new JSONObject();
                JSONArray jsonArray1 = new JSONArray();

                System.out.println("Enter first name");
                Jobj1.put("First name", obj2.nextLine());

                System.out.println("Enter Last name");
                Jobj1.put("Last name", obj2.nextLine());

                System.out.println("Enter DOB");
                Jobj1.put("DOB", obj2.nextLine());

                System.out.println("Enter Age");
                Jobj1.put("Age", obj1.nextInt());

                jsonArray.put(Jobj1);
            }
            int count = jsonArray.length();
            System.out.println(count);
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                bw1.write(jsonObject.toString());
                bw1.newLine();
                System.out.println("Student-Details " + (i + 1) + ": " + jsonObject.toString());
            }
            bw1.close();

            BufferedReader br = new BufferedReader(new FileReader(jsonFile));
            System.out.println("Enter csv File name");
            String csvFile= obj2.nextLine();
            CsvWriter csvOutput = new CsvWriter(new FileWriter(csvFile, true), ',');
            String line = "";
            boolean alreadyExists =csvFile.isEmpty();
            if(!alreadyExists){
                csvOutput.write("FirstName");
                csvOutput.write("LastName");
                csvOutput.write("DOB");
                csvOutput.write("Age");
                csvOutput.endRecord();
            }
            while ((line = br.readLine()) != null) {
                JSONObject jsonObject = new JSONObject(line);
                String dob = jsonObject.getString("DOB");
                String firstName = jsonObject.getString("First name");
                String lastName = jsonObject.getString("Last name");
                int age = jsonObject.getInt("Age");

                csvOutput.write(firstName);
                csvOutput.write(lastName);
                csvOutput.write(dob);
                csvOutput.write(String.valueOf(age));
                csvOutput.endRecord();
                }

            csvOutput.close();
            br.close();
        }

        catch(JSONException e){
            throw new RuntimeException(e);
        }

    }
}
