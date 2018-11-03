import com.devilsoftware.healthy.api.main.IllnessesClass;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.*;

public class TestClass {

    public static void main(String[] args) throws FileNotFoundException {

        IllnessesClass illnessesClass = new IllnessesClass();
        System.out.println(new Gson().toJson(illnessesClass.getIllnesses("мигрень")));

    }

}
