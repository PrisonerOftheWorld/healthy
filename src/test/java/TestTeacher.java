import com.devilsoftware.healthy.NNClass;
import com.devilsoftware.healthy.TeacherClass;

import java.io.IOException;

public class TestTeacher {

    public static void main(String[] s) throws IOException {
        float[] ma = {0, 6, 0, 24, 2, 8};

        TeacherClass teacherClass = new TeacherClass();
        //addIllness(ma, 2);
        teacherClass.addIllness(ma, 2);

        teacherClass.close();

        NNClass nnClass = new NNClass();
        nnClass.saveNN();
    }

}
