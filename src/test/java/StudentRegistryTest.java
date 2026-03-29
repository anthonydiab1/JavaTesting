
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;


class StudentRegistryTest{
    StudentRegistry sRegistry;
    @BeforeEach
    void initializeStudentRegistry(){

        sRegistry= new StudentRegistry();
    }

    @ParameterizedTest
    @DisplayName("Newly added student to student map should appear inside the map")
    @ValueSource(strings = {"Anthony Diab","Elie Helou","Jean Antoun"})
    void addValidStudentTest(String name){
        sRegistry.addStudent(1,name);
        assertEquals(1,sRegistry.getStudentCount());
        assertTrue(sRegistry.isStudentPresent(1));
        assertEquals(name,sRegistry.getStudentById(1));
    }
    @Test
    @DisplayName("Added Student with an Already existant id should throw IllegalArgumentException ")
    void addStudentExistingIdTest(){
        sRegistry.addStudent(1,"Anthony Diab");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->sRegistry.addStudent(1,"Elie Helou"));
        assertEquals("Id is already taken or id is null",exception.getMessage());
    }
    @Test
    @DisplayName("Newly added student with an empty name should not be added to the map")
    void addStudentNullNameTest(){

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->sRegistry.addStudent(1,""));
        assertEquals("Name should not be empty",exception.getMessage());
    }
    @Test
    @DisplayName("Added student should be displayed as  being present inside the map")
    void isStudentPresentTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        sRegistry.addStudent(2,"Karl Harrouz");
        assertTrue(sRegistry.isStudentPresent(1));
    }
    @Test
    @DisplayName("Invalid Id should throw exception when trying to see if student is present by id")
    void isStudentNotPresentTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        sRegistry.addStudent(2,"Karl Harrouz");
        IllegalArgumentException excep = assertThrows(IllegalArgumentException.class,()-> sRegistry.isStudentPresent(null));
        assertEquals("Id is either null or is invalid!",excep.getMessage());
    }
    @Test
    @DisplayName("Null Id should throw exception when trying to see if student is present by id")
    void nullIdIsStudentNotPresentTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        sRegistry.addStudent(2,"Karl Harrouz");
        IllegalArgumentException excep = assertThrows(IllegalArgumentException.class,()-> sRegistry.isStudentPresent(null));
        assertEquals("Id is either null or is invalid!",excep.getMessage());
    }
    @Test
    @DisplayName("A student present inside the map should return the id ")
    void getIdOfSudentTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        sRegistry.addStudent(2,"Karl Harrouz");
        assertEquals("Karl Harrouz",sRegistry.getStudentById(2));
    }
    @Test
    @DisplayName("A student  not present inside the map should not  return the id ")
    void getIdOfStudentThatIsNotPresentTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        sRegistry.addStudent(2,"Karl Harrouz");
        assertNotEquals("Elie Helou",sRegistry.getStudentById(2));
    }
    @Test
    @DisplayName("A removed student should be removed from the map")
    void removeStudentValidTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        sRegistry.addStudent(2,"Karl Harrouz");
        sRegistry.addStudent(3,"Elie Helou");
        sRegistry.addStudent(4,"Alec Iskandar");
        assertEquals(4,sRegistry.getStudentCount());
        assertTrue(sRegistry.removeStudent(1));
        assertEquals(3,sRegistry.getStudentCount());
        assertThrows(IllegalArgumentException.class,()->sRegistry.isStudentPresent(1));
    }
    @Test
    @DisplayName("A invalid id removed  student should  return an exception ")
    void removeStudentInvalidIdTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        sRegistry.addStudent(2,"Karl Harrouz");
        sRegistry.addStudent(3,"Elie Helou");
        sRegistry.addStudent(4,"Alec Iskandar");
        assertEquals(4,sRegistry.getStudentCount());
        assertThrows(IllegalArgumentException.class,()->sRegistry.isStudentPresent(5));
        assertEquals(4,sRegistry.getStudentCount());

    }
    @Test
    @DisplayName("A null  id removed  student should  return an exception ")
    void removeStudentNullIdTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        sRegistry.addStudent(2,"Karl Harrouz");
        sRegistry.addStudent(3,"Elie Helou");
        sRegistry.addStudent(4,"Alec Iskandar");

        assertAll(
                ()->  assertEquals(4,sRegistry.getStudentCount()),
                ()-> assertThrows(IllegalArgumentException.class,()->sRegistry.removeStudent(null)),
                () -> assertEquals(4,sRegistry.getStudentCount())
        );


    }

    @Test
    @DisplayName("Updated Student with valid id should update the student inside the map ")
    void updateStudentValidIdTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        assertTrue(sRegistry.editStudent(1,"Dany Abou Jalad"));
    }
    @Test
    @DisplayName("Updated Student with invalid id throw an IllegalArgumentException ")
    void updateStudentInValidIdTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        assertThrows(IllegalArgumentException.class,()->sRegistry.editStudent(4,"Dany Abou Jalad"));
    }
    @Test
    @DisplayName("Updated Student with null id throw an IllegalArgumentException ")
    void updateStudentNullIdTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        assertThrows(IllegalArgumentException.class,()->sRegistry.editStudent(null,"Dany Abou Jalad"));
    }
    @Test
    @DisplayName("Updated Student with null name should  throw an IllegalArgumentException ")
    void updateStudentNullNameTest(){
        sRegistry.addStudent(1,"Anthony Diab");
        assertThrows(IllegalArgumentException.class,()->sRegistry.editStudent(1,null));
    }






}
