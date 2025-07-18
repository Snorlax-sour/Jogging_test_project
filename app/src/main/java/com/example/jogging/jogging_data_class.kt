package com.example.jogging

data class Jogging_data_class ( // data store, consturctor of function (have parameter
    val bpm_video : Int = 150,
    val jogging_minutes : Int = 30,
//    var total_jogging_minutes : Int, // don't need user calculate
    val video_source : String? = null // using 前綴可省略
){
    init { // initialize and check input valid
//        require(bpm_video >= 150) {  /*error message*/ "BPM 需要大於150以上"} // require(condition), when condition true: accpet
//        require(bpm_video <= 220) {"BPM 需要 <= 220以下, 你是神仙喔？"}
        require(bpm_video in 150..220){ "跑步bpm要在150～220之間"}
        require(jogging_minutes > 0) { "你跑步 <= 0 分鐘？" }
        require(jogging_minutes <= 180) { "一次跑太兇了，記得休息與分批" }
//        require(total_jogging_minutes > 0) { "跑總分中 <= 0 ?"}
//        require(total_jogging_minutes <= 180) {"跑太兇了，記得休息"}


    }
//    // function store
//    fun get_jogging_information() : Jogging_data_class{
//        return this
//    }
    // can direct get
    // constuctor, with null parameter
//    constructor(): this(150, 30, 30){ // 使用預設參數
//        println("(sub) constructor with no parameter")
//    }
}
