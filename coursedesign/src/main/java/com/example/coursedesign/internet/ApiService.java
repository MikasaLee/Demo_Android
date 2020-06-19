package com.example.coursedesign.internet;



import com.example.coursedesign.Model.AnswerBean;
import com.example.coursedesign.Model.GroupMessageBean;
import com.example.coursedesign.Model.InformationBean;
import com.example.coursedesign.Model.QuestionBean;
import com.example.coursedesign.Model.ResponseResult;
import com.example.coursedesign.Model.RootBean;
import com.example.coursedesign.Model.StudentBean;
import com.example.coursedesign.Model.TeacherBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 学生登录
     *
     * @param stuId
     * @param password
     * @return
     */
    @GET("loginStu.json")
    Call<ResponseResult> loginStu(@Query("stuId") String stuId, @Query("password") String password);

    /**
     * 教师登录
     *
     * @param teaId
     * @param password
     * @return
     */
    @GET("loginTea.json")
    Call<ResponseResult> loginTea(@Query("teaId") String teaId, @Query("password") String password);

    /**
     * 管理员登录
     *
     * @param rootId
     * @param password
     * @return
     */
    @GET("loginRoot.json")
    Call<ResponseResult> loginRoot(@Query("rootId") String rootId, @Query("password") String password);


    /**
     * 学生
     * @param stuId
     *
     * @return
     */
    @GET("getStu.json")
    Call<StudentBean> getStu(@Query("stuId") String stuId);



    /**
     * 老师
     * @param teaId
     *
     * @return
     */
    @GET("getTea.json")
    Call<TeacherBean> getTea(@Query("teaId") String teaId);

    /**
     * 管理员
     * @param rootId
     *
     * @return
     */
    @GET("getRoot.json")
    Call<RootBean> getRoot(@Query("rootId") String rootId);

    /**
     * 得到公告
     * @return
     */
    @GET("getInfo.json")
    Call<InformationBean> getInfo();

    /**
     * 得到群发消息
     * @return
     */
    @GET("getGruopMessage.json")
    Call<GroupMessageBean> getGM(@Query("stuId") String stuId);

    /**
     * 得到群发消息(teacher)
     * @return
     */
    @GET("getGruopMessageByTea.json")
    Call<GroupMessageBean> getGMByTea(@Query("teaId") String teaId);

    /**
     * 学生提交问题
     * @return
     */
    @GET("putQues.json")
        Call<ResponseResult> putQues(@Query("stuId") String stuId, @Query("stuContent") String StuContent);
    /**
     * 得到学生的所有问题
     * @return
     */
    @GET("getAllQues.json")
    Call<List<QuestionBean>> getAllQues(@Query("stuId") String stuId);





    /**
     * 得到老师关于每个问题的回答
     * @return
     */
    @GET("getAllAns.json")
    Call<List<AnswerBean>> getAllAns(@Query("stuId") String stuId);

    /**
     * 老师发布群消息
     * @return
     */
    @GET("putGroupMessage.json")
    Call<ResponseResult> putGroupMessage(@Query("teaId") String teaId, @Query("gMContent") String gMContent);



    /**
     * 得到负责的所有学生的所有问题
     * @return
     */
    @GET("getAllQuesByTeaId.json")
    Call<List<QuestionBean>> getAllQuesByTeaId(@Query("teaId") String teaId);

    /**
     * 得到所有自己的回答
     * @return
     */
        @GET("getAllAnsByTeaId.json")
    Call<List<AnswerBean>> getAllAnsByTeaId(@Query("teaId") String teaId);

    /**
     * 提交所有自己的回答
     * @return
     */
    @GET("putAns.json")
    Call<ResponseResult> putAns(@Query("queId") int queId, @Query("teaContent") String teaContent);



    // -------------------------------------------------------------------------------------
    //                          管理员

    /**
     * 所有学生
     * @return
     */
    @GET("getStus.json")
    Call<List<StudentBean>> getStus();

    /**
     * 所有教师
     * @return
     */
    @GET("getTeas.json")
    Call<List<TeacherBean>> getTeas();

    @GET("addStu.json")
    Call<ResponseResult> addStu(@Query("stuId") String stuId,
                                @Query("stuName") String stuName,
                                @Query("collegeName") String collegeName,
                                @Query("stuClass") String stuClass,
                                @Query("password") String password);
    @GET("addTea.json")
    Call<ResponseResult> addTea(@Query("teaId") String teaId,
                                @Query("teaName") String teaName,
                                @Query("collegeName") String collegeName,
                                @Query("teaGrade") String teaGrade,
                                @Query("password") String password);

    @GET("updStu.json")
    Call<ResponseResult> updStu(@Query("stuId") String stuId,
                                @Query("stuName") String stuName,
                                @Query("collegeName") String collegeName,
                                @Query("stuClass") String stuClass,
                                @Query("password") String password);

    @GET("updTea.json")
    Call<ResponseResult> updTea(@Query("teaId") String teaId,
                                @Query("teaName") String teaName,
                                @Query("collegeName") String collegeName,
                                @Query("teaGrade") String teaGrade,
                                @Query("password") String password);

    @GET("delStu.json")
    Call<ResponseResult> delStu(@Query("stuId") String stuId);
    @GET("delTea.json")
    Call<ResponseResult> delTea(@Query("teaId") String teaId);


    /**
     * 发布公告
     * @return
     */
    @GET("setInfo.json")
    Call<ResponseResult> setInfo(@Query("inforContent") String inforContent);

    /**
     * 绑定学生和老师
     * @return
     */
    @GET("bindST.json")
    Call<ResponseResult> bindST(@Query("stuId") String stuId,@Query("teaId") String teaId);

}
