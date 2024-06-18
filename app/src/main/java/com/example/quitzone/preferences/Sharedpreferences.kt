package com.example.quitzone.preferences

import android.content.Context
import android.content.SharedPreferences

class Sharedpreferences(context: Context) {
    private val login = "login"
    private val myPref = "Main_pref"
    private val myToken = "Bearer"
    private val myName = "Name"
    private val myEmail = "Email"
    private val myid = "userId"
    private val age = "age"
    private val gender = "gender"
    private val smokingHabit = "smokingHabit"
    private val physicalActivity = "physicalActivity"
    private val alcoholConsumption = "alcoholConsumption"
    private val hobby_1 = "Hobby_1"
    private val hobby_2 = "Hobby_2"
    private val hobby_3 = "Hobby_3"
    private val height = "height"
    private val weight = "weight"
    private val sharedPreference: SharedPreferences

    init {
        sharedPreference = context.getSharedPreferences(myPref, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status: Boolean) {
        sharedPreference.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin(): Boolean {
        return sharedPreference.getBoolean(login, false)
    }

    fun saveUserToken(token: String) {
        sharedPreference.edit().putString(myToken, token).apply()
    }

    fun getUserToken(): String? {
        return sharedPreference.getString(myToken, " ")
    }
    fun saveUserId(id: String) {
        sharedPreference.edit().putString(myid, id).apply()
    }

    fun getUserId(): String? {
        return sharedPreference.getString(myid, " ")
    }

    fun clearUserToken() {
        sharedPreference.edit().remove(myToken).apply()
    }

    fun clearUserLogin() {
        sharedPreference.edit().remove(login).apply()
    }

    // Fungsi untuk menyimpan nama pengguna
    fun saveUserName(name: String) {
        sharedPreference.edit().putString(myName, name).apply()
    }

    // Fungsi untuk mengambil nama pengguna
    fun getUserName(): String? {
        return sharedPreference.getString(myName, " ")
    }

    // Fungsi untuk menyimpan email pengguna
    fun saveUserEmail(email: String) {
        sharedPreference.edit().putString(myEmail, email).apply()
    }

    // Fungsi untuk mengambil email pengguna
    fun getUserEmail(): String? {
        return sharedPreference.getString(myEmail, " ")
    }

    // Fungsi untuk membersihkan nama pengguna
    fun clearUserName() {
        sharedPreference.edit().remove(myName).apply()
    }

    // Fungsi untuk membersihkan email pengguna
    fun clearUserEmail() {
        sharedPreference.edit().remove(myEmail).apply()
    }

    // Fungsi untuk membersihkan semua data pengguna
    fun clearAllUserData() {
        sharedPreference.edit().clear().apply()
    }

    fun setAge(age: Int) {
        sharedPreference.edit().putInt(this.age, age).apply()
    }

    fun setGender(gender: String) {
        sharedPreference.edit().putString(this.gender, gender).apply()
    }

    fun setSmokingHabit(smokingHabit: String) {
        sharedPreference.edit().putString(this.smokingHabit, smokingHabit).apply()
    }

    fun setPhysicalActivity(physicalActivity: String) {
        sharedPreference.edit().putString(this.physicalActivity, physicalActivity).apply()
    }

    fun setAlcoholConsumption(alcoholConsumption: String) {
        sharedPreference.edit().putString(this.alcoholConsumption, alcoholConsumption).apply()
    }

    fun setHobby1(hobby_1: String) {
        sharedPreference.edit().putString(this.hobby_1, hobby_1).apply()
    }

    fun setHobby2(hobby_2: String) {
        sharedPreference.edit().putString(this.hobby_2, hobby_2).apply()
    }

    fun setHobby3(hobby_3: String) {
        sharedPreference.edit().putString(this.hobby_3, hobby_3).apply()
    }

    fun setHeight(height: Float) {
        sharedPreference.edit().putFloat(this.height, height).apply()
    }

    fun setWeight(weight: Float) {
        sharedPreference.edit().putFloat(this.weight, weight).apply()
    }

    fun getAge(): Int {
        return sharedPreference.getInt(this.age, 0)
    }

    fun getGender(): String? {
        return sharedPreference.getString(this.gender, "")
    }

    fun getSmokingHabit(): String? {
        return sharedPreference.getString(this.smokingHabit, "")
    }

    fun getPhysicalActivity(): String? {
        return sharedPreference.getString(this.physicalActivity, "")
    }

    fun getAlcoholConsumption(): String? {
        return sharedPreference.getString(this.alcoholConsumption, "")
    }

    fun getHobby1(): String? {
        return sharedPreference.getString(this.hobby_1, "")
    }

    fun getHobby2(): String? {
        return sharedPreference.getString(this.hobby_2, "")
    }

    fun getHobby3(): String? {
        return sharedPreference.getString(this.hobby_3, "")
    }

    fun getHeight(): Float {
        return sharedPreference.getFloat(this.height, 0f)
    }

    fun getWeight(): Float {
        return sharedPreference.getFloat(this.weight, 0f)
    }



}