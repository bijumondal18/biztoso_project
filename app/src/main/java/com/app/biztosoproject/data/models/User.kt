package com.app.biztosoproject.data.models


import com.google.gson.annotations.SerializedName


data class User(

    @SerializedName("LockTime") var LockTime: String? = null,
    @SerializedName("_id") var id: String? = null,
    @SerializedName("FullName") var FullName: String? = null,
    @SerializedName("Email") var Email: String? = null,
    @SerializedName("Password") var Password: String? = null,
    @SerializedName("CountryCode") var CountryCode: String? = null,
    @SerializedName("PhoneNumber") var PhoneNumber: String? = null,
    @SerializedName("RefrenceId") var RefrenceId: String? = null,
    @SerializedName("Status") var Status: Int? = null,
    @SerializedName("userStatus") var userStatus: Int? = null,
    @SerializedName("version") var version: Int? = null,
    @SerializedName("deactivatedAt") var deactivatedAt: String? = null,
    @SerializedName("bannedAt") var bannedAt: String? = null,
    @SerializedName("ProfilePic") var ProfilePic: String? = null,
    @SerializedName("preferredLanguage") var preferredLanguage: String? = null,
    @SerializedName("SignUpType") var SignUpType: Int? = null,
    @SerializedName("CoverPhoto") var CoverPhoto: String? = null,
    @SerializedName("GST") var GST: String? = null,
    @SerializedName("LoginAttempts") var LoginAttempts: Int? = null,
    @SerializedName("phoneVerify") var phoneVerify: Boolean? = null,
    @SerializedName("emailVerify") var emailVerify: Boolean? = null,
    @SerializedName("role") var role: String? = null,
    @SerializedName("push_notification") var pushNotification: Boolean? = null,
    @SerializedName("email_notification") var emailNotification: Boolean? = null,
    @SerializedName("sms_notification") var smsNotification: Boolean? = null,
    @SerializedName("profilePrivacy") var profilePrivacy: Int? = null,
    @SerializedName("connectionPrivacy") var connectionPrivacy: Int? = null,
    @SerializedName("postPrivacy") var postPrivacy: Int? = null,
    @SerializedName("isOnline") var isOnline: Boolean? = null,
    @SerializedName("lastSeenPrivacy") var lastSeenPrivacy: Boolean? = null,
    @SerializedName("shareCount") var shareCount: Int? = null,
    @SerializedName("two_factor_authentication") var twoFactorAuthentication: Int? = null,
    @SerializedName("two_factor_secret") var twoFactorSecret: String? = null,
    @SerializedName("two_factor_mobile") var twoFactorMobile: Boolean? = null,
    @SerializedName("birth_date") var birthDate: String? = null,
    @SerializedName("user_name") var userName: String? = null,
    @SerializedName("analytics_use") var analyticsUse: Boolean? = null,
    @SerializedName("marketing_use") var marketingUse: Boolean? = null,
    @SerializedName("remember_me") var rememberMe: Boolean? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("__v") var _v: Int? = null,
    @SerializedName("user_type") var userType: Int? = null,
    @SerializedName("user_privacy") var userPrivacy: Int? = null,
    @SerializedName("BusinessType") var BusinessType: String? = null,
    @SerializedName("age") var age: Int? = null

)
