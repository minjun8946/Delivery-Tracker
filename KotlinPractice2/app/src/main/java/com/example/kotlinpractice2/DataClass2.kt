package com.example.kotlinpractice2

data class DataClass2(
        val from:Datafrom,
        val to:Datato,
        val state: Datastate,
        val progresses : ArrayList<Dataprogress>,
        val carrier: Datacarrier,
        val massage : String
)
data class Datafrom(
        var name : String,
        var time : String
)


data class Datato(
        var name : String,
        var time : String
)


data class Datastate(
        var id: String,
        var text : String
)

data class Dataprogress(
        val time : String,
        val status : Datastate,
        val location : Datalocation,
        val description : String
)

data class Datalocation(
        val name: String
)

data class Datacarrier(
        val id : String,
        val name : String,
        val tel : String
)

