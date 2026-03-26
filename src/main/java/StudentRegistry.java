import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentRegistry {
    public HashMap<Integer,String> studentMap ;

    public StudentRegistry(){
        studentMap = new HashMap<Integer,String>();
    }
    public boolean addStudent(Integer id , String name){
        if( name == null ||name.isEmpty()){
            throw new IllegalArgumentException("Name should not be empty");
        }
        if(id == null || studentMap.containsKey(id)){
            throw new IllegalArgumentException("Id is already taken or id is null");
        }
        studentMap.put(id,name);
        return true;

    }
    public int getStudentCount(){
        return studentMap.size();
    }
    public String getStudentById(Integer id ){
        if(id == null){
            throw new IllegalArgumentException("Id should not be null");
        }
        if(!studentMap.containsKey(id)){
            return "There is no such Id inside the studentMap!";
        }
        return studentMap.get(id);
    }
    public boolean removeStudent(Integer id ){
        if(id == null) throw new IllegalArgumentException("Id cannot be null");
        if(!studentMap.containsKey(id)) throw new IllegalArgumentException("Id is not valid , not present inside the map");
        studentMap.remove(id);
        return true;
    }
    public boolean isStudentPresent(Integer id  ){
       if(id == null || !studentMap.containsKey(id)){
           throw new IllegalArgumentException("Id is either null or is invalid!");
       }

        return true;
    }
    public boolean editStudent( Integer id , String name){
        if(id == null || !studentMap.containsKey(id)) throw new IllegalArgumentException("Id is either null or not valid");
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Name is either empty or null");
        for(Integer identifier :studentMap.keySet()){
            if(identifier.equals(id)){
                studentMap.put(id,name);
                return true;
            }
        }
        return false;
    }
    public List<String> getAllStudents(){
        List<String> studentsList = new ArrayList<>(studentMap.values());
        return studentsList;
    }






}
