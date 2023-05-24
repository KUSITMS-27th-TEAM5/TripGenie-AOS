package com.kusitms.ovengers.retrofit

import com.kusitms.ovengers.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIS {

    // 회원가입 | 구글 로그인 | 유정
    @GET("/v1/user/oauth/google")
    fun oauthGoogle(
        @Header("Authorization") Authorization: String
    ) : Call<ResponseGoogleSignup>

    // 회원가입 | 추가 정보 수집 | 승균
    @POST("/v1/user/signUp")
    fun signUp(
        @Header("Authorization") Authorization: String,
        @Body signUp: RequestSignUp
    ) : Call<ResponseSignUp>

    // 스토어 | 포인트 조회 | 유정
    @GET("/v1/myStore/selectPoint")
    fun getPoint(
        @Header("Authorization") Authorization: String
    ) : Call<ResponseGetPoint>

    // 스토어 | 포인트 차감 | 유정
    @PUT("/v1/myStore/updatePoint")
    fun setPoint(
        @Header("Authorization") Authorization: String,
        @Body PointRequestBody: PointRequestBody
    ) : Call<ResponseSetPoint>

    // accessToken 재발급 | 유정
    @GET("/v1/user/reissue")
    fun newToken(
        @Header("Authorization") Authorization: String,
    ) : Call<ResponseNewToken>

    // 캐리어 추가 | 승균
    @POST("/v1/myCarrier/addCarrier")
    fun addCarrier(
        @Header("Authorization") Authorization: String,
        @Body RequestMakeCarrier: RequestMakeCarrier
    ) : Call<ResponseMakeCarrier>

    //캐리어 가져오기 ㅣ 승균
    @GET("/v1/myCarrier/selectAll")
    fun getCarrier(
        @Header("Authorization") Authorization: String
    ) : Call<ResponseGetCarrier>

    @PUT("/v1/myCarrier/deleteCarrier")
    fun deleteCarrier(
        @Header("Authorization") Authorization: String,
        @Body RequestDeleteCarrier : RequestDeleteCarrier

    ) : Call<ResponseDeleteCarrier>
}