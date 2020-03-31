package com.company;

/*

2002 Читаем и пишем в файл JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush.
В файле your_file_name.tmp может быть несколько объектов JavaRush.
Метод main реализован только для вас и не участвует в тестировании.

Требования:
1. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае, если список users пустой.
2. Логика чтения/записи реализованная в методах save/load должна работать корректно в случае, если список users не пустой.
3. Класс Solution.JavaRush не должен поддерживать интерфейс Serializable.
4. Класс Solution.JavaRush должен быть публичным.
5. Класс Solution.JavaRush не должен поддерживать интерфейс Externalizable.



*/

import com.sun.corba.se.pept.transport.ReaderThread;

import java.io.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

//
//
//public class Solution {
//
//    public static void main(String[] args) {
//        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
//        //&#x432;&#x44b; &#x43c;&#x43e;&#x436;&#x435;&#x442;&#x435; &#x43d;&#x430;&#x439;&#x442;&#x438; your_file_name.tmp &#x432; &#x43f;&#x430;&#x43f;&#x43a;&#x435; TMP &#x438;&#x43b;&#x438; &#x438;&#x441;&#x43f;&#x440;&#x430;&#x432;&#x44c;&#x442;&#x435; outputStream/inputStream &#x432; &#x441;&#x43e;&#x43e;&#x442;&#x432;&#x435;&#x442;&#x441;&#x442;&#x432;&#x438;&#x438; &#x441; &#x43f;&#x443;&#x442;&#x435;&#x43c; &#x43a; &#x432;&#x430;&#x448;&#x435;&#x43c;&#x443; &#x440;&#x435;&#x430;&#x43b;&#x44c;&#x43d;&#x43e;&#x43c;&#x443; &#x444;&#x430;&#x439;&#x43b;&#x443;
//        try {
//            //File your_file_name = File.createTempFile("your_file_name", null);
//            File your_file_name = new File("d:\\5.txt");
//            OutputStream outputStream = new FileOutputStream(your_file_name);
//            InputStream inputStream = new FileInputStream(your_file_name);
//
//            JavaRush javaRush = new JavaRush();
//            //initialize users field for the javaRush object here - &#x438;&#x43d;&#x438;&#x446;&#x438;&#x430;&#x43b;&#x438;&#x437;&#x438;&#x440;&#x443;&#x439;&#x442;&#x435; &#x43f;&#x43e;&#x43b;&#x435; users &#x434;&#x43b;&#x44f; &#x43e;&#x431;&#x44a;&#x435;&#x43a;&#x442;&#x430; javaRush &#x442;&#x443;&#x442;
//            User usr = new User();
//            usr.setFirstName("Ivan");
//            usr.setLastName("Ivanov");
//            usr.setBirthDate(new GregorianCalendar(1980, 11, 25).getTime());
//            usr.setCountry(User.Country.RUSSIA);
//            usr.setMale(true);
//
//            javaRush.users.add(usr);
//
//            javaRush.save(outputStream);
//            outputStream.flush();
//
//            JavaRush loadedObject = new JavaRush();
//            loadedObject.load(inputStream);
//            //check here that javaRush object equals to loadedObject object - &#x43f;&#x440;&#x43e;&#x432;&#x435;&#x440;&#x44c;&#x442;&#x435; &#x442;&#x443;&#x442;, &#x447;&#x442;&#x43e; javaRush &#x438; loadedObject &#x440;&#x430;&#x432;&#x43d;&#x44b;
//
//            outputStream.close();
//            inputStream.close();
//
//        } catch (IOException e) {
//            //e.printStackTrace();
//            System.out.println("Oops, something wrong with my file");
//        } catch (Exception e) {
//            //e.printStackTrace();
//            System.out.println("Oops, something wrong with save/load method");
//        }
//    }
//
//    public static class JavaRush {
//        public List<User> users = new ArrayList<>();
//
//        public void save(OutputStream outputStream) throws Exception {
//            //implement this method - &#x440;&#x435;&#x430;&#x43b;&#x438;&#x437;&#x443;&#x439;&#x442;&#x435; &#x44d;&#x442;&#x43e;&#x442; &#x43c;&#x435;&#x442;&#x43e;&#x434;
//            DataOutputStream outToFile = new DataOutputStream(outputStream);
//
//            outToFile.writeInt(users.size());
//            for (User user : users) {
//                /*String firstName = usr.getFirstName() != null ? usr.getFirstName() : "null";
//                String lastName = usr.getLastName() != null ? usr.getLastName() : "null";
//                String birthDate = usr.getBirthDate() != null ? dateFormat.format(usr.getBirthDate()) : "null";
//                String country = usr.getCountry() != null ? usr.getCountry().toString() : "null";
//                String sex = usr.isMale() == true ? "male" : "female";
//                outToFile.write(firstName + "\r\n");
//                outToFile.write(lastName + "\r\n");
//                outToFile.write(birthDate + "\r\n");
//                outToFile.write(country + "\r\n");
//                outToFile.write(usr.getCountry().toString() + "\r\n");
//                outToFile.write(sex + "\r\n");
//
//                String firstName = (user.getFirstName() == null) ? "null" : user.getFirstName();
//                        outToFile.writeUTF(firstName);
//                        String lastName = (user.getLastName() == null) ? "null" : user.getLastName();
//                        outToFile.writeUTF(lastName);
//                        outToFile.writeLong(user.getBirthDate().getTime());
//                        outToFile.writeBoolean(user.isMale());
//                        outToFile.writeUTF(user.getCountry().name());
//                        }
//                        outToFile.flush();
//                        }
//
//
//public void load(InputStream inputStream) throws Exception {
//        //implement this method - &#x440;&#x435;&#x430;&#x43b;&#x438;&#x437;&#x443;&#x439;&#x442;&#x435; &#x44d;&#x442;&#x43e;&#x442; &#x43c;&#x435;&#x442;&#x43e;&#x434;
////        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
//        DataInputStream fromFile = new DataInputStream(inputStream);
////        users.clear();
//
//        int usersCount = fromFile.readInt();
//        for (int i = 0; i < usersCount; i++) {
//            /*
//            String firstName = fromFile.readLine();
//            String lastName = fromFile.readLine();
//            String birthDate = fromFile.readLine();
//            String country = fromFile.readLine();
//            String sex = fromFile.readLine();
//            User usr = new User();
//            usr.setFirstName(firstName);
//            usr.setLastName(lastName);
//            usr.setBirthDate(birthDate.equals("null") ? null :  dateFormat.parse(birthDate));
//            usr.setCountry(country.equals("null") ? null: User.Country.valueOf(country));
//            usr.setMale(sex.equals("male") ? true : false);
//
////        User user = new User();
//
////        String firstName = fromFile.readUTF();
////        if (firstName.equals("null")) firstName = null;
////        user.setFirstName(firstName);
////        String lastName = fromFile.readUTF();
////        if (lastName.equals("null")) lastName = null;
////        user.setLastName(lastName);
//        user.setBirthDate(new Date(fromFile.readLong()));
//        user.setMale(fromFile.readBoolean());
//        user.setCountry(User.Country.valueOf(fromFile.readUTF()));
//
//        users.add(user);
//        }
//        }
//
//// @Override
//public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        JavaRush javaRush = (JavaRush) o;
//
//        return users != null ? users.equals(javaRush.users) : javaRush.users == null;
//
//        }
//
//@Override
//public int hashCode() {
//        return users != null ? users.hashCode() : 0;
//        }
//        }
//        }
//
//        package com.javarush.task.task20.task2002;
//
//        import java.util.Date;
//
//public class User {
//    private String firstName;
//    private String lastName;
//    private Date birthDate;
//    private boolean isMale;
//    private Country country;
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Date getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(Date birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public boolean isMale() {
//        return isMale;
//    }
//
//    public void setMale(boolean male) {
//        isMale = male;
//    }
//
//    public Country getCountry() {
//        return country;
//    }
//
//    public void setCountry(Country country) {
//        this.country = country;
//    }
//
//    public static enum Country {
//        UKRAINE("Ukraine"),
//        RUSSIA("Russia"),
//        OTHER("Other");
//
//        private String name;
//
//        private Country(String name) {
//            this.name = name;
//        }
//
//        public String getDisplayedName() {
//            return this.name;
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User user = (User) o;
//
//        if (isMale != user.isMale) return false;
//        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
//        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
//        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
//        return country == user.country;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = firstName != null ? firstName.hashCode() : 0;
//        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
//        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
//        result = 31 * result + (isMale ? 1 : 0);
//        result = 31 * result + (country != null ? country.hashCode() : 0);
//        return result;
//    }
//}
